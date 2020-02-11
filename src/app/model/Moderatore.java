package app.model;

import java.time.LocalDate;

public class Moderatore extends Utente {
	
	// Attributi
	
	private Integer IDPromotore;
	
	private LocalDate DataPromozione;
	
	private Integer NumeroPubblicazioni;

	
	// Costruttori
	
	public Moderatore(String nickName, String password, Integer ruolo, Boolean eliminato,
			LocalDate dataUltimaRecensione, String email, String cognome, String luogoNascita, String residenza,
			Integer iDPromotore, LocalDate dataPromozione, Integer numeroPubblicazioni, String nome, LocalDate datanascita) {
		super(nickName, password, ruolo, eliminato, dataUltimaRecensione, email, cognome, luogoNascita, residenza, nome, datanascita);
		IDPromotore = iDPromotore;
		DataPromozione = dataPromozione;
		NumeroPubblicazioni = numeroPubblicazioni;
	}

	public Moderatore(Integer iD, String nickName, String password, Integer ruolo, Boolean eliminato,
			LocalDate dataUltimaRecensione, String email, String cognome, String luogoNascita, String residenza,
			Integer iDPromotore, LocalDate dataPromozione, Integer numeroPubblicazioni, String nome, LocalDate datanascita) {
		super(iD, nickName, password, ruolo, eliminato, dataUltimaRecensione, email, cognome, luogoNascita, residenza, nome, datanascita);
		IDPromotore = iDPromotore;
		DataPromozione = dataPromozione;
		NumeroPubblicazioni = numeroPubblicazioni;
	}
	
	public Moderatore(Integer iD, String nickName, Integer NumPubb) {
		super(iD, nickName, null, null, null, null, null, null, null, null, null, null);
		IDPromotore = null;
		DataPromozione = null;
		NumeroPubblicazioni = NumPubb;
	}

	
	// Getters and Setters
	
	public Integer getIDPromotore() {
		return IDPromotore;
	}

	public void setIDPromotore(Integer iDPromotore) {
		IDPromotore = iDPromotore;
	}

	public LocalDate getDataPromozione() {
		return DataPromozione;
	}

	public void setDataPromozione(LocalDate dataPromozione) {
		DataPromozione = dataPromozione;
	}

	public Integer getNumeroPubblicazioni() {
		return NumeroPubblicazioni;
	}

	public void setNumeroPubblicazioni(Integer numeroPubblicazioni) {
		NumeroPubblicazioni = numeroPubblicazioni;
	}

	@Override
	public String toString() {
		return super.toString() + "UtenteModeratore [IDPromotore=" + IDPromotore + ", DataPromozione=" + DataPromozione
				+ ", NumeroPubblicazioni=" + NumeroPubblicazioni + ", getIDPromotore()=" + getIDPromotore()
				+ ", getDataPromozione()=" + getDataPromozione() + ", getNumeroPubblicazioni()="
				+ getNumeroPubblicazioni() + "]";
	}

	
}
