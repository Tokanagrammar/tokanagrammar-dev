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

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import edu.umb.cs.gui.screens.CategoriesScreen;
import edu.umb.cs.gui.screens.DifficultyScreen;


public class Controller implements Initializable{
	
	@FXML
	private AnchorPane mainFrame;
	@FXML 
	private static Pane legalDragZone;
	
	//panels
	@FXML
	private static Pane tokenBoard;
	@FXML
	private static Pane tokenBay;
	@FXML
	private static Pane outputPanel;
	

	@FXML
	private static Pane difficultyPane;
	private static ImageView curDifficultyIcon;
	
	//timer
	@FXML
	private static Pane timer;
	
	//buttons
	@FXML
	private Button runButton;
	@FXML
	private Button stopButton;
	@FXML
	private Button pauseButton;
	@FXML
	private Button skipButton;
	@FXML
	private Button categoryButton;
	@FXML
	private Button resetBoardButton;
	@FXML
	private Button logoButton;
	
	private static LinkedList<Button> buttons;
	
	/**keep this current**/
	private final String CURRENT_WEB_ADDRESS = "http://tokanagrammar.github.io/";
	
	private static HashMap<Integer, ImageView> imgViewTable;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println(this.getClass().getSimpleName() + ".initialize");
		//Store all the buttons together.
		buttons = new LinkedList<Button>();
		buttons.add(runButton);
		buttons.add(stopButton);
		buttons.add(pauseButton);
		buttons.add(skipButton);
		buttons.add(categoryButton);
		buttons.add(resetBoardButton);
		buttons.add(logoButton);
		
		
		//The default difficulty icon is a little less than "50"
		Image defaultDiffImg = new Image(getClass().getResourceAsStream("/images/ui/secondaryScreens/difficulty4.fw.png"));		//TODO
		final ImageView imgView = new ImageView(defaultDiffImg);
		setCurDifficultyIcon(imgView);
		
		imgViewTable = new HashMap<Integer, ImageView>();
		imgViewTable.put(1, new ImageView());
		for(int i=0; i < 10; i++){
			Image img = new Image(DifficultyScreen.class.getResourceAsStream("/images/ui/secondaryScreens/difficulty" + i + ".fw.png"));
			imgViewTable.put(i, new ImageView(img));
		}

	}
	
	/**
	 * 
	 * @return  the current difficulty icon being displayed
	 */
	public static ImageView getCurDifficultyIcon(){
		return curDifficultyIcon;
	}
	
	public static void setCurDifficultyIcon(final ImageView cdi){
		
		cdi.setFitWidth(64);
		cdi.setFitHeight(40);
		
		cdi.setOnMouseEntered(new EventHandler <MouseEvent>() {
			public void handle(MouseEvent event) {
				InnerShadow innerShadow = new InnerShadow();
				innerShadow.setOffsetX(1);
				innerShadow.setOffsetY(1);
				innerShadow.setChoke(0.3);
				innerShadow.setColor(Color.rgb(255, 255, 153));
				cdi.setEffect(innerShadow);
				event.consume();
			}
		});
		
		cdi.setOnMouseExited(new EventHandler <MouseEvent>() {
			public void handle(MouseEvent event) {
				cdi.setEffect(new InnerShadow());
				event.consume();
			}
		});
		
		cdi.setOnMousePressed(new EventHandler <MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				String gameState = GUI.getGameState();
				if(gameState.equals("initGUI") || gameState.equals("startGame")){
					DifficultyScreen ds = new DifficultyScreen();
				}	
				else
					System.out.println("Pause Game Here.");//pause
			}
		});

		
		difficultyPane.getChildren().remove(getCurDifficultyIcon());
		curDifficultyIcon = cdi;
		difficultyPane.getChildren().add(cdi);
	}
	
	
	
	
	public static HashMap<Integer, ImageView> getImgViewTable(){
		return imgViewTable;
	}
	
	
	
	public static Pane getTokenBay(){
		return tokenBay;
	}
	public static Pane getOutputPane(){
		return outputPanel;
	}
	public static Pane getTokenBoard(){
		return tokenBoard;
	}
	public static Pane getLegalDragZone(){
		return legalDragZone;
	}
	public static Pane getTimer(){
		return timer;
	}
	
	
	//this is being updated to allow dynamic change of this button
	//note this feature is more unique than a regular button
	//We can handle all changing here.
	public static Pane getDifficultyPane(){
		return difficultyPane;
	}
	
	
	/**
	 * Initialize the difficultyPane to hold the "difficulty: 50" image
	 */
	
	
	/**
	 * Convenience method to get buttons.
	 * @return a list of this controller's buttons
	 */
	public static LinkedList<Button> getButtons(){
		return buttons;
	}
	
	//--------------------------------------------------------------------------
	//GUI BUTTONS
	//--------------------------------------------------------------------------
    /**
     * Called when the run button is fired.
     *
     * @param event the action event.
     */
	public void runFired(ActionEvent event){
		Text text = new Text("runFired");
		GUI.getOutputPanel().writeNodes(text);
	}
	
    /**
     * Called when the stop button is fired.
     *
     * @param event the action event.
     */
	public void stopFired(ActionEvent event){
		Text text = new Text("stopFired");
		GUI.getOutputPanel().writeNodes(text);
	}
	
    /**
     * Called when the pause button is fired.
     *
     * @param event the action event.
     */
	public void pauseFired(ActionEvent event){
		Text text = new Text("pauseFired");
		OutputPanel.getInstance().writeNodes(text);
		
		
		//little pause demo only
		if(Timer.getInstance().getTimerState().equals("running"))
			Timer.getInstance().pause();
		else if(Timer.getInstance().getTimerState().equals("paused"))
			Timer.getInstance().start();
		if(Timer.getInstance().getTimerState().equals("stopped"))
			Timer.getInstance().start();
		//put in another game state gameStatePauseGame
		//stop the timer if it's running
		
		//blur current screen
        
		//open the 'continue?' dialog box
		
	}
	
    /**
     * Called when the skip button is fired.
     *
     * @param event the action event.
     */
	public void skipFired(ActionEvent event){
		Text text = new Text("skipFired");
		OutputPanel.getInstance().writeNodes(text);
		
		//TEST remove an iToken
		System.out.println("DEBUG::: " + TokenBay.getInstance().remove(TokenBay.getInstance().getITokens().getFirst()));
		System.out.println(TokenBay.getInstance().getITokens());
	}
	
    /**
     * Called when the catagory button is fired.
     *
     * @param event the action event.
     */
	public void categoryFired(ActionEvent event){
		String gameState = GUI.getGameState();
		if(gameState.equals("initGUI") || gameState.equals("startGame")){
			CategoriesScreen cs = new CategoriesScreen();
		}else
			System.out.println("Pause Game Here.");//pause
	}
	
	
    /**
     * Called when the resetBoard button is fired.
     *
     * @param event the action event.
     */
	public void resetBoardFired(ActionEvent event){
		Text text = new Text("resetBoardFired");
		GUI.getOutputPanel().writeNodes(text);
	}
	
    /**
     * Called when the logoButton is fired.
     *
     * @param event the action event.
     */
	public void logoFired(ActionEvent event){
		
		//if(GUI.getGameState().equals("initGUI")){
			try {
				URI uri = new URI(CURRENT_WEB_ADDRESS);
				java.awt.Desktop.getDesktop().browse(uri);

			} catch (URISyntaxException | IOException e) {
				System.out.println("THROW::: make sure we handle browser error");
				e.printStackTrace();
			}
		//}
		
	}
	//--------------------------------------------------------------------------------
	//END GUI BUTTONS
	//--------------------------------------------------------------------------------
}
