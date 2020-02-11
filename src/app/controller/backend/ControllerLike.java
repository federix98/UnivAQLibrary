package app.controller.backend;

import app.DAO.DAOFactory;
import app.DAO.interfaces.DAOLike;
import app.controller.abstracts.ControllerBackend;
/**
 * 
 * @author Federico Di Menna
 *
 */
public class ControllerLike extends ControllerBackend {

	private static ControllerLike instance = new ControllerLike();
	
	private DAOLike DAO;
	
	/**
	*
	* Costruttore privato del Singleton
	* 
	**/
	private ControllerLike() {
		DAO = DAOFactory.getDAOFactory(0).getDAOLike();
	}
	
	/**
	*
	* Ritorna l'istanza Singleton
	* 
	**/
	public static ControllerLike getInstance() {
		return instance;
	}
	
	/**
	*
	* Inserisce il like fra utente e pubblicazione nel sistema
	* @param IDPubblicazione  Pubblicazione soggetta
	* @param IDUtente	Utente che vuole mettere like
	* 
	**/
	public void inserisciLike(Integer IDPubblicazione, Integer IDUtente) {
		DAO.inserisciLike(IDPubblicazione, IDUtente);
	}
	
	/**
	*
	* Chech se l'utente ha messo like alla pubblicazione
	* @param IDPubblicazione  Pubblicazione soggetta
	* @param IDUtente	Utente
	* @return true se ha messo like, false altrimenti
	**/
	public Boolean getLike(Integer IDPubblicazione, Integer IDUtente) {
		return DAO.getLike(IDPubblicazione, IDUtente);
	}
	
	/**
	*
	* Elimina un like fra utente e pubblicazione nel sistema
	* @param IDPubblicazione  Pubblicazione soggetta
	* @param IDUtente	Utente che vuole rimuovere il like
	* 
	**/
	public void eliminaLike(Integer IDPubblicazione, Integer IDUtente) {
		DAO.eliminaLike(IDPubblicazione, IDUtente);
	}
	
}
