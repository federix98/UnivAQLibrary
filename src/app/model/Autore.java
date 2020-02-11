package app.model;
/**
 * 
 * @author Federico Di Menna
 *
 */
public class Autore {

	// Attributi Autore
	
	private Integer ID;
	
	private String nome;
	
	private String cognome;
	
	
	
	/**
	 * Costruttore
	 * @param nome
	 */
	public Autore(String nome) {
		this.setNome(nome);
		this.setCognome("");
	}
	
	/**
	 * Costruttore
	 * @param nome
	 * @param cognome
	 */
	public Autore(String nome, String cognome) {
		this.setNome(nome);
		this.setCognome(cognome);
	}
	
	/**
	 * Costruttore
	 * @param ID
	 * @param nome
	 * @param cognome
	 */
	public Autore(Integer ID, String nome, String cognome) {
		this.setID(ID);
		this.setCognome(cognome);
		this.setNome(nome);
	}

	
	// Getters and Setters
	
	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	
	
	@Override
	public String toString() {
		return nome + " " + cognome;
	}

}
