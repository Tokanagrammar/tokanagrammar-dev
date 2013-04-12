package edu.umb.cs.gui.screens;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import edu.umb.cs.gui.GUI;
import edu.umb.cs.gui.GUI.GameState;

/**
 * These screens are small enough to put the controller and logic together.
 * 
 * @author Matt
 *
 */
public class PauseScreen extends SecondaryScreen implements Initializable {
	
	@FXML
	private static Pane textPane;
	@FXML
	private static Pane bTitleText;
	@FXML
	private static Button confirmYes;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {  }
	
	@Override
	public void setupScreen() {
		super.setupBantamScreen("fxml/Pause.fxml");
		populateFeatures();
	}
	
	public void closeBtnFired(){
		System.out.println("closeBtnFired");
		if(GUI.getInstance().getCurGameState().equals(GameState.START_GAME)){
			GUI.getInstance().blurOff();
			tearDown();
			GUI.getInstance().gameState_startGame();
		}
	}
	

	public void confirmYesBtnFired(){
		System.out.println("confirmYesBtnFired");
		if(GUI.getInstance().getCurGameState().equals(GameState.START_GAME)){
			GUI.getInstance().blurOff();
			tearDown();
			GUI.getInstance().getTimer().start();
		}
	}


	@Override
	public void populateFeatures() {
		Label title = new Label("Game Paused");
		title.setStyle(		"-fx-font-size: 20;" +
							"-fx-text-fill: rgb(255, 255, 85);" );
		title .setLayoutX(5);
		title.setWrapText(true);
		title.setMaxWidth(256);
		bTitleText.getChildren().add(title);
		
		Label label = new Label("Resume?");
		label.setStyle(	"-fx-font-size: 40;" +
						"-fx-text-fill: rgb(255, 255, 85);" );
		label.setLayoutX(5);
		label.setLayoutY(25);
		label.setWrapText(true);
		label.setMaxWidth(256);
		textPane.getChildren().add(label);
	}

}
