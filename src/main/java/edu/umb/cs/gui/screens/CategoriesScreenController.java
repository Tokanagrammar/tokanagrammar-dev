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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import edu.umb.cs.gui.GUI;

/**
 * 
 * @author Matt
 *
 */
public class CategoriesScreenController implements SecondaryScreenController{
	
	@FXML
	private static AnchorPane anchorPane;
	@FXML
	private static Pane leftPane;
	@FXML
	private static Pane rightPane;
	@FXML
	private static Button startBtn;
	@FXML
	private static Button closeBtn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println(this.getClass().getSimpleName() + ".initialize");
	}
	
	public static Pane getLeftPane(){
		return leftPane;
	}
	public static Pane getRightPane(){
		return rightPane;
	}
	public static Button getStartBtn(){
		return startBtn;
	}
	
	public void closeBtnFired(ActionEvent event){
		System.out.println("closeBtn fired!");
		CategoriesScreen.tearDownScreen();
	}
	
//	public void startBtnFired(ActionEvent event){
//		String gameState = GUI.getGameState();
//		if((gameState.equals("initGUI") || gameState.equals("startGame"))){
//			
//			System.out.println("DEBUG::: setting current categories in GUI.java from CategoriesScreen.java");
//			//send the selected categories to the GUI, but let the controller
//			//handle the close of window
//			GUI.getInstance().setCurCategories(CategoriesScreen.getSelectectedCategories());
//			
//			GUI.getInstance().gameState_startGame();
//
//			CategoriesScreen.tearDownScreen();
//		}
//
//	}
}
