package app.controller.frontend;

import app.controller.abstracts.ControllerFrontend;
import app.controller.backend.ControllerAutore;
import app.controller.backend.ControllerSchermo;
import app.model.Autore;
import app.model.PubblicazioneCompleta;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
/**
 * 
 * @author Federico Di Menna
 *
 */
public class AggiungiAutoreController extends ControllerFrontend {

	@FXML
    private TextField NomeField;
    
	@FXML
    private TextField CognomeField;

    @FXML
    private Button AggiungiNuovoButton;
    
    @FXML
    private Button AggiungiEsistenteButton;
    
    @FXML
    private Button CancelButton;
    
    @FXML
    private TableView<Autore> TabellaAutori;
    
    @FXML
    private TableColumn<Autore, String> Nometc;
    
    @FXML
    private TableColumn<Autore, String> Cognometc;
    
    
    private ObservableList<Autore> listaAutori = FXCollections.observableArrayList();
    
    
    private PubblicazioneCompleta pubblicazione;
    
    /**
     * 
     * Aggiunge un nuovo autore al database.
     * Handler del Button AggiungiNuovo
     * 
     */
    @FXML
    private void handleAggiungiNuovo() {
    	
    	if( isInputValid() ) {
    		
    		ControllerAutore.getInstance().inserisciAutore(new Autore(NomeField.getText(), CognomeField.getText()));
    		InserisciPubblicazioneController c = (InserisciPubblicazioneController) ControllerSchermo.istanzaManager().getControllerMap().get("InserisciPubblicazione");
    		c.update();
    		refreshTableAndFields();
    		
    	}
    	
    }
    
    /**
     * 
     * Aggiunge un nuovo autore presente nel database alla pubblicazione.
     * Handler del Button AggiungiEsistente
     * 
     */
    @FXML
    private void handleAggiungiEsistente() {
    	
    	pubblicazione.getAutori().add(TabellaAutori.getSelectionModel().getSelectedItem());
    	InserisciPubblicazioneController c = (InserisciPubblicazioneController) ControllerSchermo.istanzaManager().getControllerMap().get("InserisciPubblicazione");
		c.update();
		ControllerSchermo.istanzaManager().getNewStage().close();
    	
    }

    /**
     * Handler del Button Cancel
     */
    @FXML
    private void handleCancel() {
        ControllerSchermo.istanzaManager().getNewStage().close();
    }

    /**
     * Validata l'input dell'utente.
     * 
     * @return true se l'input è valido, false altrimenti
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (NomeField.getText() == null || NomeField.getText().length() == 0) {
            errorMessage += "\tNome non valido!\n\n"; 
            // aggiungere controllo sul formato
        }
        if (CognomeField.getText() == null || CognomeField.getText().length() == 0) {
            errorMessage += "\tCognome non valido!\n\n"; 
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(ControllerSchermo.istanzaManager().getNewStage());
            alert.setTitle("Alcuni campi non sono validi");
            alert.setHeaderText("Correggi le informazioni");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
    
    
    
    /**
     * Ricarica la tabella degli utenti, e riazzera i campi
     * 
     */
    private void refreshTableAndFields() {
    	
    	NomeField.setText("");
		CognomeField.setText("");
    	
    	for(Autore a : ControllerAutore.getInstance().getListaAutori(-1)) {
			listaAutori.add(a);
		}
		
		Nometc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
		Cognometc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCognome()));
		
		TabellaAutori.setItems(listaAutori);
		
    }

    /**
     * 
     * Metodo invocato ogni volta viene settata la scena Aggiungi Autore
     * 
     */
	public void load() {
		
		
		refreshTableAndFields();
		
	}

	/**
     * 
     * Metodo che restituisce la pubblicazione sulla quale effettuare l'inserimento
     * 
     * @return Restituisce la pubblicazione interessata
     * 
     */
	public PubblicazioneCompleta getPubblicazione() {
		return pubblicazione;
	}

	/**
     * 
     * Metodo che setta la pubblicazione sulla quale effettuare l'inserimento
     * 
     * @param Prende come parametro la pubblicazione interessata
     * 
     */
	public void setPubblicazione(PubblicazioneCompleta pubblicazione) {
		this.pubblicazione = pubblicazione;
	}
	
}
