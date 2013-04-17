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

import java.util.LinkedList;
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
import edu.umb.cs.api.APIs;
import edu.umb.cs.demo.DemoSource;
import edu.umb.cs.demo.SourceToken;
import edu.umb.cs.demo.SourceTokens;
import edu.umb.cs.gui.GUI;

public class Tokanagrammar extends Application{
	
	private final static String CURRENT_VERSION = 	"Tokanagrammar " + 
													APIs.getVersion();
	
	static AnchorPane page; 
	
	private static final int FINAL_WIDTH = 886;
	private static final int FINAL_HEIGHT = 689;
	/**The main scene**/
    private static Scene scene;
	/**We need access to primaryStage to assign parent to other stages**/
	private static Stage primaryStage;
	
	/** Store default categories and descriptions here.**/
	public enum Category {														//TODO real names entered here.
		CATEGORY1("Category 1", "This is category one description."),
		CATEGORY2("Category 2", "This is category two description."),
		CATEGORY3("Category 3", "This is category three description."),
		CATEGORY4("Category 4", "This is category four description."),
		CATEGORY5("Category 5", "This is category five description."),
		CATEGORY6("Category 6", "This is category six description.");
		
		private final String name;
		private final String desc;
		
		Category(String name, String desc){
			this.name = name;
			this.desc = desc;
		}
		
		public String getName(){
			return name;
		}
		public String getDesc(){
			return desc;
		}
	}

    public static void main(String[] args) {
        Application.launch(Tokanagrammar.class, (java.lang.String[]) null);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
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
            primaryStage.setTitle(CURRENT_VERSION);

            primaryStage.setResizable(false);
            primaryStage.sizeToScene();
            primaryStage.initStyle(StageStyle.DECORATED);

            //clean up db etc
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){
				@Override
				public void handle(WindowEvent event) {
					
					System.out.println(	"\n\nGUI CLOSING REPORT: +" +
										"(see Tokanagrammar.java)");
					GUI gui = GUI.getInstance();
					System.out.println(	"CURCATEGORIES::: " + 
										gui.getCurCategories());
					System.out.println(	"END GAME STATE::: " + 
										gui.getCurGameState());
					System.out.println(	"CURDIFFICULTY::: " + 
										gui.getCurDifficulty());
				}
            });
            primaryStage.show();
            
            
            //TODO
            //TODO
            //TODO
            //TODO
            /*
             * main entry point -- setup the gui state w/ back end
             */
            //TODO -- Remove the demo
            SourceTokens sourceTokens = new SourceTokens("varied");	//demo
            LinkedList<SourceToken> tokenBoardTokens = 
            		sourceTokens.getRemainingTokens();
            LinkedList<SourceToken> tokenBayTokens = 
            		sourceTokens.getRemovedTokens();
            String metaData = "this is test meta data!";
            LinkedList<String> categories = new LinkedList<String>();
            categories.add(Category.CATEGORY1.getName());
            categories.add(Category.CATEGORY2.getName());
            categories.add(Category.CATEGORY3.getName());
            String[] hints = {"demo hint 1", "demo hint 2", "demo hint 3"};
            
            DemoSource demoSource = new DemoSource(	tokenBoardTokens,
            										tokenBayTokens,
            										metaData,
            										categories,
            										hints);
            
            //TODO Backend load the "default" category names to be displayed.
            LinkedList<Category> availableCategories = new LinkedList<Category>();	//TODO
            availableCategories.add(Category.CATEGORY1);
            availableCategories.add(Category.CATEGORY2);
            availableCategories.add(Category.CATEGORY3);
            availableCategories.add(Category.CATEGORY4);
            availableCategories.add(Category.CATEGORY5);
            availableCategories.add(Category.CATEGORY6);
            
            GUI gui = GUI.getInstance();
            GUI.getInstance().setAvailableCategories(availableCategories);
            gui.setCurDifficulty(50);	//default
            gui.setDemoSource(demoSource); //TODO remove -- selected via GUI ops
            
            GUI.getInstance().gameState_initGUI();	//Start the game!
            
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
