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

import java.util.HashMap;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import edu.umb.cs.gui.Controller;
import edu.umb.cs.gui.GUI;
import edu.umb.cs.gui.GUI.GameState;
import edu.umb.cs.gui.OutputPanel;


/**
 * 
 * @author Matt
 *
 */
public class DifficultyScreen extends SecondaryScreen{
	
	private static Pane imgDisplay;
	
	private static Slider slider;
	
	private static Pane textPane;

	/**the current image in the imgDisplay**/
	static ImageView curImgInDisplay;
	private static int curDifficultyLevel;
	private final static int DIVISOR = 11;
	private final static int DEFAULT_DIFFICULTY = 10; 


	@Override
	public void setupScreen() {
		super.setupLargeScreen("fxml/Difficulty.fxml");
		populateFeatures();
	}
	public static int getDifficultyLevel(){
		return curDifficultyLevel;
	}

	public static boolean firstRun = true;
	
	public void populateFeatures(){

		imgDisplay = DifficultyScreenController.getImgDisplay();
		textPane = DifficultyScreenController.getTextPane();
		slider = DifficultyScreenController.getSlider();
		//Display the text about how the difficulty selector works.
		textPane.setPadding(new Insets(5, 5, 5, 5));
		textPane.setMaxWidth(textPane.getWidth());
		
		String message = 	"Increasing the difficulty will remove more " +
							"tokens from the original source file.";
		//Remind the user he'll have to restart the game if difficulty sel here.
		if(GUI.getInstance().getCurGameState().equals(GameState.START_GAME))
			message += " You will have to reset the board to do this now.";
		
		//Write the message to the textPane portion of the window.
		Label label = new Label(message);
		label.setStyle(	"-fx-font-size: 20;" +
						"-fx-text-fill: rgb(255, 255, 85);" );
		label.setLayoutX(10);
		label.setLayoutY(20);
		label.setWrapText(true);
		label.setMaxWidth(textPane.getMaxWidth());
		textPane.getChildren().add(label);
		
		//When the slider moves on a certain increment, switch the image.
		slider.setBlockIncrement(DIVISOR);
		slider.setMajorTickUnit(DIVISOR);

		//The icons used to display the difficulty are created with the MAIN
		//CONTROLLER, since it's responsible for displaying the difficultyLevel.
		final HashMap<Integer, ImageView> imgViewTable = Controller.getImgViewTable();
		
		//If it's the first run, we know that the needle should be at about 50%.
		if(firstRun){
			curImgInDisplay = imgViewTable.get(DEFAULT_DIFFICULTY/DIVISOR);
			slider.setValue(50);
			firstRun = false;
		}else{
			//Automatic reposition the img and the slider at the correct val.
			curImgInDisplay = imgViewTable.get(curDifficultyLevel/DIVISOR);
			slider.setValue(curDifficultyLevel);
		}
		imgDisplay.getChildren().add(curImgInDisplay);
		slider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, 
					Number old_val, Number new_val) {
				curDifficultyLevel = (int) slider.getValue();
				if(curDifficultyLevel % DIVISOR == 0){
					imgDisplay.getChildren().remove(curImgInDisplay);
					curImgInDisplay = imgViewTable.get(curDifficultyLevel / DIVISOR);
					imgDisplay.getChildren().add(curImgInDisplay);
				}
			}
		});
	}
	
	/**
	 * Confirms the setting of a newly selected difficulty.  Handles the game-
	 * state
	 */
	public static void executeSetBtnFired(){
		
		GameState gameState = GUI.getInstance().getCurGameState();
		
		//*******Sets the difficultyLevel in in GUI*******
		GUI.getInstance().setCurDifficulty(curDifficultyLevel);
		
		if(gameState.equals(GameState.INIT_GUI)){
			//Message the output panel about the current difficulty level.
			Text difficultyText = new Text("Difficulty Level Set to: ");
			Text difficultyNumberText = new Text(curDifficultyLevel + "");
			difficultyNumberText.setFont(Font.font("Comic Sans MS", FontWeight.BOLD ,14));
			difficultyNumberText.setFill(Color.rgb(153, 153, 255));
			difficultyNumberText.setFont(new Font(14));
			
			OutputPanel.getInstance().writeNodes(difficultyText, difficultyNumberText);
			
		}else if(GUI.getInstance().getCurGameState().equals(GameState.START_GAME)){
			GUI.getInstance().resetGame();
			GUI.getInstance().gameState_initGUI();
			GUI.getInstance().gameState_startGame();
		}
		
		GUI.getInstance().blurOff();
		tearDown();
		
		//update the icon on the main screen to reflect the recent change
		//note that this does not use the original image view, we need to create
		//a new one based on the original image.
		ImageView orig = Controller.getImgViewTable().get(curDifficultyLevel/DIVISOR);
		Image img = orig.getImage();
		ImageView temp = new ImageView(img);
		Controller.setCurDifficultyIcon(temp);
		

	}

	
}
