package app.DAO.interfaces;

import java.util.ArrayList;

import app.model.Log;
import app.model.TipoStoria;

public interface DAOLog {

	public ArrayList<Log> getLogs(Integer NumeroPagina);
	
	public ArrayList<Log> getLogsTipo(TipoStoria tipo);
	
	public Integer getNumLogs();
	
}
