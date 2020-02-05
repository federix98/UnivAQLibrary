package app.controller.frontend;


import java.util.ArrayList;

import app.controller.abstracts.ControllerFrontend;
import app.controller.backend.ControllerPubblicazione;
import app.controller.backend.ControllerSchermo;
import app.controller.backend.ControllerSessione;
import app.model.Autore;
import app.model.ParolaChiave;
import app.model.PubblicazioneCompleta;
import app.model.Ristampa;
import app.model.Sorgente;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class InserisciPubblicazioneController extends ControllerFrontend {

	
	@FXML
	private Label numLikeLabel;
	
	@FXML
	private Label numLikeValue;
	
	@FXML
	private Label numRecLabel;
	
	@FXML
	private Label numRecValue;
	
	@FXML
	private Label dataModLabel;
	
	@FXML
	private Label dataModValue;
	
	@FXML
	private Button AnnullaButton;
	
	@FXML
	private Button InserisciButton;
	
	
	@FXML
	private TextField ISBNField;
	
	@FXML
	private TextField EditoreField;
	
	@FXML
	private DatePicker DataPubblicazioneField;
	
	@FXML
	private Label ErrorLabel;
	
	@FXML
	private TextField TitoloField;
	
	@FXML
	private TextField NumeroPagineField;
	
	@FXML
	private TextField LinguaField;
	
	@FXML
	private TextField DescrizioneField;
	
	@FXML
	private TextField IndiceField;
	
	@FXML
	private Button EditAutori;
	
	@FXML
	private Button EditSorgenti;
	
	@FXML
	private Button EditRistampe;
	
	@FXML
	private Button EditParole;
	
	@FXML
	private TableView<Autore> TabellaAutori;
	
	@FXML
	private TableColumn<Autore, String> NomeAutore;
	
	@FXML
	private TableColumn<Autore, String> CognomeAutore;
	
	@FXML
	private TableView<ParolaChiave> TabellaParole;
	
	@FXML
	private TableColumn<ParolaChiave, String> Parola;

	@FXML
	private TableView<Ristampa> TabellaRistampe;

	@FXML
	private TableColumn<Ristampa, Integer> Numero;
	
	@FXML
	private TableColumn<Ristampa, String> Data;
	
	@FXML
	private TableView<Sorgente> TabellaSorgenti;
	
	@FXML
	private TableColumn<Sorgente, String> TipoSorgente;
	
	@FXML
	private TableColumn<Sorgente, String> URISorgente;
	
	private ObservableList<Autore> listaAutori = FXCollections.observableArrayList();
	
	private ObservableList<ParolaChiave> listaParoleChiave = FXCollections.observableArrayList();
	
	private ObservableList<Ristampa> listaRistampe = FXCollections.observableArrayList();
	
	private ObservableList<Sorgente> listaSorgenti = FXCollections.observableArrayList();
	
	private PubblicazioneCompleta daInserire;
	
	@FXML
	private void handleInserisci() {
		
		if(TitoloField.getText() == "" ||
				ISBNField.getText() == "" ||
				EditoreField.getText() == "" ||
				DataPubblicazioneField.getValue() == null ||
				NumeroPagineField.getText() == "" ||
				LinguaField.getText() == "" ||
				DescrizioneField.getText() == "" ||
				IndiceField.getText() == "") {
			
			ErrorLabel.setVisible(true);
			ErrorLabel.setText("Riempi tutti i campi");
			return;
		
		}
		
		try { 
			Long.parseLong(ISBNField.getText()); 
			if(ISBNField.getText().length() != 13) {
				ErrorLabel.setVisible(true);
				ErrorLabel.setText("Errore nella lunghezza del campo: ISBN"); 
				return;
			}
			daInserire.setISBN(ISBNField.getText());
		} catch(NumberFormatException | NullPointerException nfe) { 
			nfe.printStackTrace();
			ErrorLabel.setVisible(true);
			ErrorLabel.setText("Errore nel campo: ISBN"); 
			return;
		}
		
		try { 
			daInserire.setNPag(Integer.parseInt(NumeroPagineField.getText())); 
		} catch(NumberFormatException | NullPointerException nfe) { 
			nfe.printStackTrace();
			ErrorLabel.setVisible(true);
			ErrorLabel.setText("Errore nel campo: Numero Pagine"); 
			return;
		}
		
		daInserire.setISBN(ISBNField.getText());
		daInserire.setTitolo(TitoloField.getText());
		daInserire.setEditore(EditoreField.getText());
		daInserire.setDataPubblicazione(DataPubblicazioneField.getValue());
		daInserire.setLingua(LinguaField.getText());
		daInserire.setDescrizione(DescrizioneField.getText());
		daInserire.setIndice(IndiceField.getText());
		
		//System.out.println("Inserito");
		ControllerPubblicazione.getIstanza().inserisciPubblicazione(daInserire);
		ControllerSchermo.istanzaManager().activateOnCenter("ProfiloUtente");
		
	}
	
	@FXML
	private void handleAnnulla() {
		
		if(ControllerSchermo.istanzaManager().privilegi.contains(ControllerSessione.getIstanza().getPaginaPrecedente()) && ControllerSessione.getIstanza().getUtenteLoggato() == null) {
			
			ControllerSchermo.istanzaManager().activateOnCenter("Login");
		
		} else {
			
			ControllerSchermo.istanzaManager().activateOnCenter(ControllerSessione.getIstanza().getPaginaPrecedente());
		
		}
		
	}
	
	@FXML
	private void handleAutori() {
		
		ControllerSchermo.istanzaManager().openPaneInWindow("AggiungiAutore");
		AggiungiAutoreController c = (AggiungiAutoreController) ControllerSchermo.istanzaManager().getControllerMap().get("AggiungiAutore");
		c.setPubblicazione(daInserire);
		
	}
	
	@FXML
	private void handleParole() {
		
		ControllerSchermo.istanzaManager().openPaneInWindow("AggiungiParolaChiave");
		AggiungiParolaChiaveController c = (AggiungiParolaChiaveController) ControllerSchermo.istanzaManager().getControllerMap().get("AggiungiParolaChiave");
		c.setPubblicazione(daInserire);
		
	}
	
	@FXML
	private void handleRistampe() {
		
		ControllerSchermo.istanzaManager().openPaneInWindow("AggiungiRistampa");
		AggiungiRistampaController c = (AggiungiRistampaController) ControllerSchermo.istanzaManager().getControllerMap().get("AggiungiRistampa");
		c.setPubblicazione(daInserire);
		
		
	}
	
	@FXML
	private void handleSorgenti() {
		
		ControllerSchermo.istanzaManager().openPaneInWindow("AggiungiSorgente");
		AggiungiSorgenteController c = (AggiungiSorgenteController) ControllerSchermo.istanzaManager().getControllerMap().get("AggiungiSorgente");
		c.setPubblicazione(daInserire);
		
	}
	
	public void initialize() {
		
	}

	@Override
	public void load() {
		
		listaAutori.clear();
		listaParoleChiave.clear();
		listaRistampe.clear();
		listaSorgenti.clear();
		
		daInserire = new PubblicazioneCompleta(null, null, null, null, null, null, null, null, null, null, null, null, 
				new ArrayList<Autore>(), new ArrayList<Ristampa>(), new ArrayList<Sorgente>(), new ArrayList<ParolaChiave>());
		
		
	}
	
	public void update() {
	
		listaAutori.clear();
		listaParoleChiave.clear();
		listaRistampe.clear();
		listaSorgenti.clear();
		
		
		// AUTORI
		for(Autore a : daInserire.getAutori()) {
			listaAutori.add(a);
		}
		NomeAutore.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
		CognomeAutore.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCognome()));
		TabellaAutori.setItems(listaAutori);
		
		
		// PAROLE CHIAVE
		for(ParolaChiave pc : daInserire.getTags()) {
			listaParoleChiave.add(pc);
		}
		Parola.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getParola()));
		TabellaParole.setItems(listaParoleChiave);
		
		// RISTAMPE
		for(Ristampa r : daInserire.getRistampe()) {
			listaRistampe.add(r);
		}
		Numero.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNumero()).asObject());
		Data.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDataRistampa().toString()));
		TabellaRistampe.setItems(listaRistampe);
		
		
		// SORGENTI
		for(Sorgente sr : daInserire.getSorgenti()) {
			listaSorgenti.add(sr);
		}
		URISorgente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getURI()));
		TipoSorgente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipo()));
		TabellaSorgenti.setItems(listaSorgenti);
	}
	
}
