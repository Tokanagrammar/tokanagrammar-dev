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

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import edu.umb.cs.demo.DemoToken;


/**
 * A graphical representation of a token.
 * @author Matt
 *
 */
public class IconizedToken{
	
	
	public enum Location {TOKENBAY, TOKENBOARD, LDZ, NOTPLACED};
	
	/**The dynamically created image representation of a token**/
	private Image image;
	/**The original token**/
	private DemoToken token;
	/*The number of duplicates of this iconized token*/
	private Integer occurrences;
	
	private ImageView imgView;
	
	/**broad location refers to where the token is in general: tokenBay, tokenBoard, or LDZ**/
	private Location broadLocation;
	
	public IconizedToken(final Image image, DemoToken token, Integer occurrences) {
		this.image = image;
		this.token = token;
		this.occurrences = occurrences;
		this.broadLocation = Location.NOTPLACED; //initialize this as empty -- it's not been placed
		this.imgView = new ImageViewFactory().createImageView(image);
	}
	
	//
	public Location getBroadLocation(){
		return broadLocation;
	}
	
	
	public void setBroadLocation(Location loc){
		this.broadLocation = loc;
	}
	
	
	
	
	public Image getImage(){
		return image;
	}
	
	public DemoToken getDemoToken(){
		return token;
	}
	
	public Integer getOccurences(){
		return occurrences;
	}
	
	public ImageView getImgView(){
		return imgView;
	}
	
	/**
	 * Compare two iTokens to see if they're equal.  This depends
	 * only it's internal standard token (as of now, the DemoToken).
	 */
    public boolean equals(Object obj) {
    	if(obj instanceof IconizedToken){
    		return 	token.equals(((IconizedToken) obj).getDemoToken());
    	}
    	else return false;
    }
    
    public int hashCode() {
    	return token.hashCode();
    }

    @Override
    public String toString(){
    	String string = "[image: " + image + " Token: " + token + " Occurrences: " + occurrences + "Location: " + broadLocation + "]";
    	
		return string;
    }
}


/**
 * IconizedToken comes pre-packaged with its mouse events.
 * 
 * 
 */
class ImageViewFactory {
	
	double initX;
	double initY;
	Point2D dragAnchor;

	public ImageView createImageView(Image img){

		final ImageView imgView = new ImageView(img);
		
	    imgView.setOnMousePressed(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent me) {
	             //when mouse is pressed, store initial position
	            initX = imgView.getTranslateX();
	            initY = imgView.getTranslateY();
	        	dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());
	        	me.consume();
	        }
	    });
		
		imgView.setOnMouseDragged(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent me) {
	            double dragX = me.getSceneX() - dragAnchor.getX();
	            double dragY = me.getSceneY() - dragAnchor.getY();
	            
	            double newXPosition = initX + dragX;
	            double newYPosition = initY + dragY;
	            
	            imgView.setTranslateX(newXPosition);
	            imgView.setTranslateY(newYPosition);
	            me.consume();
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
	            event.consume();
	        }
	    });

		return imgView;

	}
    
}

