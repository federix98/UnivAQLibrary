package app.DAO.MySQL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import app.DAO.interfaces.DAOAutore;
import app.model.Autore;

/**
 * @author agdimenna
 *
 */
public class MySQL_DAOAutore implements DAOAutore {

	
	
	/**
	 *  Inserisce un nuovo autore nel database.
	 *  
	 *  @param nome : nome dell'autore
	 *  @param cognome : cognome dell'autore
	 * 
	 */
	@Override
	public void inserisciAutore(String nome, String cognome) {
		
		Connection conn = null;

		try {

			conn = MySQL_DAOFactory.createConnection();
			System.out.println("Connessione stabilita - inserisciAutore");
			
			PreparedStatement pStmt = conn.prepareStatement("INSERT INTO Autore (Nome, Cognome) VALUES (?, ?)");

			pStmt.setString(1, nome);
			pStmt.setString(2, cognome);
			
			pStmt.execute();

			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}
		
	}

	/**
	 * Ritorna una lista di 50 autori al massimo in base al numero di pagina
	 *
	 * @param numPag : numero di pagina
	 * 
	 * @return lista di autori
	 */
	@Override
	public ArrayList<Autore> getListaAutori(Integer numPag) {
		
		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = MySQL_DAOFactory.createConnection();
			System.out.println("Connessione stabilita - getListaAutori");

			if (numPag == -1) {
				stmt = conn.prepareStatement("SELECT * FROM Autore");
			} else {
				stmt = conn.prepareStatement("SELECT * FROM Autore LIMIT ?,50");
				stmt.setInt(1, numPag * 50);
			}

			ResultSet rs = stmt.executeQuery();

			ArrayList<Autore> resultList = new ArrayList<Autore>();

			Autore a = null;

			while (rs.next()) {

				a = new Autore(rs.getInt("ID"), rs.getString("Nome"), rs.getString("Cognome"));
				//System.out.println("Autore nuovo: " + a.toString() + " " + rs.getInt("ID") + " " + rs.getString("Nome") + " " + rs.getString("Cognome"));
				resultList.add(a);

			}

			conn.close();

			return resultList;

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return null;
		
	}

	/**
	 * Rimuove un autore dal database chiamando la rispettiva procedura
	 * 
	 * @param IDAutore : ID dell'autore da eliminare
	 * @param IDUtente : ID dell'utente che effettua l'eliminazione
	 * 
	 */
	@Override
	public void rimuoviAutore(Integer IDAutore, Integer IDUtente) {
		
		Connection conn = null;

		try {

			conn = MySQL_DAOFactory.createConnection();
			CallableStatement cStmt = conn.prepareCall("{call delCascadeAutore(?, ?)}");

			cStmt.setInt(2, IDAutore);
			cStmt.setInt(3, IDUtente);

			cStmt.execute();

			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Autore Where ID = ?");
			
			pstmt.setInt(1, IDAutore);
			
			pstmt.execute();
			
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}
		
	}

	/**
	 * Aggiorna gli attributi dell'autore identificato con IDAutore nel database
	 * 
	 * @param IDAutore : ID dell'autore da modificare
	 * @param nome : nuovo nome dell'autore
	 * @param cognome : nuovo cognome dell'autore
	 */
	@Override
	public void aggiornaAutore(Integer IDAutore, String nome, String cognome) {
		
		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = MySQL_DAOFactory.createConnection();

			System.out.println("Connessione stabilita - aggiornaAutore MySQL_DAO");
			
			String query = "INSERT INTO Autore (ID, Nome, Cognome)"
					+ " VALUES (?, ?, ?)"
					+ " ON DUPLICATE KEY UPDATE"
					+ " Nome = VALUES(Nome), Cognome = VALUES(Cognome)";
			//System.out.println(query);
			
			stmt = conn.prepareStatement(query);
			
			stmt.setInt(1, IDAutore);
			stmt.setString(2, nome);
			stmt.setString(3, cognome);
			
			stmt.execute();
			
			conn.close();

			

		} catch (SQLException e) {

			e.printStackTrace();

		}
	}

	/**
	 * Ritorna la lista degli autori di una data pubblicazione
	 * 
	 * @param IDPubblicazione : ID della pubblicazione in esame
	 */
	@Override
	public ArrayList<Autore> getListaAutoriPubblicazione(Integer IDPubblicazione) {

		Connection conn = null;
		ArrayList<Autore> returnList = null;
		
		try {
		
			conn = MySQL_DAOFactory.createConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT a.ID, a.Nome, a.Cognome FROM Pubblicazione p INNER JOIN Scritto s ON p.ID = s.IDPubblicazione INNER JOIN Autore a ON s.IDAutore = a.id WHERE p.ID = ?");
			stmt.setInt(1, IDPubblicazione);
			
			
			ResultSet rs = stmt.executeQuery(); 
			
			returnList = new ArrayList<Autore>();
			
			while(rs.next()) {
				Autore a = new Autore(rs.getInt(1), rs.getString(2), rs.getString(3));
				if(a.getCognome() == null) a.setCognome("");
				if(a.getNome() == null) a.setNome("");
				returnList.add(a);
				//System.out.println(a.toString());
			}
			
			
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnList;
		
	}

	@Override
	public void aggiungiScritto(Integer IDAutore, Integer IDPubblicazione, Integer IDUtente) {
		
		Connection conn = null;

		try {

			conn = MySQL_DAOFactory.createConnection();
			System.out.println("Connessione stabilita - inserisciRistampa");
			
			CallableStatement pStmt = conn.prepareCall("{ call inserimentoAutore(?, ?, ?)}");

			pStmt.setInt(1, IDPubblicazione);
			pStmt.setInt(2, IDAutore);
			pStmt.setInt(3, IDUtente);
			
			pStmt.execute();

			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}
		
	}

	@Override
	public String getListaAutoriPubblicazioneString(Integer IDPubblicazione) {
		// TODO Auto-generated method stub
		return null;
	}

}
