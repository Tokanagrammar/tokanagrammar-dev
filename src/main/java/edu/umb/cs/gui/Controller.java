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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import edu.umb.cs.api.APIs;
import edu.umb.cs.demo.Demo;
import edu.umb.cs.demo.DemoToken;
import edu.umb.cs.entity.Hint;
import edu.umb.cs.entity.Puzzle;
import edu.umb.cs.gui.util.TokenIconizer;
import edu.umb.cs.gui.util.TokenSettler;
import edu.umb.cs.source.SourceFile;
import edu.umb.cs.source.Token;

/**
 * Merge model and View Here
 *
 */
public class Controller implements Initializable{
	
	@FXML
	private AnchorPane mainFrame;
	@FXML 
	private Pane legal_drag_zone;
	
	//panels
	@FXML
	private Pane tokenBoard;
	@FXML
	private static Pane tokenBay;
	@FXML
	private TextArea outputPane;
	
	//timer
	@FXML
	private static Pane timer;
	
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
		outputPane.appendText("Welcome to Tokanagrammar!\n");
		outputPane.appendText("Click Our Logo to Start the Demo.\n");
	}
	
	/**
	 * 
	 * @return
	 */
	public static Pane getTokenBay(){
		return tokenBay;
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
		outputPane.appendText("runFired\n");
	}
	
    /**
     * Called when the stop button is fired.
     *
     * @param event the action event.
     */
	public void stopFired(ActionEvent event){
		outputPane.appendText("stopFired\n");
	}
	
    /**
     * Called when the pause button is fired.
     *
     * @param event the action event.
     */
	public void pauseFired(ActionEvent event){
		outputPane.appendText("pauseFired\n");
		
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
		outputPane.appendText("skipFired\n");
	}
	
    /**
     * Called when the catalog button is fired.
     *
     * @param event the action event.
     */
	public void categoryFired(ActionEvent event){
		outputPane.appendText("catalogFired\n");
	}
	
    /**
     * Called when the difficulty button is fired.
     *
     * @param event the action event.
     */
	public void difficultyFired(ActionEvent event){
		outputPane.appendText("difficultyFired\n");
	}
	
    /**
     * Called when the resetBoard button is fired.
     *
     * @param event the action event.
     */
	public void resetBoardFired(ActionEvent event){
		outputPane.appendText("resetBoardFired\n");
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
			outputPane.appendText("Starting 0.5 Demo\n");
			
			Demo demo = new Demo();
			LinkedList<DemoToken> rhsTokens = demo.getRemovedTokens();
			
			LinkedList<IconizedToken> rhsIconizedTokens = TokenIconizer.iconizeTokens(rhsTokens);
			
			TokenSettler tokenSettler = new TokenSettler();
			tokenSettler.settleRHSTokens(rhsIconizedTokens);
		}else{
			outputPane.appendText("Stopping 0.5 Demo\n");
			//clear tokens
			tokenBay.getChildren().clear();
			//outputPane.clear();
		}
	}
	//--------------------------------------------------------------------------------
	//END GUI BUTTONS
	//--------------------------------------------------------------------------------
}
