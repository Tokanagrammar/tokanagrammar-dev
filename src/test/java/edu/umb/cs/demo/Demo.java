package edu.umb.cs.demo;

import java.util.LinkedList;
import java.util.List;


/**
 * This is the demo for release 0.5
 * @author Matt
 *
 */
public class Demo {
	
	static List<DemoToken> demoTokens = new LinkedList<DemoToken>();
	static List<DemoToken> removedTokens = new LinkedList<DemoToken>();
	static List<DemoToken> remainingTokens = new LinkedList<DemoToken>();
	
	public Demo(){
		//the demo uses the following faux class
		//		public class HelloWorld
		//		{
		//		    public static void main(String args[])
		//		    {
		//		    	System.out.println("Hello, world");
		//		    }
		//		}
		demoTokens.add(new DemoToken("keyword", "public"));
		demoTokens.add(new DemoToken("keyword", "class"));							//removedTokens
		demoTokens.add(new DemoToken("identifier", "HelloWorld"));				//removedTokens
		demoTokens.add(new DemoToken("delimiter", "{"));
		demoTokens.add(new DemoToken("keyword", "public"));
		demoTokens.add(new DemoToken("keyword", "static"));
		demoTokens.add(new DemoToken("keyword", "void"));								//removedTokens
		demoTokens.add(new DemoToken("identifier", "main"));
		demoTokens.add(new DemoToken("delimiter", "("));
		demoTokens.add(new DemoToken("refType", "String"));							//removedTokens
		demoTokens.add(new DemoToken("identifier", "args[]"));
		demoTokens.add(new DemoToken("delimiter", ")"));									//removedTokens
		demoTokens.add(new DemoToken("delimiter", "{"));
		demoTokens.add(new DemoToken("qualId", "System.out.println"));
		demoTokens.add(new DemoToken("delimiter", "("));									//removedTokens
		demoTokens.add(new DemoToken("stringLit", "\"Hello, world\""));
		demoTokens.add(new DemoToken("delimiter", ")"));
		demoTokens.add(new DemoToken("delimiter", ";"));									//removedTokens
		demoTokens.add(new DemoToken("delimiter",  "}"));
		demoTokens.add(new DemoToken("delimiter", "}"));
		
		//remaining tokens
		remainingTokens.add(new DemoToken("keyword", "public"));
		remainingTokens.add(new DemoToken("<MISSING>", ""));								//placeholder
		remainingTokens.add(new DemoToken("<MISSING>", ""));								//placeholder
		remainingTokens.add(new DemoToken("delimiter", "{"));
		remainingTokens.add(new DemoToken("keyword", "public"));
		remainingTokens.add(new DemoToken("keyword", "static"));
		remainingTokens.add(new DemoToken("<MISSING>", ""));								//placeholder
		remainingTokens.add(new DemoToken("identifier", "main"));
		remainingTokens.add(new DemoToken("delimiter", "("));
		remainingTokens.add(new DemoToken("<MISSING>", ""));								//placeholder
		remainingTokens.add(new DemoToken("identifier", "args[]"));
		remainingTokens.add(new DemoToken("<MISSING>", ""));								//placeholder
		remainingTokens.add(new DemoToken("delimiter", "{"));
		remainingTokens.add(new DemoToken("qualId", "System.out.println"));
		remainingTokens.add(new DemoToken("<MISSING>", ""));								//placeholder
		remainingTokens.add(new DemoToken("stringLit", "\"Hello, world\""));
		remainingTokens.add(new DemoToken("delimiter", ")"));
		remainingTokens.add(new DemoToken("<MISSING>", ""));								//placeholder
		remainingTokens.add(new DemoToken("delimiter", "}"));
		remainingTokens.add(new DemoToken("delimiter", "}"));
		
		//7 removed tokens
		removedTokens.add(new DemoToken("keyword", "class"));
		removedTokens.add(new DemoToken("identifier", "HelloWorld"));
		removedTokens.add(new DemoToken("keyword", "void"));
		removedTokens.add(new DemoToken("refType", "String"));
		removedTokens.add(new DemoToken("delimiter", ")"));
		removedTokens.add(new DemoToken("delimiter", "("));
		removedTokens.add(new DemoToken("delimiter", ";"));
	}

	
	
	//getTokens
	public List<DemoToken> getRemovedTokens(){
		return removedTokens;
	}
	
	public List<DemoToken> getRemainingTokens(){
		return remainingTokens;
	}
	
	//displayTokens
	
	//getBoard
	
	//displayBoard
}
