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
import edu.umb.cs.gui.GUI.GameState;
import edu.umb.cs.gui.screens.ConfirmRefreshBoard;
import edu.umb.cs.gui.screens.ConfirmSkipScreen;
import edu.umb.cs.gui.screens.CategoriesScreen;
import edu.umb.cs.gui.screens.DifficultyScreen;
import edu.umb.cs.gui.screens.PauseScreen;
import edu.umb.cs.gui.screens.SettingsScreen;
import javafx.event.EventType;


public class Controller implements Initializable{
	
	@FXML
	private AnchorPane mainFrame;
	@FXML 
	private static Pane legalDragZone;
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
	@FXML
	private Button settingsButton;
	
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
		
		Image defaultDiffImg = new Image(getClass().getResourceAsStream("/images/ui/secondaryScreens/difficulty5.fw.png"));
		final ImageView imgView = new ImageView(defaultDiffImg);
		setCurDifficultyIcon(imgView);
		
		imgViewTable = new HashMap<Integer, ImageView>();
		imgViewTable.put(1, new ImageView());
		for(int i=0; i < 11; i++){
			Image img = new Image(DifficultyScreen.class.getResourceAsStream("/images/ui/secondaryScreens/difficulty" + i + ".fw.png"));
			imgViewTable.put(i, new ImageView(img));
		}
	}
	
	public static ImageView getCurDifficultyIcon(){
		return curDifficultyIcon;
	}
	
	public static void setCurDifficultyIcon(final ImageView cdi){
		
		cdi.setFitWidth(80);
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
				GameState gameState = GUI.getInstance().getCurGameState();
				if(gameState.equals(	GameState.INIT_GUI) || 
										gameState.equals(GameState.START_GAME) ||
										gameState.equals(GameState.FULL_LHS))
					GUI.getInstance().pauseGame(new DifficultyScreen());
			}
		});

		difficultyPane.getChildren().remove(getCurDifficultyIcon());
		curDifficultyIcon = cdi;
		difficultyPane.getChildren().add(cdi);
	}
	
	
	public static HashMap<Integer, ImageView> getImgViewTable(){
		return imgViewTable;
	}
	public static Pane getOutputPane(){
		return outputPanel;
	}
	public static Pane getLegalDragZone(){
		return legalDragZone;
	}
	public static Pane getTimer(){
		return timer;
	}
	public static Pane getDifficultyPane(){
		return difficultyPane;
	}
	public static Pane getTokenBay(){
		return tokenBay;
	}
	
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
		GameState gs = GUI.getInstance().getCurGameState();
		if(	gs.equals(GameState.START_GAME) || gs.equals(GameState.FULL_LHS))
			GUI.getInstance().compileNewSource();
	}
	
    /**
     * Called when the stop button is fired.
     * This should only be available when in GameState.Compiling
     * @param event the action event.
     */
	public void stopFired(ActionEvent event){
		GameState gs = GUI.getInstance().getCurGameState();
		if(	gs.equals(GameState.START_GAME) || gs.equals(GameState.FULL_LHS))
			GUI.getInstance().stopCompile();
	}
	
    /**
     * Called when the pause button is fired.
     *
     * @param event the action event.
     */
	public void pauseFired(ActionEvent event){
		GameState gs = GUI.getInstance().getCurGameState();
		if(	gs.equals(GameState.START_GAME)
				|| gs.equals(GameState.FULL_LHS))
			GUI.getInstance().pauseGame(new PauseScreen());
	}
	
    /**
     * Called when the skip button is fired.
     *
     * @param event the action event.
     */
	public void skipFired(ActionEvent event){
		GameState gs = GUI.getInstance().getCurGameState();
		if(	gs.equals(GameState.START_GAME)
				|| gs.equals(GameState.FULL_LHS))
			GUI.getInstance().pauseGame(new ConfirmSkipScreen());
	}
	
    /**
     * Called when the catagory button is fired.
     *
     * @param event the action event.
     */
	public void categoryFired(ActionEvent event){
		GameState gs = GUI.getInstance().getCurGameState();
		if(	gs.equals(GameState.START_GAME)
				|| gs.equals(GameState.FULL_LHS)
				|| gs.equals(GameState.INIT_GUI))
			GUI.getInstance().pauseGame(new CategoriesScreen());
	}
	
	
    /**
     * Called when the resetBoard button is fired.
     *
     * @param event the action event.
     */
	public void resetBoardFired(ActionEvent event){
		GameState gs = GUI.getInstance().getCurGameState();
		if(	gs.equals(GameState.START_GAME)
				|| gs.equals(GameState.FULL_LHS))
			GUI.getInstance().pauseGame(new ConfirmRefreshBoard());
	}
	
    /**
     * Called when the logoButton is fired.
     *
     * @param event the action event.
     */
	public void logoFired(ActionEvent event){
		
		try {
			URI uri = new URI(CURRENT_WEB_ADDRESS);
			java.awt.Desktop.getDesktop().browse(uri);

		} catch (URISyntaxException | IOException e) {
			System.out.println("THROW::: make sure we handle browser error");
			e.printStackTrace();
		}
		
	}
	
    /**
     * Called when the settings button is fired.
     *
     * @param event the action event.
     */
	public void settingsFired(ActionEvent event){
			GUI.getInstance().pauseGame(new SettingsScreen());
	}
	//--------------------------------------------------------------------------------
	//END GUI BUTTONS
	//--------------------------------------------------------------------------------
}
