package app.model;

import java.time.LocalDate;
/**
 * 
 * @author Federico Di Menna
 *
 */
public class Utente {

	
	// Attributi
	
	private Integer ID;
	
	private String NickName;
	
	private String Password;
	
	private Integer Ruolo;
	
	private Boolean Eliminato;
	
	private LocalDate DataUltimaRecensione;
	
	private String Email;
	
	private String Nome;
	
	private String Cognome;
	
	private String LuogoNascita;
	
	private String Residenza;
	
	private LocalDate dataNascita;
	
	
	// Costruttori

	public Utente(String nickName, String password, Integer ruolo, Boolean eliminato, LocalDate dataUltimaRecensione,
			String email, String cognome, String luogoNascita, String residenza, String nome, LocalDate datanascita) {
		this.ID = null;
		NickName = nickName;
		Password = password;
		Ruolo = ruolo;
		Eliminato = eliminato;
		DataUltimaRecensione = dataUltimaRecensione;
		Email = email;
		Nome = nome;
		Cognome = cognome;
		LuogoNascita = luogoNascita;
		Residenza = residenza;
		dataNascita = datanascita;
	}

	public Utente(Integer iD, String nickName, String password, Integer ruolo, Boolean eliminato,
			LocalDate dataUltimaRecensione, String email, String cognome, String luogoNascita, String residenza, String nome, LocalDate datanascita) {
		super();
		ID = iD;
		NickName = nickName;
		Password = password;
		Ruolo = ruolo;
		Eliminato = eliminato;
		DataUltimaRecensione = dataUltimaRecensione;
		Email = email;
		Nome = nome;
		Cognome = cognome;
		LuogoNascita = luogoNascita;
		Residenza = residenza;
		dataNascita = datanascita;
	}

	// Getters and Setters

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getNickName() {
		return NickName;
	}

	public void setNickName(String nickName) {
		NickName = nickName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public Integer getRuolo() {
		return Ruolo;
	}

	public void setRuolo(Integer ruolo) {
		Ruolo = ruolo;
	}

	public Boolean getEliminato() {
		return Eliminato;
	}

	public void setEliminato(Boolean eliminato) {
		Eliminato = eliminato;
	}

	public LocalDate getDataUltimaRecensione() {
		return DataUltimaRecensione;
	}

	public void setDataUltimaRecensione(LocalDate dataUltimaRecensione) {
		DataUltimaRecensione = dataUltimaRecensione;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getCognome() {
		return Cognome;
	}

	public void setCognome(String cognome) {
		Cognome = cognome;
	}

	public String getLuogoNascita() {
		return LuogoNascita;
	}

	public void setLuogoNascita(String luogoNascita) {
		LuogoNascita = luogoNascita;
	}

	public String getResidenza() {
		return Residenza;
	}

	public void setResidenza(String residenza) {
		Residenza = residenza;
	}

	@Override
	public String toString() {
		return "Utente [ID=" + ID + ", NickName=" + NickName + ", Password=" + Password + ", Ruolo=" + Ruolo
				+ ", Eliminato=" + Eliminato + ", DataUltimaRecensione=" + DataUltimaRecensione + ", Email=" + Email
				+ ", Nome=" + Nome + ", Cognome=" + Cognome + ", LuogoNascita=" + LuogoNascita + ", Residenza="
				+ Residenza + ", dataNascita=" + dataNascita + "]";
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public LocalDate getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}
}
