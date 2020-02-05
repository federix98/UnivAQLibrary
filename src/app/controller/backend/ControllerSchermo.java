package app.controller.backend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import app.Main;
import app.controller.abstracts.ControllerBackend;
import app.controller.abstracts.ControllerFrontend;
import app.controller.frontend.HomePageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ControllerSchermo extends ControllerBackend {

	
	// INSTANCE OF SINGLETON
	private static ControllerSchermo windowManager = new ControllerSchermo();
	
	// SINGLETON DATA
	private HashMap<String, Pane> screenMap = new HashMap<>();
	private HashMap<String, ControllerFrontend> controllerMap = new HashMap<>();
	public ArrayList<String> privilegi = new ArrayList<String>();
    private Scene main;
    private BorderPane rootLayout;
    private Stage newStage = null;
    private String paneAttuale = null;
    private HomePageController mainController = null;

    
    public Scene getMain() {
		return main;
	}

	public void setMain(Scene main) {
		this.main = main;
	}

	public BorderPane getRootLayout() {
		return rootLayout;
	}

	public void setRootLayout(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}

	// SINGLETON GETINSTANCE
    public static ControllerSchermo istanzaManager() {
    	return windowManager;
    }
    
    private ControllerSchermo() {
    }

    // SINGLETON OPERATIONS
    
    public HashMap<String, Pane> getScreenMap() {
		return screenMap;
	}
    
    public HashMap<String, ControllerFrontend> getControllerMap() {
		return controllerMap;
	}
	
	private void carica(String name, Boolean privileged) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/" + name + ".fxml"));
		try {
			screenMap.put(name, loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}
		controllerMap.put(name, loader.getController());
		if(privileged) privilegi.add(name);
	}
	
	private void caricaRoot(String name, Boolean privileged) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/" + name + ".fxml"));
		try {
			screenMap.put(name, loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mainController = loader.getController();
		controllerMap.put(name, mainController);
		if(privileged) privilegi.add(name);
		this.rootLayout = (BorderPane) screenMap.get("rootLayout");
		//System.out.println(screenMap.get("rootLayout"));
		this.main = new Scene(rootLayout);
	}
	
	public HomePageController getRootController() {
		return mainController;
	}

	public void caricaLayouts() {
    	
		caricaRoot("rootLayout", false);
		
    	carica("AggiungiSorgente", true);
		
    	carica("Login", false);
		
    	carica("PannelloGestione", true);
    	
		carica("VisualizzaCatalogo", false);	
		
		carica("Registrazione", false);
		
		carica("ProfiloUtente", true);
		
		carica("BarraLaterale", true);
		
		carica("Ricerca", false);
		
		carica("VisualizzaPubblicazione", false);
		
		carica("GestioneCatalogo", true);
		
		carica("InserisciPubblicazione", true);
		
		carica("ScriviRecensione", true);
		
		carica("ApprovaRecensioni", true);
		
		carica("Classifiche", true);
		
		carica("Logs", true);
		
		carica("GestioneUtenti", true);
		
		carica("VisualizzaRistampe", false);
		
		carica("VisualizzaStessiAutori", false);
		
		carica("Informazioni", false);
    }
    
    public void addScreen(String name, Pane pane){
         screenMap.put(name, pane);
    }
    
    public void addController(String name, ControllerFrontend controller) {
    	controllerMap.put(name, controller);
    }

    public void removeScreen(String name){
        screenMap.remove(name);
    }
    
    public void removeController(String name){
        controllerMap.remove(name);
    }
    
    public void remove(String name) {
    	screenMap.remove(name);
    	controllerMap.remove(name);
    }

    public void activateOnCenter(String name){
        
    	ControllerSessione.getIstanza().check();
    	
    	// CONTROLLER PULSANTE INDIETRO
    	if(ControllerSessione.getIstanza().getPaginaPrecedente() != null) {
    		getRootController().abilitaIndietro();
    	} else {
    		getRootController().disabilitaIndietro();
    	}
    	
    	rootLayout.setCenter(screenMap.get(name));
    	try {
    		controllerMap.get(name).load();
    	} catch(Exception e) {
    		System.err.println("Errore caricamento controller per schermata : " + name);
    	}
    	paneAttuale = name;
    	
    }
    
    public void openPaneInWindow(String name) {
    	
    	if(screenMap.get(name) != null) remove(name);
    	carica(name, true);
    	
    	newStage = new Stage();
		newStage.setTitle("Progetto OOSD - Libreria");
		
		newStage.getIcons().add(new Image("res/imgs/icon.png"));
		
		Scene mainScene = new Scene(getScreenMap().get(name));
		//mainScene.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
		controllerMap.get(name).load();
		newStage.setScene(mainScene);
		newStage.setResizable(false);
		newStage.show();
    }

	public Stage getNewStage() {
		return newStage;
	}
	
	public void setWaitCursor() {
		main.setCursor(Cursor.WAIT);
	}
	
	public void setDefaultCursor() {
		main.setCursor(Cursor.DEFAULT);
	}
	
	public String getPaneAttuale() {
		return paneAttuale;
	}
}
