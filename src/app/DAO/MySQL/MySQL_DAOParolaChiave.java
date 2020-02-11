package app.DAO.MySQL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import app.DAO.interfaces.DAOParolaChiave;
import app.model.ParolaChiave;
/**
 * 
 * @author Federico Di Menna
 *
 */
public class MySQL_DAOParolaChiave implements DAOParolaChiave {

	/**Inserisci una parola chiave in una pubblicazione
	 * 
	 * @param parola
	 * @param IDPubblicazione
	 * @param IDUtente
	 */
	@Override
	public void inserisciParolaChiave(String parola, Integer IDPubblicazione, Integer IDUtente) {
		
		Connection conn = null;

		try {

			conn = MySQL_DAOFactory.createConnection();
			System.out.println("Connessione stabilita - inserisciKW");
			
			PreparedStatement pStmt = conn.prepareStatement("INSERT INTO parolachiave (ParolaChiave) VALUES (?)");

			pStmt.setString(1, parola);
			
			pStmt.execute();

			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	/**
	 * 
	 * @param numPag
	 * 
	 * @return lista delle parole chiave per pagina
	 */
	@Override
	public ArrayList<ParolaChiave> getListaParoleChiave(Integer numPag) {

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = MySQL_DAOFactory.createConnection();
			System.out.println("Connessione stabilita - getListaKW");

			if (numPag == -1) {
				stmt = conn.prepareStatement("SELECT * FROM parolachiave");
			} else {
				stmt = conn.prepareStatement("SELECT * FROM parolachiave LIMIT ?,50");
				stmt.setInt(1, numPag * 50);
			}

			ResultSet rs = stmt.executeQuery();

			ArrayList<ParolaChiave> resultList = new ArrayList<ParolaChiave>();

			ParolaChiave kw = null;

			while (rs.next()) {

				kw = new ParolaChiave(rs.getInt("ID"), rs.getString("ParolaChiave"));
				// System.out.println(s.toString());
				resultList.add(kw);

			}

			conn.close();

			return resultList;

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return null;
	}

	/**
	 * @param IDPubblicazione
	 * 
	 * @return lista delle parole chiave di una pubblicazione
	 */
	@Override
	public ArrayList<ParolaChiave> getListaParoleChiavePubblicazione(Integer IDPubblicazione) {
		
		Connection conn = null;
		PreparedStatement pStmt = null;
		
		try {

			conn = MySQL_DAOFactory.createConnection();

			pStmt = conn.prepareStatement("SELECT DISTINCT p.ID AS ID, p.ParolaChiave FROM parolachiave p INNER JOIN Tag t ON p.ID = t.IDPubblicazione WHERE p.ID = ?");
			pStmt.setInt(1, IDPubblicazione);
			
			ResultSet rs = pStmt.executeQuery();

			ArrayList<ParolaChiave> resultList = new ArrayList<ParolaChiave>();

			ParolaChiave kw = null;

			while (rs.next()) {

				kw = new ParolaChiave(rs.getInt("ID"), rs.getString("ParolaChiave"));
				// System.out.println(s.toString());
				resultList.add(kw);

			}

			conn.close();

			return resultList;

		} catch (SQLException e) {

			e.printStackTrace();

		}
		
		return null;
		
	}

	/**Rimuovi una parola chiave dal sistema
	 * 
	 * @param IDParolaChiave
	 * @param IDUtente
	 * 
	 */
	@Override
	public void rimuoviParolaChiave(Integer IDParolaChiave, Integer IDUtente) {
		// TODO Auto-generated method stub
		Connection conn = null;

		try {

			conn = MySQL_DAOFactory.createConnection();
			
			CallableStatement cStmt = conn.prepareCall("{call delCascadeTag(?, ?)}");
			
			cStmt.setInt(1, IDParolaChiave);
			cStmt.setInt(2, IDUtente);
			
			cStmt.execute();

			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ParolaChiave Where ID = ?");
			
			pstmt.setInt(1, IDParolaChiave);
			
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}
	}

	/**
	 * Inserisci una nuova parola chiave nel sistema
	 * 
	 * @param parola
	 */
	@Override
	public void inserisciParolaChiaveDirect(String parola) {
		
		Connection conn = null;
		
		try {
		
			conn = MySQL_DAOFactory.createConnection();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO parolachiave(ParolaChiave) VALUES (?)");
			stmt.setString(1, parola);
			
			stmt.execute();
			
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Aggiungi la relazione tra parola chiave e pubblicazione nel sistema
	 * 
	 * @param IDParola
	 * @param IDPubblicazione
	 * @param IDUtente
	 */
	@Override
	public void aggiungiTag(Integer IDParola, Integer IDPubblicazione, Integer IDUtente) {
		
		Connection conn = null;

		try {

			conn = MySQL_DAOFactory.createConnection();
			
			CallableStatement pStmt = conn.prepareCall("{ call inserimentoParolaChiave(?, ?, ?)}");

			pStmt.setInt(1, IDPubblicazione);
			pStmt.setInt(2, IDParola);
			pStmt.setInt(3, IDUtente);
			
			pStmt.execute();

			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}
		
	}

	/**
	 * Restituisci la parola chiave data la parola
	 * 
	 * @param parola : string
	 * 
	 * @return ParolaChiave
	 */
	@Override
	public ParolaChiave getParolaChiave(String parola) {
		
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = MySQL_DAOFactory.createConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ParolaChiave WHERE ParolaChiave = ?");
			stmt.setString(1, parola);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				ParolaChiave key = new ParolaChiave(rs.getInt("ID"), rs.getString("ParolaChiave"));
				return key;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (pStmt != null) pStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}

		return null;
		
	}

}
