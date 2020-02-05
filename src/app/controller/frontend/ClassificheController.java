package app.controller.frontend;

import java.util.ArrayList;

import app.controller.abstracts.ControllerFrontend;
import app.controller.backend.ControllerPubblicazione;
import app.controller.backend.ControllerUtente;
import app.model.Moderatore;
import app.model.Pubblicazione;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ClassificheController extends ControllerFrontend {
	
	@FXML
	private TableView<Moderatore> TabellaUtenti;
	
	@FXML
    private TableColumn<Moderatore, String> Nicknametc;
    
    @FXML
    private TableColumn<Moderatore, Integer> NumPubbtc;
    
    @FXML
	private TableView<Pubblicazione> TabellaPubblicazioni;
	
	@FXML
    private TableColumn<Pubblicazione, String> ISBNtc;
    
    @FXML
    private TableColumn<Pubblicazione, String> Titolotc;
    
    @FXML
	private TableView<Pubblicazione> TabellaPAggiornate;
	
	@FXML
    private TableColumn<Pubblicazione, String> ISBNAtc;
    
    @FXML
    private TableColumn<Pubblicazione, String> TitoloAtc;
    
    @FXML
    private Button Avanti;
    
    @FXML
    private Button Indietro;
    
    private ObservableList<Moderatore> listaUtenti = FXCollections.observableArrayList();
    
    private ObservableList<Pubblicazione> listaUltimeInserite = FXCollections.observableArrayList();
    
    private ObservableList<Pubblicazione> listaUltimeAggiornate = FXCollections.observableArrayList();

	public void initialize() {
		
		
	}


	@Override
	public void load() {
		
		listaUtenti.clear();
		listaUltimeInserite.clear();
		listaUltimeAggiornate.clear();
		
		ArrayList<Moderatore> arrayMod = ControllerUtente.getIstanza().getUtentiCollaborativi();
		ArrayList<Pubblicazione> arrayPubb = ControllerPubblicazione.getIstanza().getUltimeInserite();
		ArrayList<Pubblicazione> arrayAgg = ControllerPubblicazione.getIstanza().getUltimeAggiornate();
		
		if(arrayMod.isEmpty()) {
			
			System.out.println("Lista utenti vuota");
		
		} else {
			
			for(Moderatore m : arrayMod) {
				
				listaUtenti.add(m);
			}
			
			// Riempie la tabella con i vari campi
	    	Nicknametc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNickName()));
	    	NumPubbtc.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNumeroPubblicazioni()).asObject());

	    	TabellaUtenti.setItems(listaUtenti);
			
		}
		
		if(arrayPubb.isEmpty()) {
			
			System.out.println("Lista ultime pubblicazioni inserite vuota");
		
		} else {
			
			for(Pubblicazione p : arrayPubb) {
				
				listaUltimeInserite.add(p);
			}
			
			// Riempie la tabella con i vari campi
	    	ISBNtc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getISBN()));
	    	Titolotc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitolo()));

	    	TabellaPubblicazioni.setItems(listaUltimeInserite);
			
		}
		
		if(arrayAgg.isEmpty()) {
			
			System.out.println("Lista ultime pubblicazioni aggiornate vuota");
		
		} else {
			
			for(Pubblicazione p : arrayAgg) {
				
				listaUltimeAggiornate.add(p);
			}
			
			// Riempie la tabella con i vari campi
	    	ISBNAtc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getISBN()));
	    	TitoloAtc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitolo()));

	    	TabellaPAggiornate.setItems(listaUltimeAggiornate);
			
		}
		
	}
	
}
