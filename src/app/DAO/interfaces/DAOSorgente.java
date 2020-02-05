package app.DAO.interfaces;

import java.util.ArrayList;

import app.model.Sorgente;

public interface DAOSorgente {

	public Integer inserisciSorgente(String URI, String Tipo, String Formato, String Descrizione, Integer IDPubblicazione, Integer IDUtente);
	
	public ArrayList<Sorgente> getListaSorgenti(Integer numPag);
	
	public ArrayList<Sorgente> getListaSorgentiPubblicazione(Integer IDPubblicazione);
	
	public void rimuoviSorgente(Integer IDSorgente, Integer IDPubblicazione, Integer IDUtente);
	
}
