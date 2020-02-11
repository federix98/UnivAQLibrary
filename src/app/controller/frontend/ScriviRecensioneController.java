package app.controller.frontend;

import app.controller.abstracts.ControllerFrontend;
import app.controller.backend.ControllerRecensione;
import app.controller.backend.ControllerSchermo;
import app.controller.backend.ControllerSessione;
import app.model.Recensione;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
/**
 * 
 * @author Federico Di Menna
 *
 */
public class ScriviRecensioneController extends ControllerFrontend {

	@FXML
	private Label TitoloLabel;
	
	@FXML
	private Label AutoreLabel;
	
	@FXML
	private TextArea TestoRecensione;
	
	@FXML
	private Button PubblicaButton;
	
	/**
     * Invocato quando viene attivata la schermata
     */
	@Override
	public void load() {
		
		TitoloLabel.setText(ControllerSessione.getIstanza().getPubblicazioneSelezionata().getTitolo());
		AutoreLabel.setText("Autore: " + ControllerSessione.getIstanza().getUtenteLoggato().getNickName());
		
	}
	
	/**
	 * Handler per pubblicazione della recensione
	 */
	@FXML
	private void handlePubblica() {
		
		if(TestoRecensione.getText().isBlank() || TestoRecensione.getText().isEmpty()) return;
		
		Integer IDPubblicazione = ControllerSessione.getIstanza().getPubblicazioneSelezionata().getID();
		Integer IDUtente = ControllerSessione.getIstanza().getUtenteLoggato().getID();
		Recensione daPubblicare = new Recensione(IDPubblicazione, IDUtente, TestoRecensione.getText());
		
		ControllerRecensione.getInstance().inserisciRecensione(daPubblicare);
		
		ControllerSchermo.istanzaManager().activateOnCenter("VisualizzaPubblicazione");
		
	}

}
