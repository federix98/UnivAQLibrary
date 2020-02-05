package app.DAO.MySQL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import app.DAO.interfaces.DAOSorgente;
import app.model.Sorgente;

/**
 * @author Federico Di Menna
 *
 */

public class MySQL_DAOSorgente implements DAOSorgente {

	/**
	 * Inserisce una nuova sorgente nel DB
	 * 
	 * @param sorgente        : sorgente da inserire,
	 * @param IDPubblicazione : Id della pubblicazione alla quale riferisce la
	 *                        sorgente
	 * @param idUtente        : Id dell'utente che ha fatto l'inserimento
	 * 
	 * @return Un Intero : 1 - Inserimento effettuato 0 - Inserimento non effettuato
	 */
	@Override
	public Integer inserisciSorgente(String URI, String Tipo, String Formato, String Descrizione,
			Integer IDPubblicazione, Integer idUtente) {

		Connection conn = null;
		CallableStatement cStmt = null;

		try {

			conn = MySQL_DAOFactory.createConnection();
			cStmt = conn.prepareCall("{call inserimentoSorgente(?, ?, ?, ?, ?, ?)}");

			cStmt.setInt(1, IDPubblicazione);
			cStmt.setString(2, URI);
			cStmt.setString(3, Tipo);
			cStmt.setString(4, Formato);
			cStmt.setString(5, Descrizione);
			cStmt.setInt(6, idUtente);

			cStmt.execute();

			Integer res = cStmt.getUpdateCount();

			return res;

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			try { if (cStmt != null) cStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}

		return 0;

	}

	/**
	 * Ritorna la lista delle sorgenti nel DB dato il numero di pagina
	 * 
	 * @param numPag : numero di pagina , se -1 restituisce tutto
	 * 
	 * @return La lista dei sorgenti
	 */
	@Override
	public ArrayList<Sorgente> getListaSorgenti(Integer numPag) {

		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		try {

			conn = MySQL_DAOFactory.createConnection();
			System.out.println("Connessione stabilita");

			if (numPag == -1) {
				pStmt = conn.prepareStatement("SELECT * FROM Sorgente");
			} else {
				pStmt = conn.prepareStatement("SELECT * FROM Sorgente LIMIT ?,50");
				pStmt.setInt(1, numPag * 50);
			}

			rs = pStmt.executeQuery();

			ArrayList<Sorgente> result = new ArrayList<Sorgente>();

			Sorgente s = null;

			while (rs.next()) {

				s = new Sorgente(rs.getInt("ID"), rs.getString("URI"), rs.getString("Tipo"), rs.getString("Formato"), rs.getString("Descrizione"));
				result.add(s);

			}

			return result;

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
	 * Rimuove una sorgente dalla pubblicazione
	 * 
	 * @param IDsorgente : sorgente da rmuovere
	 * @param IDPubblicazione : id della pubblcazione di riferimento
	 * @param IDUtente : id dell'utente che compie l'eliminazione
	 * 
	 */
	@Override
	public void rimuoviSorgente(Integer IDSorgente, Integer IDPubblicazione, Integer IDUtente) {

		Connection conn = null;
		CallableStatement cStmt = null;

		try {

			conn = MySQL_DAOFactory.createConnection();
			cStmt = conn.prepareCall("{call delSorgente(?, ?, ?)}");

			cStmt.setInt(1, IDPubblicazione);
			cStmt.setInt(2, IDSorgente);
			cStmt.setInt(3, IDUtente);

			cStmt.execute();

			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			try { if (cStmt != null) cStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
	}

	/**
	 * Ritorna la lista dei sorgenti della pubblicazione in esame
	 * 
	 * @param IDPubblicazione : ID della pubblicazione
	 * 
	 * @return lista dei sorgenti della pubblicazione
	 */
	@Override
	public ArrayList<Sorgente> getListaSorgentiPubblicazione(Integer IDPubblicazione) {
	
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		try {

			conn = MySQL_DAOFactory.createConnection();

			pStmt = conn.prepareStatement("SELECT DISTINCT s.* FROM Sorgente s WHERE s.IDPubblicazione = ?");
			pStmt.setInt(1, IDPubblicazione);
			
			rs = pStmt.executeQuery();

			ArrayList<Sorgente> resultList = new ArrayList<Sorgente>();

			Sorgente kw = null;

			while (rs.next()) {

				kw = new Sorgente(rs.getInt("ID"), rs.getString("URI"), rs.getString("Tipo"), rs.getString("Formato"), rs.getString("Descrizione"));
				resultList.add(kw);

			}

			return resultList;

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
