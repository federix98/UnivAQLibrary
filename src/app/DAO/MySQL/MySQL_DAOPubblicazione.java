package app.DAO.MySQL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import app.DAO.interfaces.DAOPubblicazione;
import app.model.Pubblicazione;
import app.model.PubblicazioneCompleta;
import app.model.PubblicazioneRistampa;

public class MySQL_DAOPubblicazione implements DAOPubblicazione {

	/**
	 * Inserisce una nuova pubblicazione
	 * 
	 * @param ISBN
	 * @param titolo
	 * @param editore
	 * @param dataPubblicazione
	 * @param nPag
	 * @param lingua
	 * @param descrizione
	 * @param indice
	 * @param IDUtente
	 * 
	 * @return ID della pubblicazione inserita, -1 altrimenti
	 * 
	 */
	@Override
	public Integer inserisciPubblicazione(String ISBN, String titolo, String editore, LocalDate dataPubblicazione, Integer nPag,
			String lingua, String descrizione, String indice, Integer IDUtente) {
		
		Connection conn = null;
		CallableStatement cStmt = null;

		try {

			conn = MySQL_DAOFactory.createConnection();
			
			cStmt = conn.prepareCall("{call inserimentoPubb(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
			
			cStmt.setInt(1, IDUtente);
			cStmt.setString(2, ISBN);
			cStmt.setString(3, titolo);
			cStmt.setString(4, editore);
			cStmt.setObject(5, dataPubblicazione);
			cStmt.setInt(6, nPag);
			cStmt.setString(7, lingua);
			cStmt.setString(8, descrizione);
			cStmt.setString(9, indice);
			
			cStmt.registerOutParameter(10, java.sql.Types.INTEGER);
			
			cStmt.execute();
			
			Integer ret = cStmt.getInt(10);

			return ret;
			
		} catch (SQLException e) {

			e.printStackTrace();

		}  finally {
		    try { if (cStmt != null) cStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}

		
		return -1;

	}

	/**
	 * Restituisce l'elenco delle pubblicazioni in base alla pagina
	 * 
	 * @param NumeroPagina
	 */
	@Override
	public ArrayList<Pubblicazione> getListaPubblicazioni(Integer NumeroPagina) {
		
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		Integer valueLimit = (NumeroPagina - 1) * 50;

		try {

			conn = MySQL_DAOFactory.createConnection();

			pStmt = conn.prepareStatement("SELECT * FROM Pubblicazione LIMIT ?,50");
			pStmt.setInt(1, valueLimit);

			rs = pStmt.executeQuery();

			ArrayList<Pubblicazione> resultList = new ArrayList<Pubblicazione>();

			Pubblicazione p = null;

			while (rs.next()) {

				Integer IDPub = rs.getInt(1);
				
				p = new Pubblicazione(IDPub, rs.getString("ISBN"), rs.getString("Titolo"), rs.getString("Editore"),
						rs.getInt("NumLike"), rs.getInt("NumRec"), rs.getObject("DataPubblicazione", LocalDate.class), rs.getObject("DataUltimaModifica", LocalDate.class), new MySQL_DAOAutore().getListaAutoriPubblicazione(IDPub));
				resultList.add(p);

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
	 * Dato un autore, restituisce l'elenco delle pubblicazioni scritte da quell'autore
	 * 
	 * @param IDAutore
	 * 
	 * @return lista delle pubblicazioni scritte da quell'autore
	 * 
	 */
	@Override
	public ArrayList<Pubblicazione> getListaPubblicazioniAutore(Integer IDAutore) {

		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;

		try {

			conn = MySQL_DAOFactory.createConnection();
			//System.out.println("Connessione stabilita - getListaRistampe");

			pStmt = conn.prepareStatement("SELECT P.* FROM Pubblicazione p "
					+ "INNER JOIN Scritto s on p.ID = s.IDPubblicazione "
					+ "WHERE s.IDAutore = ?");
			
			pStmt.setInt(1, IDAutore);

			rs = pStmt.executeQuery();

			ArrayList<Pubblicazione> resultList = new ArrayList<Pubblicazione>();

			Pubblicazione p = null;

			while (rs.next()) {

				Integer IDPub = rs.getInt(1);
				
				p = new Pubblicazione(IDPub, rs.getString("ISBN"), rs.getString("Titolo"), rs.getString("Editore"),
						rs.getInt("NumLike"), rs.getInt("NumRec"), rs.getObject("DataPubblicazione", LocalDate.class), rs.getObject("DataUltimaModifica", LocalDate.class), new MySQL_DAOAutore().getListaAutoriPubblicazione(IDPub));
				resultList.add(p);

			}

			return resultList;

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
	 * Elenco delle pubblicazioni con una parola chiave
	 * 
	 * @param Parola
	 * 
	 * @return lista delle pubblicazioni contenenti la parola chiave
	 */
	@Override
	public ArrayList<Pubblicazione> getListaPubblicazioniParolaChiave(Integer IDParola) {
		
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;

		try {

			conn = MySQL_DAOFactory.createConnection();

			pStmt = conn.prepareStatement("SELECT P.* FROM Pubblicazione p INNER JOIN Tag t on p.ID = t.IDPubblicazione WHERE t.IDParolaChiave = ?");
			
			pStmt.setInt(1, IDParola);

			rs = pStmt.executeQuery();

			ArrayList<Pubblicazione> resultList = new ArrayList<Pubblicazione>();

			Pubblicazione p = null;

			while (rs.next()) {

				Integer IDPub = rs.getInt(1);
				
				p = new Pubblicazione(IDPub, rs.getString("ISBN"), rs.getString("Titolo"), rs.getString("Editore"),
						rs.getInt("NumLike"), rs.getInt("NumRec"), rs.getObject("DataPubblicazione", LocalDate.class), rs.getObject("DataUltimaModifica", LocalDate.class), new MySQL_DAOAutore().getListaAutoriPubblicazione(IDPub));
				resultList.add(p);

			}

			return resultList;

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
	 * Elenco delle pubblicazioni inserite da un utente
	 * 
	 * @param IDUtente
	 * 
	 * @return lista delle pubblicazioni inserite da un utente
	 */
	@Override
	public ArrayList<Pubblicazione> getListaPubblicazioneUtente(Integer IDUtente) {
		
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;

		try {

			conn = MySQL_DAOFactory.createConnection();

			pStmt = conn.prepareStatement("SELECT DISTINCT P.ID, P.* FROM Pubblicazione p INNER JOIN Storia s ON p.ID = s.IDPubblicazione WHERE S.IDUtente = ?");
			
			pStmt.setInt(1, IDUtente);

			rs = pStmt.executeQuery();

			ArrayList<Pubblicazione> resultList = new ArrayList<Pubblicazione>();

			Pubblicazione p = null;

			while (rs.next()) {
				
				Integer IDPub = rs.getInt(1);
				
				p = new Pubblicazione(IDPub, rs.getString("ISBN"), rs.getString("Titolo"), rs.getString("Editore"),
						rs.getInt("NumLike"), rs.getInt("NumRec"), rs.getObject("DataPubblicazione", LocalDate.class), rs.getObject("DataUltimaModifica", LocalDate.class), new MySQL_DAOAutore().getListaAutoriPubblicazione(IDPub));
				resultList.add(p);

			}

			return resultList;

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
	 * Rimuove una pubblicazione dal catalogo
	 * 
	 * @param IDPubblicazione
	 * @param IDUtente
	 */
	@Override
	public void rimuoviPubblicazione(Integer IDPubblicazione, Integer IDUtente) {
		
		Connection conn = null;
		CallableStatement cStmt = null;
		
		
		try {

			conn = MySQL_DAOFactory.createConnection();
			cStmt = conn.prepareCall("{call delPubb(?, ?)}");

			cStmt.setInt(1, IDPubblicazione);
			cStmt.setInt(2, IDUtente);

			cStmt.execute();

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
		    try { if (cStmt != null) cStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}


	}

	/**
	 * Restituisce la pubblicazione completa
	 * 
	 * @param IDPubblicazione
	 * 
	 * @return Oggetto pubblicazione completa
	 */
	@Override
	public PubblicazioneCompleta getDatiPubblicazione(Integer IDPubblicazione) {
			
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		try {

			conn = MySQL_DAOFactory.createConnection();
			
			pStmt = conn.prepareStatement("SELECT * FROM Pubblicazione p INNER JOIN Metadati m ON p.ID = m.IDPubblicazione WHERE p.ID = ?");
			
			pStmt.setInt(1, IDPubblicazione);
			
			rs = pStmt.executeQuery();
			
			Integer IDPub = null;
			if(rs.next()) IDPub = rs.getInt(1);
			
			PubblicazioneCompleta result = new PubblicazioneCompleta(IDPub, rs.getString("ISBN"), rs.getString("Titolo"), rs.getString("Editore"),
					rs.getInt("NumLike"), rs.getInt("NumRec"), rs.getObject("DataPubblicazione", LocalDate.class), rs.getObject("DataUltimaModifica", LocalDate.class), rs.getInt("NPag"), rs.getString("Lingua"),
					rs.getString("Descrizione"), rs.getString("Indice"), new MySQL_DAOAutore().getListaAutoriPubblicazione(IDPub), new MySQL_DAORistampa().getListaRistampePubblicazione(IDPub) , new MySQL_DAOSorgente().getListaSorgentiPubblicazione(IDPub), new MySQL_DAOParolaChiave().getListaParoleChiavePubblicazione(IDPub));
			
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
	 * Ritorna la lista delle pubblicazioni che hanno quella parola nel titolo
	 * 
	 * @param titolo
	 * 
	 * @return lista pubblicazioni
	 */
	@Override
	public ArrayList<Pubblicazione> getListaPubblicazioniTitolo(String titolo) {
		
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = MySQL_DAOFactory.createConnection();
			String Query = "SELECT * FROM Pubblicazione WHERE Titolo LIKE ?";
			pStmt = conn.prepareStatement(Query);
			
			pStmt.setString(1, "%" + titolo + "%");
			rs = pStmt.executeQuery();
		
			ArrayList<Pubblicazione> res = new ArrayList<Pubblicazione>();
			Pubblicazione p = null;
			
			while(rs.next()) {
				
				Integer IDPub = rs.getInt(1);
				
				p = new Pubblicazione(IDPub, rs.getString("ISBN"), rs.getString("Titolo"), rs.getString("Editore"),
						rs.getInt("NumLike"), rs.getInt("NumRec"), rs.getObject("DataPubblicazione", LocalDate.class), rs.getObject("DataUltimaModifica", LocalDate.class), new MySQL_DAOAutore().getListaAutoriPubblicazione(IDPub));
				res.add(p);
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
	 * Pubblicazione con quell'isbn
	 * 
	 * @param ISBN
	 * 
	 * @return pubblicazione con quel codice ISBN
	 */
	@Override
	public Pubblicazione getListaPubblicazioneISBN(String ISBN) {
		
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		ArrayList<Pubblicazione> res = null;
		
		try {
			
			conn = MySQL_DAOFactory.createConnection();
			String Query = "SELECT * FROM Pubblicazione WHERE ISBN = ?";
			pStmt = conn.prepareStatement(Query);
			
			pStmt.setString(1, ISBN);
			
			rs = pStmt.executeQuery();
		
			Pubblicazione p = null;
			
			if(rs.next()) {
				
				Integer IDPub = rs.getInt(1);
				
				p = new Pubblicazione(IDPub, rs.getString("ISBN"), rs.getString("Titolo"), rs.getString("Editore"),
						rs.getInt("NumLike"), rs.getInt("NumRec"), rs.getObject("DataPubblicazione", LocalDate.class), rs.getObject("DataUltimaModifica", LocalDate.class), new MySQL_DAOAutore().getListaAutoriPubblicazione(IDPub));
				return p;
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

	/**
	 * Elenco ultime pubblicazioni inserite
	 * 
	 * @return elenco pubblicazioni
	 */
	@Override
	public ArrayList<Pubblicazione> getUltimeInserite() {
	
		Connection conn = null;
		PreparedStatement pS = null;
		ResultSet rs = null;
		
		try {
			
			conn = MySQL_DAOFactory.createConnection();
			
			pS = conn.prepareStatement("SELECT * FROM Pubblicazione p INNER JOIN Storia s ON p.ID = s.IDPubblicazione WHERE Tipo = 1 ORDER BY Timestamp DESC LIMIT 10");
			
			rs = pS.executeQuery();
		
			ArrayList<Pubblicazione> res = new ArrayList<Pubblicazione>();
			Pubblicazione p = null;
			
			while(rs.next()) {
				
				Integer IDPub = rs.getInt(1);
				
				p = new Pubblicazione(IDPub, rs.getString("ISBN"), rs.getString("Titolo"), rs.getString("Editore"),
						rs.getInt("NumLike"), rs.getInt("NumRec"), rs.getObject("DataPubblicazione", LocalDate.class), rs.getObject("DataUltimaModifica", LocalDate.class), new MySQL_DAOAutore().getListaAutoriPubblicazione(IDPub));
				res.add(p);
			}
			
			return res;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (pS != null) pS.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
		return null;
		
	}
	
	/**
	 * Elenco ultime pubblicazioni aggiornate
	 * 
	 * @return elenco pubblicazioni
	 */
	@Override
	public ArrayList<Pubblicazione> getUltimeAggiornate() {
		
		Connection conn = null;
		PreparedStatement pS = null;
		ResultSet rs = null;
		
		try {
			
			conn = MySQL_DAOFactory.createConnection();
			
			pS = conn.prepareStatement("SELECT DISTINCT p.ISBN, p.* FROM Pubblicazione p INNER JOIN Storia s ON p.ID = s.IDPubblicazione WHERE Tipo = 3 AND DATEDIFF(now(), s.Timestamp) < 30");
			
			rs = pS.executeQuery();
		
			ArrayList<Pubblicazione> res = new ArrayList<Pubblicazione>();
			Pubblicazione p = null;
			
			while(rs.next()) {
				
				Integer IDPub = rs.getInt("ID");
				
				p = new Pubblicazione(IDPub, rs.getString("ISBN"), rs.getString("Titolo"), rs.getString("Editore"),
						rs.getInt("NumLike"), rs.getInt("NumRec"), rs.getObject("DataPubblicazione", LocalDate.class), rs.getObject("DataUltimaModifica", LocalDate.class), new MySQL_DAOAutore().getListaAutoriPubblicazione(IDPub));
				res.add(p);
			}
			return res;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (pS != null) pS.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
		
		return null;
		
	}

	/**
	 * Elenco pubblicazioni dell'autore con quel nome
	 * 
	 * @param nomeAutore
	 * 
	 * @return elencoPubblicazioni
	 */
	@Override
	public ArrayList<Pubblicazione> getListaPubblicazioniAutoreNome(String nomeAutore) {
		
		Connection conn = null;
		CallableStatement cStmt = null;
		ResultSet rs = null;
		
		try {

			conn = MySQL_DAOFactory.createConnection();
			
			cStmt = conn.prepareCall("{call ricercaAutori(?, NULL)}");
			
			cStmt.setString(1, nomeAutore);
			
			ArrayList<Pubblicazione> lista = null;
			
			if(cStmt.execute()) {
				
				rs = cStmt.getResultSet();
				
				lista = new ArrayList<Pubblicazione>();
				Pubblicazione p = null;
				
				while(rs.next()) {
					
					Integer IDPub = rs.getInt(1);
					
					p = new Pubblicazione(IDPub, rs.getString("ISBN"), rs.getString("Titolo"), rs.getString("Editore"),
							rs.getInt("NumLike"), rs.getInt("NumRec"), rs.getObject("DataPubblicazione", LocalDate.class), rs.getObject("DataUltimaModifica", LocalDate.class), new MySQL_DAOAutore().getListaAutoriPubblicazione(IDPub));
					lista.add(p);
					
				}
				
			}
			
			return lista;
			
		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (cStmt != null) cStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
		return null;
	}
	
	/**
	 * Elenco pubblicazioni dell'autore con quel cognome
	 * 
	 * @param cognomeAutore
	 * 
	 * @return elencoPubblicazioni
	 */
	@Override
	public ArrayList<Pubblicazione> getListaPubblicazioniAutoreCognome(String cognomeAutore) {
		
		Connection conn = null;
		CallableStatement cStmt = null;
		ResultSet rs = null;
		
		try {

			conn = MySQL_DAOFactory.createConnection();
			
			cStmt = conn.prepareCall("{call ricercaAutori(NULL, ?)}");
			
			ArrayList<Pubblicazione> lista = null;
			
			cStmt.setString(1, cognomeAutore);
			
			if(cStmt.execute()) {
				
				rs = cStmt.getResultSet();
				
				lista = new ArrayList<Pubblicazione>();
				Pubblicazione p = null;
				
				while(rs.next()) {
					
					Integer IDPub = rs.getInt(1);
					
					p = new Pubblicazione(IDPub, rs.getString("ISBN"), rs.getString("Titolo"), rs.getString("Editore"),
							rs.getInt("NumLike"), rs.getInt("NumRec"), rs.getObject("DataPubblicazione", LocalDate.class), rs.getObject("DataUltimaModifica", LocalDate.class), new MySQL_DAOAutore().getListaAutoriPubblicazione(IDPub));
					lista.add(p);
					
				}
				
			}
			
			return lista;
			
		} catch (SQLException e) {

			e.printStackTrace();

		}  finally {
			try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (cStmt != null) cStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
		return null;
	}

	/**
	 * Elenco pubblicazioni dell'autore con quel nome e cognome
	 * 
	 * @param nomeAutore
	 * @param cognomeAutore
	 * 
	 * @return elencoPubblicazioni
	 */
	@Override
	public ArrayList<Pubblicazione> getListaPubblicazioniAutoreNomeCognome(String nomeAutore, String cognomeAutore) {
		
		Connection conn = null;
		CallableStatement cStmt = null;
		ResultSet rs = null;
		
		try {

			conn = MySQL_DAOFactory.createConnection();
			
			cStmt = conn.prepareCall("{call ricercaAutori(?, ?)}");
			
			ArrayList<Pubblicazione> lista = null;
			
			cStmt.setString(1, nomeAutore);
			cStmt.setString(2, cognomeAutore);
			
			if(cStmt.execute()) {
				
				rs = cStmt.getResultSet();
				
				lista = new ArrayList<Pubblicazione>();
				Pubblicazione p = null;
				
				while(rs.next()) {
					
					Integer IDPub = rs.getInt(1);
					
					p = new Pubblicazione(IDPub, rs.getString("ISBN"), rs.getString("Titolo"), rs.getString("Editore"),
							rs.getInt("NumLike"), rs.getInt("NumRec"), rs.getObject("DataPubblicazione", LocalDate.class), rs.getObject("DataUltimaModifica", LocalDate.class), new MySQL_DAOAutore().getListaAutoriPubblicazione(IDPub));
					lista.add(p);
					
				}
				
			}
			
			return lista;
			
		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (cStmt != null) cStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
		return null;
	}

	/**
	 * Aggiorna il titolo della pubblicazione
	 * 
	 * @param IDPubblicazione
	 * @param titolo
	 * @param IDUtente
	 * 
	 */
	@Override
	public void aggiornaTitolo(Integer IDPubblicazione, String titolo, Integer IDUtente) {
		
		Connection conn = null;
		CallableStatement cStmt = null;

		try {

			conn = MySQL_DAOFactory.createConnection();
			
			cStmt = conn.prepareCall("{call modificaTitolo(?, ?, ?)}");
			
			cStmt.setInt(1, IDPubblicazione);
			cStmt.setString(2, titolo);
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
	 * Aggiorna ISBN della pubblicazione
	 * 
	 * @param IDPubblicazione
	 * @param ISBN
	 * @param IDUtente
	 * 
	 */
	@Override
	public void aggiornaISBN(Integer IDPubblicazione, String ISBN, Integer IDUtente) {
		
		Connection conn = null;
		CallableStatement cStmt = null;
		
		try {

			conn = MySQL_DAOFactory.createConnection();
			
			cStmt = conn.prepareCall("{call modificaISBN(?, ?, ?)}");
			
			cStmt.setInt(1, IDPubblicazione);
			cStmt.setString(2, ISBN);
			cStmt.setInt(3, IDUtente);
			
			cStmt.execute();
			
		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
		    try { if (cStmt != null) cStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
	}

	/**
	 * Aggiorna editore della pubblicazione
	 * 
	 * @param IDPubblicazione
	 * @param Editore
	 * @param IDUtente
	 * 
	 */
	@Override
	public void aggiornaEditore(Integer IDPubblicazione, String Editore, Integer IDUtente) {
		
		Connection conn = null;
		CallableStatement cStmt = null;
		
		try {

			conn = MySQL_DAOFactory.createConnection();
			
			cStmt = conn.prepareCall("{call modificaEditore(?, ?, ?)}");
			
			cStmt.setInt(1, IDPubblicazione);
			cStmt.setString(2, Editore);
			cStmt.setInt(3, IDUtente);
			
			cStmt.execute();
			
		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
		    try { if (cStmt != null) cStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
	}

	/**
	 * Aggiorna data di pubblicazione della pubblicazione
	 * 
	 * @param IDPubblicazione
	 * @param dataPubblicazione
	 * @param IDUtente
	 * 
	 */
	@Override
	public void aggiornaDataPubblicazione(Integer IDPubblicazione, LocalDate dataPubblicazione, Integer IDUtente) {
		
		Connection conn = null;
		CallableStatement cStmt = null;
		
		try {

			conn = MySQL_DAOFactory.createConnection();
			
			cStmt = conn.prepareCall("{call modificaDataPubb(?, ?, ?)}");
			
			cStmt.setInt(1, IDPubblicazione);
			cStmt.setObject(2, dataPubblicazione);
			cStmt.setInt(3, IDUtente);
			
			cStmt.execute();
			
		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
		    try { if (cStmt != null) cStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
	}

	/**
	 * Aggiorna numero pagine della pubblicazione
	 * 
	 * @param IDPubblicazione
	 * @param NumPag
	 * @param IDUtente
	 * 
	 */
	@Override
	public void aggiornaNumPag(Integer IDPubblicazione, Integer NumPag, Integer IDUtente) {
		
		Connection conn = null;
		CallableStatement cStmt = null;
		
		try {

			conn = MySQL_DAOFactory.createConnection();
			
			cStmt = conn.prepareCall("{call modificaNPag(?, ?, ?)}");
			
			cStmt.setInt(1, IDPubblicazione);
			cStmt.setInt(2, NumPag);
			cStmt.setInt(3, IDUtente);
			
			cStmt.execute();
			
		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
		    try { if (cStmt != null) cStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
	}

	/**
	 * Aggiorna la lingua della pubblicazione
	 * 
	 * @param IDPubblicazione
	 * @param Lingua
	 * @param IDUtente
	 * 
	 */
	@Override
	public void aggiornaLingua(Integer IDPubblicazione, String Lingua, Integer IDUtente) {
		
		Connection conn = null;
		CallableStatement cStmt = null;
		
		try {

			conn = MySQL_DAOFactory.createConnection();
			
			cStmt = conn.prepareCall("{call modificaLingua(?, ?, ?)}");
			
			cStmt.setInt(1, IDPubblicazione);
			cStmt.setString(2, Lingua);
			cStmt.setInt(3, IDUtente);
			
			cStmt.execute();
			
		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
		    try { if (cStmt != null) cStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
	}

	/**
	 * Aggiorna la descrizione della pubblicazione
	 * 
	 * @param IDPubblicazione
	 * @param descrizione
	 * @param IDUtente
	 * 
	 */
	@Override
	public void aggiornaDescrizione(Integer IDPubblicazione, String descrizione, Integer IDUtente) {
		
		Connection conn = null;
		CallableStatement cStmt = null;
		
		try {

			conn = MySQL_DAOFactory.createConnection();
			
			cStmt = conn.prepareCall("{call modificaDescrizione(?, ?, ?)}");
			
			cStmt.setInt(1, IDPubblicazione);
			cStmt.setString(2, descrizione);
			cStmt.setInt(3, IDUtente);
			
			cStmt.execute();
			
		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
		    try { if (cStmt != null) cStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
	}

	/**
	 * Aggiorna indice della pubblicazione
	 * 
	 * @param IDPubblicazione
	 * @param Indice
	 * @param IDUtente
	 * 
	 */
	@Override
	public void aggiornaIndice(Integer IDPubblicazione, String Indice, Integer IDUtente) {
		
		Connection conn = null;
		CallableStatement cStmt = null;
		
		try {

			conn = MySQL_DAOFactory.createConnection();
			
			cStmt = conn.prepareCall("{call modificaIndice(?, ?, ?)}");
			
			cStmt.setInt(1, IDPubblicazione);
			cStmt.setString(2, Indice);
			cStmt.setInt(3, IDUtente);
			
			cStmt.execute();
			
		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
		    try { if (cStmt != null) cStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
	}

	/**
	 * Elenco pubblicazioni con download
	 * 
	 * @return lista pubblicazioni con download
	 * 
	 */
	@Override
	public ArrayList<Pubblicazione> getListaPubblicazioniDownlaodable() {
	
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {

			conn = MySQL_DAOFactory.createConnection();
			//System.out.println("Connessione stabilita - getListaPubblicazioni");

			stmt = conn.prepareStatement("SELECT p.* FROM Sorgente s INNER JOIN Pubblicazione p ON s.IDPubblicazione = p.ID WHERE s.Tipo LIKE '%download%'");
		
			rs = stmt.executeQuery();

			ArrayList<Pubblicazione> resultList = new ArrayList<Pubblicazione>();

			Pubblicazione p = null;

			while (rs.next()) {

				Integer IDPub = rs.getInt(1);
				
				p = new Pubblicazione(IDPub, rs.getString("ISBN"), rs.getString("Titolo"), rs.getString("Editore"),
						rs.getInt("NumLike"), rs.getInt("NumRec"), rs.getObject("DataPubblicazione", LocalDate.class), rs.getObject("DataUltimaModifica", LocalDate.class), new MySQL_DAOAutore().getListaAutoriPubblicazione(IDPub));
				resultList.add(p);

			}

			return resultList;

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
		    try { if (rs != null) rs.close(); } catch (Exception e) {};
			try { if (stmt != null) stmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}

		return null;
		
	}

	/**
	 * Numero di pubblicazioni
	 * 
	 * @return numero di pubblicazioni totali
	 * 
	 */
	@Override
	public Integer getNumPubb() {
			
		Connection conn = null;
		PreparedStatement cStmt = null;
		ResultSet rs = null;
		
		
		Integer NumPubb = 0;
		
		try {

			conn = MySQL_DAOFactory.createConnection();
			
			cStmt = conn.prepareStatement("SELECT COUNT(*) AS NPubb FROM Pubblicazione");
			
			rs = cStmt.executeQuery();
			
			if(rs.next()) {
				NumPubb = rs.getInt("NPubb");
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
 
		} finally {
			try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (cStmt != null) cStmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
		return NumPubb;
	}


	/**
	 * Torna pubblicazioni con data ultima ristampa
	 * 
	 * @param NumeroPagina
	 * 
	 * @return lista pubblicazioni
	 * 
	 */
	@Override
	public ArrayList<PubblicazioneRistampa> getPubblicazioniRistampa(Integer NumeroPagina) {
		
		Connection conn = null;
		PreparedStatement pS = null;
		ResultSet rs = null;
		
		Integer valueLimit = (NumeroPagina - 1) * 50;
		
		try {
			
			conn = MySQL_DAOFactory.createConnection();
			String Query = "SELECT P.*, UR.DataUltimaRistampa AS DataUltimaRistampa FROM Pubblicazione P INNER JOIN " + 
					"( " + 
					"SELECT R.IDPubblicazione AS ID, max(R.DataRistampa) AS DataUltimaRistampa " + 
					"FROM Ristampa R " + 
					"GROUP BY IDPubblicazione " + 
					") UR " + 
					"ON P.ID = UR.ID LIMIT ?,50";
			pS = conn.prepareStatement(Query);
			
			pS.setInt(1, valueLimit);
			rs = pS.executeQuery();
		
			ArrayList<PubblicazioneRistampa> res = new ArrayList<PubblicazioneRistampa>();
			PubblicazioneRistampa p = null;
			
			while(rs.next()) {
				
				Integer IDPub = rs.getInt(1);
				
				p = new PubblicazioneRistampa(IDPub, rs.getString("ISBN"), rs.getString("Titolo"), rs.getString("Editore"),
						rs.getInt("NumLike"), rs.getInt("NumRec"), rs.getObject("DataPubblicazione", LocalDate.class), rs.getObject("DataUltimaModifica", LocalDate.class), new MySQL_DAOAutore().getListaAutoriPubblicazione(IDPub), rs.getObject("DataUltimaRistampa", LocalDate.class));
				res.add(p);
			}
			
			return res;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (pS != null) pS.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
		
		return null;
		
	}

	/**
	 * Elenco pubblicazioni con gli stessi autori
	 * 
	 * @param IDPubblicazione
	 * 
	 * @return lista pubblicazioni
	 */
	public ArrayList<Pubblicazione> getPubblcazioniStessiAutori(Integer IDPubblicazione) {
		
		Connection conn = null;
		PreparedStatement pS = null;
		ResultSet rs = null;
		
		try {
			
			conn = MySQL_DAOFactory.createConnection();
			String Query = "SELECT DISTINCT p.* FROM Pubblicazione p INNER JOIN Scritto s1 ON s1.IDPubblicazione = p.id INNER JOIN Scritto s2 ON s1.IDAutore = s2.IDAutore WHERE s2.IDPubblicazione = ? AND p.ID != ?";
			pS = conn.prepareStatement(Query);
			
			pS.setInt(1, IDPubblicazione);
			pS.setInt(2, IDPubblicazione);
			
			rs = pS.executeQuery();
		
			ArrayList<Pubblicazione> res = new ArrayList<Pubblicazione>();
			Pubblicazione p = null;
			
			while(rs.next()) {
				
				Integer IDPub = rs.getInt(1);
				
				p = new Pubblicazione(IDPub, rs.getString("ISBN"), rs.getString("Titolo"), rs.getString("Editore"),
						rs.getInt("NumLike"), rs.getInt("NumRec"), rs.getObject("DataPubblicazione", LocalDate.class), rs.getObject("DataUltimaModifica", LocalDate.class), new MySQL_DAOAutore().getListaAutoriPubblicazione(IDPub));
				res.add(p);
			}
			
			return res;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (pS != null) pS.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
		
		return null;
	}
	
}
