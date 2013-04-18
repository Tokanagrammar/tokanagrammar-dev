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

import java.io.InputStream;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.ImageCursor;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import edu.umb.cs.Tokanagrammar;
import edu.umb.cs.source.SourceToken;
import edu.umb.cs.source.SourceTokenKind;
import edu.umb.cs.source.std.EmptyToken;
import edu.umb.cs.source.std.SourceTokenBase;
import java.util.List;


/**
 * A graphical representation of a SourceToken.
 * @author Matt
 */
public class LHSIconizedToken extends IconizedToken{

	private double startX;
	private double startY;
	
	/**The image representation of a token**/
	private Image image;
	/**The original token**/
	private SourceToken token;
	
	/**the order this token arrived in the token bay**/
	private int index;
	
	/**Flag to tell when token is changed from a "removed" to a real Type.**/
	private boolean occupied;

	private ImageView imgView = new ImageView();

	public LHSIconizedToken(Image image, SourceToken token, int index){
		this.image = image;
		this.token = token;
		this.index = index;
		this.occupied = false;
		this.imgView = initImageView(image);
	}

	public Image getImage(){
		return image;
	}
	public SourceToken getSourceToken(){
		return token;
	}
	public ImageView getImgView(){
		return imgView;
	}
	public int getIndex(){
		return index;
	}
	
	/**
	 * The original location used for setting and tracking the new location of a
	 * token being dragged.  This is used for settling in the LHSTokenIconizer.
	 */
	public void setStartX(double startX){
		this.startX = startX;
	}
	public void setStartY(double startY){
		this.startY = startY;
	}

	/**
	 * There are no equal LHSIconizedTokens
	 */
//	public boolean equals(Object obj) {
//		if(obj instanceof LHSIconizedToken){
//			return 	token.equals(((LHSIconizedToken) obj).getSourceToken()) && 
//					index == (((LHSIconizedToken) obj).getIndex());
//		}
//		else return false;
//	}

//	public int hashCode() {
//		return token.hashCode();
//	}

	public String dragBoardString(){
		return token.kind() + " " + token.image() + " " + index;
	}

	protected ImageView initImageView(Image img){

		final ImageView imgView = new ImageView(img);

		//All LHSIconizedTokens in play are initially "removed" tokens.
		//we only want to be able to have actions over the removed tokens
		//all the others are no-ops
		if(token.kind() == SourceTokenKind.EMPTY){
			
				imgView.setOnDragOver(new EventHandler<DragEvent>() {
					
					@Override
					public void handle(DragEvent event) {
						//System.out.println("DRAG OVER TOKEN TYPE IS: " + token.getType());
//						Image image = new Image("/images/ui/legalDropIcon.fw.png");
//						Tokanagrammar.getScene().setCursor(new ImageCursor(image,
//	                            image.getWidth() / 2,
//	                            image.getHeight() /2));

						if(!occupied){
							Dragboard db = event.getDragboard();
							imgView.setEffect(new Glow(0.7));
							if (db.hasString())
								event.acceptTransferModes(TransferMode.ANY);
						}
				        event.consume();
					}
				});

			
			imgView.setOnDragDropped(new EventHandler<DragEvent>() {
				public void handle(DragEvent event) {
					
					if(!occupied){
						Dragboard db = event.getDragboard();
						boolean success = false;
						if (db.hasString()) {
						
							List<LHSIconizedToken> iTokens = 
									GameBoard.getInstance().getTokenBoardItokens();
							
							String dragBoardContent = db.getString();
							String delims = "[ ]+";
							String[] strs = dragBoardContent.split(delims);
							
							SourceToken sourceToken = new SourceTokenBase(strs[1], // image
                                                                                                      SourceTokenKind.valueOf(strs[0])); // kind
							
							//Replace the current underlying source token with new.
							token = sourceToken;
							LHSIconizedToken replacementLHSiToken = 
									LHSTokenIconizer.createSingleIconizedToken(sourceToken, index);

							//Replace the blank image with the dropped token.
					        LHSIconizedToken element = iTokens.remove(index);
					        iTokens.add(index, replacementLHSiToken);
					        
					        //Update the current image view since it's final.
					        ImageView curImgView = replacementLHSiToken.getImgView();
					        Image curImg = replacementLHSiToken.getImgView().getImage();
					        
					        imgView.setImage(curImg);
					        //imgView.resize(curImgView.getFitWidth(), curImgView.getFitHeight());
					        

							occupied = true;
					        /*
					         * BECOMES OCCUPIED
					         */
				            occupyEmptySpace(imgView);
				            
							
							success = true;
							
							event.setDropCompleted(success);
						}
						
					}

					event.consume();
				}
			});
			
			
			imgView.setOnDragExited(new EventHandler<DragEvent>(){
				@Override
				public void handle(DragEvent event) {
					//remove the "+" symbol
					
					//for now, remove the glow
					imgView.setEffect(new Glow(0.0));
					imgView.setOpacity(1);
					event.consume();
				}
			});
		}

		return imgView;
	}
	
	
	/**
	 * Updates the imageView on dropped.
	 * Previously the imgView was a blank, now it's it's a placed token.
	 */
	public ImageView occupyEmptySpace(final ImageView imgView){

		/*
		 * DO NOT DELETE ---CODE FOR VISUAL D&D!
		 */
		//		final Pane ldzPane = GameBoard.getInstance().getLDZpane();
		//
		//		final ObjectProperty<Point2D> dragAnchor = new SimpleObjectProperty<>();
		//		
		//		final DoubleProperty initX = new SimpleDoubleProperty();
		//		final DoubleProperty initY = new SimpleDoubleProperty();
		//
		//		final DoubleProperty dragX = new SimpleDoubleProperty();
		//		final DoubleProperty dragY = new SimpleDoubleProperty();
		//		
		//		final DoubleProperty newXPosition = new SimpleDoubleProperty();
		//		final DoubleProperty newYPosition = new SimpleDoubleProperty();
		//		
		//		final int buffer = 3;



		imgView.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {

				//System.out.println("**setOnDragDetected PROBLEM OF NOT BEING ABLE TO DRAG HERE**");
				//System.out.println("******************* MAJOR PROBLEM -- NO VISUAL D&D!*********");
				ClipboardContent content = new ClipboardContent();
				content.putString(LHSIconizedToken.this.dragBoardString());

				System.out.println("\nDragged Item: " + LHSIconizedToken.this.dragBoardString());

				Dragboard db = imgView.startDragAndDrop(TransferMode.ANY); //makes mouse unusable when set to anything but NONE??!!
				db.setContent(content); 
				event.consume();
			}
		});

		imgView.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				/*
				 * DO NOT DELETE ---CODE FOR VISUAL D&D!
				 */
				//initX.set(imgView.getTranslateX());
				//initY.set(imgView.getTranslateY());
				//dragAnchor.set(new Point2D(event.getSceneX(), event.getSceneY()));
				event.consume();
			}
		});

		imgView.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				/*
				 * DO NOT DELETE ---CODE FOR VISUAL D&D!
				 */
				//					imgView.toFront();
				//					
				//					dragX.set(me.getSceneX() - dragAnchor.get().getX());
				//					dragY.set(me.getSceneY() - dragAnchor.get().getY());
				//					imgView.setOpacity(0.5);
				//					
				//					newXPosition.set(initX.get() + dragX.get());
				//					newYPosition.set(initY.get() + dragY.get());
				//					
				//					if(		(Math.abs((newXPosition.get() - ldzPane.getWidth())) <=  ldzPane.getWidth() + startX + buffer) &&
				//							((newXPosition.get() + startX + imgView.getImage().getWidth()+ buffer)<=  ldzPane.getWidth()))
				//						imgView.setTranslateX(newXPosition.get());
				//					
				//					if(		(Math.abs((newYPosition.get() - ldzPane.getHeight())) <=  ldzPane.getHeight() + startY + buffer) &&
				//							((newYPosition.get() + startY + imgView.getImage().getHeight()+ buffer)<=  ldzPane.getHeight()))
				//						imgView.setTranslateY(newYPosition.get());
				//					
				me.consume();
			}
		});

		imgView.setOnDragDone(new EventHandler<DragEvent>() {

			public void handle(DragEvent event) {
				List<LHSIconizedToken> iTokens = GameBoard.getInstance().getTokenBoardItokens();
				if (event.getTransferMode() == TransferMode.MOVE) {

					LHSIconizedToken element = iTokens.remove(index);
					iTokens.add(index, new LHSIconizedToken(element.getImage(), EmptyToken.INSTANCE, index));

					imgView.setImage(new Image(Tokanagrammar.class.getResourceAsStream("/images/ui/tokens/removed_.fw.png")));

//					System.out.println("\n\nDrag Done, Remove and Replace LHS token with \"empty\" token: ");
//					System.out.println("Check Data Structures:  ");
//					System.out.println("LHSIconizedTokens: (make sure replaced!)");
//					for(LHSIconizedToken iToken: iTokens)
//						System.out.println("[[[LHSItoken index: " + iToken.getIndex() + " SourceToken: " + iToken.getSourceToken() + "]]] ");
//					System.out.println("\n\n");

					occupied = false;
				}
				event.consume();
			}
		});

		imgView.setOnMouseEntered(new EventHandler <MouseEvent>() {
			public void handle(MouseEvent event) {

				//System.out.println("occupied? : " + occupied);

				imgView.setEffect(new Glow(0.5));
				event.consume();
			}
		});

		imgView.setOnMouseExited(new EventHandler <MouseEvent>() {
			public void handle(MouseEvent event) {

				//System.out.println("occupied? : " + occupied);

				imgView.setEffect(new Glow(0.0));
				imgView.setOpacity(1);
				event.consume();
			}
		});

		return imgView;
	}
	

	@Override
	public String toString(){
		String string = 	"[image: " + image + " Token: " + token + 
							" index: " + index + "]";
		return string;
	}
}