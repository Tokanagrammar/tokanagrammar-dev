package edu.umb.cs.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class IconizedToken {

	protected abstract ImageView initImageView(Image img);
	
	public abstract ImageView getImgView();
	
	public abstract Image getImage();
	
	
}
