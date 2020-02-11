package app.controller.frontend;

import java.util.ArrayList;

import app.controller.abstracts.ControllerFrontend;
import app.controller.backend.ControllerPubblicazione;
import app.controller.backend.ControllerSchermo;
import app.controller.backend.ControllerSessione;
import app.model.Pubblicazione;
import app.model.PubblicazioneRistampa;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
/**
 * 
 * @author Federico Di Menna
 *
 */
public class VisualizzaRistampeController extends ControllerFrontend {
	
	@FXML
	private TableView<PubblicazioneRistampa> TabellaPubblicazioni;
	
	@FXML
    private TableColumn<PubblicazioneRistampa, String> ISBNtc;
    
    @FXML
    private TableColumn<PubblicazioneRistampa, String> Titolotc;
    
    @FXML
    private TableColumn<PubblicazioneRistampa, String> DataUltimaRistampatc;
    
	@FXML
	private Button Next;
	
	@FXML
	private Button Prev;
	
    @FXML
    private Button DettagliButton;
  
    
    private ObservableList<PubblicazioneRistampa> listaPubblicazioni = FXCollections.observableArrayList();
    
    private Integer ActualPage = 1;
    
    private Integer NumTotalePubblicazioni;

    /**
     * Aggiorna la tabella delle ristampe
     */
	private void aggiornaTabella() {
		
		listaPubblicazioni.clear();
		ArrayList<PubblicazioneRistampa> arraypubb;
		
		arraypubb = ControllerPubblicazione.getIstanza().getListaPubblicazioniRistampe(ActualPage);
		
		if(arraypubb.isEmpty()) {
			
			System.out.println("Lista vuota");
		
		} else {
			
			for(PubblicazioneRistampa p : arraypubb) {
				listaPubblicazioni.add(p);
			}
			
			//System.out.println(listaPubblicazioni);
			
			//System.out.println(listaPubblicazioni.toString());
			
			
			
			// Riempie la tabella con i vari campi
	    	ISBNtc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getISBN()));
	    	Titolotc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitolo()));
	    	DataUltimaRistampatc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDataUltimaRistampa().toString()));
	    	//Likestc.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNumLike()).asObject());
	    	//Recensionitc.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNumLike()).asObject());
	    	
	    	// Setta il listener
	    	//TabellaPubblicazioni.getSelectionModel().selectedItemProperty().addListener(
	        //        (observable, oldValue, newValue) -> mostraProprietaSorgente(newValue));
	    	
	    	TabellaPubblicazioni.setItems(listaPubblicazioni);
			
		}
		
		
		
		
	}

	@Override
	public void load() {
		
		NumTotalePubblicazioni = ControllerPubblicazione.getIstanza().getNumeroPubblicazioni();
		ActualPage = 1;
		
		aggiornaTabella();
		aggiornaBottoni();
		
	}
	
	@FXML
	private void handleDettagli() {
		
		Pubblicazione p = TabellaPubblicazioni.getSelectionModel().getSelectedItem();
		
		if(p != null) {
			
			ControllerSessione.getIstanza().setPubblicazioneSelezionata(TabellaPubblicazioni.getSelectionModel().getSelectedItem());
			
			ControllerSchermo.istanzaManager().activateOnCenter("VisualizzaPubblicazione");
		
		}
		
	}

	private void aggiornaBottoni() {
		
		if((ActualPage * 50) >= NumTotalePubblicazioni) {
			Next.setDisable(true);
		} else {
			Next.setDisable(false);
		}
		
		if(ActualPage == 1) {
			Prev.setDisable(true);
		} else {
			Prev.setDisable(false);
		}
	}
	
	@FXML
	private void handleNext() {
		
		ActualPage++;
		aggiornaTabella();
		aggiornaBottoni();
		
	}
	
	@FXML
	private void handlePrev() {
		
		ActualPage--;
		aggiornaTabella();
		aggiornaBottoni();
		
	}
}
