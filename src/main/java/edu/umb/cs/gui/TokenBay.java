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

import com.sun.glass.ui.Robot;

import edu.umb.cs.Tokanagrammar;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
/**
 * Controls, harbors tokens on the RHS.
 * 
 * @author Matt
 *
 */
public class TokenBay {

	/**the width and height of the tokenBay**/
	private int tokenBayWidth;
	private int tokenBayHeight;
	
	/**where to start placing the rhs tokens --first one starts as buffer**/
	private static int startPosition = 5;
	
	/**the current row to be added to on the rhs**/
	private static int currentRow = 0;
	
	/**spacing between a placed token on the rhs**/
	final int bufferSize = 5;
	
	/**row height is 32 per token + 3 for a buffer**/
	private static int rhsRowHeight = 35;
	
	private Pane pane;
	/**An image's base class is a Node**/
	private ObservableList<Node> images;
	
	private LinkedList<IconizedToken> iTokens;
	/**initTokenBay can only be called once*/
	static boolean called = false;
	
	public TokenBay(LinkedList<IconizedToken> iTokens){
		this.iTokens = iTokens;
		this.pane = Controller.getTokenBay();
		/**The images corresponding to the IconizedTokens**/
		this.images = pane.getChildren();

		
		
		
		
		this.tokenBayWidth = (int) pane.getBoundsInLocal().getWidth();
		this.tokenBayHeight = (int) pane.getBoundsInLocal().getHeight();
	}
	
	/**Called once and only once. Places the tokens in the tokenBay**/
	public boolean initTokenBay(){
		if(!called){
			settleTokenBay(iTokens);
			called = true;
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
			
			if(tokenBayWidth < (startPosition + tokenWidth + bufferSize) ){
				tooLargeForLine.add(curToken);
			}else{
				Image img = curToken.getImage();
				ImageView imgView = curToken.getImgView();//new ImageView(img);
				imgView.setLayoutX(startPosition);
				imgView.setLayoutY(currentRow * rhsRowHeight);
				images.add(imgView);

				
				
				
				
				
				startPosition += (tokenWidth + bufferSize);
				
				//TODO would like to play sound and then populate a token
				//once every 150ms but thread or any kind of loop crashes
				//if you interact with the app
			}
		}
		if(!tooLargeForLine.isEmpty()){
			startPosition = bufferSize;
			currentRow++;
			settleTokenBay(tooLargeForLine);
		}
	}
	
	
	/**
	 * All of this token bay's iTokens
	 * @return
	 */
	public LinkedList<IconizedToken> getITokens(){
		return iTokens;
	}
	
	//checkInToTokenBay
	
	/**
	 * Take a token out of the tokenBay (often to transfer it elsewhere).
	 * @param iToken
	 * @return
	 */
	public IconizedToken checkOutOfTokenBay(IconizedToken iToken){
		IconizedToken copy = iToken;
		images.remove(iToken.getImage());
		iTokens.remove(iToken);
		return copy;
	}
}
