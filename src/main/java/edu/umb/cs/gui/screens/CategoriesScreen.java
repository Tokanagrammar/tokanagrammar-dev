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
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
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
	
	private static Pane rightPane;

	public CategoriesScreen(){
		super.setupScreen("fxml/Categories.fxml");
		populateFeatures();
	}
	
	/**
	 * Populate the pane with the current categories available.
	 */
	private void populateFeatures(){

		leftPane  = CategoriesScreenController.getLeftPane();
		rightPane = CategoriesScreenController.getRightPane();
		
		rightPane.setPadding(new Insets(5, 5, 5, 5));
		rightPane.setMaxWidth(rightPane.getWidth());
		
		int rowHeight = 35;
		int rowCount = 0;

		categoryNames = leftPane.getChildren();
		categoryNames.add(new CheckBox("Demo Category 1"));
		categoryNames.add(new CheckBox("Demo Category 2"));
		categoryNames.add(new CheckBox("Demo Category 3"));
		categoryNames.add(new CheckBox("Demo Category 4"));
		categoryNames.add(new CheckBox("Demo Category 5"));
		
		//Map the name of category to the description.
		final HashMap<Node, Label> storedCategories = new HashMap<Node, Label>();
		storedCategories.put(categoryNames.get(0),new Label("This is shown for demo category 1."));
		storedCategories.put(categoryNames.get(1),new Label("This is shown for demo category 2."));
		storedCategories.put(categoryNames.get(2),new Label("This is shown for demo category 3."));
		storedCategories.put(categoryNames.get(3),new Label("This is shown for demo category 4."));
		storedCategories.put(categoryNames.get(4),new Label("This is shown for demo category 5."));
		
		
		for(int i = 0; i < categoryNames.size(); i++){
			Node categoryName = categoryNames.get(i);
			
			final CheckBox category = (CheckBox) categoryName;
			
			categoryName.setLayoutX(10);
			categoryName.setLayoutY(rowHeight * rowCount);
			rowCount++;
			
			categoryName.setStyle(	"-fx-font-size: 20;" +
				    				"-fx-base: rgb(153, 153, 50);" +
				    				"-fx-text-fill: rgb(255, 255, 90);" );

		    category.selectedProperty().addListener(new ChangeListener<Boolean>() {
		        public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
		        	
		        	//TODO
		        	// REPORT THE CATEGORY BACK TO THE CALLING CLASS HERE!

		        	//store the current value(s);
		        	
		        	//TODO 	Highlight the "Start" button when at least one
		        	//		category is selected, otherwise keep it inactive.
		        }
		    });
			category.setOnMouseEntered(new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent event) {
					category.setEffect(new Glow(0.5));
					//display the category info on the rhs
					Label label = (Label) storedCategories.get(category);
					
					label.setStyle(	"-fx-font-size: 20;" +
				    				"-fx-text-fill: rgb(153, 153, 255);" );
					label.setLayoutX(10);
					label.setLayoutY(20);
					label.setWrapText(true);
					label.setMaxWidth(rightPane.getMaxWidth());
					rightPane.getChildren().add(label);
				}
			});
			category.setOnMouseExited(new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent event) {
					category.setEffect(new Glow(0.0));
					rightPane.getChildren().remove(storedCategories.get(category));
				}
			});
		}
	}

	
}
