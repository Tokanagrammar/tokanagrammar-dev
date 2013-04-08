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

import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Gets the controller's outputPane (the console in the gui)
 * 
 * See writeNodes(Nodes... nodes) for how to send output to console.
 * 
 * @author Matt
 */
public class OutputPanel{

	private static Pane pane;
	private static VBox vBox = new VBox();
	
	private static List<Node> entries = vBox.getChildren();
	/**reposition the scroll bar so it keeps up with our outputPanel**/
	private static boolean rePosScroll = false;
	
	{
		final ScrollPane scrollPane = new ScrollPane();
		scrollPane.setMinWidth(550);
		scrollPane.setMinHeight(84);
		scrollPane.setContent(vBox);
		scrollPane.setVvalue(scrollPane.getVmax());
		scrollPane.setMaxHeight(84);
		scrollPane.setMaxWidth(550);
		
		pane = Controller.getOutputPane();
		pane.getChildren().add(scrollPane);
		
		pane.setMaxHeight(84);
		vBox.setSpacing(8);
		vBox.setPadding(new Insets(3,3,3,3));
		
		//keep the scrollbar pos at the bottom when adding new nodes.
        scrollPane.vvalueProperty().addListener(new ChangeListener<Number>() {
          @Override
          public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            if(rePosScroll) {
              scrollPane.setVvalue(scrollPane.getVmax());
              rePosScroll = false;
            }
          }
        });
	}
	
	private static final OutputPanel outputPanel = new OutputPanel();
	
	private OutputPanel(){}
	
	public static OutputPanel getInstance(){
		return outputPanel;
	}
	
	public void clear(){
		entries.removeAll(entries);
	}
	
	/**
	 * You can write ImageView, Text, Labels, and other Nodes to the
	 * OutputPanel.
	 * 
	 * EX:::	
	 * 
	 * @param nodes
	 */
	public void writeNodes(Node ... nodes){
		HBox hBox = new HBox();
		for(Node node: nodes)
			hBox.getChildren().add(node);
		vBox.getChildren().add(hBox);
		rePosScroll = true;
	}
	
}
