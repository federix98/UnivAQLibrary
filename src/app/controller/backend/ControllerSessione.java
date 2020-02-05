package app.controller.backend;

import app.controller.abstracts.ControllerBackend;
import app.model.Pubblicazione;
import app.model.Utente;
import javafx.stage.Stage;

public class ControllerSessione extends ControllerBackend {

	
	// ISTANZA SINGLETON SESSIONE
	
	private static ControllerSessione session = new ControllerSessione();
	
	
	
	// VARIABILI DI SESSIONE
	
	private Utente utenteLoggato = null;
	
	private Pubblicazione pubblicazioneSelezionata = null;
	
	private String paginaPrecedente = null;
	
	private Stage primaryStage = null;
	
	
	
	// COSTRUTTORE
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}



	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}



	private ControllerSessione() {
		
	}
	
	
	
	// GET ISTANZA SINGLETON
	
	public static ControllerSessione getIstanza() {
		return session;
	}

	
	
	
	// METODI PUBBLICI
	
	public Utente getUtenteLoggato() {
		return utenteLoggato;
	}

	public void setUtenteLoggato(Utente utenteLoggato) {

		this.utenteLoggato = utenteLoggato;		
		
	}

	public void logout() {
		
		ControllerSchermo.istanzaManager().getRootController().refreshAfterLogout();
		setUtenteLoggato(null);
		ControllerSchermo.istanzaManager().activateOnCenter("Login");
	
	}
	
	public Pubblicazione getPubblicazioneSelezionata() {
		
		return pubblicazioneSelezionata;

	}
	
	public void setPubblicazioneSelezionata(Pubblicazione pubblicazione) {
		
		if(pubblicazione.getID() != null) this.pubblicazioneSelezionata = pubblicazione;
		
	}

	
	public String getPaginaPrecedente() {
		return paginaPrecedente;
	}

	
	public void setPaginaPrecedente(String paginaPrecedente) {
		this.paginaPrecedente = paginaPrecedente;
	}

	public void check() {
		
		this.paginaPrecedente = ControllerSchermo.istanzaManager().getPaneAttuale();
		
		
		if(this.pubblicazioneSelezionata == null) {
			
			if(this.utenteLoggato == null) {
				
				ControllerSchermo.istanzaManager().getRootController().setRootLayout(false, false);
				
			} else {
				
				ControllerSchermo.istanzaManager().getRootController().setRootLayout(false, true);
				
			}
			
		} else {
			
			if(this.utenteLoggato == null) {
				
				ControllerSchermo.istanzaManager().getRootController().setRootLayout(true, false);
				
			} else {
				
				ControllerSchermo.istanzaManager().getRootController().setRootLayout(true, true);
				
			}
			
		}
		
		
		// CONTROLLER ACCESSO UTENTE
		
		if(this.utenteLoggato != null) {
			
			
    		ControllerSchermo.istanzaManager().getRootController().setUserLabelText(ControllerSessione.getIstanza().getUtenteLoggato().getNickName());

    		String ruolo = "";
    		switch(ControllerSessione.getIstanza().getUtenteLoggato().getRuolo()) {
	    		case 1: {
	    			ruolo = "Utente Passivo";
	    			break;
	    		}
	    		case 2: {
	    			ruolo = "Utente Attivo";
	    			break;
	    		}
	    		case 3: {
	    			ruolo = "Moderatore";
	    			break;
	    		}
	    		case 4: {
	    			ruolo = "Amministratore";
	    			break;
	    		}
	    		case 5: {
	    			ruolo = "Super Amministratore";
	    			break;
	    		}
    		}
    		ControllerSchermo.istanzaManager().getRootController().setRuoloLabelText(ruolo);
    		ControllerSchermo.istanzaManager().getRootController().setRuoloImgVix(true);
    		ControllerSchermo.istanzaManager().getRootController().setUserImgVix(true);
    	} else {
    		
    		// ALTRIMENTI
    		
    		ControllerSchermo.istanzaManager().getRootController().setRuoloImgVix(false);
    		ControllerSchermo.istanzaManager().getRootController().setUserImgVix(false);
    		ControllerSchermo.istanzaManager().getRootController().setUserLabelText("");
    		ControllerSchermo.istanzaManager().getRootController().setRuoloLabelText("");
    	}
		
		
	}
	
}
