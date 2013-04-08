package edu.umb.cs.demo;

/*
 * This is for demo of 0.8::  
 */
public class DemoSource {
	

	//tokens
	DemoTokens demoTokens = new DemoTokens();
	
	//getMetaData
	String metaData = "This is some test metadata";
	
	//get category of this source file
	String category = "SEE DEMO SOURCE";
	
	//getHints
	String[] hints = {"this is test hint 1", "this is test hint 2", "this is test hint 3"};
	
	public DemoTokens getDemoTokens(){
		return demoTokens;
	}
	
	public String getMetaData(){
		return metaData;
	}
	
	public String getCategory(){
		return category;
	}
	
	public String[] getHints(){
		return hints;
	}
	
}
