package app.controller.backend;

import java.util.ArrayList;

import app.DAO.DAOFactory;
import app.DAO.interfaces.DAOLog;
import app.controller.abstracts.ControllerBackend;
import app.model.Log;
import app.model.TipoStoria;

public class ControllerLog extends ControllerBackend {

	private static ControllerLog instance = new ControllerLog();
	
	private DAOLog DAO;
	
	private ControllerLog() {
		DAO = DAOFactory.getDAOFactory(0).getDAOLog();
	}
	
	public static ControllerLog getInstance() {
		return instance;
	}
	
	public ArrayList<Log> getLogs(Integer NumeroPagina) {
		
		if(NumeroPagina < 1) return null;
		
		return DAO.getLogs(NumeroPagina);

	}

	
	public ArrayList<Log> getLogsTipo(TipoStoria tipo) {
		return DAO.getLogsTipo(tipo);
	}
	
	public Integer getNumLogs() {
		return DAO.getNumLogs();
	}
	
}
