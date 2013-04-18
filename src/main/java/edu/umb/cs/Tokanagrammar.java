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


	private static final String TITLE = "Tokanagrammar " + APIs.getVersion();
        
        static AnchorPane page;
        
	private static final int FINAL_WIDTH = 886;
	private static final int FINAL_HEIGHT = 689;
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
            // start all back end services
            APIs.start();

            Tokanagrammar.primaryStage = primaryStage;

            primaryStage.initStyle(StageStyle.DECORATED);
            primaryStage.setWidth(FINAL_WIDTH);
            primaryStage.setHeight(FINAL_HEIGHT);
            primaryStage.setResizable(true); 
        	page = 	(AnchorPane) FXMLLoader.load(Thread.
        						currentThread().getContextClassLoader().
        						getResource("fxml/Tokanagrammar.fxml"));
        	
        	scene = new Scene(page);
        	
            primaryStage.setScene(scene);
            primaryStage.getIcons().add(new Image(Tokanagrammar.class.
            		getResourceAsStream("/images/ui/tokanagrammarIcon.fw.png")));
            primaryStage.setTitle(TITLE);

            primaryStage.setResizable(false);
            primaryStage.sizeToScene();
            primaryStage.initStyle(StageStyle.DECORATED);

            //clean up db etc
            // TODO: add confirmation dialogue?
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){
				@Override
				public void handle(WindowEvent event) {
                                        // stop all services properly
					APIs.stop();
				}
            });
            primaryStage.show();
            
            GUI gui = GUI.getInstance();
            gui.setCurCategories(APIs.getCategories());
            gui.setCurDifficulty(50); // default?
            
            // TODO? Why another call to getInstance()???
            GUI.getInstance().gameState_initGUI();
            
        } catch (Exception ex) {
            Logger.getLogger(Tokanagrammar.class.getName()).
            log(Level.SEVERE, null, ex);
        }
    }

    public static AnchorPane getAnchorPane(){
    	return page;
    }
   
     
    public static Scene getScene(){
    	return scene;
    }
    
    public static Stage getStage(){
    	return primaryStage;
    }

}