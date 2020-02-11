package app.controller.frontend;

import app.controller.abstracts.ControllerFrontend;
import app.controller.backend.ControllerAuth;
import app.controller.backend.ControllerSchermo;
import app.utility.ManageCapabilities;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
/**
 * 
 * @author Federico Di Menna
 *
 */
public class PannelloGestioneController extends ControllerFrontend {

	@FXML
	private Label Ricerca;
	
	@FXML
	private Label Classifiche;
	
	@FXML
	private Label Impostazioni;
	
	@FXML
	private Label Profilo;
	
	@FXML
	private Label Recensioni;
	
	@FXML
	private Label Logs;
	
	@FXML
	private Label GestioneCatalogo;
	
	@FXML
	private Label GestioneUtenti;
	
	@FXML
	private HBox Box00;
	
	@FXML
	private HBox Box01;
	
	@FXML
	private HBox Box02;
	
	@FXML
	private HBox Box03;
	
	@FXML
	private HBox Box10;
	
	@FXML
	private HBox Box11;
	
	@FXML
	private HBox Box12;
	
	@FXML
	private HBox Box13;
	
	
	/**
	 * Caricamento del pannello di gestione in base al proprio ruolo
	 */
	public void caricaPannello() {
		
		Box00.setVisible(ControllerAuth.getInstance().abilita(ManageCapabilities.RICERCA));
		Box01.setVisible(ControllerAuth.getInstance().abilita(ManageCapabilities.PROFILO));
		Box02.setVisible(ControllerAuth.getInstance().abilita(ManageCapabilities.APPROVA_RECENSIONI));
		Box03.setVisible(ControllerAuth.getInstance().abilita(ManageCapabilities.LOGS));
		Box10.setVisible(ControllerAuth.getInstance().abilita(ManageCapabilities.CLASSIFICHE));
		Box11.setVisible(ControllerAuth.getInstance().abilita(ManageCapabilities.INFORMAZIONI));
		Box12.setVisible(ControllerAuth.getInstance().abilita(ManageCapabilities.GESTIONE_CATALOGO));
		Box13.setVisible(ControllerAuth.getInstance().abilita(ManageCapabilities.GESTIONE_UTENTI));
		
	}
	
	/**
	 * Handler attiva ricerca
	 */
	@FXML
	public void handleRicerca() {
		
		ControllerSchermo.istanzaManager().activateOnCenter("Ricerca");
		
	}
	
	/**
	 * Handler attiva classifiche
	 */
	@FXML
	public void handleClassifiche() {
		
		ControllerSchermo.istanzaManager().activateOnCenter("Classifiche");
		
	}
	
	/**
	 * Handler attiva Informazioni
	 */
	@FXML
	public void handleImpostazioni() {
		
		ControllerSchermo.istanzaManager().activateOnCenter("Informazioni");
		
	}
	
	/**
	 * Handler attiva profilo
	 */
	@FXML
	public void handleProfilo() {
		
		//System.out.println(ControllerSchermo.istanzaManager().getScreenMap().get("Informazioni"));
		ControllerSchermo.istanzaManager().activateOnCenter("ProfiloUtente");
		
	}
	
	/**
	 * Handler attiva recensioni
	 */
	@FXML
	public void handleRecensioni() {
		
		ControllerSchermo.istanzaManager().activateOnCenter("ApprovaRecensioni");
		
	}
	
	/**
	 * Handler attiva logs
	 */
	@FXML
	public void handleLogs() {
		
		ControllerSchermo.istanzaManager().activateOnCenter("Logs");
		
	}
	
	/**
	 * Handler attiva gestione catalogo
	 */
	@FXML
	public void handleGestioneCatalogo() {
		
		ControllerSchermo.istanzaManager().activateOnCenter("GestioneCatalogo");
		
	}

	
	/**
	 * Handler attiva gestione utenti
	 */
	@FXML
	public void handleGestioneUtenti() {
		
		ControllerSchermo.istanzaManager().activateOnCenter("GestioneUtenti");
		
	}
	

	/**
     * Invocato quando viene attivata la schermata
     */
	@Override
	public void load() {
		
		caricaPannello();
		
	}
	
	
	
}
