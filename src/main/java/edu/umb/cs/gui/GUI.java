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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import edu.umb.cs.Tokanagrammar;
import edu.umb.cs.demo.DemoSource;
import edu.umb.cs.demo.DemoToken;
import edu.umb.cs.demo.DemoTokens;
import edu.umb.cs.gui.screens.SecondaryScreen;

/**
 * Handle game states and also work as a main GUI API.
 * @author Matt
 */
public class GUI {
	
	public enum GameState {INIT_GUI, START_GAME};
	
	private static TokenBay tokenBay;
	private static TokenBoard tokenBoard;
	private static LegalDragZone legalDragZone;
	private static Timer timer;
	private static OutputPanel outputPanel;
	
	private static GameState curGameState;
	
	/**maps the gameState to a list of active buttons while in this state**/
	private static HashMap<GameState, ArrayList<String>> activeButtons;
	
	private int curDifficulty = 50;
	
	private List<String> curCategories = new LinkedList<String>();
	
	private static boolean showTutorial;										//not implemented.
	
	private static boolean inGame;
	
	private LinkedList<DemoToken> tokenBayTokens;
	private LinkedList<DemoToken> tokenBoardTokens;
	
	/** Used to blur screen on pausing*/
	private static boolean blurOn = false;
	private static boolean init  = false;
	
	private static HashMap<String, String> defaultCategories;
	
	private static final GUI gui = new GUI();
	
	private GUI(){}
	
	/**
	 * GUI uses the singleton pattern.
	 * @return
	 */
	public static GUI getInstance(){
		showTutorial = false;													//not implemented.
		
		if(!init){
			setupActiveButtonsTable();
			init = true;
		}

		return gui;
	}
	
	
	
	

	//--------------------------------------------------------------------------
	//GAMESTATES
	
	/**
	 * Initialize the GUI:
	 * gameState is "initGUI"
	 * Welcome the user and give prompt to choose category.
	 */
	public void gameState_initGUI(){
		
		inGame = false;
		curGameState = GameState.INIT_GUI;
		System.out.println("GAMESTATE::: " + curGameState);
		blurOff();
		tokenBay = TokenBay.getInstance();
		tokenBoard = TokenBoard.getInstance();
		legalDragZone = LegalDragZone.getInstance();
		outputPanel = OutputPanel.getInstance();
		timer = Timer.getInstance();
		
		if(showTutorial){ }														//not implemented.

		Text welcomeText = new Text("Welcome to Tokanagrammar! ");
		welcomeText.setFont(new Font(14));
		outputPanel.writeNodes(welcomeText);
		Text categoryText = new Text("Please select a category ");
		categoryText.setFont(new Font(14));
		Image img = new Image(OutputPanel.class.
				getResourceAsStream("/images/ui/categoryButton_console_display_size.fw.png"));
		ImageView imgView = new ImageView(img);
		Text text = new Text(" to continue.");
		text.setFont(new Font(14));
		outputPanel.writeNodes(categoryText, imgView, text);
		
		/*
		 * TEST COMPILER MESSAGE
		 */
		outputPanel.compilerMessage("**TESTING COMPILER MESSAGE**This is a compiler message test it should be plenty wide to " +
									"test that it wraps this is a compiler message test it should " + 
									"be plenty wide to test that it wraps this is a compiler message " +
									"test it should be plenty wide to test that it wraps");
		
		initButtons(activeButtons.get(curGameState));
	}
	
	
	/**
	 * Start the game
	 * gameState is "startGame"
	 * The user has selected a category (or categories) and pressed "start".
	 */
	public void gameState_startGame(){
		curGameState = GameState.START_GAME;
		System.out.println("GAMESTATE::: " + curGameState);
		blurOff();
		//initialization of new start of game
		if(!inGame){
			//TODO  Remove Demo Stuff
			//TODO replace with real source
			DemoSource demoSource = new DemoSource();
			DemoTokens demoTokens = demoSource.getDemoTokens();
			//note that this would be set by the setter from outside code
			tokenBayTokens = demoTokens.getRemovedTokens();		//set w/ setter!
			tokenBoardTokens = demoTokens.getRemainingTokens();	//set w/ setter!
			
			tokenBay.initTokenBay(TokenIconizer.iconizeTokens(tokenBayTokens));
			tokenBay.initTokenBay(TokenIconizer.iconizeTokens(tokenBoardTokens));
			
			outputPanel.clear();  //start w/a clean outputPanel
			
			/*
			 * Message to user	"Category <categories> has been selected on difficulty <difficulty>
			 * 					 Hitnts: <hints>
			 */
			String concatCategories = "";
			Text text = new Text("Category " + "<");
			for(int i=0; i< curCategories.size(); i++)
				concatCategories += (curCategories.get(i) + " ");
			Label categoryText = new Label(concatCategories);
			categoryText.setStyle(	"-fx-font-size: 14; -fx-text-fill: rgb(153, 153, 50);" );
			Text text2 = new Text(">" + " has been selected on difficulty ");
			Label difficultyText = new Label(curDifficulty + "");
			difficultyText.setStyle(	"-fx-font-size: 18; -fx-text-fill: rgb(153, 153, 50);" );
			Text text3 = new Text("Hint: ");
			Label hintText = new Label(" <GET HINTS FROM BACKEND CODE> ");			//TODO should get from backend-- place api in GUI.java
			hintText.setStyle(	"-fx-font-size: 14; -fx-text-fill: rgb(153, 153, 50);" );
			outputPanel.writeNodes(text, categoryText, text2, difficultyText);
			outputPanel.writeNodes(text3, hintText);
		}
		
		timer.start();
		inGame = true;
		initButtons(activeButtons.get(curGameState));
	}
	
	/**
	 * Pause the game
	 * All screens go here.
	 */
	public void pauseGame(SecondaryScreen screen){
		
		timer.stop();
		
		//blur screen
		blurOn();

		screen.setupScreen();

	}
	
	/**
	 * Blurs the main frame of the GUI (it's AnchorPane).
	 */
	public void blurOn(){
		
		System.out.println("BLURON");
		
		AnchorPane mainScreen = Tokanagrammar.getAnchorPane();
		ObservableList<Node> screenComponents = mainScreen.getChildren();
		
		BoxBlur bb = new BoxBlur();
		bb.setIterations(3);
		for(Node node: screenComponents)
			node.effectProperty().set(bb);
			
	}
	
	/**
	 * Turn blur off the main frame.
	 */
	public void blurOff(){
		
		System.out.println("BLUROFF");
		
		AnchorPane mainScreen = Tokanagrammar.getAnchorPane();
		ObservableList<Node> screenComponents = mainScreen.getChildren();
		
		for(Node node: screenComponents)
			node.effectProperty().set(null);
	}
	

	
	/**
	 * Reset the Game
	 * Warning: This reboots the game completely!
	 * If you want to place the orig rhs and lhs tokens
	 * back to their original state, use refresh.
	 * 
	 * GameState is Reset
	 */
	public void resetGame(){
		tokenBay.resetTokenBay();
		timer.reset();
		outputPanel.clear();
	}
	
	/**
	 * Refresh the Game
	 * Places all original tokens back to their
	 * original place.
	 * 
	 * GameState is Refresh
	 */
	public void refreshGame(){
		tokenBay.resetTokenBay();
		inGame = false;
		gameState_startGame();
	}
	
	/**
	 * Skips the current board and goes to the next. //TODO
	 */
	public void skipPuzzle(){
		System.out.println("<<<Back end for skipping a puzzle>>>");		//TODO
		
		outputPanel.clear();
		
		//set the next puzzle via nextPuzzle backend api
		//for now, print a fake
		LinkedList<String> demoCurCategories = new LinkedList<String>();
		
		
		
		setCurCategories(demoCurCategories);
		
		
		printCategoryAndDifficultyMessage();
		
		resetGame();
		inGame = false;
		gameState_startGame();
	}
	
	
	private void printCategoryAndDifficultyMessage(){
		/*
		 * Message to user	"Category <categories> has been selected on difficulty <difficulty>
		 * 					 Hitnts: <hints>
		 */
		String concatCategories = "";
		Text text = new Text("Category " + "<");
		for(int i=0; i< curCategories.size(); i++)
			concatCategories += (curCategories.get(i) + " ");
		Label categoryText = new Label(concatCategories);
		categoryText.setStyle(	"-fx-font-size: 14; -fx-text-fill: rgb(153, 153, 50);" );
		Text text2 = new Text(">" + " has been selected on difficulty ");
		Label difficultyText = new Label(curDifficulty + "");
		difficultyText.setStyle(	"-fx-font-size: 18; -fx-text-fill: rgb(153, 153, 50);" );
		Text text3 = new Text("Hint: ");
		Label hintText = new Label(" <GET HINTS FROM BACKEND CODE> ");			//TODO should get from backend-- place api in GUI.java
		hintText.setStyle(	"-fx-font-size: 14; -fx-text-fill: rgb(153, 153, 50);" );
		outputPanel.writeNodes(text, categoryText, text2, difficultyText);
		outputPanel.writeNodes(text3, hintText);
	}
	
	
	//--------------------------------------------------------------------------
	//Getters / Setters
	
	/**
	 * GameState is set by Controller or logic classes.
	 * @return the current game state
	 */
	public GameState getCurGameState(){
		return curGameState;
	}
	
	/**
	 * Get the current difficulty
	 */
	public int getCurDifficulty(){
		return curDifficulty;
	}
	
	/**
	 * Get the current categories
	 */
	public List<String> getCurCategories(){
		return curCategories;
	}
	
	/**
	 * Get the OutputPanel
	 */
	public OutputPanel getOutputPanel(){
		return outputPanel;
	}
	
	/**
	 * Get the TokenBay
	 */
	public TokenBay getTokenBay(){
		return tokenBay;
	}
	
	/**
	 * Get the TokenBoard
	 */
	public TokenBoard getTokenBoard(){
		return tokenBoard;
	}
	
	/**
	 * Get the Timer
	 */
	public Timer getTimer(){
		return timer;
	}
	
	/**
	 * Get the LegalDropZone
	 */
	public LegalDragZone getLegalDragZone(){
		return legalDragZone;
	}
	
	/**
	 * Set the current difficulty
	 */
	public void setCurDifficulty(int curDifficulty){
		this.curDifficulty = curDifficulty;
	}
	
	/**
	 * Set the current categories
	 */
	public void setCurCategories(List<String> categories){
		this.curCategories = categories;
	}
	
	/**
	 * Sets the tokenBay tokens
	 * Used by external API
	 */
	public void setTokenBayTokens(LinkedList<DemoToken> tokens){
		this.tokenBayTokens = tokens;
	}
	
	/**
	 * Sets the tokenBoard tokens
	 * Used by external API
	 */
	public void setTokenBoardTokens(LinkedList<DemoToken> tokens){
		this.tokenBoardTokens = tokens;
	}
	
	/**
	 * Sets the available categories so they can be referenced elsewhere.
	 */
	public void setDefaultCategories(HashMap<String, String> categories){
		this.defaultCategories = categories;
	}
	
	//--------------------------------------------------------------------------
	//PRIVATE HELPERS
	
	/**
	 * Activate only the buttons the user is allowed to click.
	 * Get the buttons from the controller
	 * @param buttons
	 */
	private void initButtons(ArrayList<String> buttonNames){
		LinkedList<Button> buttons = Controller.getButtons();
		
		for(Button button: buttons){
			String buttonID = button.getId();
			for(String str: buttonNames)
				if(buttonID.equals(str)){
					button.setDisable(false);
					break;
				}else
					button.setDisable(true);
		}
	}
	
	/**
	 * Uses the global activeButtons to map the game's state
	 * to a particular list of active buttons.
	 * 
	 * This is only run once per game.
	 * 
	 * Buttons are 	runButton, stopButton, pauseButton, skipButton,
	 * 				categoryButton, difficultyButton, resetBoardButton,
	 * 				logoButton
	 */
	private static void setupActiveButtonsTable(){
		
		activeButtons = new HashMap<GameState, ArrayList<String>>();
		//initialization state ("initGUI")
		ArrayList<String> initGuiBtns = new ArrayList<String>();
		initGuiBtns.add("categoryButton");
		initGuiBtns.add("logoButton");
		initGuiBtns.add("difficultyButton");
		activeButtons.put(GameState.INIT_GUI, initGuiBtns);
		
		//starting the game state ("startGame")
		ArrayList<String> startGameBtns = new ArrayList<String>();
		startGameBtns.add("pauseButton");
		startGameBtns.add("skipButton");
		startGameBtns.add("categoryButton");
		startGameBtns.add("difficultyButton");
		startGameBtns.add("resetBoardButton");
		startGameBtns.add("logoButton");
		activeButtons.put(GameState.START_GAME, startGameBtns);
	}
}
