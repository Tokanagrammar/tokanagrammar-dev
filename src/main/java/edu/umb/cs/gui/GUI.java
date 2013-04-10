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

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
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
	
	private List<String> curCategories;
	
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
		this.curCategories = new LinkedList<String>();
		
		tokenBay = TokenBay.getInstance();
		tokenBoard = TokenBoard.getInstance();
		legalDragZone = LegalDragZone.getInstance();
		outputPanel = OutputPanel.getInstance();
		timer = Timer.getInstance();
		
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
		
		
		
		
		

		/*
		 * TEST COMPILER MESSAGE
		 */
		outputPanel.compilerMessage("**TESTING COMPILER MESSAGE**This is a compiler message test it should be plenty wide to " +
									"test that it wraps this is a compiler message test it should " + 
									"be plenty wide to test that it wraps this is a compiler message " +
									"test it should be plenty wide to test that it wraps");
		
		
		
		initButtons(activeButtons.get("initGUI"));
	}
	
	
	/**
	 * Start the game
	 * gameState is "startGame"
	 * The user has selected a category (or categories) and pressed "start".
	 */
	public void gameState_startGame(){
		gameState = "startGame";
		timer.start();
		outputPanel.clear();  //start w/a clean outputPanel
		
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


		String concatCategories = "";
		Text text = new Text("Category " + "<");
		for(int i=0; i< curCategories.size(); i++)
			concatCategories += (curCategories.get(i) + " ");
		
		System.out.println("DEBUG::: concatCategories " + concatCategories);
		
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
	 * Get the current categories
	 */
	public List<String> getCurCategories(){
		return curCategories;
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