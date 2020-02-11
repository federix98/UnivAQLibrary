package app.controller.backend;

import java.util.ArrayList;

import app.DAO.DAOFactory;
import app.DAO.interfaces.DAOAutore;
import app.controller.abstracts.ControllerBackend;
import app.model.Autore;
import app.model.Pubblicazione;
import app.model.Utente;
/**
 * 
 * @author Federico Di Menna
 *
 */
public class ControllerAutore extends ControllerBackend {
	
	private static ControllerAutore instance = new ControllerAutore();
	
	DAOAutore DAO = null;
	
	/**
	*
	* Costruttore del Singleton
	* 
	**/
	private ControllerAutore() {
		DAO = DAOFactory.getDAOFactory(0).getDAOAutore();
	}
	
	/**
	*
	* Ritorna l'istanza Singleton
	* @return instance
	**/
	public static ControllerAutore getInstance() {
		return instance;
	}
	
	/**
	*
	* Inserisce l'autore nel sistema
	* @param Autore   Oggetto Autore da inserire
	* 
	**/
	public void inserisciAutore(Autore autore) {
		
		if(autore.getID() != null) return;
		DAO.inserisciAutore(autore.getNome(), autore.getCognome());
		
	}
	
	/**
	*
	* Ritorna la lista degli autori in base al numero pagina
	* @param numPag   Numero di pagina
	* @return listaAutori
	**/
	public ArrayList<Autore> getListaAutori(Integer numPag) {
		return DAO.getListaAutori(numPag);
	}

	
	/**
	*
	* Ritorna la lista di autori in una pubblicazione
	* @param Autore   Oggetto pubblicazione da analizzare
	* @return Lista degli autori della pubblicazione data
	* 
	**/
	public ArrayList<Autore> getListaAutoriPubblicazione(Pubblicazione pubblicazione) {
		return DAO.getListaAutoriPubblicazione(pubblicazione.getID());
	}
	
	/**
	*
	* Rimuove un autore dal sistema
	* @param Autore   Oggetto autore da rimuovere
	* @param Utente   Utente che effettua la cancellazione
	* 
	**/
	public void rimuoviAutore(Autore autore, Utente utente) {
		DAO.rimuoviAutore(autore.getID(), utente.getID());
	}
		
	/**
	*
	* Aggiorna un autore dal sistema
	* @param Autore   Oggetto autore da aggiornare
	* 
	**/
	public void aggiornaAutore(Autore autore) {
		DAO.aggiornaAutore(autore.getID(), autore.getNome(), autore.getCognome());
	}
	
	/**
	*
	* Ritorna la stringa "Nome Cognome" dell'autore
	* @param Autore   Oggetto autore da aggiornare
	* @return infoAutore
	* 
	**/
	public static String returnInfo(Autore autore) {
		
		if(autore.getNome() == null) return autore.getCognome();
		if(autore.getCognome() == null) return autore.getNome();
		return autore.getNome() + " " + autore.getCognome();
	}

}
