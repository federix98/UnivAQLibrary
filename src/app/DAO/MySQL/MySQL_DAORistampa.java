package app.DAO.MySQL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import app.DAO.interfaces.DAORistampa;
import app.model.Ristampa;

public class MySQL_DAORistampa implements DAORistampa {

	/**
	 * Inserisce una nuova ristampa nel database
	 * 
	 * @param dataRistampa
	 * @param numRistampa
	 * @param IDUtente
	 * @param IDPubblicazione
	 * 
	 */
	@Override
	public void inserisciRistampa(LocalDate dataRistampa, Integer numRistampa, Integer IDUtente, Integer IDPubblicazione) {
		
		Connection conn = null;
		CallableStatement pStmt = null;

		try {

			conn = MySQL_DAOFactory.createConnection();
			System.out.println("Connessione stabilita - inserisciRistampa");
			
			pStmt = conn.prepareCall("{ call inserimentoRistampa(?, ?, ?, ?)}");

			pStmt.setInt(1, IDPubblicazione);
			pStmt.setObject(2, dataRistampa);
			pStmt.setInt(3, numRistampa);
			pStmt.setInt(4, IDUtente);
			
			pStmt.execute();

		} catch (SQLException e) {

			e.printStackTrace();

		}  finally {
		    try { if (pStmt != null) pStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}

	}

	
	/**
	 * Restituisce l'elenco delle ristampe di una pubblicazione
	 * 
	 * @param IDPubblicazione
	 * 
	 * @return Lista delle ristampe della pubblicazione in esame
	 */
	@Override
	public ArrayList<Ristampa> getListaRistampePubblicazione(Integer IDPubblicazione) {
		
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		try {

			conn = MySQL_DAOFactory.createConnection();

			pStmt = conn.prepareStatement("SELECT DISTINCT r.* FROM Ristampa r WHERE r.IDPubblicazione = ? ORDER BY Numero");
			pStmt.setInt(1, IDPubblicazione);
			
			rs = pStmt.executeQuery();

			ArrayList<Ristampa> resultList = new ArrayList<Ristampa>();

			Ristampa r = null;

			while (rs.next()) {

				r = new Ristampa(rs.getInt("ID"), rs.getObject("DataRistampa", LocalDate.class), rs.getInt("Numero"));
				resultList.add(r);

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

	/**
	 * Rimuove la ristampa dalla pubblicazione
	 * 
	 * @param IDRistampa
	 * @param IDPubblicazione
	 * @param IDUtente
	 * 
	 */
	@Override
	public void rimuoviRistampa(Integer IDRistampa, Integer IDPubblicazione, Integer IDUtente) {
		
		Connection conn = null;
		CallableStatement cStmt = null;
		
		try {

			conn = MySQL_DAOFactory.createConnection();
			cStmt = conn.prepareCall("{call delRistampa(?, ?, ?)}");

			cStmt.setInt(1, IDPubblicazione);
			cStmt.setInt(2, IDRistampa);
			cStmt.setInt(3, IDUtente);

			cStmt.execute();
			
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}  finally {
		    try { if (cStmt != null) cStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}


	}


}
