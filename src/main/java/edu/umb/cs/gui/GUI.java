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

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import edu.umb.cs.demo.DemoSource;
import edu.umb.cs.demo.DemoTokens;
import edu.umb.cs.gui.screens.CategoriesScreen;

/**
 * Handle game states and also work as a main GUI API.
 * @author Matt
 */
public class GUI {
	
	/**controller wrappers**/
	private static TokenBay tokenBay;
	private static TokenBoard tokenBoard;
	private static LegalDragZone legalDragZone;
	private static Timer timer;
	private static OutputPanel outputPanel;
	
	/**For now, gameState is a String -- might stay that way**/
	private static String gameState;
	
	/**maps the gameState to a list of active buttons while in this state**/
	private static HashMap<String, ArrayList<String>> activeButtons;
	
	private static boolean showTutorial;										//not implemented.
	
	private static final GUI gui = new GUI();
	
	private int curDifficulty;
	
	private GUI(){}
	
	/**
	 * GUI uses the singleton pattern.
	 * @return
	 */
	public static GUI getInstance(){
		showTutorial = false;													//not implemented.
		setupActiveButtonsTable();
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
		
		gameState = "initGUI";
		System.out.println("GAMESTATE::: " + gameState);
		this.curDifficulty = 50;
		
		tokenBay = TokenBay.getInstance();
		tokenBoard = TokenBoard.getInstance();
		legalDragZone = LegalDragZone.getInstance();
		outputPanel = OutputPanel.getInstance();
		
		if(showTutorial){ }														//not implemented.
		
		//Message Is::	"Category <DEMO> has been selected on difficulty <DEMO>
		//				 Hint: <GET HINTS FROM BACKEND CODE>"
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
		
		initButtons(activeButtons.get("initGUI"));
	}
	
	
	/**
	 * Start the game
	 * gameState is "startGame"
	 * The user has selected a category (or categories) and pressed "start".
	 */
	public void gameState_startGame(){
		gameState = "startGame";
		System.out.println("GAMESTATE::: " + gameState);
		//close the categories screen and begin the game
		CategoriesScreen.tearDownScreen();
		
		//get the output panel and announce the puzzle and the hint(s)
		outputPanel.clear();
		
		/*  TODO
		 *  -- HERE ASSUME THAT THERE IS A CATEGORY SELECTED --
		 *  ** back end randomly selects a source file in this category **
		 *  ** source file is parsed and the tag, meta-data, and source **
		 *  ** file are passed here (implementation may be different    **
		 *  ** but this is what is assumed here).
		 *  -- ALSO ASSUME A DIFFICULTY WAS SELECTED OR USE DEFAULT --
		 *  ** use back end algorithms to get the difficulty, store it  **
		 *  ** in a variable here and return tokens for lhs and rhs.    **
		 *  -- HINTS SHOULD ALSO COME FROM BACK-END -- IS THIS META?    **
		 */
		
		//TODO replace with real source
		DemoSource demoSource = new DemoSource();
		
		//TODO replace with real tokens
		DemoTokens demoTokens = demoSource.getDemoTokens();
		
		//TODO replace argument with real "removed" and "new-source-tokens" tokens
		LinkedList<IconizedToken> lhsIconizedTokens = 
				(TokenIconizer.iconizeTokens(demoTokens.getRemainingTokens()));
		LinkedList<IconizedToken> rhsIconizedTokens = 
				(TokenIconizer.iconizeTokens(demoTokens.getRemovedTokens()));
		
		//tokenBoard.initTokenBoard(lhsIconizedTokens);
		tokenBay.initTokenBay(rhsIconizedTokens);
		
		//announce category, difficulty, and hints
		//for now announce that the demo has begun!
		//TODO See what can be done about formating for output at a lower level than this.
		Text text = new Text("Category ");
		Text categoryText = new Text("<" + demoSource.getCategory() + ">");		//TODO should get from the submitted form
		categoryText.setFill(Color.web("#9999FF"));
		categoryText.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 12));
		Text text2 = new Text(" has been selected on difficulty ");
		Text difficultyText = new Text(curDifficulty + "");						//TODO should get from the submitted form or use default
		difficultyText.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 12));
		difficultyText.setFill(Color.web("#9999FF"));
		Text text3 = new Text("Hint: ");
		//randomly grab one of the hints from the hints array (DemoSource)
		Text hintText = new Text(" <GET HINTS FROM BACKEND CODE> ");			//TODO should get from backend-- place api in GUI.java
		hintText.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 12));
		hintText.setFill(Color.rgb(234, 175, 175));
		outputPanel.writeNodes(text, categoryText, text2, difficultyText);
		outputPanel.writeNodes(text3, hintText);

		initButtons(activeButtons.get("startGame"));
	}
	
	//--------------------------------------------------------------------------
	//Getters / Setters
	
	/**
	 * GameState is set by Controller or logic classes.
	 * @return the current game state
	 */
	public static String getGameState(){
		return gameState;
	}
	
	/**
	 * Get the current difficulty
	 */
	public int getCurDifficulty(){
		return curDifficulty;
	}
	
	/**
	 * Get the OutputPanel
	 */
	public static OutputPanel getOutputPanel(){
		return outputPanel;
	}
	
	/**
	 * Get the TokenBay
	 */
	public static TokenBay getTokenBay(){
		return tokenBay;
	}
	
	/**
	 * Get the TokenBoard
	 */
	public static TokenBoard getTokenBoard(){
		return tokenBoard;
	}
	
	/**
	 * Get the LegalDropZone
	 */
	public static LegalDragZone getLegalDragZone(){
		return legalDragZone;
	}
	
	/**
	 * Set the current difficulty
	 */
	public void setCurDifficulty(int curDifficulty){
		this.curDifficulty = curDifficulty;
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
		
		activeButtons = new HashMap<String, ArrayList<String>>();
		
		//initialization state ("initGUI")
		ArrayList<String> initGuiBtns = new ArrayList<String>();
		initGuiBtns.add("categoryButton");
		initGuiBtns.add("logoButton");
		initGuiBtns.add("difficultyButton");
		activeButtons.put("initGUI", initGuiBtns);
		
		//starting the game state ("startGame")
		ArrayList<String> startGameBtns = new ArrayList<String>();
		startGameBtns.add("pauseButton");
		startGameBtns.add("skipButton");
		startGameBtns.add("categoryButton");
		startGameBtns.add("difficultyButton");
		startGameBtns.add("resetBoardButton");
		startGameBtns.add("logoButton");
		activeButtons.put("startGame", startGameBtns);
	}
}
