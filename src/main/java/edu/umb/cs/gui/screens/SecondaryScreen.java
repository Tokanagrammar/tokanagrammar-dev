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

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import edu.umb.cs.Tokanagrammar;

public abstract class SecondaryScreen {
	

	final static int SCREEN_WIDTH = 528;
	final static int SCREEN_HEIGHT = 396;
	
	private static Stage primaryStage = Tokanagrammar.getStage();
	
	private static Stage stage;
	private static Scene scene;
	
	private static double initX;
	private static double initY;
	
	private static Pane page;
	
	/**override this with setup for this unique secondary screen**/
	private static void populateFeatures(){}
	
	//possibly make part of an abstract type
	public void setupScreen(String resource){
		try {
			Stage primaryStage = Tokanagrammar.getStage();
			stage = new Stage();
			stage.initStyle(StageStyle.TRANSPARENT);
			stage.initOwner(Tokanagrammar.getStage());
			stage.setWidth(SCREEN_WIDTH);
			stage.setHeight(SCREEN_HEIGHT);
			page = FXMLLoader.load(Thread.currentThread().getContextClassLoader().getResource(resource));
			scene = new Scene(page);
			//do not allow the user to access main stage while this is open
			stage.initModality(Modality.APPLICATION_MODAL);
			//place this screen centered in the calling screen
			stage.setX((primaryStage.getX() + primaryStage.getWidth() /2) - stage.getWidth()/2);
			stage.setY((primaryStage.getY() + primaryStage.getHeight() /2) - stage.getHeight()/2);
			stage.setScene(scene);
			stage.show();

			//features of this screen
			populateFeatures();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		//Setup so that if there's any drag across the brow of the child 
		//screen then the screen will be repositioned.
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				initX = me.getSceneX();
				initY = me.getSceneY();
			}
		});
		scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				if (me.getButton() != MouseButton.MIDDLE) {
					scene.getWindow().setX(me.getScreenX() - initX);
					scene.getWindow().setY(me.getScreenY() - initY);
					primaryStage.setX((scene.getWindow().getX() + scene.getWindow().getWidth() /2) - primaryStage.getWidth()/2);
					primaryStage.setY((scene.getWindow().getY() + scene.getWindow().getHeight() /2) - primaryStage.getHeight()/2);
				}
			}
		});
	}
	

	//possibly make part of an abstract type
	public static void tearDownScreen() { 
		stage.close();
	}


}
