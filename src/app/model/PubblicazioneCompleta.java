package app.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class PubblicazioneCompleta extends Pubblicazione {
	
	
	private Integer NPag;
	
	private String Lingua;
	
	private String Descrizione;
	
	private String Indice;
	
	private ArrayList<Ristampa> Ristampe;
	
	private ArrayList<Sorgente> Sorgenti;
	
	private ArrayList<ParolaChiave> Tags;

	public PubblicazioneCompleta(Integer iD, String iSBN, String titolo, String editore, Integer numLike,
			Integer numRec, LocalDate dataPubblicazione, LocalDate dataUltimaModifica, Integer nPag, String lingua,
			String descrizione, String indice, ArrayList<Autore> autori, ArrayList<Ristampa> ristampe,
			ArrayList<Sorgente> sorgenti, ArrayList<ParolaChiave> tags) {
		super(iD, iSBN, titolo, editore, numLike, numRec, dataPubblicazione, dataUltimaModifica, autori);
		NPag = nPag;
		Lingua = lingua;
		Descrizione = descrizione;
		Indice = indice;
		Ristampe = ristampe;
		Sorgenti = sorgenti;
		Tags = tags;
	}
	
	public Integer getNPag() {
		return NPag;
	}



	public void setNPag(Integer nPag) {
		NPag = nPag;
	}



	public String getLingua() {
		return Lingua;
	}



	public void setLingua(String lingua) {
		Lingua = lingua;
	}



	public String getDescrizione() {
		return Descrizione;
	}



	public void setDescrizione(String descrizione) {
		Descrizione = descrizione;
	}



	public String getIndice() {
		return Indice;
	}



	public void setIndice(String indice) {
		Indice = indice;
	}



	public ArrayList<Ristampa> getRistampe() {
		return Ristampe;
	}



	public void setRistampe(ArrayList<Ristampa> ristampe) {
		Ristampe = ristampe;
	}



	public ArrayList<Sorgente> getSorgenti() {
		return Sorgenti;
	}



	public void setSorgenti(ArrayList<Sorgente> sorgenti) {
		Sorgenti = sorgenti;
	}



	public ArrayList<ParolaChiave> getTags() {
		return Tags;
	}



	public void setTags(ArrayList<ParolaChiave> tags) {
		Tags = tags;
	}



	@Override
	public String toString() {
		return "PubblicazioneCompleta [NPag=" + super.toString() + NPag + ", Lingua=" + Lingua + ", Descrizione=" + Descrizione
				+ ", Indice=" + Indice + ", Ristampe=" + Ristampe + ", Sorgenti=" + Sorgenti
				+ ", Tags=" + Tags + "]";
	}
	
	
}
