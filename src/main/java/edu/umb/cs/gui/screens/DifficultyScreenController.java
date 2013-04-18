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

import edu.umb.cs.gui.GUI;
import edu.umb.cs.gui.GUI.GameState;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class DifficultyScreenController implements SecondaryScreenController{
	
	@FXML
	private static AnchorPane anchorPane;
	@FXML
	private static Pane imgDisplay;
	@FXML
	private static Slider slider;
	@FXML
	private static Pane textPane;
	@FXML
	private static Button setBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println(this.getClass().getSimpleName() + ".initialize");
	}
	
	public static Pane getImgDisplay(){
		return imgDisplay;
	}
	
	public static Slider getSlider(){
		return slider;
	}
	
	public static Pane getTextPane(){
		return textPane;
	}
	
	public void closeBtnFired(ActionEvent event){
		if(GUI.getInstance().getCurGameState().equals(GameState.INIT_GUI))
			GUI.getInstance().blurOff();
		else if(GUI.getInstance().getCurGameState().equals(GameState.START_GAME))
			GUI.getInstance().gameState_startGame();
		
		CategoriesScreen.tearDown();
	}
	
	public void setBtnFired(ActionEvent event){
		DifficultyScreen.executeSetBtnFired();
	}
	
}
