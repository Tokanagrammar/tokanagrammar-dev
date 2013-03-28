package edu.umb.cs.gui;

import java.awt.image.BufferedImage;

import edu.umb.cs.demo.DemoToken;

public class IconizedToken {
	
	/**The dynamically created image represention of a token**/
	private BufferedImage bufferedImage;
	/**The original token**/
	private DemoToken token;

	public IconizedToken(BufferedImage bufferedImage, DemoToken token) {
		this.bufferedImage = bufferedImage;
		this.token = token;
	}
	
	public BufferedImage getBufferedImage(){
		return bufferedImage;
	}
	
	public DemoToken getDemoToken(){
		return token;
	}

}
