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

package edu.umb.cs;

import edu.umb.cs.api.APIs;
import edu.umb.cs.gui.GUI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class Tokanagrammar extends Application{

	/**The main scene**/
    private static Scene scene;
	/**We need access to primaryStage to assign parent to other stages**/
	private static Stage primaryStage;

    public static void main(String[] args) {
        Application.launch(Tokanagrammar.class, (java.lang.String[]) null);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
        	Tokanagrammar.primaryStage = primaryStage;
			
        	AnchorPane page = 	(AnchorPane) FXMLLoader.load(Thread.
        						currentThread().getContextClassLoader().
        						getResource("fxml/Tokanagrammar.fxml"));
        	
        	scene = new Scene(page);
        	
            primaryStage.setScene(scene);
            primaryStage.getIcons().add(new Image(Tokanagrammar.class.
            		getResourceAsStream("/images/ui/tokanagrammarIcon.fw.png")));
            primaryStage.setTitle("Tokanagrammar " + APIs.getVersion());
            primaryStage.setResizable(false);
            primaryStage.sizeToScene();
            primaryStage.initStyle(StageStyle.DECORATED);

            //clean up db etc
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){
				@Override
				public void handle(WindowEvent event) {
					System.out.println("Handle for saving db.");
				}
            });
            primaryStage.show();
            
            GUI.getInstance().gameState_initGUI();
            
        } catch (Exception ex) {
            Logger.getLogger(Tokanagrammar.class.getName()).
            log(Level.SEVERE, null, ex);
        }
    }

    public static Scene getScene(){
    	return scene;
    }
    
    public static Stage getStage(){
    	return primaryStage;
    }

}
