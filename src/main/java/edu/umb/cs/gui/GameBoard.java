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

import edu.umb.cs.source.SourceFile;
import edu.umb.cs.source.SourceToken;
import edu.umb.cs.source.SourceTokenKind;
import edu.umb.cs.source.std.SourceTokenBase;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GameBoard {
	
	private static Pane legalDragZone;
	private static ObservableList<Node> ldzChildren;
	
	

	private static final GameBoard gameBoard = new GameBoard();
	private GameBoard(){}
	
	/**
	 * There can only be one instance of the LegalDragZone
	 * at any time.  This uses the Singleton pattern to ensure this.
	 * @return an instance of the LegalDragZone
	 */
	public static GameBoard getInstance(){
		
		legalDragZone = Controller.getLegalDragZone();
		
		ldzChildren = legalDragZone.getChildren();
		
		return gameBoard;
	}
	
	//--------------------------------------------------------------------------
	//TOKEN BOARD
	
	static final int TOKENBOARD_OFFSET = 5;
	static final int TOKENBOARD_WIDTH = 510;
	static final int TOKENBOARD_HEIGHT = 433;
	static final int TOKENBOARD_BUFFERSIZE = 1;
	static final int TOKENBOARD_ROWHEIGHT = 35;
	
	private static VBox lhsLineContainer = new VBox();
	
	private static List<Node> lhsLines = lhsLineContainer.getChildren();
	
	private static List<LHSIconizedToken> tokenBoardItokens;
	private static int tokenBoardStartPosition = 	TOKENBOARD_OFFSET + 
													TOKENBOARD_BUFFERSIZE;
	
	/**Places the tokens in the tokenBoard by calling settleTokenBoard**/
	public void initTokenBoard(SourceFile src){
		
		final ScrollPane scrollPane = new ScrollPane();
		scrollPane.setMinWidth(TOKENBOARD_WIDTH);
		scrollPane.setMinHeight(TOKENBOARD_HEIGHT);
		//scrollPane.setVvalue(scrollPane.getVmax());
		scrollPane.setMaxHeight(TOKENBOARD_HEIGHT);
		scrollPane.setMaxWidth(TOKENBOARD_WIDTH);
		scrollPane.setStyle("-fx-background-color: transparent;");
		
		ldzChildren.add(scrollPane);
		lhsLineContainer.setPadding(new Insets(0, 5, 5, 5));		
		scrollPane.setContent(lhsLineContainer);

		tokenBoardItokens = settleTokenBoard(src);
	}
	
	public void resetTokenBoard(){
		tokenBoardStartPosition = TOKENBOARD_OFFSET + TOKENBOARD_BUFFERSIZE;

		lhsLines.removeAll(lhsLines);
		
		tokenBoardItokens.removeAll(tokenBoardItokens);
                
                RHSTokenIconizer.resetIndex();
                LHSTokenIconizer.resetIndex();
	}
	
	/**
	 * Properly formats the LHS.
	 * @param tokens
	 */
        private List<LHSIconizedToken> settleTokenBoard(SourceFile src)
        {
            List<LHSIconizedToken> ret = new ArrayList<>(src.tokenCount());
            
            //TODO line numbering here.
            HBox line = new HBox();
            //int curLine = 1;
            //Text lineNumber = new Text(curLine + "");

            //lineNumber.se
            int lineNumberSize = src.lineCount();
            int lineCount = src.lineCount();

            //line.getChildren().add(lineNumber);
            int printedLine = 0; // not always the same as the 'real' number of line in source
            for (int lineIndex = 0; lineIndex < lineCount; ++lineIndex)
            {
                Text lineNumber = new Text(Integer.toString(printedLine));
                lineNumber.setStyle("-fx-fill:black;");

                for (SourceToken curToken : src.getTokens(lineIndex))
                {
                    final LHSIconizedToken iconizedCurTok = LHSTokenIconizer.iconizeToken(curToken);
                    final double tokenWidth = iconizedCurTok.getImage().getWidth();
                    ret.add(iconizedCurTok);
                    if ((TOKENBOARD_OFFSET + TOKENBOARD_WIDTH) < (tokenBoardStartPosition
                                                        + tokenWidth
                                                        + TOKENBOARD_BUFFERSIZE
                                                        + lineNumberSize))
                    {

                        lineNumber  = new Text(Integer.toString(printedLine));
                        lineNumber.setY(-10);

                        tokenBoardStartPosition = (TOKENBOARD_OFFSET
                                                + TOKENBOARD_BUFFERSIZE);

                        lhsLines.add(line);

                        ++printedLine;
                        line = new HBox();
                        //line.getChildren().add(lineNumber);

                    }

                    ImageView imgView = iconizedCurTok.getImgView();
                    line.getChildren().add(imgView);
                    tokenBoardStartPosition += (tokenWidth + TOKENBOARD_BUFFERSIZE);
                }

                tokenBoardStartPosition = (TOKENBOARD_OFFSET + TOKENBOARD_BUFFERSIZE);
                lhsLines.add(line);
                ++printedLine;
                line = new HBox();
            }
            
            return ret;
        }
	
	//--------------------------------------------------------------------------
	//TOKEN BAY 
	
	static final int TOKENBAY_WIDTH = 225;
	static final int TOKENBAY_BUFFERSIZE = 5;
	static final int TOKENBAY_ROWHEIGHT = 35;
	
	private static List<RHSIconizedToken> tokenBayItokens;
	private static int tokenBayCurrentRow;
	private static int tokenBayStartPosition = 	TOKENBAY_BUFFERSIZE;
	
	private static Pane tokenBay = new Pane();
	
	private static ObservableList<Node> tokenBayChildren;
	
	private static boolean isTokenBayInit = false;
	
	/**Places the tokens in the tokenBay by calling settleTokenBay**/
	public void initTokenBay(List<RHSIconizedToken> iTokens){
		
		//System.out.println("\nTOKENBAY TOKENS INIT! (GameBoard.java):::" + iTokens);

		tokenBayItokens = iTokens;


		if(!isTokenBayInit){
			ldzChildren.add(tokenBay);
			isTokenBayInit = true;
		}
		
		tokenBay.setLayoutX(545);
		tokenBay.setLayoutY(0);
		tokenBay.setMinWidth(225);
		tokenBay.setMinHeight(421);
		tokenBay.setMaxWidth(225);
		tokenBay.setMaxHeight(421);
		//tokenBay event handlers
		tokenBay.setOnDragOver(new EventHandler<DragEvent>(){
			@Override
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				if (db.hasString())
					event.acceptTransferModes(TransferMode.ANY);
			}
			
		});
		
		tokenBay.setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {

				Dragboard db = event.getDragboard();
				boolean success = false;
				if (db.hasString()) {

					List<RHSIconizedToken> iTokens = 
							GameBoard.getInstance().getTokenBayItokens();

					//if it was from this side we don't want to mess w/
					//any data structure, just put the image here.
					
					//otherwise the dragboard content is important, we transfer
					//the token from the LHS to the tokenBay

					//or either way, you could just remove the data structure
					//and replace it like you have before.
					String dragBoardContent = db.getString();
					String delims = "[ ]+";
					String[] strs = dragBoardContent.split(delims);

					SourceToken sourceToken = new SourceTokenBase(strs[1], // image
                                                                                      SourceTokenKind.valueOf(strs[0])); // kind

					String index = strs[2];
					Integer intIndex = Integer.parseInt(index);
					RHSIconizedToken replacementRHSiToken = 
							RHSTokenIconizer.createSingleIconizedToken(sourceToken, intIndex);

					//Replace the blank spot with the iToken.
			        RHSIconizedToken element = iTokens.remove((int)intIndex);
			        iTokens.add(intIndex, replacementRHSiToken);
			        
			        ImageView rImgView = replacementRHSiToken.getImgView();
			        
			        rImgView.setLayoutX(event.getX());
			        rImgView.setLayoutY(event.getY());
			        
			        tokenBayChildren.add(replacementRHSiToken.getImgView());
			        
					success = true;

					event.setDropCompleted(success);
				}

				event.consume();
			}
		});
		
		settleTokenBay(iTokens);
	}
	
	public void resetTokenBay(){
		tokenBayCurrentRow = 0;
		tokenBayStartPosition = TOKENBAY_BUFFERSIZE;
		
		tokenBayChildren.removeAll(tokenBayChildren);
		
		tokenBayItokens.removeAll(tokenBayItokens);
	}
	
	public boolean removeRHSiToken(RHSIconizedToken iToken){
		return tokenBayItokens.remove(iToken);
	}
	
	/**
	 * Uses a circular buffer technique to place all the tokens removed 
	 * from the original source on the RHS in a "best fit" manner.
	 * @param tokens
	 */
	private void settleTokenBay(List<RHSIconizedToken> rhsTokens){
		
		LinkedList<RHSIconizedToken> tooLargeForLine = 
				new LinkedList<RHSIconizedToken>();

		tokenBayChildren = tokenBay.getChildren();

		for(int i=0; i < rhsTokens.size(); i++){
			
			final RHSIconizedToken curToken = rhsTokens.get(i);
			final double tokenWidth = curToken.getImage().getWidth();
			
			if((TOKENBAY_WIDTH) < (	tokenBayStartPosition + 
									tokenWidth + 
									TOKENBAY_BUFFERSIZE) )
				
				tooLargeForLine.add(curToken);
			else{
				ImageView imgView = curToken.getImgView();
				imgView.setLayoutX(tokenBayStartPosition);
				imgView.setLayoutY(tokenBayCurrentRow * TOKENBAY_ROWHEIGHT);
				
				//This is for visual dragging, which hopefully gets fixed soon! 		//TODO re-implement visual dragging.
				curToken.setStartX(imgView.getLayoutX());
				curToken.setStartY(imgView.getLayoutY());
				
				tokenBayChildren.add(imgView);
				tokenBayStartPosition += (tokenWidth + TOKENBAY_BUFFERSIZE);
			}
		}
		
		if(!tooLargeForLine.isEmpty()){
			tokenBayStartPosition = TOKENBAY_BUFFERSIZE;
			tokenBayCurrentRow++;
			settleTokenBay(tooLargeForLine);
		}

	}
	
	
	//--------------------------------------------------------------------------
	//GETTERS / SETTERS
	public Pane getLDZpane(){
		return legalDragZone;
	}
	
	public List<LHSIconizedToken> getTokenBoardItokens(){
		return tokenBoardItokens;
	}
	public List<RHSIconizedToken> getTokenBayItokens(){
		return tokenBayItokens;
	}
	
	//TESTING ONLY
	//CHECK TO SEE IF ALL THE TOKENS ON THE RHS ARE "REMOVED"
	/**
	 * If all the tokens on the RHS have been removed, we have to turn on
	 * the compile button.  This is used to test that case.
	 * @return
	 */
	public boolean isRHSempty(){
		for(RHSIconizedToken rhsItoken: getTokenBayItokens())
                    if (rhsItoken.getSourceToken().kind() != SourceTokenKind.EMPTY)
                        return false;
		return true;
	}
	
}
