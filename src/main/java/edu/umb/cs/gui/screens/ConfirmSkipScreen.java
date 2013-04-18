/**
 * Copyright (C) 2013 Tokanagrammar Team
 *
 * This is a jigsaw-like puzzle game,
 * except each piece is token from a source file,
 * and the 'complete picture' is the program.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
public class ConfirmSkipScreen extends SecondaryScreen implements Initializable {
	
	@FXML
	private static Pane textPane;
	@FXML
	private static Pane bTitleText;
	@FXML
	private static Button confirmYes;
	@FXML
	private static Button confirmNo;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) { }
	
	@Override
	public void setupScreen() {
		
		super.setupBantamScreen("fxml/SkipScreen.fxml");
		populateFeatures();
	}
	
	public void closeBtnFired(){
		if(GUI.getInstance().getCurGameState().equals(GameState.START_GAME)){
			GUI.getInstance().blurOff();
			tearDown();
			GUI.getInstance().gameState_startGame();
		}
	}
	
	public void confirmNoBtnFired(){
		if(GUI.getInstance().getCurGameState().equals(GameState.START_GAME)){
			GUI.getInstance().blurOff();
			tearDown();
			GUI.getInstance().gameState_startGame();
		}
	}
	

	public void confirmYesBtnFired(){
		if(GUI.getInstance().getCurGameState().equals(GameState.START_GAME)){
			GUI.getInstance().blurOff();
			tearDown();
			GUI.getInstance().skipPuzzle();
		}
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

	}


}
