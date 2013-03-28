package edu.umb.cs.gui;

import javafx.scene.image.Image;
//import javafx.scene.image.WritableImage;
import edu.umb.cs.demo.DemoToken;

public class IconizedToken {
	
	/**The dynamically created image representation of a token**/
	private Image image;
	/**The original token**/
	private DemoToken token;

	public IconizedToken(Image image, DemoToken token) {
		this.image = image;
		this.token = token;
	}

	
	public Image getImage(){
		return image;
	}
	
	public DemoToken getDemoToken(){
		return token;
	}

}
