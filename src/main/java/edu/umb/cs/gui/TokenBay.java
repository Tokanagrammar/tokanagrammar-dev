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

import java.util.LinkedList;

import edu.umb.cs.gui.IconizedToken.Location;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


/**
 * Controls, harbors tokens on the RHS.
 * 
 * @author Matt
 *
 */
public class TokenBay {

	/**the width and height of the tokenBay**/
	private static int tokenBayWidth;
	private static int tokenBayHeight;
	
	/**row height is 32 per token + 3 for a buffer**/
	static final int RHSROWHEIGHT = 35;
	
	/**spacing between a placed token on the rhs**/
	static final int BUFFERSIZE = 5;
	
	/**where to start placing the rhs tokens --first one starts as buffer**/
	private static int startPosition = BUFFERSIZE;
	
	/**the current row to be added to on the rhs**/
	private static int currentRow;
	
	/**TokenBay pane uses Controller.getTokenBay()**/
	private static Pane pane;
	
	/**An image's base class is a Node**/
	private static ObservableList<Node> nodes;
	
	/**The current tokens contained in tokenBay**/
	private static LinkedList<IconizedToken> iTokens;
	
	private TokenBay(){ }
	
	private static final TokenBay tokenBay = new TokenBay();
	
	public static TokenBay getInstance(){
		pane = Controller.getTokenBay();
		//pane.getChildren().
		tokenBayWidth = (int) pane.getBoundsInLocal().getWidth();
		tokenBayHeight = (int) pane.getBoundsInLocal().getHeight();
		return tokenBay;
	}
	
	public void resetTokenBay(){
		currentRow = 0;
		startPosition = BUFFERSIZE;
		nodes.removeAll(nodes);
		iTokens.removeAll(iTokens);
	}

	
	/**Places the tokens in the tokenBay by calling settleTokenBay**/
	public void initTokenBay(LinkedList<IconizedToken> iTokens){
		TokenBay.iTokens = iTokens;
		nodes = pane.getChildren();

		//set the location of each iToken to TokenBay
		for(IconizedToken token: iTokens)
			token.setBroadLocation(Location.TOKENBAY);
		
		//place the tokens
		settleTokenBay(iTokens);
	}
	
	/**
	 * All IconizedTokens in TokenBay.
	 * @return
	 */
	public LinkedList<IconizedToken> getITokens(){
		return iTokens;
	}
	/**
	 * Take a token out of the tokenBay.
	 * @param iToken the removed IconizedToken
	 * @return
	 */
	public IconizedToken remove(IconizedToken iToken){
		
		IconizedToken copy = iToken;
		nodes.remove(iToken.getImgView());
		iTokens.remove(iToken);
		//temporarily set the iToken loction to NOTPLACED
		copy.setBroadLocation(Location.NOTPLACED);
		
		return copy;
	}
	/**
	 * Place a token into tokenBay.
	 * @param iToken
	 * @return true if successfully added
	 */
	public boolean add(IconizedToken iToken){
		
		if(iTokens.add(iToken)){

			iToken.setBroadLocation(Location.TOKENBAY);

			return true;
		}
		return false;
	}
	
	
	/**
	 * Uses a circular buffer like technique to place all the tokens removed 
	 * from the original source on the RHS in a "best fit" manner.
	 * @param tokens
	 */
	private void settleTokenBay(LinkedList<IconizedToken> rhsTokens){
		
		LinkedList<IconizedToken> tooLargeForLine = new LinkedList<IconizedToken>();
		
		for(int i=0; i < rhsTokens.size(); i++){
			
			IconizedToken curToken = rhsTokens.get(i);
			double tokenWidth = curToken.getImage().getWidth();
			
			if(tokenBayWidth < (startPosition + tokenWidth + BUFFERSIZE) )
				tooLargeForLine.add(curToken);
			else{
				ImageView imgView = curToken.getImgView(); //comes with actions
				imgView.setLayoutX(startPosition);
				imgView.setLayoutY(currentRow * RHSROWHEIGHT);
				nodes.add(imgView);
				startPosition += (tokenWidth + BUFFERSIZE);
				
				//TODO would like to play sound and then populate a token
				//once every 150ms but thread or any kind of loop crashes
				//if you interact with the app
			}
		}
		if(!tooLargeForLine.isEmpty()){
			startPosition = BUFFERSIZE;
			currentRow++;
			settleTokenBay(tooLargeForLine);
		}
	}

}
