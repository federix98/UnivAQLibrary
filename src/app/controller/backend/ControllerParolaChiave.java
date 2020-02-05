package app.controller.backend;

import java.util.ArrayList;

import app.DAO.DAOFactory;
import app.DAO.interfaces.DAOParolaChiave;
import app.controller.abstracts.ControllerBackend;
import app.model.ParolaChiave;
import app.model.Pubblicazione;

public class ControllerParolaChiave extends ControllerBackend {

	private static ControllerParolaChiave instance = new ControllerParolaChiave();
	
	private DAOParolaChiave DAO;
	
	private ControllerParolaChiave() {
		
		DAO = DAOFactory.getDAOFactory(0).getDAOParolaChiave();
	
	}
	
	public static ControllerParolaChiave getInstance() {
	
		return instance;
	
	}
	
	public ArrayList<ParolaChiave> getParoleChiavePubblicazione(Pubblicazione pubb) {
		return DAO.getListaParoleChiavePubblicazione(pubb.getID());
	}
	
	public void inserisciParolaChiaveDirect(String parola) {
		DAO.inserisciParolaChiaveDirect(parola);
	}
	
	public ArrayList<ParolaChiave> getParoleChiave(Integer numPag) {
		return DAO.getListaParoleChiave(numPag);
	}
	
}
