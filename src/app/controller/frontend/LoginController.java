package app.controller.frontend;

import app.controller.abstracts.ControllerFrontend;
import app.controller.backend.ControllerSchermo;
import app.controller.backend.ControllerSessione;
import app.controller.backend.ControllerUtente;
import app.model.Utente;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
/**
 * 
 * @author Federico Di Menna
 *
 */
public class LoginController extends ControllerFrontend {
	
	@FXML
	private Button Accedi;
	
	@FXML
	private TextField Username;
	
	@FXML
	private TextField Password;
	
	@FXML
	private Label NonValido;
	
	@FXML
	private Hyperlink linkRegistrazione;
	
	/**
	 * Handler per accedere al sistema
	 */
	@FXML
	public void handleAccedi() {
		
		if(Username.getText().isBlank() || Password.getText().isBlank()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Errore");
			alert.setHeaderText("Attenzione");
			alert.setContentText("Credenziali non riconosciute");

			alert.showAndWait();
			//NonValido.setVisible(true);
			return;
		} else {
			
			Utente user = ControllerUtente.getIstanza().accessoUtente(Username.getText(), Password.getText());
			
			if(user != null) {
				ControllerSessione.getIstanza().setUtenteLoggato(user);
				ControllerSchermo.istanzaManager().activateOnCenter("PannelloGestione");
				( (PannelloGestioneController) ControllerSchermo.istanzaManager().getControllerMap().get("PannelloGestione")).caricaPannello();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Errore");
				alert.setHeaderText("Attenzione");
				alert.setContentText("Credenziali non riconosciute");

				alert.showAndWait();
				//NonValido.setVisible(true);
				return;
				//NonValido.setVisible(true);
			}
		}
		
	}
	
	/**
	 * Handler per registrarsi
	 */
	@FXML
	public void handleRegistrati() {
		ControllerSchermo.istanzaManager().activateOnCenter("Registrazione");
	}

	/**
     * Invocato quando viene attivata la schermata
     */
	@Override
	public void load() {
		
		NonValido.setVisible(false);
		Username.setText("");
		Password.setText("");
		
	}

}
