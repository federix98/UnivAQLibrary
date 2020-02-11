package app.DAO.MySQL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.DAO.interfaces.DAOLike;
/**
 * 
 * @author Federico Di Menna
 *
 */
public class MySQL_DAOLike implements DAOLike {

	/**Inserisci un Like
	 * 
	 * @param IDPubblicazione
	 * @param IDUtente
	 */
	@Override
	public void inserisciLike(Integer IDPubblicazione, Integer IDUtente) {
		
		Connection conn = null;

		try {

			conn = MySQL_DAOFactory.createConnection();
			
			CallableStatement cStmt = conn.prepareCall("{call inserimentoLike(?, ?)}");
			
			cStmt.setInt(1, IDUtente);
			cStmt.setInt(2, IDPubblicazione);
			
			cStmt.execute();
			
			conn.close();
			
		} catch (SQLException e) {

			e.printStackTrace();

		}
		
	}

	/**
	 * 
	 * @param IDPubblicazione
	 * @param IDUtente
	 * 
	 * @return true se esiste un like tra le entità, false altrimenti
	 */
	@Override
	public Boolean getLike(Integer IDPubblicazione, Integer IDUtente) {
		
		Connection conn = null;
		Boolean ret = false;
		
		try {

			conn = MySQL_DAOFactory.createConnection();
			
			PreparedStatement cStmt = conn.prepareStatement("SELECT * FROM progettodb.like WHERE IDPubblicazione = ? AND IDUtente = ?");
			cStmt.setInt(1, IDPubblicazione);
			cStmt.setInt(2, IDUtente);
			
			ResultSet rs = cStmt.executeQuery();
			
			if(rs.next()) {
				ret = true;
			}
			
			conn.close();
			
		} catch (SQLException e) {

			e.printStackTrace();

		}
		
		return ret;
		
	}

	/**
	 * Rimuovi il like
	 * 
	 * @param IDPubblicazione
	 * @param IDUtente
	 */
	@Override
	public void eliminaLike(Integer IDPubblicazione, Integer IDUtente) {
		
		Connection conn = null;

		try {

			conn = MySQL_DAOFactory.createConnection();
			
			CallableStatement cStmt = conn.prepareCall("{call delLike(?, ?)}");
			
			cStmt.setInt(1, IDUtente);
			cStmt.setInt(2, IDPubblicazione);
			
			cStmt.execute();
			
			conn.close();
			
		} catch (SQLException e) {

			e.printStackTrace();

		}
		
	}

	
	
}
