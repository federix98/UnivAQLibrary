package app.controller.frontend;

import java.util.EnumSet;

import app.controller.abstracts.ControllerFrontend;
import app.controller.backend.ControllerLog;
import app.model.Log;
import app.model.TipoStoria;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
/**
 * 
 * @author Federico Di Menna
 *
 */
public class LogsController extends ControllerFrontend {

	// FXML VARIABLES
	
	@FXML
	private TableView<Log> TabellaStoria;
	
	@FXML
	private TableColumn<Log, Integer> ISBNtc;
	
	@FXML
	private TableColumn<Log, Integer> Nicknametc;
	
	@FXML
	private TableColumn<Log, String> Timestamptc;
	
	@FXML
	private TableColumn<Log, String> Operazionetc;
	
	@FXML
	private TableColumn<Log, Integer> IDtc;
	
	@FXML
	private ComboBox<String> TipoField;

	@FXML
	private Text DescrizioneText;
	
	@FXML
	private Button Next;
	
	@FXML
	private Button Prev;
	
	private ObservableList<Log> listaLog = FXCollections.observableArrayList();
	
	private FilteredList<Log> filteredLogs;
	
	private Integer NumTotaleLogs;
	
	private Integer ActualPage;
	
	
	/**
	 * Metodo lanciato in automatico quando viene settata la schermata.
	 */
	@Override
	public void load() {
		
		// Inizializzazione della ListBox
		EnumSet.allOf(TipoStoria.class).forEach(TipoStoria -> TipoField.getItems().add(TipoStoria.name()));
		TipoField.getItems().add("TUTTI");
		TipoField.setValue(TipoField.getItems().get(TipoField.getItems().size() - 1));
		
		filteredLogs = new FilteredList<>(listaLog, p -> true);
		
		NumTotaleLogs = ControllerLog.getInstance().getNumLogs();
		ActualPage = 1;
			
		aggiornaTabella();
		aggiornaBottoni();
		
	}
	
	/**
	 * Aggiorna la tabella dei logs in base al numero pagina
	 */
	private void aggiornaTabella() {

		listaLog.clear();
		
		for(Log l : ControllerLog.getInstance().getLogs(ActualPage)) {
			listaLog.add(l);
		}
		
		ISBNtc.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIDPubblicazione()).asObject());
		Nicknametc.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIDUtente()).asObject());
		Timestamptc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTs().toString()));
		Operazionetc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipostoria().name()));
		IDtc.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getID()).asObject());
	
		TabellaStoria.setItems(filteredLogs);
		
		TabellaStoria.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		        DescrizioneText.setText(newSelection.getDescrizione());
		    }
		});
	
	}
	
	/**
	 * Setta se i bottoni pagina precedente e successiva sono cliccabili o meno
	 */
	private void aggiornaBottoni() {
		
		if((ActualPage * 50) >= NumTotaleLogs) {
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
	
	/**
	 * Invocato quando viene cliccato il bottone di pagina successiva. Cambia pagina
	 */
	@FXML
	private void handleNext() {
		
		ActualPage++;
		aggiornaTabella();
		aggiornaBottoni();
		
	}
	
	/**
	 * Invocato quando viene cliccato il bottone di pagina precedente. Cambia pagina
	 */
	@FXML
	private void handlePrev() {
		
		ActualPage--;
		aggiornaTabella();
		aggiornaBottoni();
		
	}

	/**
	 * 
	 * @param event
	 * 
	 * Invocato quando viene settato un nuovo filtro dalla ChoiceBox
	 */
	@FXML
	private void handleTipo(ActionEvent event) {
		
		TipoField.valueProperty().addListener((observable, oldValue, newValue) -> {
		
			filteredLogs.setPredicate(log -> {
				
				if (newValue == null || newValue.isEmpty() || newValue.equals("TUTTI")) {
	                return true;    
	            }
				
	            if (log.getTipostoria().name().equals(newValue)) {
	                return true;
	            }
	            
	            return false; 
	            
        	});
        });
	
	}
	
}
