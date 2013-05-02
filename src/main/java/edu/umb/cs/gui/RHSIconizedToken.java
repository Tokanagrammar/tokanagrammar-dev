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

import javafx.event.EventHandler;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import edu.umb.cs.source.SourceToken;

/**
 * A graphical representation of a token.
 * @author Matt
 */
public class RHSIconizedToken extends IconizedToken{

	private double startX;
	private double startY;

	/**The dynamically created image representation of a token**/
	private Image image;
	/**The original SourceToken**/
	private SourceToken token;
	/**Assigned by order in which this IconizedToken (iToken) has arrived in the token bay.**/
	private int index;
	/**The view of the image on the GameBoard.**/
	private ImageView imgView;
	
	static DataFormat dataFormat = new DataFormat("RHSIconizedToken");

	public RHSIconizedToken(Image image, SourceToken token, int index){
		this.image = image;
		this.token = token;
		this.index = index;
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
	static public DataFormat getDataFormat(){
		return dataFormat;
	}
	
	/**
	 * The original location used for setting and tracking the new location of a
	 * token being dragged.
	 */
	public void setStartX(double startX){
		this.startX = startX;
	}
	public void setStartY(double startY){
		this.startY = startY;
	}

	
	public String tokenLookupInfo(){
		return token.kind() + ":::" + token.image() + ":::" + index;
	}

	protected ImageView initImageView(Image img){

		final ImageView imgView = new ImageView(img);
		
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
		    	ClipboardContent content = new ClipboardContent();
		    	//content.put(dataFormat, RHSIconizedToken.this);
		    	content.putString(RHSIconizedToken.this.tokenLookupInfo());
		    	Dragboard db = imgView.startDragAndDrop(TransferMode.ANY);
		    	//db.setDragView(image, 7, 7);	//Java 8 only -- perfect soln to visual d&d though :(
		    	db.setContent(content); 
		    	System.err.println(db.getString());
		    	event.consume();
		    }
		});

		imgView.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				//initX.set(imgView.getTranslateX());
				//initY.set(imgView.getTranslateY());
				//dragAnchor.set(new Point2D(event.getSceneX(), event.getSceneY()));
				event.consume();
			}
		});

		imgView.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
//				imgView.toFront();
//				
//				dragX.set(me.getSceneX() - dragAnchor.get().getX());
//				dragY.set(me.getSceneY() - dragAnchor.get().getY());
//				imgView.setOpacity(0.5);
//				
//				newXPosition.set(initX.get() + dragX.get());
//				newYPosition.set(initY.get() + dragY.get());
//				
//				if(		(Math.abs((newXPosition.get() - ldzPane.getWidth())) <=  ldzPane.getWidth() + startX + buffer) &&
//						((newXPosition.get() + startX + imgView.getImage().getWidth()+ buffer)<=  ldzPane.getWidth()))
//					imgView.setTranslateX(newXPosition.get());
//				
//				if(		(Math.abs((newYPosition.get() - ldzPane.getHeight())) <=  ldzPane.getHeight() + startY + buffer) &&
//						((newYPosition.get() + startY + imgView.getImage().getHeight()+ buffer)<=  ldzPane.getHeight()))
//					imgView.setTranslateY(newYPosition.get());
//				
				me.consume();
			}
		});
		
		imgView.setOnDragDone(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    
		    	LinkedList<RHSIconizedToken> iTokens = (LinkedList<RHSIconizedToken>) GameBoard.getInstance().getTokenBayItokens();
		    	
		        if (event.getTransferMode() == TransferMode.MOVE) {
		            RHSIconizedToken element = iTokens.remove(index);
		            
		            iTokens.add(index, new RHSIconizedToken(element.getImage(), element.getSourceToken(), index));
		            imgView.setVisible(false);
		            
		            GameBoard theGameBoard = GameBoard.getInstance();
		            GUI theGUI = GUI.getInstance();
		            
		            if(theGameBoard.isRHSempty()){
		            	theGUI.enableCompileButton();
		            }else{
		            	theGUI.disableCompileButton();
		            }
		        }
		        event.consume();
		    }
		});

		imgView.setOnMouseEntered(new EventHandler <MouseEvent>() {
			public void handle(MouseEvent event) {
				imgView.setEffect(new Glow(0.5));
				event.consume();
			}
		});
		
		imgView.setOnMouseExited(new EventHandler <MouseEvent>() {
			public void handle(MouseEvent event) {
				imgView.setEffect(new Glow(0.0));
				imgView.setOpacity(1);
				event.consume();
			}
		});
		
		return imgView;
	}
	
	

	@Override
	public String toString(){
		String string = 	"[image: " + image + " Token: " + token + " index: "
							+ index + "]";
		return string;
	}

}
