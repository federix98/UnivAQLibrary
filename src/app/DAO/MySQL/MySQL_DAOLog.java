package app.DAO.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import app.DAO.interfaces.DAOLog;
import app.model.Log;
import app.model.TipoStoria;
/**
 * 
 * @author Federico Di Menna
 *
 */
public class MySQL_DAOLog implements DAOLog {

	/**
	 * @param NumeroPagine
	 * 
	 * @return lista dei logs per pagina
	 */
	@Override
	public ArrayList<Log> getLogs(Integer NumeroPagina) {
		
		Connection conn = null;
		
		Integer valueLimit = (NumeroPagina - 1) * 50;
		
		try {

			conn = MySQL_DAOFactory.createConnection();
			
			PreparedStatement cStmt = conn.prepareStatement("SELECT * FROM storia ORDER BY ID DESC LIMIT ?, 50");
			cStmt.setInt(1, valueLimit);
			
			ResultSet rs = cStmt.executeQuery();
			
			ArrayList<Log> logs = new ArrayList<Log>();
			
			while(rs.next()) {
				Log l = new Log(rs.getInt("ID"), rs.getInt("IDPubblicazione"), rs.getInt("IDUtente"), rs.getObject("Timestamp", LocalDateTime.class), rs.getString("Descrizione"), null);
				switch(rs.getInt("Tipo")) {
					case 1 : {
						l.setTipostoria(TipoStoria.INSERIMENTO_PUBBLICAZIONE);
						break;
					}
					case 2 : {
						l.setTipostoria(TipoStoria.CANCELLAZIONE_PUBBLICAZIONE);
						break;
					}
					case 3 : {
						l.setTipostoria(TipoStoria.MODIFICA_PUBBLICAZIONE);
						break;
					}
					case 4 : {
						l.setTipostoria(TipoStoria.APPROVAZIONE_RECENSIONE);
						break;
					}
					case 5 : {
						l.setTipostoria(TipoStoria.INSERIMENTO_RECENSIONE);
						break;
					}
					case 6 : {
						l.setTipostoria(TipoStoria.ELIMINAZIONE_RECENSIONE);
						break;
					}
					case 7 : {
						l.setTipostoria(TipoStoria.INSERIMENTO_LIKE);
						break;
					}
					case 8 : {
						l.setTipostoria(TipoStoria.ELIMINAZIONE_LIKE);
						break;
					}
					default : {
						l.setTipostoria(TipoStoria.SCONOSCIUTO);
						break;
					}
				}
				logs.add(l);
			}
			
			conn.close();
			
			return logs;
			
		} catch (SQLException e) {

			e.printStackTrace();

		}
		
		return null;
	}

	/**
	 * @param tipo
	 * 
	 * @return lista dei logs di quel tipo
	 */
	@Override
	public ArrayList<Log> getLogsTipo(TipoStoria tipo) {
		Connection conn = null;
		
		try {

			conn = MySQL_DAOFactory.createConnection();
			
			String query = "SELECT * FROM storia WHERE Tipo = ";
			
			switch(tipo) {
			
				case INSERIMENTO_PUBBLICAZIONE: {
					query += "1";
					break;
				}
				
				case CANCELLAZIONE_PUBBLICAZIONE: {
					query += "2";
					break;
				}
				
				case MODIFICA_PUBBLICAZIONE: {
					query += "3";
					break;
				}
				
				case APPROVAZIONE_RECENSIONE: {
					query += "4";
					break;
				}
				
				case INSERIMENTO_RECENSIONE: {
					query += "5";
					break;
				}
				
				case ELIMINAZIONE_RECENSIONE: {
					query += "6";
					break;
				}
				
				case INSERIMENTO_LIKE: {
					query += "7";
					break;
				}
				
				case ELIMINAZIONE_LIKE: {
					query += "8";
					break;
				}
				
				case SCONOSCIUTO: {
					query += "0";
					break;
				}
				
			}
			
			PreparedStatement cStmt = conn.prepareStatement(query);
			
			ResultSet rs = cStmt.executeQuery();
			
			ArrayList<Log> logs = new ArrayList<Log>();
			
			while(rs.next()) {
				Log l = new Log(rs.getInt("ID"), rs.getInt("IDPubblicazione"), rs.getInt("IDUtente"), rs.getObject("Timestamp", LocalDateTime.class), rs.getString("Descrizione"), tipo);
				logs.add(l);
			}
			
			conn.close();
			
			return logs;
			
		} catch (SQLException e) {

			e.printStackTrace();

		}
		
		return null;
	}

	/**
	 * @return numero totale di logs nel DB
	 */
	@Override
	public Integer getNumLogs() {
		
		Connection conn = null;
		Integer NumLogs = 0;
		
		try {

			conn = MySQL_DAOFactory.createConnection();
			
			PreparedStatement cStmt = conn.prepareStatement("SELECT COUNT(*) AS NLog FROM storia");
			
			ResultSet rs = cStmt.executeQuery();
			
			if(rs.next()) {
				NumLogs = rs.getInt("NLog");
			}
						
			conn.close();
			
		} catch (SQLException e) {

			e.printStackTrace();

		}
		
		return NumLogs;
	}

	
	
}
