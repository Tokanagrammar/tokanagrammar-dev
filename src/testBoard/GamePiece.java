package testBoard;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * an actual "token" that is in a graphical form on the board.
 * It seems practical to extend Node class from FX since it's 
 * a lower-level dragable object.  THE GAME PIECES WILL NEED
 * TO BE DRAGGABLE!
 * 
 * @author Matt
 *
 */
public abstract class GamePiece extends Node{
	
	private FauxToken token;
	private Image bgImage;
	private ImageView imageView;
	
	
	public GamePiece(FauxToken token){
		this.token = token;
		this.bgImage = getBGImage();
	}
	
	
	public Image getBGImage(){
		if(token.type == "INTEGER"){
			Image img = new Image("file:green.fw.png");
			
			this.imageView = new ImageView(img);
			
			return new Image("file:red_intBG.fw.png");
		}
		else
			return new Image("file:greenStd.fw.png");
	}


}
