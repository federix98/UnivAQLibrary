package app;

import app.controller.backend.ControllerSchermo;
import app.controller.backend.ControllerSessione;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	
	
	/**
	*
	* Metodo chiamato in automatico da Application 
	* al lancio della stessa
	* @param primaryStage  Stage primario dell'applicazione
	* 
	**/
	
	@Override
	public void start(Stage primaryStage) {

		ControllerSchermo.istanzaManager().caricaLayouts();
		/*
		Splash splash = new Splash();
        splash.show();
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(splash.getSplashScene());
        primaryStage.centerOnScreen();
        splash.getTransition().setOnFinished(e -> {
            Timeline timeline = new Timeline();
            KeyFrame key = new KeyFrame(Duration.millis(400),
                    new KeyValue(splash.getSplashScene().getRoot().opacityProperty(), 0));
            timeline.getKeyFrames().add(key);
            timeline.setOnFinished((event) -> {
                this.primaryStage = primaryStage;
				this.primaryStage.setTitle("Progetto OOSD - Libreria");
				//primaryStage.getIcons().add(new Image("res/imgs/icon.png"));
				
				initRootLayout();
				
				ControllerSchermo.istanzaManager().activateOnCenter("Login");
            });
            timeline.play();
        });
        */
        
        this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Progetto OOSD - Libreria");
		//primaryStage.getIcons().add(new Image("res/imgs/icon.png"));
		
		initRootLayout();
		
		ControllerSchermo.istanzaManager().activateOnCenter("Login");
        
        primaryStage.show();
        
        
	}
	/**
	* 
	* Inizializza il layout della scena principale
	* 
	**/
	private void initRootLayout() {
		
		ControllerSchermo.istanzaManager().getMain().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.hide();
		primaryStage.setScene(ControllerSchermo.istanzaManager().getMain());
		primaryStage.setResizable(false);
		ControllerSessione.getIstanza().setPrimaryStage(primaryStage);
		primaryStage.centerOnScreen();
		primaryStage.show();
		
	}

	
	/**
	*
	* Metodo Main
	* @param args  lista parametri in riga di comando
	* 
	**/
	public static void main(String[] args) {
		launch(args);
	}
	
	
	/**
	*
	* Ritorna lo Stage primario
	* 
	**/
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	/**
	*
	* Ritorna il borderpane principale
	* 
	**/
	public BorderPane getRootLayout() {
		return rootLayout;
	}

}
