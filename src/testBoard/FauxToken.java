package testBoard;



/**
 * This is not a real class, but intended to be used
 * for testing game board functionality.  This class
 * is wrapped in a GamePiece and is intended for this
 * (testing) purpose only.
 * 
 * It should be noted that GamePiece should wrap a
 * Token class for conception and release as well!
 * 
 * @author Matt
 *
 */
public class FauxToken {
	
	//type
	String type;
	//token char length
	int chLen;
	
	//constructor
	public FauxToken(String type){
		this.type = type;
		this.chLen = type.length();
	}
	

}
