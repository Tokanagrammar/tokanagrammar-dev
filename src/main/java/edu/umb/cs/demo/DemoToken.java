package edu.umb.cs.demo;

public class DemoToken {
	
	
	private String type;
	private String image;
	
	
	public DemoToken(String type, String image){
		this.type = type;
		this.image = image;
	}
	
	public String getType(){
		return type;
	}
	
	public String getImage(){
		return image;
	}

	
	/**
	 * Compare two tokens to see if they're equal.  This
	 * will be used to determine if a "number subscript" needs
	 * to be put on a token. 
	 */
    public boolean equals(Object obj) {
    	if(obj instanceof DemoToken)
    		return 	type.equals(((DemoToken) obj).getType()) &&
    				image.equals(((DemoToken) obj).getImage());
    	else return false;

    }
    
    public int hashCode() {
    	return image.hashCode() + type.hashCode();
    }
    
    @Override
    public String toString(){
    	String string = "[type: " + type + " image: " + image + "]";
    	
		return string;
    }

}



