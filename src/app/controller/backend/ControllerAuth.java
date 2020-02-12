package app.controller.backend;

import app.controller.abstracts.ControllerBackend;
import app.model.Utente;
import app.utility.ManageCapabilities;
/**
 * 
 * @author Federico Di Menna
 *
 */
public class ControllerAuth extends ControllerBackend {
	
	private static ControllerAuth authManager = new ControllerAuth();
	
	/**
	 * Ritorna l'istanza Singleton del Controller
	 *
	 * @return authManager
	 */
    public static ControllerAuth getInstance() {
    	return authManager;
    }
    
    /**
	 * Costruttore (privato) del controller
	 *
	 */
    private ControllerAuth() {
    }

    /**
	 * Torna vero se l'utente loggato è abilitato ad accedere a quella pagina, falso altrimenti
	 *
	 * @param capability : pagina alla quale l'utente deve accedere
	 * 
	 * @return true se ha i permessi, false altrimenti
	 */
	public boolean abilita(ManageCapabilities capability) {
		Utente logged = ControllerSessione.getIstanza().getUtenteLoggato();
		if(logged == null) return false;
		
		if(capability == ManageCapabilities.RICERCA) return true;
		if(capability == ManageCapabilities.CLASSIFICHE) return true;
		if(capability == ManageCapabilities.PROFILO) return true;
		if(capability == ManageCapabilities.INFORMAZIONI) return true;
		
		
		if(capability == ManageCapabilities.APPROVA_RECENSIONI) {
			if(logged.getRuolo() >= Utente.getIdFromRuolo("Moderatore")) return true;
			return false;
		}
		
		if(capability == ManageCapabilities.GESTIONE_CATALOGO) {
			if(logged.getRuolo() >= Utente.getIdFromRuolo("Moderatore")) return true;
			return false;
		}
		
		if(capability == ManageCapabilities.GESTIONE_UTENTI) {
			if(logged.getRuolo() >= Utente.getIdFromRuolo("Amministratore")) return true;
			return false;
		}
		
		if(capability == ManageCapabilities.LOGS) {
			if(logged.getRuolo() == Utente.getIdFromRuolo("Super Amministratore")) return true;
			return false;
		}
		return false;
	}
	
}
