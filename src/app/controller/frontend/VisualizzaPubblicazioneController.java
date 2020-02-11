package app.controller.frontend;

import java.util.ArrayList;

import app.controller.abstracts.ControllerFrontend;
import app.controller.backend.ControllerLike;
import app.controller.backend.ControllerPubblicazione;
import app.controller.backend.ControllerRecensione;
import app.controller.backend.ControllerSchermo;
import app.controller.backend.ControllerSessione;
import app.controller.backend.ControllerUtente;
import app.model.Autore;
import app.model.ParolaChiave;
import app.model.PubblicazioneCompleta;
import app.model.Recensione;
import app.model.Ristampa;
import app.model.Sorgente;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
/**
 * 
 * @author Federico Di Menna
 *
 */
public class VisualizzaPubblicazioneController extends ControllerFrontend {

	private Boolean showAll = false;
	private Boolean edit = false;
	
	@FXML
	private Hyperlink mostraLink;
	
	@FXML
	private Label numLikeValue;
	
	@FXML
	private Label numRecValue;
	
	@FXML
	private Label dataModLabel;
	
	@FXML
	private Label dataModValue;
	
	@FXML
	private Button modificaButton;
	
	@FXML
	private Button EsciButton;
	
	@FXML
	private Button SalvaButton;

	@FXML
	private Label titoloLabel;
	
	@FXML
	private TextField ISBNField;
	
	@FXML
	private TextField EditoreField;

	@FXML
	private TextField TitoloField;
	
	@FXML
	private DatePicker DataPubblicazioneField;
	
	@FXML
	private TextField NumeroPagineField;
	
	@FXML
	private TextField LinguaField;
	
	@FXML
	private TextArea DescrizioneField;
	
	@FXML
	private TextArea IndiceField;
	
	@FXML
	private Button RecensioneButton;
	
	@FXML
	private Button LikeButton;
	
	@FXML
	private Button LeggiRecensioniButton;
	
	@FXML
	private GridPane RecPane;
	
	@FXML
	private ScrollPane RecScroll;
	
	@FXML
	private Label RecensioniLabel;
	
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
	
	private PubblicazioneCompleta pubc = null;
	
	/**
	 * 
	 * Handle del "Mostra Tutto". Mostra dati secondari come il Numero di Likes, numero di Recensioni e data ultima modifica.
	 * 
	 */
	@FXML
    private void handleMostraLinkClick() {
		
		if(showAll) {
			
			numLikeValue.setVisible(false);
			numRecValue.setVisible(false);
			dataModLabel.setVisible(false);
			dataModValue.setVisible(false);
			
			mostraLink.setText("Mostra tutto");
			
			showAll = false;
			
		} else {
			
			numLikeValue.setVisible(true);
			numRecValue.setVisible(true);
			dataModLabel.setVisible(true);
			dataModValue.setVisible(true);
			
			mostraLink.setText("Mostra meno");
			
			showAll = true;
			
		}
			
	}
	
	
	/**
	 * 
	 * Handle del "Modifica" button. Rende i campi modificabili e viceversa.
	 * 
	 */
	@FXML
    private void handleModificaClick() {
		
		edit = !edit;
		
		if(edit == false) {
			aggiornaContenuto();
			modificaButton.setText("Modifica");
			titoloLabel.setVisible(true);
			TitoloField.setVisible(false);
			ISBNField.setEditable(false);
			NumeroPagineField.setEditable(false);
			LinguaField.setEditable(false);
			EditoreField.setEditable(false);
			DataPubblicazioneField.setEditable(false);
			DataPubblicazioneField.setDisable(true);
			IndiceField.setEditable(false);
			DescrizioneField.setEditable(false);
			SalvaButton.setVisible(false);
		} else {
			modificaButton.setText("Annulla");
			ISBNField.setEditable(true);
			NumeroPagineField.setEditable(true);
			LinguaField.setEditable(true);
			EditoreField.setEditable(true);
			IndiceField.setEditable(true);
			DataPubblicazioneField.setEditable(true);
			DataPubblicazioneField.setDisable(false);
			DescrizioneField.setEditable(true);
			SalvaButton.setVisible(true);
			titoloLabel.setVisible(false);
			TitoloField.setVisible(true);
		}		
		
	}
	
	
	/**
	 * 
	 * Handle del "Salva" button. Salva le modifiche effettuate.
	 * 
	 */
	@FXML
    private void handleSalva() {
		
		if(pubc == null) return;
		PubblicazioneCompleta pubModificata = new PubblicazioneCompleta(0, ISBNField.getText(), TitoloField.getText(), EditoreField.getText(),
				0, 0, DataPubblicazioneField.getValue(), null, Integer.parseInt(NumeroPagineField.getText()), LinguaField.getText(), DescrizioneField.getText(), 
				IndiceField.getText(), null, null, null, null);
		
		ControllerPubblicazione.getIstanza().aggiornaPubblicazione(pubc, pubModificata);
		aggiornaContenuto();
		
		edit = true;
//		handleModificaClick();
		
	}
	
	/**
	 * 
	 * Handle del "Esci" button. Esce senza salvare le modifiche.
	 * 
	 */
	@FXML
	private void handleEsci() {
	}
	
	
	
	/**
	 * 
	 * Handle dello "Scrivi Recensione" button. Va alla pagina Scrivi Recensione sulla pubblicazione in analisi.
	 * 
	 */
	@FXML
	private void handleRecensione() {
		
		ControllerSchermo.istanzaManager().activateOnCenter("ScriviRecensione");
		
	}
	
	
	@FXML
	private void handleLeggiRecensioni() {
		
	}
	
	/**
	 * 
	 * Handle del "Like" button. Gestisce il pulsante like.
	 * 
	 */
	@FXML
	private void handleLike() {
		
		// IMPLEMENTARE LIKE
		if(ControllerLike.getInstance().getLike(ControllerSessione.getIstanza().getPubblicazioneSelezionata().getID(), ControllerSessione.getIstanza().getUtenteLoggato().getID())) {
			
			ControllerLike.getInstance().eliminaLike(ControllerSessione.getIstanza().getPubblicazioneSelezionata().getID(), ControllerSessione.getIstanza().getUtenteLoggato().getID());
			this.load();
			
		} else {
			
			ControllerLike.getInstance().inserisciLike(ControllerSessione.getIstanza().getPubblicazioneSelezionata().getID(), ControllerSessione.getIstanza().getUtenteLoggato().getID());
			this.load();
			
		}
		
	}
	
	/**
	 * 
	 * Aggiorna il contenuto della tabella. Salva le modifiche effettuate.
	 * 
	 */
	private void aggiornaContenuto() {
		
		visualizzaRecensioni();
		
		numLikeValue.setVisible(false);
		numRecValue.setVisible(false);
		dataModLabel.setVisible(false);
		dataModValue.setVisible(false);
		
		mostraLink.setText("Mostra tutto");
		
		checkLike();
		
		showAll = false;
		
		listaAutori.clear();
		listaParoleChiave.clear();
		listaRistampe.clear();
		listaSorgenti.clear();
		
		// IMPLEMENTARE LIKE
		
		Integer IDPubblicazione = ControllerSessione.getIstanza().getPubblicazioneSelezionata().getID();
		pubc = ControllerPubblicazione.getIstanza().getDatiPubblicazione(IDPubblicazione);
		
		if(pubc == null) {
			
			titoloLabel.setText("Pubblicazione non esistente");
			ISBNField.setText("");
			titoloLabel.setText("");
			TitoloField.setText("");
			EditoreField.setText("");
			DataPubblicazioneField.setValue(null);
			DescrizioneField.setText("");
			NumeroPagineField.setText("");
			LinguaField.setText("");
			IndiceField.setText("");
			dataModValue.setText("");
			numLikeValue.setText("");
			numRecValue.setText("");
			
		} else {
			
			ISBNField.setText(pubc.getISBN());
			titoloLabel.setText(pubc.getTitolo());
			TitoloField.setText(pubc.getTitolo());
			EditoreField.setText(pubc.getEditore());
			
			
			for(Autore a : pubc.getAutori()) {
				listaAutori.add(a);
			}
			
			NomeAutore.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
	    	CognomeAutore.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCognome()));
	    	
	    	TabellaAutori.setItems(listaAutori);
	    	
	    	
	    	for(ParolaChiave pc : pubc.getTags()) {
				listaParoleChiave.add(pc);
			}
			
			Parola.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getParola()));
	    	
	    	TabellaParole.setItems(listaParoleChiave);
	    	
	    	
	    	for(Ristampa r : pubc.getRistampe()) {
				listaRistampe.add(r);
			}
			
			Numero.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNumero()).asObject());
	    	Data.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDataRistampa().toString()));
	    	
	    	TabellaRistampe.setItems(listaRistampe);
	    	
	    	
	    	for(Sorgente s : pubc.getSorgenti()) {
				listaSorgenti.add(s);
			}
			
			TipoSorgente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipo()));
	    	URISorgente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getURI()));
	    	
	    	TabellaSorgenti.setItems(listaSorgenti);
			
	    	DataPubblicazioneField.setValue(pubc.getDataPubblicazione());
			DescrizioneField.setText(pubc.getDescrizione());
			NumeroPagineField.setText(pubc.getNPag().toString());
			LinguaField.setText(pubc.getLingua());
			IndiceField.setText(pubc.getIndice());
			dataModValue.setText(pubc.getDataUltimaModifica().toString());
			numLikeValue.setText(pubc.getNumLike().toString() + " Likes");
			numRecValue.setText(pubc.getNumRec().toString() + " Recensioni");
			
		}
	}
	
	/**
	 * 
	 * Load method. Metodo invocato automaticamente quando la schermata viene visualizzata.
	 * 
	 */
	@Override
	public void load() {
		
		SalvaButton.setVisible(false);
		TabellaAutori.setPlaceholder(new Label("Nessun Autore"));
		TabellaRistampe.setPlaceholder(new Label("Nessuna Ristampa"));
		TabellaSorgenti.setPlaceholder(new Label("Nessuna Sorgente"));
		TabellaParole.setPlaceholder(new Label("Nessuna Parola Chiave"));
		
		aggiornaContenuto();
		
		modificaButton.setText("Modifica");
		titoloLabel.setVisible(true);
		TitoloField.setVisible(false);
		ISBNField.setEditable(false);
		NumeroPagineField.setEditable(false);
		LinguaField.setEditable(false);
		EditoreField.setEditable(false);
		DataPubblicazioneField.setEditable(false);
		DataPubblicazioneField.setDisable(true);
		IndiceField.setEditable(false);
		DescrizioneField.setEditable(false);
		SalvaButton.setVisible(false);
		
		if(ControllerSessione.getIstanza().getPubblicazioneSelezionata() == null) {
			ControllerSchermo.istanzaManager().activateOnCenter("PannelloGestione");
		}
		
		// CHECK SE L'UTENTE PUO MODIFICARE
		if(ControllerSessione.getIstanza().getUtenteLoggato() == null) {
			
			modificaButton.setVisible(false);
			SalvaButton.setVisible(false);
			RecensioneButton.setVisible(false);
			LikeButton.setVisible(false);
			
		} else if(ControllerSessione.getIstanza().getUtenteLoggato().getRuolo() < 3) {
			
			modificaButton.setVisible(false);
			RecensioneButton.setVisible(true);
			LikeButton.setVisible(true);
		
		} else {
			
			modificaButton.setVisible(true);
			RecensioneButton.setVisible(true);
			LikeButton.setVisible(true);
		}
		
		
		
		
	}

	/**
	 * 
	 * Metodo che gestisce il like del'utente sulla pubblicazione. 
	 * 
	 */
	private void checkLike() {
		
		if(ControllerSessione.getIstanza().getUtenteLoggato() == null ) {
			LikeButton.setVisible(false);
			return;
		}
		
		if(ControllerLike.getInstance().getLike(ControllerSessione.getIstanza().getPubblicazioneSelezionata().getID(), ControllerSessione.getIstanza().getUtenteLoggato().getID())) {
			
			LikeButton.setText(" Rimuovi Like");
			LikeButton.getStyleClass().add("liked");
			
		} else {
			
			LikeButton.setText(" Like");
			LikeButton.getStyleClass().remove("liked");
			
		}
		
	}
	
	/**
	 * 
	 * Metodo che visualizza tutte le recensioni della pubblicazione.
	 * 
	 */
	private void visualizzaRecensioni() {
		
		
		//if(ControllerSessione.getIstanza().getUtenteLoggato() == null) return;
		
		ArrayList<Recensione> listaRecensioni = ControllerRecensione.getInstance().getRecensioniPubblicazione(ControllerSessione.getIstanza().getPubblicazioneSelezionata().getID());
		//System.out.println("lista recensioni " + listaRecensioni);
		if(listaRecensioni.isEmpty()) {
			
			RecPane = new GridPane();
			RecPane.setVisible(false);
			RecensioniLabel.setText("Nessuna Recensione");
		
		} else {
			
			RecPane = new GridPane();
			RecPane.setPadding(new Insets(10, 10, 10, 10));
			RecPane.setVisible(true);
			RecPane.setHgap(10.0);
			RecPane.setVgap(10.0);
			RecScroll.setContent(RecPane);
			RecensioniLabel.setText(listaRecensioni.size() + " Recensioni");
			
			Integer i = 0;
			
			for(Recensione r : listaRecensioni) {
				
				Label nickLab = new Label(ControllerUtente.getIstanza().getUtente(r.getIDUtente()).getNickName());
				nickLab.setStyle("-fx-text-fill : white");
				
				Label recLab = new Label(r.getDescrizione());
				recLab.getStyleClass().add("rec-label");
				recLab.setWrapText(true);
				recLab.setMaxWidth(270);
				
				Button elimina = new Button("Elimina");
				if(ControllerSessione.getIstanza().getUtenteLoggato() != null && ControllerSessione.getIstanza().getUtenteLoggato().getRuolo() >= 3) {
					elimina.setVisible(true);
				} else {
					elimina.setVisible(false);
				}
				
				RecPane.add(nickLab, 0, i);
				RecPane.add(recLab, 0, i+1);
				RecPane.add(elimina, 1, i+1);
				i+=2;
			}
		
		}
		
	}
	
	/**
	 * 
	 * Handle dello "Stessi Autori" button. Cerca pubblcazioni con glis tessi autori.
	 * 
	 */
	@FXML
	private void handleStessiAutori() {
		
		ControllerSchermo.istanzaManager().activateOnCenter("VisualizzaStessiAutori");
		
	}
	
}
