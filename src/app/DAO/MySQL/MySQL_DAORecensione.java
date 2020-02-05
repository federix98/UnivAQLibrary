package app.DAO.MySQL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import app.DAO.interfaces.DAORecensione;
import app.model.Recensione;

public class MySQL_DAORecensione implements DAORecensione {

	/**
	 * Inserisce una recensione nel database
	 * 
	 * @param IDPubblicazione : pubblicazione di riferimento della recensione
	 * @param IDUtente : ID utente che ha scritto la recensione
	 * @param Testo
	 * 
	 */
	@Override
	public void inserisciRecensione(Integer IDPubblicazione, Integer IDUtente, String Testo) {
		
		Connection conn = null;
		CallableStatement cStmt = null;
		
		try {

			conn = MySQL_DAOFactory.createConnection();
			
			cStmt = conn.prepareCall("{call inserimentoRecensione(?, ?, ?)}");
			
			cStmt.setInt(1, IDUtente);
			cStmt.setInt(2, IDPubblicazione);
			cStmt.setString(3, Testo);
			
			cStmt.execute();
			
		} catch (SQLException e) {

			e.printStackTrace();

		}  finally {
		    try { if (cStmt != null) cStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}

		
	}

	/**
	 * Approvazione di una recensione
	 * 
	 * @param IDPubblicazione : pubblicazione di riferimento della recensione
	 * @param IDUtente : Utente che ha scritto la recensione
	 * @param IDApprovatore : Utente che sta approvando la recensione
	 * 
	 */
	@Override
	public void approvaRecensione(Integer IDPubblicazione, Integer IDUtente, Integer IDApprovatore) {
		
		Connection conn = null;
		CallableStatement cStmt = null;
		
		try {

			conn = MySQL_DAOFactory.createConnection();
			
			cStmt = conn.prepareCall("{call ApprovazioneRecensione(?, ?, ?)}");
			
			cStmt.setInt(1, IDApprovatore);
			cStmt.setInt(2, IDPubblicazione);
			cStmt.setInt(3, IDUtente);
			
			cStmt.execute();
			
		} catch (SQLException e) {

			e.printStackTrace();

		}  finally {
		    try { if (cStmt != null) cStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}

		
	}

	/**
	 * Elenco delle recensioni in fase di approvazione
	 * 
	 * @return lista delle recensioni da approvare
	 */
	@Override
	public ArrayList<Recensione> getRecensioniDaApprovare() {
		
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		try {

			conn = MySQL_DAOFactory.createConnection();
			
			pStmt = conn.prepareStatement("SELECT * FROM Recensione WHERE FlagApprovazione = 0");
			
			rs = pStmt.executeQuery();
			
			ArrayList<Recensione> listaRec = new ArrayList<Recensione>();
			while(rs.next()) {
				listaRec.add(new Recensione(rs.getInt("IDPubblicazione"), rs.getInt("IDUtente"), rs.getString("Descrizione"), rs.getBoolean("FlagApprovazione"), null, rs.getObject("Timestamp", LocalDateTime.class)));
			}
			
			return listaRec;
			
		} catch (SQLException e) {

			e.printStackTrace();

		}  finally {
			try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (pStmt != null) pStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}

		
		return null;
		
	}

	/**
	 * Elenco delle recensioni approvate in una pubblicazione
	 * 
	 * @param IDPubblicazione : id della pubblicazione in esame
	 * 
	 * @return lista delle recensioni approvate della pubblicazione
	 */
	@Override
	public ArrayList<Recensione> getRecensioniApprovatePubblicazione(Integer IDPubblicazione) {
		
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;

		try {

			conn = MySQL_DAOFactory.createConnection();
			
			pStmt = conn.prepareStatement("SELECT * FROM Recensione WHERE FlagApprovazione = 1 AND IDPubblicazione = ?");
			
			pStmt.setInt(1, IDPubblicazione);
			rs = pStmt.executeQuery();
			
			ArrayList<Recensione> listaRec = new ArrayList<Recensione>();
			while(rs.next()) {
				listaRec.add(new Recensione(rs.getInt("IDPubblicazione"), rs.getInt("IDUtente"), rs.getString("Descrizione"), rs.getBoolean("FlagApprovazione"), rs.getInt("ApprovatoDa"), rs.getObject("Timestamp", LocalDateTime.class)));
			}
			
			return listaRec;
			
		} catch (SQLException e) {

			e.printStackTrace();

		}  finally {
			try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (pStmt != null) pStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}

		
		return null;
		
	}

}
