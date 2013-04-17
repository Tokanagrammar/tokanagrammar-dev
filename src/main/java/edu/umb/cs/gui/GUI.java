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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import edu.umb.cs.Tokanagrammar;
import edu.umb.cs.Tokanagrammar.Category;
import edu.umb.cs.demo.DemoSource;
import edu.umb.cs.demo.SourceToken;
import edu.umb.cs.gui.screens.SecondaryScreen;

/**
 * Handle game states and also work as a main GUI API.
 * @author Matt
 */
public class GUI {
	
	public enum GameState {INIT_GUI, START_GAME, FULL_LHS, COMPILING};
	
	private static GameBoard legalDragZone;
	private static Timer timer;
	private static OutputPanel outputPanel;
	private static GameState curGameState;
	
	/**maps the gameState to a list of active buttons while in this state**/
	private static HashMap<GameState, ArrayList<String>> activeButtons;
	
	private int curDifficulty;
	
	LinkedList<Category> categories= new LinkedList<Category>();

	private DemoSource demoSource;												//TODO replace with real source
	
	private static boolean inGame;
	
	private LinkedList<SourceToken> tokenBayTokens;
	private LinkedList<SourceToken> tokenBoardTokens;
	
	private LinkedList<String> curCategories;
	
	/** Used to blur screen on pausing*/
	private static boolean init  = false;
	
	private static final GUI gui = new GUI();
	private GUI(){}
	
	/**
	 * GUI uses the singleton pattern.
	 * @return
	 */
	public static GUI getInstance(){
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
		blurOff();
		legalDragZone = GameBoard.getInstance();
		outputPanel = OutputPanel.getInstance();
		timer = Timer.getInstance();
		
		printWelcomeMessage();
		
		/*
		 * TEST COMPILER MESSAGE
		 */
		outputPanel.compilerMessage("\n**TESTING COMPILER MESSAGE**This is a compiler message test it should be plenty wide to " +
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
		blurOff();
		//initialization of new start of game
		if(!inGame){
			tokenBayTokens = demoSource.getTokenBayTokens();  		//TODO set w/ setter!
			tokenBoardTokens = demoSource.getTokenBoardTokens();  	//TODO set w/ setter!
			
			legalDragZone.initTokenBoard(LHSTokenIconizer.iconizeTokens(tokenBoardTokens));
			legalDragZone.initTokenBay(RHSTokenIconizer.iconizeTokens(tokenBayTokens));
			
			outputPanel.clear();
			
			printCategoryAndDifficultyMessage();
		}
		
		timer.start();
		inGame = true;
		initButtons(activeButtons.get(curGameState));
	}
		
	
	//--------------------------------------------------------------------------
	//UTIL
	
	/**
	 * Pause the game
	 * All secondary screens go here.
	 */
	public void pauseGame(SecondaryScreen screen){
		timer.stop();
		blurOn();
		screen.setupScreen();
	}
	
	/**
	 * Blurs the main frame of the GUI (it's AnchorPane).
	 */
	public void blurOn(){
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
		legalDragZone.resetTokenBay();
		legalDragZone.resetTokenBoard();
		timer.reset();
		outputPanel.clear();
	}
	
	/**
	 * Refresh the Game
	 * Places all original tokens back to their
	 * original place -- DOES NOT RESET TIMER.
	 * 
	 * GameState is Refresh
	 */
	public void refreshGame(){
		legalDragZone.resetTokenBay();
		legalDragZone.resetTokenBoard();
		inGame = false;
		gameState_startGame();
	}
	
	/**
	 * Skips the current board and goes to the next. 							//TODO backend
	 */
	public void skipPuzzle(){
		System.out.println("<<<Back end for getting the next puzzle>>>");			//TODO backend
		
		outputPanel.clear();

		printCategoryAndDifficultyMessage();
		
		resetGame();
		inGame = false;
		gameState_startGame();
	}
	
	/**
	 * Called when all of the RHS is empty.
	 */
	public void enableCompileButton(){
		System.out.println("ENABLE COMPILE BUTTON CALLED");
		initButtons(activeButtons.get(GameState.FULL_LHS));
	}
	
	/**
	 * Called when there is at least one iToken on the RHS.
	 */
	public void disableCompileButton(){
		initButtons(activeButtons.get(GameState.START_GAME));
	}
	
	/**
	 * Called after all the RHS tokens are on the LHS and the compile btn
	 * was enabled, then pressed.
	 */
	public void compileNewSource(){												//TODO Backend specialty!  
		LinkedList<LHSIconizedToken> tokenList;									//For now, just print formated source code!
		GameBoard gb = GameBoard.getInstance();
		
		tokenList = gb.getTokenBoardItokens();
		
		//make sure the RHS is empty -- it should since we control the comp. btn
		if(GameBoard.getInstance().isRHSempty()){								//TODO PUT CALL COMPILER CODE HERE
			
			System.out.println("\n\nCompiling New Source Code.");
			String compileMessage = "";
			
			//Stop the timer to save the user precious ms while
			//compiling -- restart it immediatly below if there are errors.
			timer.stop();	
			
			for(LHSIconizedToken iToken: tokenList){
				//FOR NOW, DISPLAY THE WHAT THE PROGRAM FILE LOOKS LIKE
				//PRINTS A FORMATED VERSION OF THE SOURCE IN THE OUTPUT PANEL.
				String nextToken = iToken.getSourceToken().getImage();
				System.out.print(nextToken);
				compileMessage += nextToken;
			}
			
			//While compiling, show the stop button.							//TODO while compiling, show the stop button -- this is very crude code for now.
			boolean isCompiling = true;
			if(isCompiling)
				enableStopButton();
			else
				disableStopButton();
			
			boolean success = true;												//TODO -- crude -- need real compiler success of course
			if(success){
				//If source code passes, then show the appropriate message to the user.		//TODO @mhs
				//Think the team agreed would be the database reports page with latest score etc. ?double check.
				timer.stop();
			}else{
				//Output to the output panel using "compiler message" if fails.
				outputPanel.compilerMessage("THERE WAS SOME ERROR HERE OR THERE");
				timer.start();
			}
			outputPanel.clear();
			//For now print the formatted text in the outputPanel too.
			outputPanel.compilerMessage("COMPILING SOURCE CODE... not really.");
			outputPanel.compilerMessage("THIS IS SIMULATING THE COMPILER BEING IN SOME LOOP.");
			outputPanel.compilerMessage("You were trying to compile the following...");
			outputPanel.compilerMessage(compileMessage);
			outputPanel.compilerMessage("\n\nPRESS THE STOP BUTTON TO RECOVER.");
		}
		
	}
	
	/**
	 * Enable stop button.
	 * Only used in "compile state"
	 */
	public void enableStopButton(){
		initButtons(activeButtons.get(GameState.COMPILING));
	}
	
	/**
	 * Disable stop button.
	 * Fired when returning from "compile state".
	 */
	public void disableStopButton(){
		initButtons(activeButtons.get(GameState.START_GAME));
	}
	
	/**
	 * The compile button is turned on while compiling only.
	 * Use this as a fail safe to stop compiling (avoids stack overflow etc).
	 */
	public void stopCompile(){
		Text text = new Text("Stop button needs to be hooked up -- enable WHILE COMPILING ONLY");
		System.out.println("Stop button needs to be hooked up -- enable WHILE COMPILING ONLY");
		outputPanel.writeNodes(text);
		
		//Last, send the user back to the full LHS pseudo state and restart the timer.
		initButtons(activeButtons.get(GameState.FULL_LHS));
		timer.start();
	}
	
	
	//--------------------------------------------------------------------------
	//Static message printing
	
	private void printCategoryAndDifficultyMessage(){
		/*
		 * Message to user	"Category <categories> has been selected on difficulty <difficulty>
		 * 					 Hints: <hints>
		 */
		String concatCategories = "";
		
		Text text;
		if(curCategories.size() > 1)
			text = new Text("Categories Selected: ");
		else
			text = new Text("Category Selected: ");
		for(int i=0; i< curCategories.size(); i++)
			concatCategories += (curCategories.get(i) + " ");
		
		Label categoryText = new Label(concatCategories);
		categoryText.setStyle(	"-fx-font-size: 14; -fx-text-fill: rgb(153, 153, 50);" );
		Text text2 = new Text("Difficulty: ");
		
		DropShadow dropShadow = new DropShadow();
		dropShadow.setColor(Color.GRAY);
		dropShadow.setRadius(30);
		dropShadow.setHeight(30);
		dropShadow.setBlurType(BlurType.ONE_PASS_BOX);
		Label difficultyText = new Label(curDifficulty + "");
		Label difficultyRank = new Label("");
		//difficulty 0-31 easy 32-65 med 66-99 hard
		if(curDifficulty >= 0 && curDifficulty <= 31){
			difficultyRank.setText("(EASY)");
			difficultyRank.setStyle(	"-fx-font-size: 18; -fx-text-fill: rgb(255, 255, 38);" );
			difficultyText.setStyle(	"-fx-font-size: 18; -fx-text-fill: rgb(255, 255, 38);" );
		}
		else if(curDifficulty >= 32 && curDifficulty <= 65){
			difficultyRank.setText("(MEDIUM)");
			difficultyRank.setStyle(	"-fx-font-size: 18; -fx-text-fill: rgb(253, 148, 37);" );
			difficultyText.setStyle(	"-fx-font-size: 18; -fx-text-fill: rgb(253, 148, 37);" );
		}
		else if(curDifficulty >= 66 && curDifficulty <= 89){
			difficultyRank.setText("(HARD)");
			difficultyRank.setStyle(	"-fx-font-size: 18; -fx-text-fill: rgb(255, 0, 0);" );
			difficultyText.setStyle(	"-fx-font-size: 18; -fx-text-fill: rgb(255, 0, 0);" );
		}
		else if(curDifficulty >= 90 && curDifficulty <= 100){
			difficultyRank.setText("(INSANE)");
			difficultyRank.setStyle(	"-fx-font-size: 18; -fx-text-fill: rgb(255, 0, 0);" );
			difficultyText.setStyle(	"-fx-font-size: 18; -fx-text-fill: rgb(255, 0, 0);" );
		}
		difficultyRank.setEffect(dropShadow);
		difficultyText.setEffect(dropShadow);
		
		Text text3 = new Text("Hint: ");
		String hint = demoSource.getHints()[0];									//TODO replace demoSource with real source -- should be random?
		Label hintText = new Label(" < " + hint + " > ");
		hintText.setStyle(	"-fx-font-size: 14; -fx-text-fill: rgb(153, 153, 50);" );
		outputPanel.writeNodes(text, categoryText);
		outputPanel.writeNodes(text2, difficultyText, difficultyRank);
		outputPanel.writeNodes(text3, hintText);
	}
	
	private void printWelcomeMessage(){
		Text welcomeText = new Text("Welcome to Tokanagrammar, Java Edition! ");
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
	 * Get the current categories being played
	 */
	public LinkedList<String> getCurCategories(){
		return curCategories;
	}
	
	/**
	 * Get the OutputPanel
	 */
	public OutputPanel getOutputPanel(){
		return outputPanel;
	}
	
	/**
	 * Get the Timer
	 */
	public Timer getTimer(){
		return timer;
	}
	
	/**
	 * Get the LegalDragZone
	 */
	public GameBoard getLegalDragZone(){
		return legalDragZone;
	}
	
	/**
	 * Get the Source File
	 */
	public DemoSource getSource(){												//TODO
		return this.demoSource;
	}
	
	/**
	 * Get the current RHS tokens -- tokenBay tokens
	 */
	public LinkedList<RHSIconizedToken> getRHSIconizedTokens(){
		return GameBoard.getInstance().getTokenBayItokens();
	}
	
	/**
	 * Get the current LHS tokens
	 */
	public LinkedList<LHSIconizedToken> getLHSIconizedTokens(){
		return GameBoard.getInstance().getTokenBoardItokens();
	}
	
	/**
	 * Set the current difficulty
	 */
	public void setCurDifficulty(int curDifficulty){
		this.curDifficulty = curDifficulty;
	}
	
	/**
	 * Set the current categories being played.
	 */
	public void setCurCategories(LinkedList<String> categories){
		this.curCategories = categories;
	}
	
	/**
	 * Set the AVAILABLE categories
	 */
	public void setAvailableCategories(LinkedList<Category> categories){
		this.categories = categories;
	}
	
	/**
	 * Sets the tokenBay tokens
	 * Used by external API
	 */
	public void setTokenBayTokens(LinkedList<SourceToken> tokens){
		this.tokenBayTokens = tokens;
	}
	
	/**
	 * Sets the tokenBoard tokens
	 * Used by external API
	 */
	public void setTokenBoardTokens(LinkedList<SourceToken> tokens){
		this.tokenBoardTokens = tokens;
	}
	
	/**
	 * 
	 */
	public void setDemoSource(DemoSource source){								//TODO replace with real source
		this.demoSource = source;
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
			System.out.println("ENABLING BUTTON: " + buttonID);
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
		
		//Pseudo game state "full left hand side" (all tokens placed on LHS)
		ArrayList<String> fullLHSbtns = new ArrayList<String>();
		fullLHSbtns.add("runButton");
		fullLHSbtns.add("pauseButton");
		fullLHSbtns.add("skipButton");
		fullLHSbtns.add("categoryButton");
		fullLHSbtns.add("difficultyButton");
		fullLHSbtns.add("resetBoardButton");
		fullLHSbtns.add("logoButton");
		activeButtons.put(GameState.FULL_LHS, fullLHSbtns);
		
		//Pseudo game state "compiling"
		ArrayList<String> compilingBtns = new ArrayList<String>();
		compilingBtns.add("stopButton");
		activeButtons.put(GameState.COMPILING, compilingBtns);
	}
}
