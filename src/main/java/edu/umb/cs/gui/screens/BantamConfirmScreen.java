package edu.umb.cs.gui.screens;

import java.net.URL;
import java.util.ResourceBundle;

import edu.umb.cs.gui.GUI;
import edu.umb.cs.gui.GUI.GameState;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * These screens are small enough to put the controller and logic together.
 * 
 * @author Matt
 *
 */
public class BantamConfirmScreen extends SecondaryScreen implements Initializable {
	
	private String name = "confirm";
	
	
	@FXML
	private static Pane textPane;
	@FXML
	private static Pane bTitleText;
	@FXML
	private static Button confirmYes;
	@FXML
	private static Button confirmNo;
	
	
	public BantamConfirmScreen(){
	}
	
	public void closeBtnFired(){
		System.out.println("closeBtnFired");
		
		//REFACTOR BLUR TECHNIQUE
		if(GUI.getInstance().getCurGameState().equals(GameState.START_GAME)){
			GUI.getInstance().blurOff();
			tearDown();
			GUI.getInstance().gameState_startGame();
		}
	}
	
	public void confirmNoBtnFired(){
		System.out.println("confirmNoBtnFired(");
		//REFACTOR BLUR TECHNIQUE
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

			GUI.getInstance().skipPuzzle();
			
			System.out.println("For now, just reset.");
			GUI.getInstance().resetGame();
			GUI.getInstance().gameState_initGUI();
		}
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void populateFeatures() {
		Label title = new Label("Skip Puzzle?");
		title.setStyle(		"-fx-font-size: 20;" +
							"-fx-text-fill: rgb(255, 255, 85);" );
		title .setLayoutX(5);
		//title .setLayoutY(25);
		title.setWrapText(true);
		title.setMaxWidth(256);
		bTitleText.getChildren().add(title);
		
		Label label = new Label("Are you sure you wish to skip this puzzle?");
		label.setStyle(	"-fx-font-size: 20;" +
						"-fx-text-fill: rgb(255, 255, 85);" );
		label.setLayoutX(5);
		label.setLayoutY(25);
		label.setWrapText(true);
		label.setMaxWidth(256);
		textPane.getChildren().add(label);
		

		confirmYes.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				//TODO this is backend code
				if(GUI.getInstance().getCurGameState().equals(GameState.START_GAME)){

					GUI.getInstance().skipPuzzle();
					GUI.getInstance().gameState_startGame();
					tearDown();
				}
			}
		});
		
		
	}

	@Override
	public void setupScreen() {
		super.setupBantamScreen("fxml/BantamGeneric.fxml");
		populateFeatures();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
