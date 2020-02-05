package app.model;

import java.time.LocalDateTime;

public class Log {

	
	// Attributi
	
	private Integer ID;
	
	private Integer IDPubblicazione;
	
	private Integer IDUtente;
	
	private LocalDateTime Ts;
	
	private String Descrizione;
	
	private TipoStoria Tipostoria;

	
	// Costruttore
	
	public Log(Integer iDPubblicazione, Integer iDUtente, LocalDateTime ts, String descrizione,
			TipoStoria tipostoria) {
		super();
		IDPubblicazione = iDPubblicazione;
		IDUtente = iDUtente;
		Ts = ts;
		Descrizione = descrizione;
		Tipostoria = tipostoria;
	}

	public Log(Integer iD, Integer iDPubblicazione, Integer iDUtente, LocalDateTime ts, String descrizione,
			TipoStoria tipostoria) {
		super();
		ID = iD;
		IDPubblicazione = iDPubblicazione;
		IDUtente = iDUtente;
		Ts = ts;
		Descrizione = descrizione;
		Tipostoria = tipostoria;
	}

	
	// Getters and Setters
	
	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

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

	public LocalDateTime getTs() {
		return Ts;
	}

	public void setTs(LocalDateTime ts) {
		Ts = ts;
	}

	public String getDescrizione() {
		return Descrizione;
	}

	public void setDescrizione(String descrizione) {
		Descrizione = descrizione;
	}

	public TipoStoria getTipostoria() {
		return Tipostoria;
	}

	public void setTipostoria(TipoStoria tipostoria) {
		Tipostoria = tipostoria;
	}
	
}
