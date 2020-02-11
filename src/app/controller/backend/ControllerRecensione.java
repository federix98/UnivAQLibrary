package app.controller.backend;

import java.util.ArrayList;

import app.DAO.DAOFactory;
import app.DAO.interfaces.DAORecensione;
import app.controller.abstracts.ControllerBackend;
import app.model.Recensione;
/**
 * 
 * @author Federico Di Menna
 *
 */
public class ControllerRecensione extends ControllerBackend {

	private static ControllerRecensione instance = new ControllerRecensione();
	private DAORecensione DAO;
	
	/**
	 *  Costruttore privato del controller
	 */
	private ControllerRecensione() {
		DAO = DAOFactory.getDAOFactory(0).getDAORecensione();
	}
	
	/**
	 * 
	 * @return istanza singleton del controller
	 */
	public static ControllerRecensione getInstance() {
		return instance;
	}
	
	/**
	 * Inserisci una recensione nel sistema
	 * @param daInserire
	 */
	public void inserisciRecensione(Recensione daInserire) {
		DAO.inserisciRecensione(daInserire.getIDPubblicazione(), daInserire.getIDUtente(), daInserire.getDescrizione());
	}
	
	/**
	 * 
	 * @return lista delle recensioni da approvare
	 */
	public ArrayList<Recensione> getRecensioniDaApprovare() {
		return DAO.getRecensioniDaApprovare();
	}
	
	/**
	 * Approva una recensione nel sistema
	 * @param IDPubblicazione
	 * @param IDUtente
	 * @param IDApprovatore
	 */
	public void approvaRecensione(Integer IDPubblicazione, Integer IDUtente, Integer IDApprovatore) {
		DAO.approvaRecensione(IDPubblicazione, IDUtente, IDApprovatore);
	}
	
	/**
	 * 
	 * @param IDPubblicazione
	 * @return lista delle recensioni di quella pubblicazione
	 */
	public ArrayList<Recensione> getRecensioniPubblicazione(Integer IDPubblicazione) {
		return DAO.getRecensioniApprovatePubblicazione(IDPubblicazione);
	}
}
