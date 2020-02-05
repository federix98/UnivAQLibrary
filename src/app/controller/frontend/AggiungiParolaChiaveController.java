package app.controller.frontend;

import app.controller.abstracts.ControllerFrontend;
import app.controller.backend.ControllerParolaChiave;
import app.controller.backend.ControllerSchermo;
import app.model.ParolaChiave;
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

public class AggiungiParolaChiaveController extends ControllerFrontend {

	@FXML
    private TextField ParolaField;

    @FXML
    private Button AggiungiNuovoButton;
    
    @FXML
    private Button AggiungiEsistenteButton;
    
    @FXML
    private Button CancelButton;
    
    @FXML
    private TableView<ParolaChiave> TabellaParole;
    
    @FXML
    private TableColumn<ParolaChiave, String> Parolatc;
    
    
    private ObservableList<ParolaChiave> listaParole = FXCollections.observableArrayList();
    
    private PubblicazioneCompleta pubblicazione;


    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */

    @FXML
    private void handleAggiungiNuovo() {
    	
    	if( isInputValid() ) {
    		
    		ControllerParolaChiave.getInstance().inserisciParolaChiaveDirect(ParolaField.getText());
    		InserisciPubblicazioneController c = (InserisciPubblicazioneController) ControllerSchermo.istanzaManager().getControllerMap().get("InserisciPubblicazione");
    		c.update();
    		this.load();
    		
    	}
    	
    }
    
    @FXML
    private void handleAggiungiEsistente() {
    	
    	pubblicazione.getTags().add(TabellaParole.getSelectionModel().getSelectedItem());
    	InserisciPubblicazioneController c = (InserisciPubblicazioneController) ControllerSchermo.istanzaManager().getControllerMap().get("InserisciPubblicazione");
		c.update();
		ControllerSchermo.istanzaManager().getNewStage().close();
    	
    }
    
    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */


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

        if (ParolaField.getText() == null || ParolaField.getText().length() == 0) {
            errorMessage += "\tParola non valida!\n\n"; 
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

	public void load() {
		// TODO Auto-generated method stub
		
		ParolaField.setText("");
		
		for(ParolaChiave pc : ControllerParolaChiave.getInstance().getParoleChiave(-1)) {
			listaParole.add(pc);
		}
		
		System.out.println("Lista Parole Chiave: " + listaParole.toString());
		
		Parolatc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getParola()));
		
		TabellaParole.setItems(listaParole);
		
	}

	public PubblicazioneCompleta getPubblicazione() {
		return pubblicazione;
	}

	public void setPubblicazione(PubblicazioneCompleta pubblicazione) {
		this.pubblicazione = pubblicazione;
	}
	
}
