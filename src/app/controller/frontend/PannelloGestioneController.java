package app.controller.frontend;

import app.controller.abstracts.ControllerFrontend;
import app.controller.backend.ControllerAuth;
import app.controller.backend.ControllerSchermo;
import app.utility.ManageCapabilities;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

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
	
	
	// IMPLEMENTED
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
	
	// IMPLEMENTED
	@FXML
	public void handleRicerca() {
		
		ControllerSchermo.istanzaManager().activateOnCenter("Ricerca");
		
	}
	
	// IMPLEMENTED
	@FXML
	public void handleClassifiche() {
		
		ControllerSchermo.istanzaManager().activateOnCenter("Classifiche");
		
	}
	
	// IMPLEMENTED
	@FXML
	public void handleImpostazioni() {
		
		ControllerSchermo.istanzaManager().activateOnCenter("Informazioni");
		
	}
	
	// IMPLEMENTED
	@FXML
	public void handleProfilo() {
		
		//System.out.println(ControllerSchermo.istanzaManager().getScreenMap().get("Informazioni"));
		ControllerSchermo.istanzaManager().activateOnCenter("ProfiloUtente");
		
	}
	
	// IMPLEMENTED
	@FXML
	public void handleRecensioni() {
		
		ControllerSchermo.istanzaManager().activateOnCenter("ApprovaRecensioni");
		
	}
	
	// IMPLEMENTED
	@FXML
	public void handleLogs() {
		
		ControllerSchermo.istanzaManager().activateOnCenter("Logs");
		
	}
	
	// IMPLEMENTED
	@FXML
	public void handleGestioneCatalogo() {
		
		ControllerSchermo.istanzaManager().activateOnCenter("GestioneCatalogo");
		
	}

	
	// IMPLEMENTED
	@FXML
	public void handleGestioneUtenti() {
		
		ControllerSchermo.istanzaManager().activateOnCenter("GestioneUtenti");
		
	}
	


	@Override
	public void load() {
		
		caricaPannello();
		
	}
	
	
	
}
