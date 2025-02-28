package app.DAO.interfaces;

import java.util.ArrayList;

import app.model.Autore;
/**
 * 
 * @author Federico Di Menna
 *
 */
public interface DAOAutore {

	// Metodi
	
	public void inserisciAutore(String nome, String cognome);
	
	public ArrayList<Autore> getListaAutori(Integer numPag);
	
	public void aggiungiScritto(Integer IDAutore, Integer IDPubblicazione, Integer IDUtente);
	
	public ArrayList<Autore> getListaAutoriPubblicazione(Integer IDPubblicazione);
	
	public void rimuoviAutore(Integer IDAutore, Integer IDUtente);
	
	public void aggiornaAutore(Integer IDAutore, String nome, String cognome);	
	
}
