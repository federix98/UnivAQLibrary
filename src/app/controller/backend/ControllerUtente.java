package app.controller.backend;

import java.util.ArrayList;

import app.DAO.DAOFactory;
import app.DAO.interfaces.DAOUtente;
import app.controller.abstracts.ControllerBackend;
import app.model.Moderatore;
import app.model.Utente;
import app.utility.BCrypt;
/**
 * 
 * @author Federico Di Menna
 *
 */
public class ControllerUtente extends ControllerBackend {
	
	private static ControllerUtente istanzaController = new ControllerUtente();

	private DAOUtente DAO = null;
	
	/**
	 * Costruttore privato del controller
	 */
	private ControllerUtente() {
		DAO = DAOFactory.getDAOFactory(0).getDAOUtente();
	}
	
	/**
	 * 
	 * @return istanza singleton del controller
	 */
	public static ControllerUtente getIstanza() {
		return istanzaController;
	}
	
	/**
	 * Inserisci un utente nel sistema
	 * @param utente
	 * @return ID dell'utente inserito
	 */
	public Integer inserisciUtente(Utente utente) {
		return DAO.inserisciUtente(utente.getNickName(), utente.getEmail(), utente.getPassword(), utente.getNome(), utente.getCognome(), utente.getDataNascita(), utente.getLuogoNascita(), utente.getResidenza());
	}
	
	/**
	 * Effettua l'accesso dell'utete
	 * @param username
	 * @param password
	 * @return L'utente che ha effettutato l'accesso
	 */
	public Utente accessoUtente(String username, String password) {
		
		Integer IDUtente = DAO.getIDUtenteFromUsername(username);
		Utente loginUser = DAO.getUtente(IDUtente);
		
		try {
			
			if(BCrypt.checkpw(password, loginUser.getPassword())) {
				return getUtente(IDUtente);
			}
		
		} catch (IllegalArgumentException e) { return null; }
		
		return null;
	}
	
	/**
	 * 
	 * @return elenco delle province nel sistema
	 */
	public ArrayList<String> getProvince() {
		return DAO.getProvince();
	}
	
	/**
	 * 
	 * @param IDUtente
	 * @return Oggetto utente in base all'ID
	 */
	public Utente getUtente(Integer IDUtente) {
		return DAO.getUtente(IDUtente);
	}
	
	/**
	 * 
	 * @return elenco degli utenti più collaborativi
	 */
	public ArrayList<Moderatore> getUtentiCollaborativi() {
		return DAO.getUtentiCollaborativi();
	}
	
	/**
	 * 
	 * @return tutti gli utenti del sistema
	 */
	public ArrayList<Utente> getUtentiSistema() {
		ArrayList<Utente> result = new ArrayList<Utente>();
		
		for(Utente u : DAO.getListaUtenti()) {
			if(!u.getEliminato()) result.add(u);
		}
		
		return result;
	}
	
	// TO CHECK
	/**
	 * Effettua la promozione dell'utente
	 * @param ruolo
	 * @param IDUtente
	 */
	public void promuoviUtente(Integer ruolo, Integer IDUtente) {
		
		if(ruolo < Utente.getIdFromRuolo("Moderatore") || ruolo > Utente.getIdFromRuolo("Amministratore")) return;
		
		Integer IDPromotore = ControllerSessione.getIstanza().getUtenteLoggato().getID();
		Integer ruoloPrec = DAO.getUtente(IDUtente).getRuolo();
		
		
		
		switch(ruoloPrec) {
			case 1:
				;
				
			case 2: {
				// POSSO SOLTANTO PROMUOVERE
				if(ruolo == Utente.getIdFromRuolo("Moderatore")) {
					DAO.promuoviUtenteAModeratore(IDUtente, IDPromotore);
				}
				else if(ruolo == Utente.getIdFromRuolo("Amministratore")) {
					DAO.promuoviUtenteAModeratore(IDUtente, IDPromotore);
					DAO.promuoviUtenteAdAmministratore(IDUtente);
				}
				break;
			}
			
			case 3: {
				// POSSO SOLTANTO PROMUOVERE
				DAO.promuoviUtenteAdAmministratore(IDUtente);
				break;
			}
			
			case 4: {
				// POSSO SOLTANTO DEGRADARE
				DAO.degradaAModeratore(IDUtente);
			}
		}
		
	}
	
	/**
	 * Cambia la password dell'utente
	 * @param newPassword
	 * @param utente
	 */
	public void cambiaPassword(String newPassword, Utente utente) {
		
		String hash = BCrypt.hashpw(newPassword, BCrypt.gensalt());
		DAO.aggiornaPassword(hash, utente.getID());
		
	}
	
	/**
	 * Aggiorna i metadati dell'utente
	 * @param utente
	 */
	public void aggiornaUtente(Utente utente) {
		DAO.aggiornaUtente(utente.getID(), utente.getNickName(), utente.getEmail(), utente.getPassword(), utente.getNome(), utente.getCognome(), utente.getDataNascita(), utente.getLuogoNascita(), utente.getResidenza());
	}
}
