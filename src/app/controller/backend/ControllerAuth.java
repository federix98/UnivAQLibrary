package app.controller.backend;

import app.controller.abstracts.ControllerBackend;
import app.model.Utente;
import app.utility.ManageCapabilities;

public class ControllerAuth extends ControllerBackend {
	
	private static ControllerAuth authManager = new ControllerAuth();
	
	// SINGLETON GETINSTANCE
    public static ControllerAuth getInstance() {
    	return authManager;
    }
    
    private ControllerAuth() {
    }

	public boolean abilita(ManageCapabilities capability) {
		Utente logged = ControllerSessione.getIstanza().getUtenteLoggato();
		if(logged == null) return false;
		
		if(capability == ManageCapabilities.RICERCA) return true;
		if(capability == ManageCapabilities.CLASSIFICHE) return true;
		if(capability == ManageCapabilities.PROFILO) return true;
		if(capability == ManageCapabilities.INFORMAZIONI) return true;
		
		
		if(capability == ManageCapabilities.APPROVA_RECENSIONI) {
			if(logged.getRuolo() >= 3) return true;
			return false;
		}
		
		if(capability == ManageCapabilities.GESTIONE_CATALOGO) {
			if(logged.getRuolo() >= 3) return true;
			return false;
		}
		
		if(capability == ManageCapabilities.GESTIONE_UTENTI) {
			if(logged.getRuolo() >= 4) return true;
			return false;
		}
		
		if(capability == ManageCapabilities.LOGS) {
			if(logged.getRuolo() == 5) return true;
			return false;
		}
		return false;
	}
	
}
