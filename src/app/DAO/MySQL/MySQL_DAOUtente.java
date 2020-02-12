package app.DAO.MySQL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import app.DAO.interfaces.DAOUtente;
import app.model.Moderatore;
import app.model.Utente;
/**
 * 
 * @author Federico Di Menna
 *
 */
public class MySQL_DAOUtente implements DAOUtente{

	/**
	 *  Chiama la procedura di inserimento di un utente sul database
	 *  
	 *  @param nick : nickname dell'utente
	 *  @param email : email dell'utente
	 *  @param password : password dell'utent
	 *  @param nome : nome dell'utente
	 *  @param cognome : cognome dell'utente
	 *  @param datanascita : data di nascita
	 *  @param luogonascita : luogo di nascita
	 *  @param residenza : residenza dell'utente
	 *  
	 *  @return ID del nuovo utente se l'inserimento è andato a buon fine, -1 altrimenti
	 */
	@Override
	public Integer inserisciUtente(String nick, String email, String password, String nome, String cognome,
			LocalDate datanascita, String luogonascita, String residenza) {
		
		Connection conn = null;
		CallableStatement pStmt = null;

		try {

			conn = MySQL_DAOFactory.createConnection();
			
			pStmt = conn.prepareCall("{call inserimentoUtente(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
			
			pStmt.registerOutParameter(9, java.sql.Types.INTEGER);

			pStmt.setString(1, nick);
			pStmt.setString(2, email);
			pStmt.setString(3, password);
			pStmt.setString(4, nome);
			pStmt.setString(5, cognome);
			pStmt.setObject(6, datanascita, java.sql.Types.DATE);
			pStmt.setString(7, luogonascita);
			pStmt.setString(8, residenza);
			
			pStmt.execute();

			Integer ret = pStmt.getInt(9);

			return ret;
			
		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
		    try { if (pStmt != null) pStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
		return -1;
		
	}

	/**
	 * Ritorna la lista degli utenti presenti nel database
	 * 
	 * @return una lista di utenti, null se qualcosa va storto o non è presente nessun utente
	 * 
	 */
	@Override
	public ArrayList<Utente> getListaUtenti() {
		
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		try {

			conn = MySQL_DAOFactory.createConnection();
			
			pStmt = conn.prepareStatement("SELECT * FROM utente");
			
			rs = pStmt.executeQuery();
			ArrayList<Utente> res = new ArrayList<Utente>();
			
			while(rs.next()) {
				res.add(new Utente(rs.getInt("ID"), rs.getString("Nickname"), rs.getString("Password"), rs.getInt("Ruolo"), rs.getBoolean("UtenteEliminato"), rs.getObject("DataUltimaRecensione", LocalDate.class), null, null, null, null, null, null));
			}
			
			return res;

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (pStmt != null) pStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
		return null;
		
		
	}

	/**
	 * Restituisce l'oggetto utente dall'ID
	 * 
	 * @param IDUtente : ID dell'utente da recuperare
	 * 
	 * @return Oggetto Utente 
	 */
	public Utente getUtente(Integer IDUtente) {
		
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;

		try {

			conn = MySQL_DAOFactory.createConnection();
			
			pStmt = conn.prepareStatement("SELECT * FROM UTENTE u LEFT JOIN DATIUTENTE du ON u.ID = du.IDUtente WHERE u.ID = ?");

			pStmt.setInt(1, IDUtente);
			
			rs = pStmt.executeQuery();
			
			Utente u = null;
			
			if(rs.next()) {
				u = new Utente(IDUtente, 
						rs.getString("Nickname"), // Nickname
						rs.getString("Password"), // Password
						rs.getInt("Ruolo"), // Ruolo
						rs.getBoolean("UtenteEliminato"),// Eliminato
						rs.getObject("DataUltimaRecensione", LocalDate.class),// Dataultimarec
						rs.getString("Email"),// Email
						rs.getString("Cognome"),// cognome
						rs.getString("LuogoNascita"),// luogonascita
						rs.getString("Residenza"),// residenza
						rs.getString("Nome"),// nome
						rs.getObject("DataNascita", LocalDate.class) // datanascita
						);
				IDUtente = rs.getInt(1);
			}
			
			return u;

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (pStmt != null) pStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
		return null;
	}

	/**
	 * Restituisce tutte le province italiane
	 * 
	 * @return Lista delle province
	 */
	@Override
	public ArrayList<String> getProvince() {
		
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;

		try {

			conn = MySQL_DAOFactory.createConnection();
			
			pStmt = conn.prepareStatement("SELECT * FROM Province");
			
			rs = pStmt.executeQuery();
			ArrayList<String> res = new ArrayList<String>();
			
			while(rs.next()) res.add(rs.getString(2));

			conn.close();
			
			return res;

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (pStmt != null) pStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
		return null;
		
	}

	/**
	 * Ritorna la lista degli utenti più collaborativi
	 * 
	 * @return Lista utenti più collaborativi
	 */
	@Override
	public ArrayList<Moderatore> getUtentiCollaborativi() {
		
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;

		try {

			conn = MySQL_DAOFactory.createConnection();
			
			pStmt = conn.prepareStatement("SELECT u.*, d.*, m.* FROM utente u INNER JOIN datiutente d ON u.ID = d.IDUtente INNER JOIN datimoderatore m ON u.ID = m.IDUtente ORDER BY NumPubb DESC LIMIT 30");
			
			rs = pStmt.executeQuery();
			ArrayList<Moderatore> res = new ArrayList<Moderatore>();
			
			while(rs.next()) {
				res.add(new Moderatore(rs.getInt("ID"), rs.getString("Nickname"), rs.getInt("NumPubb")));
			}
			
			return res;

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (pStmt != null) pStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
		return null;
	}

	/**
	 * Effettua la promozione di un utente da utente base a moderatore
	 * 
	 * @param IDUtente : id dell'utente che deve essere promosso
	 * @param IDPromotore : id dell'amministratore che effettua la promozione
	 * 
	 */
	@Override
	public void promuoviUtenteAModeratore(Integer IDUtente, Integer IDPromotore) {
		
		Connection conn = null;
		CallableStatement pStmt = null;

		try {

			conn = MySQL_DAOFactory.createConnection();
			
			pStmt = conn.prepareCall("{call promModer(?, ?)}");

			pStmt.setInt(1, IDPromotore);
			pStmt.setInt(2, IDUtente);
			
			
			pStmt.execute();
			
		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			try { if (pStmt != null) pStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
	}

	/**
	 * Effettua la promozione di un utente moderatore a amministratore
	 * 
	 * @param IDUtente : id dell'utente che deve essere promosso
	 * 
	 */
	public void promuoviUtenteAdAmministratore(Integer IDUtente) {
		
		Connection conn = null;
		PreparedStatement pStmt = null;
		
		try {

			conn = MySQL_DAOFactory.createConnection();
			
			pStmt = conn.prepareStatement("UPDATE Utente SET Ruolo = 4 WHERE ID = ?");

			pStmt.setInt(1, IDUtente);
			
			pStmt.execute();
			
		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			try { if (pStmt != null) pStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
	}
	
	/**
	 * Effettua la retrocessione di un utente da amministratore a moderatore
	 * 
	 * @param IDUtente : id dell'utente che deve essere degradato
	 */
	@Override
	public void degradaAModeratore(Integer IDUtente) {
		
		Connection conn = null;
		PreparedStatement pStmt = null;
		
		if(getUtente(IDUtente).getRuolo() <= Utente.getIdFromRuolo("Moderatore")) return;

		try {

			conn = MySQL_DAOFactory.createConnection();
			
			pStmt = conn.prepareStatement("UPDATE Utente SET Ruolo = 3 WHERE ID = ?");

			pStmt.setInt(1, IDUtente);
			
			pStmt.execute();
			
		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			try { if (pStmt != null) pStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
	}

	/**
	 * Ritorna l'ID dell'utente dato il proprio username
	 * 
	 * @param Username : stringa username dell'utente
	 * 
	 * @return ID dell'utente, null se non presente
	 */
	@Override
	public Integer getIDUtenteFromUsername(String Username) {
		
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;

		try {

			conn = MySQL_DAOFactory.createConnection();
			
			pStmt = conn.prepareStatement("SELECT ID FROM Utente WHERE Nickname = ?");

			pStmt.setString(1, Username);
			
			rs = pStmt.executeQuery();
			
			Integer IDUtente = null;
			
			if(rs.next()) {
				IDUtente = rs.getInt(1);
			}
			
			return IDUtente;

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (pStmt != null) pStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
		return null;
		
	}

	/**Aggiorna i metadati dell'utente
	 * 
	 * @param ID
	 * @param nick
	 * @param email
	 * @param password
	 * @param nome
	 * @param cognome
	 * @param datanascita
	 * @param luogonascita
	 * @param residenza
	 */
	@Override
	public void aggiornaUtente(Integer ID, String nick, String email, String password, String nome, String cognome,
			LocalDate datanascita, String luogonascita, String residenza) {
		
		Connection conn = null;
		PreparedStatement pStmt = null;

		try {

			conn = MySQL_DAOFactory.createConnection();
			
			pStmt = conn.prepareStatement("INSERT INTO datiutente( `IDUtente`, `Nome`, `Cognome`, `DataNascita`, `LuogoNascita`, `Residenza`,`Email`) \r\n" + 
					"VALUES (?, ?, ?, ?, ?, ?, ?) \r\n" + 
					"ON DUPLICATE KEY UPDATE `Nome`=VALUES(`Nome`), `Cognome`=VALUES(`Cognome`), `DataNascita`=VALUES(`DataNascita`),\r\n" + 
					"`LuogoNascita`=VALUES(`LuogoNascita`), `Residenza`=VALUES(`Residenza`), `Email`=VALUES(`Email`);");
			
			/*
			 * DA MODIFICARE
			 * */
			pStmt.setInt(1, ID);
			pStmt.setString(2, nome);
			pStmt.setString(3, cognome);
			pStmt.setObject(4, datanascita, java.sql.Types.DATE);
			pStmt.setString(5, luogonascita);
			pStmt.setString(6, residenza);
			pStmt.setString(7, email);
			
			pStmt.execute();
			
		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
		    try { if (pStmt != null) pStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
	}

	/**Aggiorna la password dell'utente
	 * 
	 * @param hashedPassword
	 * @param IDUtente
	 */
	@Override
	public void aggiornaPassword(String hashedPassword, Integer IDUtente) {
		
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;

		try {

			conn = MySQL_DAOFactory.createConnection();
			
			pStmt = conn.prepareStatement("UPDATE utente SET Password = ? WHERE ID = ?");

			pStmt.setString(1, hashedPassword);
			pStmt.setInt(2, IDUtente);
			
			pStmt.execute();
			
		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (pStmt != null) pStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
	}
	
}
