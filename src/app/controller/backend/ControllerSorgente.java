package app.controller.backend;

import java.util.ArrayList;

import app.DAO.DAOFactory;
import app.DAO.interfaces.DAOSorgente;
import app.controller.abstracts.ControllerBackend;
import app.model.Pubblicazione;
import app.model.Sorgente;

public class ControllerSorgente extends ControllerBackend {

	private static ControllerSorgente instance = new ControllerSorgente();
	
	private DAOSorgente DAOs = null;
	
	private ControllerSorgente() {
		DAOs = DAOFactory.getDAOFactory(0).getDAOSorgente();
	}
	
	public static ControllerSorgente getInstance() {
		return instance;
	}
	
	public Integer inserisciSorgente(Sorgente sorgente, Integer IDPubblicazione, Integer IDUtente) {
		// Non posso inserire una sorgente con un ID, ma quest'ultimo deve essere inserito dal DB
		if(sorgente.getID() != null) return 0;
		
		return DAOs.inserisciSorgente(sorgente.getURI(), sorgente.getTipo(), sorgente.getFormato(), sorgente.getDescrizione(), IDPubblicazione, IDUtente);
		
	}
	
	public void rimuoviSorgente(Sorgente s, Integer IDPubblicazione, Integer IDUtente) {
		
		DAOs.rimuoviSorgente(s.getID(), IDPubblicazione, IDUtente);
		
	}
	
	public ArrayList<Sorgente> getListaSorgenti(Integer numPag) {
		
		return DAOs.getListaSorgenti(numPag);
		
	}
	
	public ArrayList<Sorgente> getListaSorgentiPubblicazione(Pubblicazione pubb) {
		
		return DAOs.getListaSorgentiPubblicazione(pubb.getID());
		
	}
}
