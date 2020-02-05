package app.controller.frontend;

import app.controller.abstracts.ControllerFrontend;
import app.controller.backend.ControllerPubblicazione;
import app.controller.backend.ControllerRecensione;
import app.controller.backend.ControllerSessione;
import app.controller.backend.ControllerUtente;
import app.model.Recensione;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ApprovaRecensioniController extends ControllerFrontend {

	@FXML
	private TableView<Recensione> TabellaRecensioni;
	
	@FXML
	private TableColumn<Recensione, Integer> Utentetc;
	
	@FXML
	private TableColumn<Recensione, Integer> Pubbtc;
	
	@FXML
	private TableColumn<Recensione, String> Testotc;
	
	private ObservableList<Recensione> listaRecensioni = FXCollections.observableArrayList();
	
	@FXML
	private TextArea TestoField;
	
	@FXML
	private Button ApprovaButton;
	
	@FXML
	private TextField NickField;
	
	@FXML
	private TextField TitoloField;
	
	@FXML
	private TextField ISBNField;
	
	
	
	@FXML
	private void handleApprova() {
		
		Recensione daApprovare = TabellaRecensioni.getSelectionModel().getSelectedItem();
		
		if(daApprovare == null) return;
		
		ControllerRecensione.getInstance().approvaRecensione(daApprovare.getIDPubblicazione(), daApprovare.getIDUtente(), ControllerSessione.getIstanza().getUtenteLoggato().getID());
		
		aggiorna();
	}

	private void aggiorna() {
		
		listaRecensioni.clear();
		
		for(Recensione rec : ControllerRecensione.getInstance().getRecensioniDaApprovare()) {
			listaRecensioni.add(rec);
		}
		
		//System.out.println("utente " + ControllerUtente.getIstanza().getUtente(14) );
		
		
		Utentetc.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIDUtente()).asObject()); // ControllerUtente.getIstanza().getUtente(cellData.getValue().getIDUtente()).getNickName()));
		Pubbtc.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIDPubblicazione()).asObject()); // ControllerPubblicazione.getIstanza().getDatiPubblicazione(cellData.getValue().getIDPubblicazione()).getISBN()));
		Testotc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescrizione()));
		
		
		
		TabellaRecensioni.setItems(listaRecensioni);
		
		TabellaRecensioni.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> mostraRecensione(newValue));
		
	}
	
	private void mostraRecensione(Recensione rec) {
		
		if(rec == null) {
			TestoField.setText("");
			NickField.setText("");
			TitoloField.setText("");
			ISBNField.setText("");
			
		} else {
			TestoField.setText(rec.getDescrizione());
			NickField.setText(ControllerUtente.getIstanza().getUtente(rec.getIDUtente()).getNickName());
			TitoloField.setText(ControllerPubblicazione.getIstanza().getDatiPubblicazione(rec.getIDPubblicazione()).getTitolo());
			ISBNField.setText(ControllerPubblicazione.getIstanza().getDatiPubblicazione(rec.getIDPubblicazione()).getISBN());
			
		}
		
	}

	@Override
	public void load() {
		
		aggiorna();
		
	}
	
}
