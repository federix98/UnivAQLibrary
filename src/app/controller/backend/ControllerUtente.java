package app.controller.backend;

import java.util.ArrayList;

import app.DAO.DAOFactory;
import app.DAO.interfaces.DAOUtente;
import app.controller.abstracts.ControllerBackend;
import app.model.Moderatore;
import app.model.Utente;
import app.utility.BCrypt;

public class ControllerUtente extends ControllerBackend {
	
	private static ControllerUtente istanzaController = new ControllerUtente();

	private DAOUtente DAO = null;
	
	private ControllerUtente() {
		DAO = DAOFactory.getDAOFactory(0).getDAOUtente();
	}
	
	public static ControllerUtente getIstanza() {
		return istanzaController;
	}
	
	public Integer inserisciUtente(Utente utente) {
		return DAO.inserisciUtente(utente.getNickName(), utente.getEmail(), utente.getPassword(), utente.getNome(), utente.getCognome(), utente.getDataNascita(), utente.getLuogoNascita(), utente.getResidenza());
	}
	
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
	
	public ArrayList<String> getProvince() {
		return DAO.getProvince();
	}
	
	public Utente getUtente(Integer IDUtente) {
		return DAO.getUtente(IDUtente);
	}
	
	public ArrayList<Moderatore> getUtentiCollaborativi() {
		return DAO.getUtentiCollaborativi();
	}
	
	public ArrayList<Utente> getUtentiSistema() {
		ArrayList<Utente> result = new ArrayList<Utente>();
		
		for(Utente u : DAO.getListaUtenti()) {
			if(!u.getEliminato()) result.add(u);
		}
		
		return result;
	}
	
	// TO CHECK
	public void promuoviUtente(Integer ruolo, Integer IDUtente) {
		
		if(ruolo < 3 || ruolo > 4) return;
		
		Integer IDPromotore = ControllerSessione.getIstanza().getUtenteLoggato().getID();
		Integer ruoloPrec = DAO.getUtente(IDUtente).getRuolo();
		
		switch(ruoloPrec) {
			case 1:
				;
				
			case 2: {
				// POSSO SOLTANTO PROMUOVERE
				if(ruolo == 3) {
					DAO.promuoviUtenteAModeratore(IDUtente, IDPromotore);
				}
				else if(ruolo == 4) {
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
	
	public void cambiaPassword(String newPassword, Utente utente) {
		
		String hash = BCrypt.hashpw(newPassword, BCrypt.gensalt());
		DAO.aggiornaPassword(hash, utente.getID());
		
	}
	
	public void aggiornaUtente(Utente utente) {
		DAO.aggiornaUtente(utente.getID(), utente.getNickName(), utente.getEmail(), utente.getPassword(), utente.getNome(), utente.getCognome(), utente.getDataNascita(), utente.getLuogoNascita(), utente.getResidenza());
	}
}
