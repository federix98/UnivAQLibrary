package app.controller.backend;

import java.util.ArrayList;

import app.DAO.DAOFactory;
import app.DAO.interfaces.DAOSorgente;
import app.controller.abstracts.ControllerBackend;
import app.model.Pubblicazione;
import app.model.Sorgente;
/**
 * 
 * @author Federico Di Menna
 *
 */
public class ControllerSorgente extends ControllerBackend {

	private static ControllerSorgente instance = new ControllerSorgente();
	
	private DAOSorgente DAOs = null;
	
	/**
	 * Costruttore privato del controller
	 */
	private ControllerSorgente() {
		DAOs = DAOFactory.getDAOFactory(0).getDAOSorgente();
	}
	
	/**
	 * 
	 * @return istanza singleton del controller
	 */
	public static ControllerSorgente getInstance() {
		return instance;
	}
	
	/**
	 * Inserisci una sorgente nel sistema
	 * @param sorgente
	 * @param IDPubblicazione
	 * @param IDUtente
	 * @return
	 */
	public Integer inserisciSorgente(Sorgente sorgente, Integer IDPubblicazione, Integer IDUtente) {
		// Non posso inserire una sorgente con un ID, ma quest'ultimo deve essere inserito dal DB
		if(sorgente.getID() != null) return 0;
		
		return DAOs.inserisciSorgente(sorgente.getURI(), sorgente.getTipo(), sorgente.getFormato(), sorgente.getDescrizione(), IDPubblicazione, IDUtente);
		
	}
	
	/**
	 * Rimuovi una sorgente dal sistema
	 * @param s
	 * @param IDPubblicazione
	 * @param IDUtente
	 */
	public void rimuoviSorgente(Sorgente s, Integer IDPubblicazione, Integer IDUtente) {
		
		DAOs.rimuoviSorgente(s.getID(), IDPubblicazione, IDUtente);
		
	}
	
	/**
	 * 
	 * @param numPag
	 * @return lista delle sorgenti per numero di pagina
	 */
	public ArrayList<Sorgente> getListaSorgenti(Integer numPag) {
		
		return DAOs.getListaSorgenti(numPag);
		
	}
	
	/**
	 * 
	 * @param pubb
	 * @return lista delle sorgenti di quella pubblicazione
	 */
	public ArrayList<Sorgente> getListaSorgentiPubblicazione(Pubblicazione pubb) {
		
		return DAOs.getListaSorgentiPubblicazione(pubb.getID());
		
	}
}
