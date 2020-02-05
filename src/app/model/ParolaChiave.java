package app.model;

public class ParolaChiave {

	
	// Attributi
	
	private Integer ID;
	
	private String Parola;
	
	
	// Costruttori
	
	public ParolaChiave(String Parola) {
	
		this.setID(null);
		this.setParola(Parola);
	
	}
	
	public ParolaChiave(Integer ID, String Parola) {
		
		this.setID(ID);
		this.setParola(Parola);
	
	}
	
	
	// Getters and Setters

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getParola() {
		return Parola;
	}

	public void setParola(String parola) {
		Parola = parola;
	}

	@Override
	public String toString() {
		return "ParolaChiave [ID=" + ID + ", Parola=" + Parola + ", getID()=" + getID() + ", getParola()=" + getParola()
				+ "]";
	}
	
	
	
}
