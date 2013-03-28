package edu.umb.cs.gui;

import java.util.LinkedList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import edu.umb.cs.demo.DemoToken;

/**
 * When the user starts the game, removed tokens are placed on the rhs of the 
 * screen, called the tokenBay, and the remaining tokens are placed on the lhs 
 * of the screen, called the tokenBoard.  Tokens are placed in a "clean" format, with no overrage.
 * @author Matt
 *
 */
public class TokenSettler {
	
	/**spacing between a placed token on the rhs**/
	final int rhsBufferSize = 5;
	
	/**the width of the tokenBay**/
	private int tokenBayWidth;
	private int tokenBayHeight;
	/**where to start placing the rhs tokens --first one starts as buffer**/
	private static int rhsStartPosition = 5;
	
	/**the current row to be added to on the rhs**/
	private static int rhsCurrentRow = 0;
	
	/**row height is 32 per token + 3 for a buffer**/
	private static int rhsRowHeight = 35;
	
	
	private LinkedList<IconizedToken> rhsIconizedTokens;
	
	private Pane tokenBay;
	
	
	public TokenSettler(){
		this.tokenBay = Controller.getTokenBay();
		this.tokenBayWidth = (int) tokenBay.getBoundsInLocal().getWidth();
		this.tokenBayHeight = (int) tokenBay.getBoundsInLocal().getHeight();
		rhsIconizedTokens = new LinkedList<IconizedToken>();
	}
	
	/**
	 * 
	 * @param tokens
	 */
	public void settleRHSTokens(LinkedList<DemoToken> tokens){
		
		//get them all first
		for(DemoToken token : tokens){
			System.out.println(token.getImage());
			rhsIconizedTokens.add(TokenIconizer.iconizeToken(token));
		}
		//ensure no errors and then start placing them
		for(IconizedToken iToken : rhsIconizedTokens){
			settleSingleRHSToken(iToken);
		}
		
	}

	/**
	 * Places a single token on the rhs
	 * This is called consecutively until there are no more tokens.
	 * @param iToken
	 */
	private void settleSingleRHSToken(IconizedToken iToken) {
		
		double tokenWidth = iToken.getImage().getWidth();
		
		//While there's still room left in the row, keep accumulating tokens
		//in the current row
		if(tokenBayWidth > (rhsStartPosition + tokenWidth + rhsBufferSize) ){
			
			//place the token in the token bay at the rhsStartPosition
			Image img = iToken.getImage();
			ImageView imgView = new ImageView(img);
			imgView.setLayoutX(rhsStartPosition);
			imgView.setLayoutY(rhsCurrentRow * rhsRowHeight);
			tokenBay.getChildren().add(imgView);
			
			rhsStartPosition += (tokenWidth + rhsBufferSize);

		}else{
			//start a new row
			rhsStartPosition = 0;
			rhsCurrentRow++;
			
			Image img = iToken.getImage();
			ImageView imgView = new ImageView(img);
			imgView.setLayoutX(rhsStartPosition);
			imgView.setLayoutY(rhsCurrentRow * rhsRowHeight);
			tokenBay.getChildren().add(imgView);
			rhsStartPosition += (tokenWidth + rhsBufferSize);
		}

	}
	
	
	
	
	//also remove rhs tokens when they are placed on lhs
	

}
