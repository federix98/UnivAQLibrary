package app.controller.backend;

import java.util.ArrayList;

import app.DAO.DAOFactory;
import app.DAO.interfaces.DAORistampa;
import app.controller.abstracts.ControllerBackend;
import app.model.Pubblicazione;
import app.model.Ristampa;
import app.model.Utente;
/**
 * 
 * @author Federico Di Menna
 *
 */
public class ControllerRistampa extends ControllerBackend {

	private static ControllerRistampa instance = new ControllerRistampa();
	
	DAORistampa DAO = null;
	
	/**
	 * Costruttore privato del singleton
	 */
	private ControllerRistampa() {
		DAO = DAOFactory.getDAOFactory(0).getDAORistampa();
	}
	
	/**
	 * 
	 * @return istanza singleton del controller
	 */
	public static ControllerRistampa getInstance() {
		return instance;
	}
	
	/**Inserisci una ristampa di una pubblicazione
	 * 
	 * @param ristampa
	 * @param utente
	 * @param pubb
	 */
	public void inserisciRistampa(Ristampa ristampa, Utente utente, Pubblicazione pubb) {
		
		DAO.inserisciRistampa(ristampa.getDataRistampa(), ristampa.getNumero(), utente.getID(), pubb.getID());
		
	}

	/**
	 * 
	 * @param pubblicazione
	 * @return lista delle ristampe di quella pubblicazione
	 */
	public ArrayList<Ristampa> getListaRistampePubblicazione(Pubblicazione pubblicazione) {
		return DAO.getListaRistampePubblicazione(pubblicazione.getID());
	}
	
	/**
	 * Rimuovi una ristampa dal sistema
	 * @param ristampa
	 * @param pubblicazione
	 * @param utente
	 */
	public void rimuoviRistampa(Ristampa ristampa, Pubblicazione pubblicazione, Utente utente) {
		DAO.rimuoviRistampa(ristampa.getID(), pubblicazione.getID(), utente.getID());
	}
	
}
