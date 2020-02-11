package app.DAO.interfaces;

import java.util.ArrayList;

import app.model.Recensione;
/**
 * 
 * @author Federico Di Menna
 *
 */
public interface DAORecensione {

	public void inserisciRecensione(Integer IDPubblicazione, Integer IDUtente, String Testo);
	
	public void approvaRecensione(Integer IDPubblicazione, Integer IDUtente, Integer IDApprovatore);
	
	public ArrayList<Recensione> getRecensioniDaApprovare();
	
	public ArrayList<Recensione> getRecensioniApprovatePubblicazione(Integer IDPubblicazione);
	
}
