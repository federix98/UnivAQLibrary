package app.controller.frontend;

import app.controller.abstracts.ControllerFrontend;
import app.controller.backend.ControllerSchermo;
import app.model.PubblicazioneCompleta;
import app.model.Sorgente;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
/**
 * 
 * @author Federico Di Menna
 *
 */
public class AggiungiSorgenteController extends ControllerFrontend {

	@FXML
    private TextField URIField;
	
	@FXML
    private TextField TipoField;
	
	@FXML
    private TextField FormatoField;
	
	@FXML
    private TextArea DescrizioneField;
	
	
    private PubblicazioneCompleta pubblicazione;


    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	
    }

    /**
     * Salva gli inserimenti effettuati
     */
    @FXML
    private void handleSalva() {
    	
    	if( isInputValid() ) {
    		Sorgente s = new Sorgente(URIField.getText(), TipoField.getText(), FormatoField.getText(), DescrizioneField.getText());
	    	
	    	pubblicazione.getSorgenti().add(s);
	    	InserisciPubblicazioneController c = (InserisciPubblicazioneController) ControllerSchermo.istanzaManager().getControllerMap().get("InserisciPubblicazione");
			c.update();
			//System.out.println("Sorgente da aggiungere: " + pubblicazione.getSorgenti().toString());
			ControllerSchermo.istanzaManager().getNewStage().close();
    	}
    	
    }


    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        ControllerSchermo.istanzaManager().getNewStage().close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (URIField.getText() == null || URIField.getText().length() == 0) {
            errorMessage += "\tURI non valido!\n\n"; 
            // aggiungere controllo sul formato
        }
        
        if (TipoField.getText() == null || TipoField.getText().length() == 0) {
            errorMessage += "\tTipo non valido!\n\n"; 
            // aggiungere controllo sul formato
        }
        
        if (FormatoField.getText() == null || FormatoField.getText().length() == 0) {
            errorMessage += "\tFormato non valido!\n\n"; 
            // aggiungere controllo sul formato
        }
        
        if (DescrizioneField.getText() == null || DescrizioneField.getText().length() == 0) {
            errorMessage += "\tDescrizione non valida!\n\n"; 
            // aggiungere controllo sul formato
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
     * Invocato quando viene attivata la schermata
     */
	public void load() {
		
		URIField.setText("");
		TipoField.setText("");
		FormatoField.setText("");
		DescrizioneField.setText("");
		
	}

	/**
	 * 
	 * @return pubblicazione completa
	 */
	public PubblicazioneCompleta getPubblicazione() {
		return pubblicazione;
	}

	/**
	 * Set pubblicazione completa
	 * @param pubblicazione
	 */
	public void setPubblicazione(PubblicazioneCompleta pubblicazione) {
		this.pubblicazione = pubblicazione;
	}
	
}
