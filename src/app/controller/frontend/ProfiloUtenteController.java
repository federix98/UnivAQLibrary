package app.controller.frontend;

import app.controller.abstracts.ControllerFrontend;
import app.controller.backend.ControllerPubblicazione;
import app.controller.backend.ControllerSchermo;
import app.controller.backend.ControllerSessione;
import app.controller.backend.ControllerUtente;
import app.model.Pubblicazione;
import app.model.Utente;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ProfiloUtenteController extends ControllerFrontend {

	@FXML
	private TextField NomeField;
	
	@FXML
	private TextField CognomeField;
	
	@FXML
	private TextField EmailField;
	
	@FXML
	private TextField NicknameField;
	
	@FXML
	private PasswordField newPass;
	
	@FXML
	private ComboBox<String> ResidenzaField;
	
	@FXML
	private ComboBox<String> LuogoNascitaField;
	
	@FXML
	private DatePicker DataNascitaField;
	
	@FXML
	private Button SalvaButton;
	
	@FXML
	private Button ModificaButton;
	
	@FXML
	private Button PasswordButton;
	
	@FXML
	private TableView<Pubblicazione> TabellaPubblicazioni;
	
	@FXML
    private TableColumn<Pubblicazione, String> Titolotc;
	
	
	private ObservableList<Pubblicazione> listaPubblicazioni = FXCollections.observableArrayList();
	
	private Boolean activeChangePassword = false;
	
	/**
	 * Metodo invocato quando viene settato il layout. Controlla se l'utente è loggato e riempie i campi
	 */
	public void load() {
		
		activeChangePassword = false;
		newPass.setEditable(false);
		newPass.setDisable(true);
		
		
		// Se un utente ha eseguito l'accesso
		if(ControllerSessione.getIstanza().getUtenteLoggato() != null) {
			
			listaPubblicazioni.clear();
			
			// RIEMPIO I CAMPI UTENTE
			
			for(String s : ControllerUtente.getIstanza().getProvince()) {
				ResidenzaField.getItems().add(s);
				LuogoNascitaField.getItems().add(s);
			}
			
			NomeField.setText(ControllerSessione.getIstanza().getUtenteLoggato().getNome());
			CognomeField.setText(ControllerSessione.getIstanza().getUtenteLoggato().getCognome());
			EmailField.setText(ControllerSessione.getIstanza().getUtenteLoggato().getEmail());
			NicknameField.setText(ControllerSessione.getIstanza().getUtenteLoggato().getNickName());
			DataNascitaField.setValue( ControllerSessione.getIstanza().getUtenteLoggato().getDataNascita() );
			ResidenzaField.setValue(ControllerSessione.getIstanza().getUtenteLoggato().getResidenza());
			LuogoNascitaField.setValue(ControllerSessione.getIstanza().getUtenteLoggato().getLuogoNascita());
			
			
			
			// RIEMPIO LA TABELLA DELLE MIE PUBBLICAZIONI
			
			for(Pubblicazione p : ControllerPubblicazione.getIstanza().getListaPubblicazoniUtente(ControllerSessione.getIstanza().getUtenteLoggato().getID())) {
				listaPubblicazioni.add(p);
			}
			
			Titolotc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitolo()));
			
			TabellaPubblicazioni.setItems(listaPubblicazioni);
		}
		
	}
	
	
	/**
	 * Metodo Handler del bottone Cambia password
	 */
	@FXML
	private void handlePassword() {
		
		activeChangePassword = true;
		newPass.setDisable(false);
		newPass.setEditable(true);
		
	}
	
	/**
	 * Metodo Handler del bottone modifica Pubblicazione
	 */
	@FXML
	private void handleModifica() {
		
		Pubblicazione p = TabellaPubblicazioni.getSelectionModel().getSelectedItem();
		
		if(p != null) {
			
			ControllerSessione.getIstanza().setPubblicazioneSelezionata(TabellaPubblicazioni.getSelectionModel().getSelectedItem());
			ControllerSchermo.istanzaManager().activateOnCenter("VisualizzaPubblicazione");
		
		}
		
	}
	
	
	/**
	 * Metodo Handler del bottone Salva cambiamenti
	 */
	@FXML
	private void handleSalva() {
		
		Utente modificato = ControllerSessione.getIstanza().getUtenteLoggato();
		modificato.setNome(NomeField.getText());
		modificato.setCognome(CognomeField.getText());
		modificato.setDataNascita(DataNascitaField.getValue());
		modificato.setLuogoNascita(LuogoNascitaField.getValue());
		modificato.setResidenza(ResidenzaField.getValue());
		
		
		ControllerUtente.getIstanza().aggiornaUtente(modificato);
		
		if(activeChangePassword) {
			ControllerUtente.getIstanza().cambiaPassword(newPass.getText(), modificato);
		}
		
		load();
	}
	
}
