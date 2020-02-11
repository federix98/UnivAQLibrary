package app.model;

import java.time.LocalDate;
import java.util.ArrayList;

import app.controller.backend.ControllerAutore;
/**
 * 
 * @author Federico Di Menna
 *
 */
public class Pubblicazione {

	private Integer ID;
	
	private String ISBN;
	
	private String Titolo;
	
	private String Editore;
	
	private Integer NumLike;
	
	private Integer NumRec;
	
	private LocalDate DataPubblicazione;
	
	private LocalDate DataUltimaModifica;
	
	private ArrayList<Autore> Autori;

	
	// Costruttori

	public Pubblicazione(Integer iD, String iSBN, String titolo, String editore, Integer numLike, Integer numRec,
			LocalDate dataPubblicazione, LocalDate dataUltimaModifica, ArrayList<Autore> autori) {
		super();
		ID = iD;
		ISBN = iSBN;
		Titolo = titolo;
		Editore = editore;
		NumLike = numLike;
		NumRec = numRec;
		DataPubblicazione = dataPubblicazione;
		DataUltimaModifica = dataUltimaModifica;
		Autori = autori;
	}
	
	// Getters and Setters
	
	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitolo() {
		return Titolo;
	}

	public void setTitolo(String titolo) {
		Titolo = titolo;
	}

	public String getEditore() {
		return Editore;
	}

	public void setEditore(String editore) {
		Editore = editore;
	}

	public Integer getNumLike() {
		return NumLike;
	}

	public void setNumLike(Integer numLike) {
		NumLike = numLike;
	}

	public Integer getNumRec() {
		return NumRec;
	}

	public void setNumRec(Integer numRec) {
		NumRec = numRec;
	}

	public ArrayList<Autore> getAutori() {
		return Autori;
	}

	public void setAutori(ArrayList<Autore> autori) {
		Autori = autori;
	}

	public void setDataPubblicazione(LocalDate dataPubblicazione) {
		DataPubblicazione = dataPubblicazione;
	}
	
	public LocalDate getDataPubblicazione() {
		return DataPubblicazione;
	}

	public void setDataUltimaRecensione(LocalDate dataPubblicazione) {
		DataPubblicazione = dataPubblicazione;
	}

	public LocalDate getDataUltimaModifica() {
		return DataUltimaModifica;
	}

	public void setDataUltimaModifica(LocalDate dataUltimaModifica) {
		DataUltimaModifica = dataUltimaModifica;
	}
	
	public String getListaAutori() {
		
		String result = "";
		
		if(getAutori() == null) return "Nessun autore";
		else {
			for(Autore aut : getAutori()) {				
				result = result + (ControllerAutore.returnInfo(aut) + ", ");
			}
			if(!result.isEmpty()) result = result.substring(0, result.length() - 2);
			return result;
		}
		
	}
	
	@Override
	public String toString() {
		if(Autori != null) {
			return "Pubblicazione [ID=" + ID + ", ISBN=" + ISBN + ", Titolo=" + Titolo + ", Editore=" + Editore
					+ ", NumLike=" + NumLike + ", NumRec=" + NumRec + ", DataPubblicazione=" + DataPubblicazione
					+ ", DataUltimaModifica=" + DataUltimaModifica + ", Autori=" + Autori.toString() + "]";
		} else {
			return "Pubblicazione [ID=" + ID + ", ISBN=" + ISBN + ", Titolo=" + Titolo + ", Editore=" + Editore
					+ ", NumLike=" + NumLike + ", NumRec=" + NumRec + ", DataPubblicazione=" + DataPubblicazione
					+ ", DataUltimaModifica=" + DataUltimaModifica + ", Autori=sconosciuto]";
		}
		
	}

	

}
