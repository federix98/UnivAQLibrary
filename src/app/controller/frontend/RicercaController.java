package app.controller.frontend;

import java.util.ArrayList;

import app.DAO.MySQL.MySQL_DAOAutore;
import app.controller.abstracts.ControllerFrontend;
import app.controller.backend.ControllerPubblicazione;
import app.controller.backend.ControllerSchermo;
import app.controller.backend.ControllerSessione;
import app.model.Pubblicazione;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
/**
 * 
 * @author Federico Di Menna
 *
 */
public class RicercaController extends ControllerFrontend {

	@FXML
	private TableView<Pubblicazione> TabellaPubblicazioni;
	
	@FXML
	private TableColumn<Pubblicazione, String> ISBNtc;
	
	@FXML
	private TableColumn<Pubblicazione, String> Titolotc;
	
	@FXML
	private TableColumn<Pubblicazione, String> Autoretc;
	
	@FXML
	private TableColumn<Pubblicazione, String> Editoretc;
	
	@FXML
	private TableColumn<Pubblicazione, Integer> Liketc;
	
	@FXML
	private TableColumn<Pubblicazione, Integer> Recensionitc;
	
	@FXML
	private TableColumn<Pubblicazione, String> Datatc;
	
	@FXML
	private TextField KeyField;
	
	@FXML
	private ChoiceBox<String> FilterField;
	
	@FXML
	private Button CercaButton;
	
	@FXML
	private TextField NomeAutoreField;
	
	@FXML
	private TextField CognomeAutoreField;
	
	@FXML
	private Label ErrorLabel;
	
	
	private ObservableList<Pubblicazione> listaPubblicazioni = FXCollections.observableArrayList();
	
	/**
	 * Metodo invocato cliccando sul bottone "Cerca".
	 */
	@FXML
	private void handleCerca() {
		
		listaPubblicazioni.clear();
		TabellaPubblicazioni.setItems(null);
		
		if(checkValid()) {
			
			switch(FilterField.getSelectionModel().getSelectedItem()) {
				
				case "per ISBN": {
					
					listaPubblicazioni.add(ControllerPubblicazione.getIstanza().getPubblicazioneISBN(KeyField.getText()));
					
					ISBNtc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getISBN()));
			    	Titolotc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitolo()));
			    	Autoretc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getListaAutori()));
			    	Editoretc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEditore()));
			    	Liketc.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNumLike()).asObject());
			    	Recensionitc.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNumLike()).asObject());
			    	Datatc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDataPubblicazione().toString()));
			    	// Setta il listener
			    	TabellaPubblicazioni.getSelectionModel().selectedItemProperty().addListener(
			                (observable, oldValue, newValue) -> visualizzaPubblicazione(newValue));
			    	
			    	TabellaPubblicazioni.setItems(listaPubblicazioni);
			    	break;
				
				}

				case "per Titolo": {
					
					
					ArrayList<Pubblicazione> result = ControllerPubblicazione.getIstanza().getListaPubblicazioniTitolo(KeyField.getText());
					
					if(result == null) {
						System.out.println("Nessun risultato");
						return;
					}
					
					for(Pubblicazione p : result)  {
						listaPubblicazioni.add(p);
					}
					
					
					ISBNtc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getISBN()));
			    	Titolotc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitolo()));
			    	Autoretc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getListaAutori()));
			    	// OLD METHOD
			    	//Autoretc.setCellValueFactory(cellData -> new SimpleStringProperty(new MySQL_DAOAutore().getListaAutoriPubblicazioneString(cellData.getValue().getID())));
			    	Editoretc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEditore()));
			    	Liketc.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNumLike()).asObject());
			    	Recensionitc.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNumLike()).asObject());
			    	Datatc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDataPubblicazione().toString()));
			    	// Setta il listener
			    	TabellaPubblicazioni.getSelectionModel().selectedItemProperty().addListener(
			                (observable, oldValue, newValue) -> visualizzaPubblicazione(newValue));
			    	
			    	TabellaPubblicazioni.setItems(listaPubblicazioni);
			    	break;
				}
				
				case "per Autore": {
					
					//System.out.println("NomeAutore: " + NomeAutoreField.getText() + ", CognomeAutore: " + CognomeAutoreField.getText());
					if(ControllerPubblicazione.getIstanza().getListaPubblicazioniAutoreNome(NomeAutoreField.getText(), CognomeAutoreField.getText()) == null) return;
					for(Pubblicazione p : ControllerPubblicazione.getIstanza().getListaPubblicazioniAutoreNome(NomeAutoreField.getText(), CognomeAutoreField.getText()) ) {
						listaPubblicazioni.add(p);
					}
					
					ISBNtc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getISBN()));
			    	Titolotc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitolo()));
			    	Autoretc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getListaAutori()));
			    	Editoretc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEditore()));
			    	Liketc.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNumLike()).asObject());
			    	Recensionitc.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNumLike()).asObject());
			    	Datatc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDataPubblicazione().toString()));
			    	// Setta il listener
			    	TabellaPubblicazioni.getSelectionModel().selectedItemProperty().addListener(
			                (observable, oldValue, newValue) -> visualizzaPubblicazione(newValue));
			    	
			    	TabellaPubblicazioni.setItems(listaPubblicazioni);
			    	break;
			    	
				}
				
				case "per Parola Chiave": {
					
					for(Pubblicazione p : ControllerPubblicazione.getIstanza().getListaPubblicazioniParolaChiave(KeyField.getText()) ) {
						listaPubblicazioni.add(p);
					}
					
					ISBNtc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getISBN()));
			    	Titolotc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitolo()));
			    	Autoretc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getListaAutori()));
			    	Editoretc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEditore()));
			    	Liketc.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNumLike()).asObject());
			    	Recensionitc.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNumLike()).asObject());
			    	Datatc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDataPubblicazione().toString()));
			    	// Setta il listener
			    	TabellaPubblicazioni.getSelectionModel().selectedItemProperty().addListener(
			                (observable, oldValue, newValue) -> visualizzaPubblicazione(newValue));
			    	
			    	TabellaPubblicazioni.setItems(listaPubblicazioni);
			    	break;
			    	
				}
				
				case "per ID": {
					
					
					listaPubblicazioni.add(ControllerPubblicazione.getIstanza().findByID(Integer.parseInt((KeyField.getText()))));
				
					
					ISBNtc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getISBN()));
			    	Titolotc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitolo()));
			    	Autoretc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getListaAutori()));
			    	Editoretc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEditore()));
			    	Liketc.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNumLike()).asObject());
			    	Recensionitc.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNumLike()).asObject());
			    	Datatc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDataPubblicazione().toString()));
			    	// Setta il listener
			    	TabellaPubblicazioni.getSelectionModel().selectedItemProperty().addListener(
			                (observable, oldValue, newValue) -> visualizzaPubblicazione(newValue));
			    	
			    	TabellaPubblicazioni.setItems(listaPubblicazioni);
			    	break;
			    	
				}
				
			}
		}
		
		
	}
	
	/**
	 * Visualizza la pubblicazione scelta
	 * @param newValue
	 */
	private void visualizzaPubblicazione(Pubblicazione newValue) {
		
		if(newValue == null) return;
		ControllerSessione.getIstanza().setPubblicazioneSelezionata(newValue);
		ControllerSchermo.istanzaManager().activateOnCenter("VisualizzaPubblicazione");
	}

	/**
	 * Check se l'input è valido
	 * 
	 * @return true se input valido, false altrimenti
	 */
	private boolean checkValid() {
		
		if(!FilterField.getValue().equals("per Autore") && KeyField.getLength() < 5 && !FilterField.getValue().equals("per ID")) {
			
			ErrorLabel.setText("La keywork è troppo corta");
			ErrorLabel.setVisible(true);
			return false;
		
		} 
		
		ErrorLabel.setText("");
		ErrorLabel.setVisible(false);
		return true;
		
	}
	
	/**
	 * Handler per cambiare i box di ricerca in base al parametro
	 */
	@FXML
	private void handleBox() {
		
		ChangeListener<String> listener = ((observable, oldvalue, newvalue) -> {
			//System.out.println("OLD: " + oldvalue + ", NEW:  " + newvalue);
			if(newvalue.equalsIgnoreCase("per Autore")) {
				KeyField.setVisible(false);
				NomeAutoreField.setVisible(true);
				CognomeAutoreField.setVisible(true);
			} else {
				KeyField.setVisible(true);
				NomeAutoreField.setVisible(false);
				CognomeAutoreField.setVisible(false);
			}
		});
		
		FilterField.getSelectionModel().selectedItemProperty().addListener(listener);
	}
	
	/**
	 * Metodo invocato al primo caricamento della schermata
	 */
	public void initialize() {
		
		FilterField.getItems().clear();
		FilterField.getItems().add("per ISBN");
		FilterField.getItems().add("per Titolo");
		FilterField.getItems().add("per Autore");
		FilterField.getItems().add("per Parola Chiave");
		FilterField.getItems().add("per ID");
		
		FilterField.setValue("per Titolo");
		
		ErrorLabel.setVisible(false);
		NomeAutoreField.setVisible(false);
		CognomeAutoreField.setVisible(false);
		
		TabellaPubblicazioni.setPlaceholder(new Label("Nessuna pubblicazione trovata"));
		
	}
	
	/**
     * Invocato quando viene attivata la schermata
     */
	@Override
	public void load() {
		
	}

}
