package app.controller.backend;

import java.util.ArrayList;

import app.DAO.DAOFactory;
import app.DAO.interfaces.DAOPubblicazione;
import app.controller.abstracts.ControllerBackend;
import app.model.Autore;
import app.model.ParolaChiave;
import app.model.Pubblicazione;
import app.model.PubblicazioneCompleta;
import app.model.PubblicazioneRistampa;
import app.model.Ristampa;
import app.model.Sorgente;

public class ControllerPubblicazione extends ControllerBackend {

	private static ControllerPubblicazione istanzaController = new ControllerPubblicazione();
	private DAOPubblicazione DAO = null;
	
 	private ControllerPubblicazione() {
		
		DAO = DAOFactory.getDAOFactory(0).getDAOPubblicazione();
		
	}
	
	public static ControllerPubblicazione getIstanza() {
		return istanzaController;
	}
	
	// public PubblicazioneCompleta getDatiPubblicazione(Pubblicazione p) {
	
	public void inserisciPubblicazione(PubblicazioneCompleta pubb) {
		
		if(pubb.getAutori().isEmpty()) return;
		
		Integer IDUtente = ControllerSessione.getIstanza().getUtenteLoggato().getID();
		
		Integer IDpubblicazione = DAO.inserisciPubblicazione(pubb.getISBN(), pubb.getTitolo(), pubb.getEditore(), pubb.getDataPubblicazione(), 
				pubb.getNPag(), pubb.getLingua(), pubb.getDescrizione(), pubb.getIndice(), 
				IDUtente);
		
		for(Autore a : pubb.getAutori()) DAOFactory.getDAOFactory(0).getDAOAutore().aggiungiScritto(a.getID(), IDpubblicazione, IDUtente);
		
		for(ParolaChiave pc : pubb.getTags()) DAOFactory.getDAOFactory(0).getDAOParolaChiave().aggiungiTag(pc.getID(), IDpubblicazione, IDUtente);
		
		for(Ristampa r : pubb.getRistampe()) DAOFactory.getDAOFactory(0).getDAORistampa().inserisciRistampa(r.getDataRistampa(), r.getNumero(), IDUtente, IDpubblicazione);
		
		for(Sorgente s : pubb.getSorgenti()) DAOFactory.getDAOFactory(0).getDAOSorgente().inserisciSorgente(s.getURI(), s.getTipo(), s.getFormato(), s.getDescrizione(), IDpubblicazione, IDUtente);
	}
	
	public PubblicazioneCompleta getDatiPubblicazione(Integer IDPubblicazione) {
		
		return DAO.getDatiPubblicazione(IDPubblicazione);
		
	}
	
	public ArrayList<Pubblicazione> getListaPubblicazioniStessiAutori(Integer IDPubblicazione) {
		return DAO.getPubblcazioniStessiAutori(IDPubblicazione);
	}
	
	public ArrayList<Pubblicazione> getListaPubblicazioni(Integer numPag) {
		System.out.println("Controller getListaPubblicazioni");
		return DAO.getListaPubblicazioni(numPag);
	}
	
	public ArrayList<Pubblicazione> getListaPubblicazoniUtente(Integer IDUtente) {
		return DAO.getListaPubblicazioneUtente(IDUtente);
	}
	
	public ArrayList<Pubblicazione> getListaPubblicazioniTitolo(String titolo) {
		return DAO.getListaPubblicazioniTitolo(titolo);
	}
	
	public Pubblicazione getPubblicazioneISBN(String ISBN) {
		return DAO.getListaPubblicazioneISBN(ISBN);
	}
	
	public ArrayList<Pubblicazione> getListaPubblicazioniAutore(Autore autore) {
		return DAO.getListaPubblicazioniAutore(autore.getID());
	}
	
	public ArrayList<Pubblicazione> getListaPubblicazioniAutoreNome(String nomeAutore, String cognomeAutore) {
		
		if(nomeAutore.isBlank() || nomeAutore.isEmpty()) {
			
			if(cognomeAutore.isBlank() || cognomeAutore.isEmpty()) return null;
			return DAO.getListaPubblicazioniAutoreCognome(cognomeAutore);
		} 
		
		if(cognomeAutore.isBlank() || cognomeAutore.isEmpty()) return DAO.getListaPubblicazioniAutoreNome(nomeAutore); 
		
		return DAO.getListaPubblicazioniAutoreNomeCognome(nomeAutore, cognomeAutore);
	}
	
	public ArrayList<Pubblicazione> getListaPubblicazioniParolaChiave(String parolaChiave) {
		
		ParolaChiave keyword = DAOFactory.getDAOFactory(0).getDAOParolaChiave().getParolaChiave(parolaChiave);
		
		return DAO.getListaPubblicazioniParolaChiave(keyword.getID());// DA MODIFICARE
	}
	
	public ArrayList<Pubblicazione> getUltimeInserite() {
		return DAO.getUltimeInserite();
	}
	
	public ArrayList<Pubblicazione> getUltimeAggiornate() {
		return DAO.getUltimeAggiornate();
	}

	public void aggiornaPubblicazione(PubblicazioneCompleta pubc, PubblicazioneCompleta pubModificata) {
		
		if(!pubc.getISBN().equals(pubModificata.getISBN())) DAO.aggiornaISBN(pubc.getID(), pubModificata.getISBN(), ControllerSessione.getIstanza().getUtenteLoggato().getID());
		if(!pubc.getTitolo().equals(pubModificata.getTitolo())) DAO.aggiornaTitolo(pubc.getID(), pubModificata.getTitolo(), ControllerSessione.getIstanza().getUtenteLoggato().getID());
		if(!pubc.getEditore().equals(pubModificata.getEditore())) DAO.aggiornaEditore(pubc.getID(), pubModificata.getEditore(), ControllerSessione.getIstanza().getUtenteLoggato().getID());
		if(!pubc.getISBN().equals(pubModificata.getISBN())) DAO.aggiornaISBN(pubc.getID(), pubModificata.getTitolo(), ControllerSessione.getIstanza().getUtenteLoggato().getID());
		if(!pubc.getLingua().equals(pubModificata.getLingua())) DAO.aggiornaLingua(pubc.getID(), pubModificata.getLingua(), ControllerSessione.getIstanza().getUtenteLoggato().getID());
		if(!pubc.getNPag().equals(pubModificata.getNPag())) DAO.aggiornaNumPag(pubc.getID(), pubModificata.getNPag(), ControllerSessione.getIstanza().getUtenteLoggato().getID());
		if(!pubc.getIndice().equals(pubModificata.getIndice())) DAO.aggiornaIndice(pubc.getID(), pubModificata.getIndice(), ControllerSessione.getIstanza().getUtenteLoggato().getID());
		if(!pubc.getDescrizione().equals(pubModificata.getDescrizione())) DAO.aggiornaDescrizione(pubc.getID(), pubModificata.getDescrizione(), ControllerSessione.getIstanza().getUtenteLoggato().getID());
		
	}
	
	public PubblicazioneCompleta findByID(Integer IDPubblicazione) {
		return DAO.getDatiPubblicazione(IDPubblicazione);
	}

	public ArrayList<Pubblicazione> getListaPubblicazioniDownload() {
		return DAO.getListaPubblicazioniDownlaodable();
	}
	
	public Integer getNumeroPubblicazioni() {
		return DAO.getNumPubb();
	}
	
	public ArrayList<PubblicazioneRistampa> getListaPubblicazioniRistampe(Integer NumeroPagina) {
		return DAO.getPubblicazioniRistampa(NumeroPagina);
	}
	
}
