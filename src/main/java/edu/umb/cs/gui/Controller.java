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
package main.java.edu.umb.cs.gui;

import java.net.URL;
import java.util.ResourceBundle;

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
	
	//global
	@FXML
	AnchorPane mainFrame;
	@FXML 
	Pane legal_drag_zone;	//global
	
	//control panels
	@FXML
	Button runButton;
	@FXML
	Button stopButton;
	@FXML
	Button pauseButton;
	@FXML
	Button skipButton;
	@FXML
	Button catalogButton;
	@FXML
	Button difficultyButton;
	@FXML
	Button resetBoardButton;
	@FXML
	Button logoButton;	//control panels
	
	//board
	@FXML
	TextArea board;	//board
	
	/**
	 * mhs to look into-- this may not be needed.  See initialize interface documentation.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println(this.getClass().getSimpleName() + ".initialize"); //See javadoc as this probably isn't necessary.
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
	}
	//--------------------------------------------------------------------------------
	//END GUI BUTTONS
	//--------------------------------------------------------------------------------
}
