package app.DAO.interfaces;

import java.time.LocalDate;
import java.util.ArrayList;

import app.model.Pubblicazione;
import app.model.PubblicazioneCompleta;
import app.model.PubblicazioneRistampa;
/**
 * 
 * @author Federico Di Menna
 *
 */
public interface DAOPubblicazione {

	// Metodi
	
	public Integer inserisciPubblicazione(String ISBN, String titolo, String editore, LocalDate dataPubblicazione, 
			Integer nPag, String lingua, String descrizione, String indice, Integer IDUtente);
	
	public ArrayList<Pubblicazione> getListaPubblicazioni(Integer numPag);
	
	public ArrayList<Pubblicazione> getListaPubblicazioniAutore(Integer IDAutore);
	
	public ArrayList<Pubblicazione> getListaPubblicazioniAutoreNome(String nomeAutore);
	
	public ArrayList<Pubblicazione> getListaPubblicazioniAutoreCognome(String cognomeAutore);
	
	public ArrayList<Pubblicazione> getListaPubblicazioniAutoreNomeCognome(String nomeAutore, String cognomeAutore);
	
	public ArrayList<Pubblicazione> getListaPubblicazioniParolaChiave(Integer IDParola);
	
	public ArrayList<Pubblicazione> getListaPubblicazioneUtente(Integer IDUtente);
	
	public ArrayList<Pubblicazione> getListaPubblicazioniTitolo(String titolo);
	
	public Pubblicazione getListaPubblicazioneISBN(String ISBN);
	
	public ArrayList<Pubblicazione> getListaPubblicazioniDownlaodable();
	
	public ArrayList<Pubblicazione> getUltimeInserite();
	
	public ArrayList<Pubblicazione> getUltimeAggiornate();
	
	public PubblicazioneCompleta getDatiPubblicazione(Integer IDPubblicazione);
	
	public void rimuoviPubblicazione(Integer IDPubblicazione, Integer IDUtente);
	
	public void aggiornaTitolo(Integer IDPubblicazione, String titolo, Integer IDUtente);
	
	public void aggiornaISBN(Integer IDPubblicazione, String ISBN, Integer IDUtente);
	
	public void aggiornaEditore(Integer IDPubblicazione, String Editore, Integer IDUtente);
	
	public void aggiornaDataPubblicazione(Integer IDPubblicazione, LocalDate dataPubblicazione, Integer IDUtente);
		
	public void aggiornaNumPag(Integer IDPubblicazione, Integer NumPag, Integer IDUtente);
	
	public void aggiornaLingua(Integer IDPubblicazione, String Lingua, Integer IDUtente);
	
	public void aggiornaDescrizione(Integer IDPubblicazione, String descrizione, Integer IDUtente);
	
	public void aggiornaIndice(Integer IDPubblicazione, String Indice, Integer IDUtente);
	
	public Integer getNumPubb();
	
	public ArrayList<PubblicazioneRistampa> getPubblicazioniRistampa(Integer NumeroPagina);
	
	public ArrayList<Pubblicazione> getPubblcazioniStessiAutori(Integer IDPubblicazione);
	
}
