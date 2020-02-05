package app.controller.backend;

import java.util.ArrayList;

import app.DAO.DAOFactory;
import app.DAO.interfaces.DAORistampa;
import app.controller.abstracts.ControllerBackend;
import app.model.Pubblicazione;
import app.model.Ristampa;
import app.model.Utente;

public class ControllerRistampa extends ControllerBackend {

	private static ControllerRistampa instance = new ControllerRistampa();
	
	DAORistampa DAO = null;
	
	private ControllerRistampa() {
		DAO = DAOFactory.getDAOFactory(0).getDAORistampa();
	}
	
	// Metodi
	
	public static ControllerRistampa getInstance() {
		return instance;
	}
	
	public void inserisciRistampa(Ristampa ristampa, Utente utente, Pubblicazione pubb) {
		
		DAO.inserisciRistampa(ristampa.getDataRistampa(), ristampa.getNumero(), utente.getID(), pubb.getID());
		
	}

	public ArrayList<Ristampa> getListaRistampePubblicazione(Pubblicazione pubblicazione) {
		return DAO.getListaRistampePubblicazione(pubblicazione.getID());
	}
	
	public void rimuoviRistampa(Ristampa ristampa, Pubblicazione pubblicazione, Utente utente) {
		DAO.rimuoviRistampa(ristampa.getID(), pubblicazione.getID(), utente.getID());
	}
	
}
