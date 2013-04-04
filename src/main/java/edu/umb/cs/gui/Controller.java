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
package edu.umb.cs.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import edu.umb.cs.demo.Demo;

/**
 * Merge model and View Here
 *
 */
public class Controller implements Initializable{
	
	@FXML
	private AnchorPane mainFrame;
	@FXML 
	private static Pane legalDragZone;
	
	//panels
	@FXML
	private static Pane tokenBoard;
	@FXML
	private static Pane tokenBay;
	@FXML
	private static TextArea outputPanel;
	
	//timer
	@FXML
	private Pane timer;
	
	//buttons
	@FXML
	private Button runButton;
	@FXML
	private Button stopButton;
	@FXML
	private Button pauseButton;
	@FXML
	private Button skipButton;
	@FXML
	private Button catalogButton;
	@FXML
	private Button difficultyButton;
	@FXML
	private Button resetBoardButton;
	@FXML
	private Button logoButton;
	
	
	static boolean demoStart = false;		//DEMO 0.5 remove for real implementation
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println(this.getClass().getSimpleName() + ".initialize");
		outputPanel.appendText("Welcome to Tokanagrammar!\n");
		outputPanel.appendText("Click Our Logo to Start the Demo.\n");
	}
	
	/**
	 * 
	 * @return
	 */
	public static Pane getTokenBay(){
		return tokenBay;
	}
	
	public static TextArea getOutputPane(){
		return outputPanel;
	}
	
	public static Pane getTokenBoard(){
		return tokenBoard;
	}
	
	public static Pane getLegalDragZone(){
		return legalDragZone;
	}

	
	//--------------------------------------------------------------------------
	//GUI BUTTONS
	//--------------------------------------------------------------------------
    /**
     * Called when the run button is fired.
     *
     * @param event the action event.
     */
	public void runFired(ActionEvent event){
		outputPanel.appendText("runFired\n");
	}
	
    /**
     * Called when the stop button is fired.
     *
     * @param event the action event.
     */
	public void stopFired(ActionEvent event){
		outputPanel.appendText("stopFired\n");
	}
	
    /**
     * Called when the pause button is fired.
     *
     * @param event the action event.
     */
	public void pauseFired(ActionEvent event){
		outputPanel.appendText("pauseFired\n");
		
		//stop the timer if it's running
		
		//blur current screen
        
		//open the 'continue?' dialog box
		
	}
	
    /**
     * Called when the skip button is fired.
     *
     * @param event the action event.
     */
	public void skipFired(ActionEvent event){
		outputPanel.appendText("skipFired\n");
	}
	
    /**
     * Called when the catalog button is fired.
     *
     * @param event the action event.
     */
	public void categoryFired(ActionEvent event){
		outputPanel.appendText("catalogFired\n");
	}
	
    /**
     * Called when the difficulty button is fired.
     *
     * @param event the action event.
     */
	public void difficultyFired(ActionEvent event){
		outputPanel.appendText("difficultyFired\n");
	}
	
    /**
     * Called when the resetBoard button is fired.
     *
     * @param event the action event.
     */
	public void resetBoardFired(ActionEvent event){
		outputPanel.appendText("resetBoardFired\n");
	}
	
    /**
     * Called when the logoButton is fired.
     *
     * @param event the action event.
     */
//	public void logoFired(ActionEvent event){
//		
//		demoStart = !demoStart; //toggle
//		
//		if(demoStart){
//			
//			APIs.start();
//			APIs.addPuzzle("puzzles/HelloWorld.java", "Hello, world", "meta", (Hint) null);
//			List<Puzzle> puzzles = APIs.getPuzzles();
//			Puzzle testPuzzle = puzzles.get(0);
//			SourceFile source = testPuzzle.getSourceFile();
//			
//			
//		}else{
//			outputPane.appendText("Stopping 0.5 Demo\n");
//			//clear tokens
//			tokenBay.getChildren().clear();
//			//outputPane.clear();
//		}
//	}
	
//	API-less DEMO
	public void logoFired(ActionEvent event){
		
		demoStart = !demoStart; //toggle
		
		if(demoStart){
			outputPanel.appendText("Starting 0.5x Demo\n");
			
			Demo demo = new Demo();
			
		}else{
			outputPanel.appendText("Stopping 0.5 Demo\n");
			//clear tokens
			tokenBay.getChildren().clear();
		}
	}
	//--------------------------------------------------------------------------------
	//END GUI BUTTONS
	//--------------------------------------------------------------------------------
}
