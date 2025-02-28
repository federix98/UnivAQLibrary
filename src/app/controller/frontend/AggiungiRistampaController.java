package app.controller.frontend;

import app.controller.abstracts.ControllerFrontend;
import app.controller.backend.ControllerSchermo;
import app.model.PubblicazioneCompleta;
import app.model.Ristampa;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
/**
 * 
 * @author Federico Di Menna
 *
 */
public class AggiungiRistampaController extends ControllerFrontend {

	@FXML
    private TextField NumeroField;
	
	@FXML
	private DatePicker DataField;
	
	
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
	    	Ristampa newR = new Ristampa(DataField.getValue(), Integer.parseInt(NumeroField.getText())); 
	    	
	    	pubblicazione.getRistampe().add(newR);
	    	InserisciPubblicazioneController c = (InserisciPubblicazioneController) ControllerSchermo.istanzaManager().getControllerMap().get("InserisciPubblicazione");
			c.update();
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

        if (NumeroField.getText() == null || NumeroField.getText().length() == 0) {
            errorMessage += "\tParola non valida!\n\n"; 
            // aggiungere controllo sul formato
        }
        
        if (DataField.getValue() == null) {
            errorMessage += "\tData non valida!\n\n"; 
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
		
		NumeroField.setText("");
		DataField.setValue(null);
		
		
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
