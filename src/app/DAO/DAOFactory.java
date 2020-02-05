package app.DAO;

import app.DAO.MySQL.MySQL_DAOFactory;
import app.DAO.interfaces.DAOAutore;
import app.DAO.interfaces.DAOLike;
import app.DAO.interfaces.DAOLog;
import app.DAO.interfaces.DAOParolaChiave;
import app.DAO.interfaces.DAOPubblicazione;
import app.DAO.interfaces.DAORecensione;
import app.DAO.interfaces.DAORistampa;
import app.DAO.interfaces.DAOSorgente;
import app.DAO.interfaces.DAOUtente;

public abstract class DAOFactory {

	/** Membro statico per la factory MySQL */
    public static final int MYSQL = 0;  
    
    // OTHERS IMPLEMENTATIONS
 
    /** Metodi statici per le classi DAO */
    public abstract DAOSorgente getDAOSorgente();
    public abstract DAORistampa getDAORistampa();
    public abstract DAOAutore getDAOAutore();
    public abstract DAOParolaChiave getDAOParolaChiave();
    public abstract DAOPubblicazione getDAOPubblicazione();
    public abstract DAOUtente getDAOUtente();
    public abstract DAORecensione getDAORecensione();
    public abstract DAOLike getDAOLike();
    public abstract DAOLog getDAOLog();
 
 
    /**
     * Metodo Factory
     * 
     * @param database
     *            il database da scegliere
     * @return la factory corrispondente
     */
    public static DAOFactory getDAOFactory(int database) {
        switch (database) {
        case MYSQL:
            return new MySQL_DAOFactory();
        default:
            return null;
        }
    }
	


	
	
}
