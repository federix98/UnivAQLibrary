package app.DAO.interfaces;

import java.util.ArrayList;
import java.time.LocalDate;

import app.model.Moderatore;
import app.model.Utente;

/**
 * 
 * @author Federico Di Menna
 *
 */
public interface DAOUtente {
	
	public Integer inserisciUtente(String nick, String email, String password, String nome, String cognome, LocalDate datanascita, String luogonascita, String residenza);
	
	public void aggiornaUtente(Integer ID, String nick, String email, String password, String nome, String cognome, LocalDate datanascita, String luogonascita, String residenza);
	
	public ArrayList<Utente> getListaUtenti();
	
	public Utente getUtente(Integer IDUtente);
	
	public ArrayList<String> getProvince();
	
	public ArrayList<Moderatore> getUtentiCollaborativi();
	
	public void promuoviUtenteAModeratore(Integer IDUtente, Integer IDPromotore);
	
	public void promuoviUtenteAdAmministratore(Integer IDUtente);
	
	public void degradaAModeratore(Integer IDUtente);
	
	public Integer getIDUtenteFromUsername(String Username);
	
	public void aggiornaPassword(String hashedPassword, Integer IDUtente); 
}
