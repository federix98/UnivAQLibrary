package app.controller.frontend;

import java.util.HashMap;

import app.controller.abstracts.ControllerFrontend;
import app.controller.backend.ControllerSessione;
import app.controller.backend.ControllerUtente;
import app.model.Utente;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
/**
 * 
 * @author Federico Di Menna
 *
 */
public class GestioneUtentiController extends ControllerFrontend {

	@FXML
	private TableView<Utente> TabellaUtenti;
	
	@FXML
	private TableColumn<Utente, Integer> IDUtentetc;
	
	@FXML
	private TableColumn<Utente, String> Nicknametc;
	
	@FXML
	private TableColumn<Utente, String> Ruolotc;
	
	@FXML
	private Button PromuoviButton;
	
	@FXML
	private ComboBox<String> RuoloField;
	
	private HashMap<Integer, String> mappaRuoli;
	
	private HashMap<String, Integer> mappaNomi;
	
	private ObservableList<Utente> listaUtenti = FXCollections.observableArrayList();
	
	// FXML METHODS
	
	/**
	 * Invocato al caricamento iniziale della schermata
	 */
	@FXML
	private void initialize() {
		
		mappaRuoli = new HashMap<Integer, String>();
		mappaNomi = new HashMap<String, Integer>();
		
		mappaRuoli.put(1, "Utente Passivo");
		mappaRuoli.put(2, "Utente Attivo");
		mappaRuoli.put(3, "Moderatore");
		mappaRuoli.put(4, "Amministratore");
		mappaRuoli.put(5, "Super Amministratore");
		mappaRuoli.put(6, "Utente");
		
		mappaNomi.put("Utente", 2);
		mappaNomi.put("Moderatore", 3);
		mappaNomi.put("Amministratore", 4);
		
		RuoloField.getItems().add("Utente");
		RuoloField.getItems().add("Moderatore");
		RuoloField.getItems().add("Amministratore");
		
	}

	/**
	 * Promuovi un utente
	 */
	@FXML
	private void handlePromuovi() {
		
		if(TabellaUtenti.getSelectionModel().getSelectedItem() == null) return;
		
		ControllerUtente.getIstanza().promuoviUtente(mappaNomi.get(RuoloField.getValue()), TabellaUtenti.getSelectionModel().getSelectedItem().getID());
		refreshTable();
		
	}
	
	/**
	 * In base al ruolo setta cosa si può fare e cosa no
	 */
	@FXML
	private void handleRuolo() {
		
		// MappaRuoli = Mappa dei ruoli con chiave numero
		// MappaNomi = mappa dei numeri con chiave nomi
		
		if(TabellaUtenti.getSelectionModel().getSelectedItem() == null) return;
		
		Integer ut = TabellaUtenti.getSelectionModel().getSelectedItem().getRuolo();
		
		if(ut == 1 || ut == 2) ut = 6;
		
		if(RuoloField.getValue().equals(mappaRuoli.get(ut)) || RuoloField.getValue() == "Utente" || ( ControllerSessione.getIstanza().getUtenteLoggato().getRuolo() <= 4 && RuoloField.getValue().equals("Amministratore")))  {
			PromuoviButton.setDisable(true);
		} else {
			PromuoviButton.setDisable(false);
			
			if(mappaNomi.get(RuoloField.getValue()) < TabellaUtenti.getSelectionModel().getSelectedItem().getRuolo()) {
				PromuoviButton.setText("Degrada");
			} 
			else if(mappaNomi.get(RuoloField.getValue()) > TabellaUtenti.getSelectionModel().getSelectedItem().getRuolo()) {
				PromuoviButton.setText("Promuovi");
			}
		}
		
		
	}

	/**
	 * Aggiorna la tabella utenti
	 */
	private void refreshTable() {
		
		listaUtenti.clear();
		
		for(Utente u : ControllerUtente.getIstanza().getUtentiSistema()) {
			if(u.getRuolo() >= ControllerSessione.getIstanza().getUtenteLoggato().getRuolo()) continue;
			listaUtenti.add(u);
			//System.out.println(l);
		}
		
		IDUtentetc.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getID()).asObject());
		Nicknametc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNickName()));
		Ruolotc.setCellValueFactory(cellData -> new SimpleStringProperty(mappaRuoli.get(cellData.getValue().getRuolo())));
		
		TabellaUtenti.setItems(listaUtenti);
		
		TabellaUtenti.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> mostraUtente(newValue));
	
		handleRuolo();
		
	}

	/**
     * Invocato quando viene attivata la schermata
     */
	@Override
	public void load() {

		refreshTable();	
		
	}

	/**
	 * Mostra i dati dell'utente selezionato
	 * @param newValue
	 */
	private void mostraUtente(Utente newValue) {
		
		if(newValue == null) return;
		String toPut = mappaRuoli.get(newValue.getRuolo());
		if(toPut.equals("Utente Attivo") || toPut.equals("Utente Passivo")) toPut = "Utente";
		RuoloField.setValue(toPut);
		
	}
	
}
