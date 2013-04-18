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

import edu.umb.cs.api.APIs;
import edu.umb.cs.api.CategoryDescriptor;
import edu.umb.cs.gui.GUI;
import edu.umb.cs.gui.GUI.GameState;
import java.util.LinkedList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * A window to select a category.
 * @author Matt
 */
public class CategoriesScreen extends SecondaryScreen{
	
	/**left and right panes will be used to populate data about categories**/
	private static Pane leftPane;
	private static ObservableList<Node> categoryNames;
	private static Button startBtn;
	private static Pane rightPane;
	
	@Override
	public void setupScreen(){
		super.setupLargeScreen("fxml/Categories.fxml");
		populateFeatures();
	}

	
	/**
	 * Populate the pane with the current categories available.
	 */
	public void populateFeatures(){

		leftPane  = CategoriesScreenController.getLeftPane();
		rightPane = CategoriesScreenController.getRightPane();
		
		rightPane.setPadding(new Insets(5, 5, 5, 5));
		rightPane.setMaxWidth(rightPane.getWidth());
		//store the currently selected categories to be processed 'on start' pressed
		final List<CategoryDescriptor> selectedCategories = new LinkedList<>();
		
		startBtn = CategoriesScreenController.getStartBtn();
		startBtn.setDisable(true);

		//Note that this can only be clicked if at least one category is selected.
		//It also sets the game's global selected categories in GUI.java and
		//closes the window.
		startBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {

				GUI.getInstance().setCurCategories(selectedCategories);
				
                                tearDown();
				if(GUI.getInstance().getCurGameState().equals(GameState.INIT_GUI)){
					//This is the one and only entry point into actually playing a game.
					GUI.getInstance().gameState_startGame();
				}else if(GUI.getInstance().getCurGameState().equals(GameState.START_GAME)){
					GUI.getInstance().resetGame();
					GUI.getInstance().gameState_initGUI();
					GUI.getInstance().gameState_startGame();
					GUI.getInstance().setCurCategories(selectedCategories);
				}
				
			}
		});
		
		int rowHeight = 35;
		int rowCount = 0;

		categoryNames = leftPane.getChildren();
		

		for(final CategoryDescriptor category: APIs.getCategories()){
			final CheckBox checkbox = new CheckBox(category.getName());
			final Label label = new Label(category.getDesc());
			categoryNames.add(checkbox);
			
			checkbox.setLayoutX(10);
			checkbox.setLayoutY(rowHeight * rowCount);
			rowCount++;
			
			checkbox.setStyle(		"-fx-font-size: 20;" +
				    				"-fx-base: rgb(153, 153, 50);" +
				    				"-fx-text-fill: rgb(255, 255, 90);" );

			//handle changing a checkbox.
			checkbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
		    	boolean selected = false;
		        public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
		        	//here is our toggle -- keep track of how many are selected
		        	selected = !selected;
		        	if(selected)
		        		selectedCategories.add(category);
		        	else
		        		selectedCategories.remove(category);
		        	
		        	if(selectedCategories.size() > 0)
		        		startBtn.setDisable(false);
		        	else
		        		startBtn.setDisable(true);
		        }
		    });
		    
			checkbox.setOnMouseEntered(new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent event) {
					checkbox.setEffect(new Glow(0.5));
					label.setStyle(	"-fx-font-size: 20;" +
				    				"-fx-text-fill: rgb(153, 153, 255);" );
					label.setLayoutX(10);
					label.setLayoutY(20);
					label.setWrapText(true);
					label.setMaxWidth(rightPane.getMaxWidth());
					rightPane.getChildren().add(label);
				}
			});
			
			checkbox.setOnMouseExited(new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent event) {
					checkbox.setEffect(new Glow(0.0));
					rightPane.getChildren().remove(label);
				}
			});
		}
	}
}
