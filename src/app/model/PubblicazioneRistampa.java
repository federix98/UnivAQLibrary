package app.model;

import java.time.LocalDate;
import java.util.ArrayList;
/**
 * 
 * @author Federico Di Menna
 *
 */
public class PubblicazioneRistampa extends Pubblicazione {

	private LocalDate DataUltimaRistampa;
	
	public PubblicazioneRistampa(Integer iD, String iSBN, String titolo, String editore, Integer numLike,
			Integer numRec, LocalDate dataPubblicazione, LocalDate dataUltimaModifica, ArrayList<Autore> autori, LocalDate DataUltimaRistampa) {
		super(iD, iSBN, titolo, editore, numLike, numRec, dataPubblicazione, dataUltimaModifica, autori);
		this.setDataUltimaRistampa(DataUltimaRistampa);
	}

	public LocalDate getDataUltimaRistampa() {
		return DataUltimaRistampa;
	}

	public void setDataUltimaRistampa(LocalDate dataUltimaRistampa) {
		DataUltimaRistampa = dataUltimaRistampa;
	}

	
	
}
