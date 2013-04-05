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

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JDialog;

import edu.umb.cs.gui.GUI;
import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Tokanagrammar extends Application{
	
	//static Group root = new Group();		//fix this to keep track of everything needing blurring! -mhs
	
    public static void main(String[] args) {
        Application.launch(Tokanagrammar.class, (java.lang.String[]) null);
    }
    
    static Scene scene;

	static final JDialog jDialog = new JDialog();
	
    public static Scene getScene(){
    	return scene;
    }
    
    static public JDialog getJDialog(){
    	return jDialog;
    }

    @Override
    public void start(Stage primaryStage) {
    	
        try {
        	//wrapping in a JDialog is kind of a hack...  JavaFX does not 
        	//support the kind of windowing we want, so this is a work-around.
        	//ie can't open centered, always on top of parent.
			//jDialog.setUndecorated(true);
			//jDialog.setSize(886, 689);				//no hard-coded numbers
			//jDialog.setResizable(false);
			//jDialog.setLocationRelativeTo(null);	//to center in the screen
			
        	AnchorPane page = (AnchorPane) FXMLLoader.load(Thread.currentThread().getContextClassLoader().getResource("fxml/Tokanagrammar.fxml"));
        	this.scene = new Scene(page);
        	
            primaryStage.setScene(scene);
            //primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Tokanagrammar 0.5x");
            
            //JFXPanel fxPanel = new JFXPanel();
            //fxPanel.setScene(scene);
            
            //jDialog.add(fxPanel);
            //jDialog.setVisible(true);
            
            primaryStage.show();
            
            GUI gui = new GUI();
            gui.initGUI();
            
            //root.getChildren().add(page);	// -mhs this is causing roll-overs to stop working, but I'd like to save all layers here.
            //ref BUG --- -mhs experimenting with pause blur here seems to work here, but not in controller where I want it.
            //root.getChildren().get(0).effectProperty().set(new BoxBlur());  //-mhs
        } catch (Exception ex) {
            Logger.getLogger(Tokanagrammar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Getting the group might be helpful in doing any kind of
     * effect for the background.  (pausing is the only thing
     * that comes to mind now... [blurring])
     * @return
     */
    public static Group getRootGroup(){
    	return null;
    	//return root; //-mhs
    }
}
