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

package edu.umb.cs.gui.util;

import java.util.LinkedList;
import edu.umb.cs.gui.Controller;
import edu.umb.cs.gui.IconizedToken;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * When the user starts the game, removed tokens are placed on the rhs of the 
 * screen, called the tokenBay, and the remaining tokens are placed on the lhs 
 * of the screen, called the board.
 * 
 * @author Matt
 *
 */
public class TokenSettler {

	/**the width and height of the tokenBay**/
	private int tokenBayWidth;
	private int tokenBayHeight;
	
	/**where to start placing the rhs tokens --first one starts as buffer**/
	private static int rhsStartPosition = 5;
	
	/**the current row to be added to on the rhs**/
	private static int rhsCurrentRow = 0;
	
	/**spacing between a placed token on the rhs**/
	final int rhsBufferSize = 5;
	
	/**row height is 32 per token + 3 for a buffer**/
	private static int rhsRowHeight = 35;
	
	private Pane tokenBay;
	private Pane board;
	
	
	public TokenSettler(){
		this.tokenBay = Controller.getTokenBay();
		this.tokenBayWidth = (int) tokenBay.getBoundsInLocal().getWidth();
		this.tokenBayHeight = (int) tokenBay.getBoundsInLocal().getHeight();
	}
	
	/**
	 * Uses a circular buffer like technique to place all the tokens removed 
	 * from the original source on the RHS in a "best fit" manner.
	 * @param tokens
	 */
	public void settleRHSTokens(LinkedList<IconizedToken> rhsTokens){
		
		LinkedList<IconizedToken> tooLargeForLine = new LinkedList<IconizedToken>();
		
		for(int i=0; i < rhsTokens.size(); i++){
			
			IconizedToken curToken = rhsTokens.get(i);
			double tokenWidth = curToken.getImage().getWidth();
			
			if(tokenBayWidth < (rhsStartPosition + tokenWidth + rhsBufferSize) ){
				tooLargeForLine.add(curToken);
			}else{
				Image img = curToken.getImage();
				ImageView imgView = new ImageView(img);
				imgView.setLayoutX(rhsStartPosition);
				imgView.setLayoutY(rhsCurrentRow * rhsRowHeight);
				tokenBay.getChildren().add(imgView);
				rhsStartPosition += (tokenWidth + rhsBufferSize);
				
				//TODO would like to play sound and then populate a token
				//once every 150ms but thread or any kind of loop crashes
				//if you interact with the app
			}
		}
		if(!tooLargeForLine.isEmpty()){
			rhsStartPosition = rhsBufferSize;
			rhsCurrentRow++;
			settleRHSTokens(tooLargeForLine);
		}
	}
	
	/**
	 * 
	 * @param lhsTokens
	 */
	public void settleLHSTokens(LinkedList<IconizedToken> lhsTokens){
		
	}
}
