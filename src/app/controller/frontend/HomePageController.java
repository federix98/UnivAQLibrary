package app.controller.frontend;

import app.controller.abstracts.ControllerFrontend;
import app.controller.backend.ControllerSchermo;
import app.controller.backend.ControllerSessione;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;

public class HomePageController extends ControllerFrontend {

	
	@FXML
	private Menu Entra;
	
	@FXML
	private MenuItem Accedi;
	
	@FXML
	private MenuItem Registrati;
	
	@FXML
	private MenuItem Catalogo;
	
	@FXML
	private MenuItem Cerca;
	
	@FXML
	private ImageView ruoloImg;
	
	@FXML
	private ImageView userImg;
	
	@FXML
	private Button GestioneButton;
	
	@FXML
	private Button EsciButton;

	@FXML
	private Button IndietroButton;

	@FXML
	private Label RuoloLabel;
	
	@FXML
	private Label UserLabel;
	

	// FXML METHODS
	
	
	/**
	 * Handler del MenuItem Catalogo
	 */
	@FXML
	private void handleCatalogo() {
		
		ControllerSchermo.istanzaManager().activateOnCenter("VisualizzaCatalogo");
		
	}
	
	/**
	 * Handler del MenuItem Accedi
	 */
	@FXML
	private void handleAccedi() {

		ControllerSchermo.istanzaManager().activateOnCenter("Login");
		
	}
	
	/**
	 * Handler del MenuItem Registrati
	 */
	@FXML
	private void handleRegistrati() {
		
		ControllerSchermo.istanzaManager().activateOnCenter("Registrazione");
	}

	/**
	 *  Handler del MenuItem Cerca
	 */
	@FXML
	private void handleCerca() {
		
		ControllerSchermo.istanzaManager().activateOnCenter("Ricerca");
	
	}
	
	/**
	 * Handler del MenuItem Indietro
	 */
	@FXML
	private void handleIndietro() {
		
		if(ControllerSchermo.istanzaManager().privilegi.contains(ControllerSessione.getIstanza().getPaginaPrecedente()) && ControllerSessione.getIstanza().getUtenteLoggato() == null) {
			ControllerSchermo.istanzaManager().activateOnCenter("Login");
		} else {
			ControllerSchermo.istanzaManager().activateOnCenter(ControllerSessione.getIstanza().getPaginaPrecedente());
		}
		
	}
	
	/**
	 * Handler del MenuItem Gestione
	 */
	@FXML
	private void handleGestione() {
		ControllerSchermo.istanzaManager().activateOnCenter("PannelloGestione");
	}
	
	/**
	 * Handler del MenuItem Esci
	 */
	@FXML
	private void handleEsci() {
		ControllerSessione.getIstanza().logout();
	}
	
	/**
	 * Handler del MenuItem Ultime Ristampe
	 */
	@FXML
	private void handleRistampe() {
		
		ControllerSchermo.istanzaManager().activateOnCenter("VisualizzaRistampe");
		
	}
	
	/**
	 * Handler del Button Chiudi
	 */
	@FXML
	private void handleChiudi() {
		ControllerSessione.getIstanza().getPrimaryStage().close();
	}
	
	
	/**
	 * Metodo invocato ogni volta che il la schermata viene inserita nella scena.
	 */
	@Override
	public void load() {
		
		
	}
	

	/**
	 * A seconda del parametro setta la visibilità dell'icona Ruolo
	 * 
	 * @param option. 
	 */
	public void setRuoloImgVix(Boolean option) {
		this.ruoloImg.setVisible(option);
	}
	
	/**
	 * A seconda del parametro setta la visibilità dell'icona Utente.
	 * 
	 * @param option
	 */
	public void setUserImgVix(Boolean option) {
		this.userImg.setVisible(option);
	}
	
	/**
	 * Setta il testo della label Utente con il valore della stringa passata come parametro.
	 * 
	 * @param userLabel. Stringa da inserire nella label
	 */
	public void setUserLabelText(String userLabel) {
		UserLabel.setText(userLabel);
		UserLabel.setVisible(true);
	}

	/**
	 * Setta il testo della label Ruolo con il valore della stringa passata come parametro.
	 * 
	 * @param ruoloLabel
	 */
	public void setRuoloLabelText(String ruoloLabel) {
		RuoloLabel.setText(ruoloLabel);
		RuoloLabel.setVisible(true);
	}
	
	/**
	 * Metodo che refresha la schermata principale dopo aver effettuato il logout.
	 */
	public void refreshAfterLogout() {
		this.Entra.setVisible(true);
		this.ruoloImg.setVisible(false);
		this.userImg.setVisible(false);
		this.RuoloLabel.setText("");
		this.RuoloLabel.setVisible(false);
		this.UserLabel.setText("");
		this.UserLabel.setVisible(false);
	}

	/**
	 * Disabilita il Button Indietro
	 */
	public void disabilitaIndietro() {
		this.IndietroButton.setDisable(true);
	}
	
	/**
	 * Abilita il Button Indietro
	 */
	public void abilitaIndietro() {
		this.IndietroButton.setDisable(false);
	}
	
	/**
	 * Setta la visibilità e il valore degli elementi del RootLayout in base
	 * alle condizioni UtenteSelezionato (selUt) e PubblicazioneSelezionata (selPubb)
	 * 
	 * @param selPubb
	 * @param selUt
	 */
	public void setRootLayout(Boolean selPubb, Boolean selUt) {
		
		if(selUt) {
			
			this.Entra.setVisible(false);
			
    		setRuoloImgVix(true);
    		setUserImgVix(true);
    		this.EsciButton.setVisible(true);
    		this.GestioneButton.setVisible(true);
		
		} else {
			
			this.Entra.setVisible(true);

    		setRuoloImgVix(false);
    		setUserImgVix(false);
    		setUserLabelText("");
    		setRuoloLabelText("");
    		this.EsciButton.setVisible(false);
    		this.GestioneButton.setVisible(false);
		}
		
	}
	
}
