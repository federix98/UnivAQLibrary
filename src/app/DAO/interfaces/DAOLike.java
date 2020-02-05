package app.DAO.interfaces;

public interface DAOLike {

	public void inserisciLike(Integer IDPubblicazione, Integer IDUtente);
	
	public Boolean getLike(Integer IDPubblicazione, Integer IDUtente);
	
	public void eliminaLike(Integer IDPubblicazione, Integer IDUtente);
	
}
