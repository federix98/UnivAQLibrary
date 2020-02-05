package app.controller.frontend;

import app.controller.abstracts.ControllerFrontend;
import app.controller.backend.ControllerSchermo;
import app.controller.backend.ControllerSessione;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class BarraLateraleController extends ControllerFrontend {

	@FXML
	private AnchorPane GlobalPane;
	
	@FXML
	private Button Pannello;
	
	@FXML
	private Button Logout;
	
	@Override
	public void load() {
		
		GlobalPane.setVisible(true);
		
	}
	
	@FXML
	private void handlePannello() {
		
		ControllerSchermo.istanzaManager().activateOnCenter("PannelloGestione");
		
	}
	
	@FXML
	private void handleLogout() {
		ControllerSessione.getIstanza().logout();
	}

}
