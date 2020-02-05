package app.DAO.MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import app.DAO.DAOFactory;
import app.DAO.interfaces.DAOAutore;
import app.DAO.interfaces.DAOLike;
import app.DAO.interfaces.DAOLog;
import app.DAO.interfaces.DAOParolaChiave;
import app.DAO.interfaces.DAOPubblicazione;
import app.DAO.interfaces.DAORecensione;
import app.DAO.interfaces.DAORistampa;
import app.DAO.interfaces.DAOSorgente;
import app.DAO.interfaces.DAOUtente;

public class MySQL_DAOFactory extends DAOFactory {

	/** la classe driver */
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    /** L'url al database */
    public static final String DBURL = "jdbc:mysql://localhost:3306/progettodb?serverTimezone=UTC";
    /** Lo username per le operazioni sul DB  */
    public static final String USER = "fede";
    /** La password per le operazioni sul DB */
    public static final String PASS = "tepgogomvi";
    
    /**
     * Metodo per creare una connessione sul DB MySQL
     * 
     * @return l'oggetto Connection.
     */
    public static Connection createConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    /**
     * Metodo per restituire un oggetto DAOSorgente compatibile con MySQL
     * 
     * @return l'oggetto DAOSorgente implementato per il DBMS MySQL.
     */
    @Override
	public DAOSorgente getDAOSorgente() {
		return new MySQL_DAOSorgente();
	}

    /**
     * Metodo per restituire un oggetto DAORistampa compatibile con MySQL
     * 
     * @return l'oggetto DAORistampa implementato per il DBMS MySQL.
     */
	@Override
	public DAORistampa getDAORistampa() {
		return new MySQL_DAORistampa();
	}

	/**
     * Metodo per restituire un oggetto DAOAutore compatibile con MySQL
     * 
     * @return l'oggetto DAOAutore implementato per il DBMS MySQL.
     */
	@Override
	public DAOAutore getDAOAutore() {
		return new MySQL_DAOAutore();
	}

	/**
     * Metodo per restituire un oggetto DAOParolaChiave compatibile con MySQL
     * 
     * @return l'oggetto DAOParolaChiave implementato per il DBMS MySQL.
     */
	@Override
	public DAOParolaChiave getDAOParolaChiave() {
		return new MySQL_DAOParolaChiave();
	}
	
	/**
     * Metodo per restituire un oggetto DAOPubblicazione compatibile con MySQL
     * 
     * @return l'oggetto DAOPubblicazione implementato per il DBMS MySQL.
     */
	@Override
	public DAOPubblicazione getDAOPubblicazione() {
		return new MySQL_DAOPubblicazione();
	}

	/**
     * Metodo per restituire un oggetto DAOUtente compatibile con MySQL
     * 
     * @return l'oggetto DAOUtente implementato per il DBMS MySQL.
     */
	@Override
	public DAOUtente getDAOUtente() {
		return new MySQL_DAOUtente();
	}

	/**
     * Metodo per restituire un oggetto DAORecensione compatibile con MySQL
     * 
     * @return l'oggetto DAORecensione implementato per il DBMS MySQL.
     */
	@Override
	public DAORecensione getDAORecensione() {
		return new MySQL_DAORecensione();
	}
	
	/**
     * Metodo per restituire un oggetto DAOLike compatibile con MySQL
     * 
     * @return l'oggetto DAOLike implementato per il DBMS MySQL.
     */
	@Override
	public DAOLike getDAOLike() {
		return new MySQL_DAOLike();
	}

	/**
     * Metodo per restituire un oggetto DAOLog compatibile con MySQL
     * 
     * @return l'oggetto DAOLog implementato per il DBMS MySQL.
     */
	@Override
	public DAOLog getDAOLog() {
		return new MySQL_DAOLog();
	}


}
