package app.controller.frontend;

import app.controller.abstracts.ControllerFrontend;
import app.controller.backend.ControllerSchermo;
import app.controller.backend.ControllerSessione;
import app.controller.backend.ControllerUtente;
import app.model.Utente;
import app.utility.BCrypt;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
/**
 * 
 * @author Federico Di Menna
 *
 */
public class RegistrazioneController extends ControllerFrontend {

	@FXML
	private ComboBox<String> Residenza;
	
	@FXML
	private ComboBox<String> ProvNascita;
	
	@FXML
	private Label NickName;
	
	@FXML
	private TextField NickField;
	
	@FXML
	private Label Email;
	
	@FXML
	private TextField EmailField;
	
	@FXML
	private Label Password;
	
	@FXML
	private PasswordField PassField;
	
	@FXML
	private Label Nome;
	
	@FXML
	private Label Error;
	
	@FXML
	private TextField NomeField;
	
	@FXML
	private Label Cognome;
	
	@FXML
	private TextField CognomeField;
	
	@FXML
	private Label DataNascita;
	
	@FXML
	private DatePicker DataNascitaField;
	
	@FXML
	private PasswordField PassField2;
	
	@FXML
	private Button Registrati;
	
	/**
	 * Metodo invocato al caricamento iniziale della schermata
	 */
	public void initialize() {
		
		for(String s : ControllerUtente.getIstanza().getProvince()) {
			Residenza.getItems().add(s);
			ProvNascita.getItems().add(s);
		}
		
		Error.setVisible(false);
	}
	
	/**
	 * Handle per registrarsi
	 */
	@FXML
	public void handleRegistrati() {
		
		// VALIDAZIONE CAMPI
		if(NickField.getText().isBlank() || EmailField.getText().isBlank() || PassField.getText().isBlank() || PassField2.getText().isBlank() || DataNascitaField.getValue() == null || NomeField.getText().isBlank() || CognomeField.getText().isBlank() || Residenza.getSelectionModel().getSelectedItem().isBlank() || ProvNascita.getSelectionModel().getSelectedItem().isBlank()) {
			Error.setText("Errore. Riempi tutti i campi");
			Error.setVisible(true);
			return;
		} else if( !isValidEmail( EmailField.getText() ) ) {
			Error.setText("Errore. Email non valida");
			Error.setVisible(true);
			return;
		} else if( !isValidNickname( NickField.getText())) {
			Error.setText("Errore. Nickname non valido");
			Error.setVisible(true);
			return;
		} else if( !isValidPassword( PassField.getText(), PassField2.getText())) {
			Error.setText("Errore. Password non valida");
			Error.setVisible(true);
			return;
		}
		
		String passwordCifrata = criptaPassword(PassField.getText());
		
		Utente daInserire = new Utente(NickField.getText(), passwordCifrata, null, null, null,
				EmailField.getText(), CognomeField.getText(), ProvNascita.getSelectionModel().getSelectedItem() + ", Italia", Residenza.getSelectionModel().getSelectedItem() + ", Italia", NomeField.getText(), DataNascitaField.getValue());
		Integer IDUtente = ControllerUtente.getIstanza().inserisciUtente(daInserire); 
		
		Utente utente = ControllerUtente.getIstanza().getUtente(IDUtente);
		
		if(utente != null) {
			ControllerSessione.getIstanza().setUtenteLoggato(utente);
			ControllerSchermo.istanzaManager().activateOnCenter("PannelloGestione");
		} else {
			Error.setText("Errore nella registrazione");
			Error.setVisible(true);
		}
		
	}
	
	/**
	 * 
	 * @param email
	 * @return true se email valida, false altrimenti
	 */
	public static boolean isValidEmail(String email) {
	      String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	      return email.matches(regex);
	   }
	
	/**
	 * 
	 * @param nickname
	 * @return true se nickname valido, false altrimenti
	 */
	public boolean isValidNickname(String nickname) {
		if(nickname.length() > 45) return false;
		return true;
	}
	
	/**
	 * 
	 * @param password
	 * @param confirm
	 * @return true se password valida, false altrimenti
	 */
	public boolean isValidPassword(String password, String confirm) {
		
		if(password.length() > 45 || password.length() < 8) return false;
		if(confirm.length() > 45 || confirm.length() < 8) return false;
		if(!password.equals((String)confirm)) return false;
		
		return true;
	}
	
	/**
	 * 
	 * @param passwordInChiaro
	 * @return Password cifrata
	 */
	public String criptaPassword(String passwordInChiaro) {
		
		return BCrypt.hashpw(passwordInChiaro, BCrypt.gensalt());
		
	}

	/**
     * Invocato quando viene attivata la schermata
     */
	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}
	
}
