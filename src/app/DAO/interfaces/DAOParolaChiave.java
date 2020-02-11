package app.DAO.interfaces;

import java.util.ArrayList;

import app.model.ParolaChiave;
/**
 * 
 * @author Federico Di Menna
 *
 */
public interface DAOParolaChiave {

	// Metodi
	
	public void inserisciParolaChiave(String parola, Integer IDPubblicazione, Integer IDUtente);
	
	public ArrayList<ParolaChiave> getListaParoleChiave(Integer numPag);
	
	public ParolaChiave getParolaChiave(String parola);
	
	public void aggiungiTag(Integer IDParola, Integer IDPubblicazione, Integer IDUtente);
	
	public ArrayList<ParolaChiave> getListaParoleChiavePubblicazione(Integer IDPubblicazione);
	
	public void rimuoviParolaChiave(Integer IDParolaChiave, Integer IDUtente);
	
	public void inserisciParolaChiaveDirect(String parola);
}
