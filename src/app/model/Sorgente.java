package app.model;
/**
 * 
 * @author Federico Di Menna
 *
 */
public class Sorgente {

	
	// Attributi
	
	private Integer ID;
	
	private String URI;
	
	private String Tipo;
	
	private String Formato;
	
	private String Descrizione;
	
	
	// Costruttori
	
	public Sorgente(String URI, String Tipo, String Formato, String Descrizione) {
		
		this.ID = null;
		this.URI = URI;
		this.Tipo = Tipo;
		this.Formato = Formato;
		this.Descrizione = Descrizione;
	
	}
	
	public Sorgente(Integer iD, String uRI, String tipo, String formato, String descrizione) {

		ID = iD;
		URI = uRI;
		Tipo = tipo;
		Formato = formato;
		Descrizione = descrizione;
		
	}

	
	// Getters and Setters


	public Integer getID() {
		return ID;
	}


	public void setID(Integer iD) {
		ID = iD;
	}


	public String getURI() {
		return URI;
	}


	public void setURI(String uRI) {
		URI = uRI;
	}


	public String getTipo() {
		return Tipo;
	}


	public void setTipo(String tipo) {
		Tipo = tipo;
	}


	public String getFormato() {
		return Formato;
	}


	public void setFormato(String formato) {
		Formato = formato;
	}


	public String getDescrizione() {
		return Descrizione;
	}


	public void setDescrizione(String descrizione) {
		Descrizione = descrizione;
	}


	@Override
	public String toString() {
		return "Sorgente [ID=" + ID + ", URI=" + URI + ", Tipo=" + Tipo + ", Formato=" + Formato + ", Descrizione="
				+ Descrizione + ", getID()=" + getID() + ", getURI()=" + getURI() + ", getTipo()=" + getTipo()
				+ ", getFormato()=" + getFormato() + ", getDescrizione()=" + getDescrizione() + "]";
	}
	
	
}
