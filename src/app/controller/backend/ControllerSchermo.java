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
/**
 * 
 * @author Federico Di Menna
 *
 */
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

    /**
     * 
     * @return la scena principale del sistema
     */
    public Scene getMain() {
		return main;
	}

    /**
     * Setta la scena principale del sistema
     * @param main
     */
	public void setMain(Scene main) {
		this.main = main;
	}

	/**
	 * 
	 * @return il root layout del sistema
	 */
	public BorderPane getRootLayout() {
		return rootLayout;
	}

	/**
	 * Setta il rootLayout del sistema
	 * @param rootLayout
	 */
	public void setRootLayout(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}

	/**
	 * 
	 * @return istanza singleton del controller
	 */
    public static ControllerSchermo istanzaManager() {
    	return windowManager;
    }
    
    /**
     * Costruttore privato del controller
     */
    private ControllerSchermo() {
    }

    // SINGLETON OPERATIONS
    
    /**
     * 
     * @return l'hashmap <Nome, Schermata> dei layout del sistema
     */
    public HashMap<String, Pane> getScreenMap() {
		return screenMap;
	}
    
    /**
     * 
     * @return l'hashmap <Nome, Controller> dei controller frontend del sistema
     */
    public HashMap<String, ControllerFrontend> getControllerMap() {
		return controllerMap;
	}
	
    /**Carica il layout e il rispettivo controller nel sistema
     * 
     * @param name
     * @param privileged
     */
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
	
	/**
	 * Carica il rootlayout nel sistema
	 * @param name
	 * @param privileged
	 */
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
	
	/**
	 * 
	 * @return controller della homepage
	 */
	public HomePageController getRootController() {
		return mainController;
	}

	/**
	 * Metodo che carica inizialmente tutti i layout del sistema per limitare i tempi di risposta nell'utilizzo
	 */
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
    
	/**Metodo per aggiungere dinamicamente una schermata
	 * 
	 * @param name
	 * @param pane
	 */
    public void addScreen(String name, Pane pane){
         screenMap.put(name, pane);
    }
    
    /**
     * Metodo per aggiungere dinamicamente un controller
     * @param name
     * @param controller
     */
    public void addController(String name, ControllerFrontend controller) {
    	controllerMap.put(name, controller);
    }

    /**
     * Metodo per rimuovere dinamicamente una schermata
     * @param name
     */
    public void removeScreen(String name){
        screenMap.remove(name);
    }
    
    /**
     * Metodo per rimuovere dinamicamente un controller
     * @param name
     */
    public void removeController(String name){
        controllerMap.remove(name);
    }
    
    /**Metodo per rimuovere dinamicamente schermata e controller
     * 
     * @param name
     */
    public void remove(String name) {
    	screenMap.remove(name);
    	controllerMap.remove(name);
    }

    /**
     * Metodo che attiva al centro dell'applicazione la schermata desiderata
     * @param name
     */
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
    
    /**
     * Metodo che consente di aprire una finestra con il layout scelto
     * @param name
     */
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

    /**
     * 
     * @return il nuovo stage per la finestra
     */
	public Stage getNewStage() {
		return newStage;
	}
	
	/**
	 * Imposta il cursore su attesa
	 */
	public void setWaitCursor() {
		main.setCursor(Cursor.WAIT);
	}
	
	/**
	 * Imposta il cursore su default
	 */
	public void setDefaultCursor() {
		main.setCursor(Cursor.DEFAULT);
	}
	
	/**
	 * 
	 * @return l'attuale schermata
	 */
	public String getPaneAttuale() {
		return paneAttuale;
	}
}
