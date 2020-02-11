package app.controller.backend;

import app.controller.abstracts.ControllerBackend;
import app.model.Pubblicazione;
import app.model.Utente;
import javafx.stage.Stage;
/**
 * 
 * @author Federico Di Menna
 *
 */
public class ControllerSessione extends ControllerBackend {

	
	// ISTANZA SINGLETON SESSIONE
	
	private static ControllerSessione session = new ControllerSessione();
	
	
	
	// VARIABILI DI SESSIONE
	
	private Utente utenteLoggato = null;
	
	private Pubblicazione pubblicazioneSelezionata = null;
	
	private String paginaPrecedente = null;
	
	private Stage primaryStage = null;
	
	
	
	/**
	 * 
	 * @return lo stage primario dell'applicazione
	 */
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}


	/**
	 * Setta il primaryStage dell'app
	 * @param primaryStage
	 */
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}


	/**
	 * Costruttore privato del controller
	 */
	private ControllerSessione() {
		
	}
	
	
	
	// GET ISTANZA SINGLETON
	/**
	 * 
	 * @return istanza singleton del controller
	 */
	public static ControllerSessione getIstanza() {
		return session;
	}

	
	
	
	// METODI PUBBLICI
	/**
	 * 
	 * @return l'utente in questo momento loggato
	 */
	public Utente getUtenteLoggato() {
		return utenteLoggato;
	}

	/**
	 * Setta l'utente che si è appena loggato
	 * @param utenteLoggato
	 */
	public void setUtenteLoggato(Utente utenteLoggato) {

		this.utenteLoggato = utenteLoggato;		
		
	}

	/**
	 * Effettua il logout dell'utente
	 */
	public void logout() {
		
		ControllerSchermo.istanzaManager().getRootController().refreshAfterLogout();
		setUtenteLoggato(null);
		ControllerSchermo.istanzaManager().activateOnCenter("Login");
	
	}
	
	/**
	 * 
	 * @return la pubblicazione che l'utente ha preso in analisi
	 */
	public Pubblicazione getPubblicazioneSelezionata() {
		
		return pubblicazioneSelezionata;

	}
	
	/**
	 * Setta la pubblicazione che l'utente ha preso in analisi
	 * @param pubblicazione
	 */
	public void setPubblicazioneSelezionata(Pubblicazione pubblicazione) {
		
		if(pubblicazione.getID() != null) this.pubblicazioneSelezionata = pubblicazione;
		
	}

	/**
	 * 
	 * @return la pagina precedentemente visitata
	 */
	public String getPaginaPrecedente() {
		return paginaPrecedente;
	}

	/**
	 * Setta la pagina precedentemente visitata
	 * @param paginaPrecedente
	 */
	public void setPaginaPrecedente(String paginaPrecedente) {
		this.paginaPrecedente = paginaPrecedente;
	}

	/**
	 * Metodo che si occupa di gestire i vari pulsanti del rootlayout in base a quello che l'utente sta facendo
	 */
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
