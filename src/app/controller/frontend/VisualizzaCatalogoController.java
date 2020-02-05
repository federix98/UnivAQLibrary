package app.controller.frontend;

import java.util.ArrayList;

import app.controller.abstracts.ControllerFrontend;
import app.controller.backend.ControllerPubblicazione;
import app.controller.backend.ControllerSchermo;
import app.controller.backend.ControllerSessione;
import app.model.Autore;
import app.model.Pubblicazione;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class VisualizzaCatalogoController extends ControllerFrontend {
	
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
	private Button Next;
	
	@FXML
	private Button Prev;
	
    @FXML
    private Button DettagliButton;
    
    @FXML
    private CheckBox Downloadable;
    
    private ObservableList<Pubblicazione> listaPubblicazioni = FXCollections.observableArrayList();
    
    private Integer ActualPage = 1;
    
    private Integer NumTotalePubblicazioni;

    private Integer typeFilter = 1;
    
	public void initialize() {
		
		
	}

	private void aggiornaTabella(int type) {
		
		listaPubblicazioni.clear();
		ArrayList<Pubblicazione> arraypubb;
		
		if(type == 1) {
			arraypubb = ControllerPubblicazione.getIstanza().getListaPubblicazioni(ActualPage);
		} else {
			arraypubb = ControllerPubblicazione.getIstanza().getListaPubblicazioniDownload();
		}
		
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
	    	Autoritc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getListaAutori()));
	    	Editoretc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEditore()));
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
		
		aggiornaTabella(typeFilter);
		aggiornaBottoni();
		
		Downloadable.selectedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		        if(Downloadable.isSelected()) {
		        	typeFilter = 0;
		        	aggiornaTabella(0);
		        } else {
		        	typeFilter = 1;
		        	aggiornaTabella(1);
		        }
		    }
		});
		
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
		aggiornaTabella(typeFilter);
		aggiornaBottoni();
		
	}
	
	@FXML
	private void handlePrev() {
		
		ActualPage--;
		aggiornaTabella(typeFilter);
		aggiornaBottoni();
		
	}
}
