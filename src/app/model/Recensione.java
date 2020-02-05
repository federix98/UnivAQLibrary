package app.model;

import java.time.LocalDateTime;

public class Recensione {

	
	// Attributi
	
	private Integer IDPubblicazione;
	
	private Integer IDUtente;
	
	private String Descrizione;
	
	private Boolean FlagApprovazione;
	
	private Integer ApprovatoDa;
	
	private LocalDateTime Ts;

	
	// Costruttori
	
	public Recensione(Integer iDPubblicazione, Integer iDUtente, String descrizione, Boolean flagApprovazione,
			Integer approvatoDa, LocalDateTime ts) {
		IDPubblicazione = iDPubblicazione;
		IDUtente = iDUtente;
		Descrizione = descrizione;
		FlagApprovazione = flagApprovazione;
		ApprovatoDa = approvatoDa;
		Ts = ts;
	}
	
	public Recensione(Integer iDPubblicazione, Integer iDUtente, String descrizione) {
		IDPubblicazione = iDPubblicazione;
		IDUtente = iDUtente;
		Descrizione = descrizione;
		FlagApprovazione = null;
		ApprovatoDa = null;
		Ts = null;
	}


	// Getters and Setters
	
	public Integer getIDPubblicazione() {
		return IDPubblicazione;
	}


	public void setIDPubblicazione(Integer iDPubblicazione) {
		IDPubblicazione = iDPubblicazione;
	}


	public Integer getIDUtente() {
		return IDUtente;
	}


	public void setIDUtente(Integer iDUtente) {
		IDUtente = iDUtente;
	}


	public String getDescrizione() {
		return Descrizione;
	}


	public void setDescrizione(String descrizione) {
		Descrizione = descrizione;
	}


	public Boolean getFlagApprovazione() {
		return FlagApprovazione;
	}


	public void setFlagApprovazione(Boolean flagApprovazione) {
		FlagApprovazione = flagApprovazione;
	}


	public Integer getApprovatoDa() {
		return ApprovatoDa;
	}


	public void setApprovatoDa(Integer approvatoDa) {
		ApprovatoDa = approvatoDa;
	}


	public LocalDateTime getTs() {
		return Ts;
	}


	public void setTs(LocalDateTime ts) {
		Ts = ts;
	}


	@Override
	public String toString() {
		return "Recensione [IDPubblicazione=" + IDPubblicazione + ", IDUtente=" + IDUtente + ", Descrizione="
				+ Descrizione + ", FlagApprovazione=" + FlagApprovazione + ", ApprovatoDa=" + ApprovatoDa + ", Ts=" + Ts
				+ ", getIDPubblicazione()=" + getIDPubblicazione() + ", getIDUtente()=" + getIDUtente()
				+ ", getDescrizione()=" + getDescrizione() + ", getFlagApprovazione()=" + getFlagApprovazione()
				+ ", getApprovatoDa()=" + getApprovatoDa() + ", getTs()=" + getTs() + "]";
	}
	
}
