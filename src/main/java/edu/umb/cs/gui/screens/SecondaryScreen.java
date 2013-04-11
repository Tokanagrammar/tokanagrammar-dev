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

/**
 * SecondaryScreen
 * 
 * 
 * Note: Secondary screens are instantiated, they don't act like components of the
 * game board.  On that note this class itself is abstract and cannot be.  You
 * should make to new screen by extending this.
 * @author Matt
 *
 */
public abstract class SecondaryScreen {

	
	private static Stage primaryStage = Tokanagrammar.getStage();
	
	protected static Stage stage;
	protected static Scene scene;
	
	protected static double initX;
	protected static double initY;
	
	protected static Pane page;
	
	public abstract String getName();
	
	public abstract void populateFeatures();
	
	//subclasses use to call super -- avoids having to pass "xxx.fxml"
	public abstract void setupScreen();

	/**
	 * Override to do other things in this window on close.
	 */
	public static void tearDown(){
		stage.close();
	}
	
	/**
	 * A large screen is 528x396 and is centered on the main stage.
	 * @param resource
	 */
	public void setupLargeScreen(String resource){
		try {
			int screenWidth = 528;
			int screenHeight = 396;
			Stage primaryStage = Tokanagrammar.getStage();
			stage = new Stage();
			stage.initStyle(StageStyle.TRANSPARENT);
			stage.initOwner(Tokanagrammar.getStage());
			stage.setWidth(screenWidth);
			stage.setHeight(screenHeight);
			page = FXMLLoader.load(Thread.currentThread().getContextClassLoader().getResource(resource));
			scene = new Scene(page);
			//do not allow the user to access main stage while this is open
			stage.initModality(Modality.APPLICATION_MODAL);
			//place this screen centered in the calling screen
			stage.setX((primaryStage.getX() + primaryStage.getWidth() /2) - stage.getWidth()/2);
			stage.setY((primaryStage.getY() + primaryStage.getHeight() /2) - stage.getHeight()/2);
			stage.setScene(scene);
			stage.show();
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
	
	
	/**
	 * A small screen is xxxxxxxx and is centered on the main stage.
	 * @param resource
	 */
	public void setupSmallScreen(String resource){
		try {
			int screenWidth = 528;
			int screenHeight = 396;
			Stage primaryStage = Tokanagrammar.getStage();
			stage = new Stage();
			stage.initStyle(StageStyle.TRANSPARENT);
			stage.initOwner(Tokanagrammar.getStage());
			stage.setWidth(screenWidth);
			stage.setHeight(screenHeight);
			page = FXMLLoader.load(Thread.currentThread().getContextClassLoader().getResource(resource));
			scene = new Scene(page);
			//do not allow the user to access main stage while this is open
			stage.initModality(Modality.APPLICATION_MODAL);
			//place this screen centered in the calling screen
			stage.setX((primaryStage.getX() + primaryStage.getWidth() /2) - stage.getWidth()/2);
			stage.setY((primaryStage.getY() + primaryStage.getHeight() /2) - stage.getHeight()/2);
			stage.setScene(scene);
			stage.show();
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



}
