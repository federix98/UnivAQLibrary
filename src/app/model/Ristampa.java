package app.model;

import java.time.LocalDate;

public class Ristampa {

	
	// Attributi

	private Integer ID;
	
	private Integer Numero;
	
	private LocalDate DataRistampa;
	
	
	// Costruttori
	
	public Ristampa(LocalDate DataRistampa, Integer Numero) {
		this.setID(null);
		this.setNumero(Numero);
		this.setDataRistampa(DataRistampa);
	}
	
	public Ristampa(Integer ID, LocalDate DataRistampa, Integer Numero) {
		this.setID(ID);
		this.setNumero(Numero);
		this.setDataRistampa(DataRistampa);
	}

	
	// Getters and Setters
	
	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Integer getNumero() {
		return Numero;
	}

	public void setNumero(Integer numero) {
		Numero = numero;
	}

	public LocalDate getDataRistampa() {
		return DataRistampa;
	}

	public void setDataRistampa(LocalDate dataRistampa) {
		DataRistampa = dataRistampa;
	}
	
	
	
	@Override
	public String toString() {
		return "Ristampa [ID=" + ID + ", Numero=" + Numero + ", DataRistampa=" + DataRistampa + ", getID()=" + getID()
				+ ", getNumero()=" + getNumero() + ", getDataRistampa()=" + getDataRistampa() + "]";
	}
	
}
