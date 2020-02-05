package app.controller.frontend;

import java.util.ArrayList;

import app.DAO.MySQL.MySQL_DAOAutore;
import app.controller.abstracts.ControllerFrontend;
import app.controller.backend.ControllerPubblicazione;
import app.controller.backend.ControllerSchermo;
import app.controller.backend.ControllerSessione;
import app.model.Autore;
import app.model.Pubblicazione;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class GestioneCatalogoController extends ControllerFrontend {
	
	@FXML
	private TableView<Pubblicazione> TabellaPubblicazioni;
	
	@FXML
    private TableColumn<Pubblicazione, String> ISBNtc;
    
    @FXML
    private TableColumn<Pubblicazione, String> Titolotc;
    
    @FXML
    private TableColumn<Pubblicazione, String> Autoritc;
    
    @FXML
    private TableColumn<Pubblicazione, String> Editoretc;
    
    @FXML
    private TableColumn<Pubblicazione, Integer> Likestc;
    
    @FXML
    private TableColumn<Pubblicazione, Integer> Recensionitc;
    
    @FXML
    private Button Avanti;
    
    @FXML
    private Button Indietro;
    
    @FXML
    private Button InserisciButton;
    
    private ObservableList<Pubblicazione> listaPubblicazioni = FXCollections.observableArrayList();

	public void initialize() {
		
		aggiornaLista(1);
		
	}

	private void aggiornaLista(int i) {
		
		listaPubblicazioni.clear();
		
		ArrayList<Pubblicazione> arraypubb = ControllerPubblicazione.getIstanza().getListaPubblicazioni(i);
		
		if(arraypubb.isEmpty()) {
			
			System.out.println("Lista vuota");
		
		} else {
			
			for(Pubblicazione p : arraypubb) {
				// gestione autore null
				if(p.getAutori() == null) {
					ArrayList<Autore> scon = new ArrayList<Autore>();
					scon.add(new Autore("Sconosciuto"));
					p.setAutori(scon);
				}
				
				listaPubblicazioni.add(p);
			}
			
			//System.out.println(listaPubblicazioni);
			
			//System.out.println(listaPubblicazioni.toString());
			
			
			
			// Riempie la tabella con i vari campi
	    	ISBNtc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getISBN()));
	    	Titolotc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitolo()));
	    	Autoritc.setCellValueFactory(cellData -> new SimpleStringProperty(new MySQL_DAOAutore().getListaAutoriPubblicazioneString(cellData.getValue().getID())));
	    	Editoretc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEditore()));
	    	Likestc.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNumLike()).asObject());
	    	Recensionitc.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNumLike()).asObject());
	    	
	    	// Setta il listener
	    	//TabellaPubblicazioni.getSelectionModel().selectedItemProperty().addListener(
	        //        (observable, oldValue, newValue) -> mostraProprietaSorgente(newValue));
	    	
	    	TabellaPubblicazioni.setItems(listaPubblicazioni);
			
		}
		
		
		
		
	}

	@FXML
	private void handleInserisci() {
		
		ControllerSchermo.istanzaManager().activateOnCenter("InserisciPubblicazione");
		
	}
	
	@FXML
	private void handleDettagli() {
		
		Pubblicazione p = TabellaPubblicazioni.getSelectionModel().getSelectedItem();
		
		if(p != null) {
			
			ControllerSessione.getIstanza().setPubblicazioneSelezionata(TabellaPubblicazioni.getSelectionModel().getSelectedItem());
			ControllerSchermo.istanzaManager().activateOnCenter("VisualizzaPubblicazione");
		
		}
		
	}
	
	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}
	
}
