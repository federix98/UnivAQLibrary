package app.controller.backend;

import java.util.ArrayList;

import app.DAO.DAOFactory;
import app.DAO.interfaces.DAORecensione;
import app.controller.abstracts.ControllerBackend;
import app.model.Recensione;

public class ControllerRecensione extends ControllerBackend {

	private static ControllerRecensione instance = new ControllerRecensione();
	private DAORecensione DAO;
	
	
	private ControllerRecensione() {
		DAO = DAOFactory.getDAOFactory(0).getDAORecensione();
	}
	
	public static ControllerRecensione getInstance() {
		return instance;
	}
	
	public void inserisciRecensione(Recensione daInserire) {
		DAO.inserisciRecensione(daInserire.getIDPubblicazione(), daInserire.getIDUtente(), daInserire.getDescrizione());
	}
	
	public ArrayList<Recensione> getRecensioniDaApprovare() {
		return DAO.getRecensioniDaApprovare();
	}
	
	public void approvaRecensione(Integer IDPubblicazione, Integer IDUtente, Integer IDApprovatore) {
		DAO.approvaRecensione(IDPubblicazione, IDUtente, IDApprovatore);
	}
	
	public ArrayList<Recensione> getRecensioniPubblicazione(Integer IDPubblicazione) {
		return DAO.getRecensioniApprovatePubblicazione(IDPubblicazione);
	}
}
