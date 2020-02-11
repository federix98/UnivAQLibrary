package app.controller.backend;

import java.util.ArrayList;

import app.DAO.DAOFactory;
import app.DAO.interfaces.DAOParolaChiave;
import app.controller.abstracts.ControllerBackend;
import app.model.ParolaChiave;
import app.model.Pubblicazione;
/**
 * 
 * @author Federico Di Menna
 *
 */
public class ControllerParolaChiave extends ControllerBackend {

	private static ControllerParolaChiave instance = new ControllerParolaChiave();
	
	private DAOParolaChiave DAO;
	
	/**
	 * Costruttore privato del controller singleton
	 */
	private ControllerParolaChiave() {
		
		DAO = DAOFactory.getDAOFactory(0).getDAOParolaChiave();
	
	}
	
	/**
	 * 
	 * @return istanza singleton del controller
	 */
	public static ControllerParolaChiave getInstance() {
	
		return instance;
	
	}
	
	/**
	 * 
	 * @param pubb
	 * @return lista di parole chiave della pubblicazione
	 */
	public ArrayList<ParolaChiave> getParoleChiavePubblicazione(Pubblicazione pubb) {
		return DAO.getListaParoleChiavePubblicazione(pubb.getID());
	}
	
	/**Inserisci una parola chiave nel sistema
	 * 
	 * @param parola
	 */
	public void inserisciParolaChiaveDirect(String parola) {
		DAO.inserisciParolaChiaveDirect(parola);
	}
	
	/**
	 * 
	 * @param numPag
	 * @return lista delle parole chiave divise in pagine
	 */
	public ArrayList<ParolaChiave> getParoleChiave(Integer numPag) {
		return DAO.getListaParoleChiave(numPag);
	}
	
}
