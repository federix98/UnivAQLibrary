package app.DAO.interfaces;

import java.time.LocalDate;
import java.util.ArrayList;

import app.model.Ristampa;
/**
 * 
 * @author Federico Di Menna
 *
 */
public interface DAORistampa {

	public void inserisciRistampa(LocalDate dataRistampa, Integer numRistampa, Integer IDUtente, Integer IDPubblicazione);
	
	public ArrayList<Ristampa> getListaRistampePubblicazione(Integer IDPubblicazione);
	
	public void rimuoviRistampa(Integer IDRistampa, Integer IDPubblicazione, Integer IDUtente);
	
}
