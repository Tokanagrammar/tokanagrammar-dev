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
import java.util.ResourceBundle;

import edu.umb.cs.demo.Demo;
import edu.umb.cs.demo.DemoToken;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

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


	static boolean demoStart = false;		//DEMO remove for real implementation


	/**
	 * mhs to look into-- this may not be needed.  See initialize interface documentation.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println(this.getClass().getSimpleName() + ".initialize"); //See javadoc as this probably isn't necessary.
		outputPane.appendText("Welcome to Tokanagrammar!\n");
	}

	//try to access the tokenBay from outside
	public static Pane getTokenBay(){
		return tokenBay;
	}


	//--------------------------------------------------------------------------------
	//GUI BUTTONS
	//--------------------------------------------------------------------------------
    /**
     * Called when the run button is fired.
     *
     * @param event the action event.
     */
	public void runFired(ActionEvent event){
		System.out.println("runFired");
	}

    /**
     * Called when the stop button is fired.
     *
     * @param event the action event.
     */
	public void stopFired(ActionEvent event){
		System.out.println("stopFired");
	}

    /**
     * Called when the pause button is fired.
     *
     * @param event the action event.
     */
	public void pauseFired(ActionEvent event){
		System.out.println("pauseFired");

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
		System.out.println("skipFired");
	}

    /**
     * Called when the catalog button is fired.
     *
     * @param event the action event.
     */
	public void categoryFired(ActionEvent event){
		System.out.println("catalogFired");
	}

    /**
     * Called when the difficulty button is fired.
     *
     * @param event the action event.
     */
	public void difficultyFired(ActionEvent event){
		System.out.println("difficultyFired");
	}

    /**
     * Called when the resetBoard button is fired.
     *
     * @param event the action event.
     */
	public void resetBoardFired(ActionEvent event){
		System.out.println("resetBoardFired");
	}

    /**
     * Called when the logoButton is fired.
     *
     * @param event the action event.
     */
	public void logoFired(ActionEvent event){
		System.out.println("logoFired");

		demoStart = !demoStart; //toggle

		if(demoStart){
			outputPane.appendText("Starting 0.5 Demo\n");

			Demo demo = new Demo();
			LinkedList<DemoToken> rhsTokens = demo.getRemovedTokens();

			//populate our demo tokens in the tokenBay
			TokenSettler tokenSettler = new TokenSettler();

			tokenSettler.settleRHSTokens(rhsTokens);


		}else{
			outputPane.appendText("Stopping 0.5 Demo\n");
			//outputPane.clear();
		}
	}
	//--------------------------------------------------------------------------------
	//END GUI BUTTONS
	//--------------------------------------------------------------------------------
}