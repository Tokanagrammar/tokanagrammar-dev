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

}



