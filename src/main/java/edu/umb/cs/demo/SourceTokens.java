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
package edu.umb.cs.demo;

import java.util.LinkedList;


/*
 * For testing only.
 * @author Matt
 *
 */
public class SourceTokens {
	
	//private LinkedList<SourceToken> demoTokens = new LinkedList<SourceToken>();
	private LinkedList<SourceToken> removedTokens = new LinkedList<SourceToken>();
	private LinkedList<SourceToken> remainingTokens = new LinkedList<SourceToken>();
	

	//getTokens
	public LinkedList<SourceToken> getRemovedTokens(){
		return removedTokens;
	}
	
	public LinkedList<SourceToken> getRemainingTokens(){
		return remainingTokens;
	}
	
	public SourceTokens(String callWhatMethod){
		//if(callWhatMethod.equals("allLHS"))
		//	allLHS();
		if(callWhatMethod.equals("varied"))
			varied();
	}
	
	public void varied(){

		//demoTokens.clear();
		removedTokens.clear();
		remainingTokens.clear();

		//remaining
		remainingTokens.add(new SourceToken("keyword", "public"));
		remainingTokens.add(new SourceToken("space", " "));
		remainingTokens.add(new SourceToken("removed", ""));						//removed
		removedTokens.add(new SourceToken("keyword", "class"));					//now rhs
		remainingTokens.add(new SourceToken("space", " "));
		remainingTokens.add(new SourceToken("identifier", "GCD"));
		remainingTokens.add(new SourceToken("delimiter", "{"));
		remainingTokens.add(new SourceToken("newline", "\n"));
		
		remainingTokens.add(new SourceToken("tab", "\t"));
		remainingTokens.add(new SourceToken("keyword", "public"));
		remainingTokens.add(new SourceToken("space", " "));
		remainingTokens.add(new SourceToken("keyword", "static"));
		remainingTokens.add(new SourceToken("space", " "));
		remainingTokens.add(new SourceToken("removed", ""));						//removed
		removedTokens.add(new SourceToken("keyword", "void"));					//now rhs
		remainingTokens.add(new SourceToken("space", " "));
		remainingTokens.add(new SourceToken("identifier", "main"));
		remainingTokens.add(new SourceToken("delimiter", "("));
		remainingTokens.add(new SourceToken("identifier", "String"));
		remainingTokens.add(new SourceToken("delimiter", "["));
		remainingTokens.add(new SourceToken("delimiter", "]"));
		remainingTokens.add(new SourceToken("space", " "));
		remainingTokens.add(new SourceToken("removed", ""));						//removed
		removedTokens.add(new SourceToken("identifier", "args"));					//now rhs
		remainingTokens.add(new SourceToken("delimiter", ")"));
		remainingTokens.add(new SourceToken("delimiter", "{"));
		remainingTokens.add(new SourceToken("newline", "\n"));
		
		remainingTokens.add(new SourceToken("tab", "\t"));
		remainingTokens.add(new SourceToken("tab", "\t"));
		remainingTokens.add(new SourceToken("identifier", "System"));
		remainingTokens.add(new SourceToken("delimiter", "."));
		remainingTokens.add(new SourceToken("identifier", "out"));
		remainingTokens.add(new SourceToken("delimiter", "."));
		remainingTokens.add(new SourceToken("identifier", "println"));
		remainingTokens.add(new SourceToken("delimiter", "("));
		remainingTokens.add(new SourceToken("string_literal", "\"GCD:  \""));
		remainingTokens.add(new SourceToken("space", " "));
		remainingTokens.add(new SourceToken("operator", "+"));
		remainingTokens.add(new SourceToken("space", " "));
		remainingTokens.add(new SourceToken("removed", ""));						//removed
		removedTokens.add(new SourceToken("identifier", "gcd"));					//now rhs
		remainingTokens.add(new SourceToken("delimiter", "("));
		remainingTokens.add(new SourceToken("num_literal", "1024"));
		remainingTokens.add(new SourceToken("delimiter", ","));
		remainingTokens.add(new SourceToken("space", " "));
		remainingTokens.add(new SourceToken("num_literal", "768"));
		remainingTokens.add(new SourceToken("delimiter", ")"));
		remainingTokens.add(new SourceToken("delimiter", ")"));
		remainingTokens.add(new SourceToken("delimiter", ";"));
		remainingTokens.add(new SourceToken("newline", "\n"));
		
		remainingTokens.add(new SourceToken("tab", "\t"));
		remainingTokens.add(new SourceToken("delimiter", "}"));
		remainingTokens.add(new SourceToken("newline", "\n"));
		
		remainingTokens.add(new SourceToken("tab", "\t"));
		remainingTokens.add(new SourceToken("keyword", "public"));
		remainingTokens.add(new SourceToken("space", " "));
		remainingTokens.add(new SourceToken("keyword", "static"));
		remainingTokens.add(new SourceToken("space", " "));
		remainingTokens.add(new SourceToken("removed", ""));						//removed
		removedTokens.add(new SourceToken("keyword", "int"));						//now rhs
		remainingTokens.add(new SourceToken("space", " "));
		remainingTokens.add(new SourceToken("identifier", "gcd"));
		remainingTokens.add(new SourceToken("delimiter", "("));
		remainingTokens.add(new SourceToken("keyword", "int"));
		remainingTokens.add(new SourceToken("space", " "));
		remainingTokens.add(new SourceToken("identifier", "x"));
		remainingTokens.add(new SourceToken("delimiter", ","));
		remainingTokens.add(new SourceToken("space", " "));
		remainingTokens.add(new SourceToken("keyword", "int"));
		remainingTokens.add(new SourceToken("space", " "));
		remainingTokens.add(new SourceToken("identifier", "y"));
		remainingTokens.add(new SourceToken("delimiter", ")"));
		remainingTokens.add(new SourceToken("delimiter", "{"));
		remainingTokens.add(new SourceToken("newline", "\n"));
		
		remainingTokens.add(new SourceToken("tab", "\t"));
		remainingTokens.add(new SourceToken("tab", "\t"));
		remainingTokens.add(new SourceToken("keyword", "if"));
		remainingTokens.add(new SourceToken("delimiter", "("));
		remainingTokens.add(new SourceToken("identifier", "y"));
		remainingTokens.add(new SourceToken("space", " "));
		remainingTokens.add(new SourceToken("removed", ""));						//removed
		removedTokens.add(new SourceToken("operator", "=="));						//now rhs
		remainingTokens.add(new SourceToken("space", " "));
		remainingTokens.add(new SourceToken("num_literal", "0"));
		remainingTokens.add(new SourceToken("delimiter", ")"));
		remainingTokens.add(new SourceToken("delimiter", "{"));
		remainingTokens.add(new SourceToken("newline", "\n"));
		
		remainingTokens.add(new SourceToken("tab", "\t"));
		remainingTokens.add(new SourceToken("tab", "\t"));
		remainingTokens.add(new SourceToken("tab", "\t"));
		remainingTokens.add(new SourceToken("keyword", "return"));
		remainingTokens.add(new SourceToken("space", " "));
		remainingTokens.add(new SourceToken("identifier", "x"));
		remainingTokens.add(new SourceToken("delimiter", ";"));
		remainingTokens.add(new SourceToken("newline", "\n"));
		
		remainingTokens.add(new SourceToken("tab", "\t"));
		remainingTokens.add(new SourceToken("tab", "\t"));
		remainingTokens.add(new SourceToken("delimiter", "}"));
		remainingTokens.add(new SourceToken("newline", "\n"));
		
		remainingTokens.add(new SourceToken("tab", "\t"));
		remainingTokens.add(new SourceToken("tab", "\t"));
		remainingTokens.add(new SourceToken("removed", ""));						//removed
		removedTokens.add(new SourceToken("keyword", "return"));					//now rhs
		remainingTokens.add(new SourceToken("space", " "));
		remainingTokens.add(new SourceToken("identifier", "gcd"));
		remainingTokens.add(new SourceToken("delimiter", "("));
		remainingTokens.add(new SourceToken("identifier", "y"));
		remainingTokens.add(new SourceToken("delimiter", ","));
		remainingTokens.add(new SourceToken("space", " "));
		remainingTokens.add(new SourceToken("identifier", "x"));
		remainingTokens.add(new SourceToken("space", " "));
		remainingTokens.add(new SourceToken("operator", "%"));
		remainingTokens.add(new SourceToken("space", " "));
		remainingTokens.add(new SourceToken("removed", ""));						//removed
		removedTokens.add(new SourceToken("identifier", "y"));					//now rhs
		remainingTokens.add(new SourceToken("delimiter", ")"));
		remainingTokens.add(new SourceToken("delimiter", ";"));
		remainingTokens.add(new SourceToken("newline", "\n"));
		
		remainingTokens.add(new SourceToken("tab", "\t"));
		remainingTokens.add(new SourceToken("delimiter", "}"));
		remainingTokens.add(new SourceToken("newline", "\n"));
		
		remainingTokens.add(new SourceToken("delimiter", "}"));
		remainingTokens.add(new SourceToken("newline", "\n"));
		
		
		//TEST ONLY
//		remainingTokens.add(new SourceToken("removed", ""));						//removed
//		removedTokens.add(new SourceToken("identifier", "y"));					//now rhs
		
		
		
		
		
		
		
		
		
		//test scrollBar
//		remainingTokens.add(new SourceToken("keyword", "public"));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("removed", ""));						//removed
//		removedTokens.add(new SourceToken("keyword", "class"));					//now rhs
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("identifier", "GCD"));
//		remainingTokens.add(new SourceToken("delimiter", "{"));
//		remainingTokens.add(new SourceToken("newline", "\n"));
//		
//		remainingTokens.add(new SourceToken("tab", "\t"));
//		remainingTokens.add(new SourceToken("keyword", "public"));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("keyword", "static"));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("removed", ""));						//removed
//		removedTokens.add(new SourceToken("keyword", "void"));					//now rhs
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("identifier", "main"));
//		remainingTokens.add(new SourceToken("delimiter", "("));
//		remainingTokens.add(new SourceToken("identifier", "String"));
//		remainingTokens.add(new SourceToken("delimiter", "["));
//		remainingTokens.add(new SourceToken("delimiter", "]"));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("removed", ""));						//removed
//		removedTokens.add(new SourceToken("identifier", "args"));					//now rhs
//		remainingTokens.add(new SourceToken("delimiter", ")"));
//		remainingTokens.add(new SourceToken("delimiter", "{"));
//		remainingTokens.add(new SourceToken("newline", "\n"));
//		
//		remainingTokens.add(new SourceToken("tab", "\t"));
//		remainingTokens.add(new SourceToken("tab", "\t"));
//		remainingTokens.add(new SourceToken("identifier", "System"));
//		remainingTokens.add(new SourceToken("delimiter", "."));
//		remainingTokens.add(new SourceToken("identifier", "out"));
//		remainingTokens.add(new SourceToken("delimiter", "."));
//		remainingTokens.add(new SourceToken("identifier", "println"));
//		remainingTokens.add(new SourceToken("delimiter", "("));
//		remainingTokens.add(new SourceToken("string_literal", "\"GCD:  \""));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("operator", "+"));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("removed", ""));						//removed
//		removedTokens.add(new SourceToken("identifier", "gcd"));					//now rhs
//		remainingTokens.add(new SourceToken("delimiter", "("));
//		remainingTokens.add(new SourceToken("num_literal", "1024"));
//		remainingTokens.add(new SourceToken("delimiter", ","));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("num_literal", "768"));
//		remainingTokens.add(new SourceToken("delimiter", ")"));
//		remainingTokens.add(new SourceToken("delimiter", ")"));
//		remainingTokens.add(new SourceToken("delimiter", ";"));
//		remainingTokens.add(new SourceToken("newline", "\n"));
//		
//		remainingTokens.add(new SourceToken("tab", "\t"));
//		remainingTokens.add(new SourceToken("delimiter", "}"));
//		remainingTokens.add(new SourceToken("newline", "\n"));
//		
//		remainingTokens.add(new SourceToken("tab", "\t"));
//		remainingTokens.add(new SourceToken("keyword", "public"));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("keyword", "static"));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("removed", ""));						//removed
//		removedTokens.add(new SourceToken("keyword", "int"));						//now rhs
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("identifier", "gcd"));
//		remainingTokens.add(new SourceToken("delimiter", "("));
//		remainingTokens.add(new SourceToken("keyword", "int"));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("identifier", "x"));
//		remainingTokens.add(new SourceToken("delimiter", ","));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("keyword", "int"));
//		remainingTokens.add(new SourceToken("identifier", "y"));
//		remainingTokens.add(new SourceToken("delimiter", ")"));
//		remainingTokens.add(new SourceToken("delimiter", "{"));
//		remainingTokens.add(new SourceToken("newline", "\n"));
//		
//		remainingTokens.add(new SourceToken("tab", "\t"));
//		remainingTokens.add(new SourceToken("tab", "\t"));
//		remainingTokens.add(new SourceToken("keyword", "if"));
//		remainingTokens.add(new SourceToken("delimiter", "("));
//		remainingTokens.add(new SourceToken("identifier", "y"));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("removed", ""));						//removed
//		removedTokens.add(new SourceToken("operator", "=="));						//now rhs
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("num_literal", "0"));
//		remainingTokens.add(new SourceToken("delimiter", ")"));
//		remainingTokens.add(new SourceToken("delimiter", "{"));
//		remainingTokens.add(new SourceToken("newline", "\n"));
//		
//		remainingTokens.add(new SourceToken("tab", "\t"));
//		remainingTokens.add(new SourceToken("tab", "\t"));
//		remainingTokens.add(new SourceToken("tab", "\t"));
//		remainingTokens.add(new SourceToken("keyword", "return"));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("identifier", "x"));
//		remainingTokens.add(new SourceToken("delimiter", ";"));
//		remainingTokens.add(new SourceToken("newline", "\n"));
//		
//		remainingTokens.add(new SourceToken("tab", "\t"));
//		remainingTokens.add(new SourceToken("tab", "\t"));
//		remainingTokens.add(new SourceToken("delimiter", "}"));
//		remainingTokens.add(new SourceToken("newline", "\n"));
//		
//		remainingTokens.add(new SourceToken("tab", "\t"));
//		remainingTokens.add(new SourceToken("tab", "\t"));
//		remainingTokens.add(new SourceToken("removed", ""));						//removed
//		removedTokens.add(new SourceToken("keyword", "return"));					//now rhs
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("identifier", "gcd"));
//		remainingTokens.add(new SourceToken("delimiter", "("));
//		remainingTokens.add(new SourceToken("identifier", "y"));
//		remainingTokens.add(new SourceToken("delimiter", ","));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("identifier", "x"));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("operator", "%"));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("removed", ""));						//removed
//		removedTokens.add(new SourceToken("identifier", "y"));					//now rhs
//		remainingTokens.add(new SourceToken("delimiter", ")"));
//		remainingTokens.add(new SourceToken("delimiter", ";"));
//		remainingTokens.add(new SourceToken("newline", "\n"));
//		
//		remainingTokens.add(new SourceToken("tab", "\t"));
//		remainingTokens.add(new SourceToken("delimiter", "}"));
//		remainingTokens.add(new SourceToken("newline", "\n"));
//		
//		remainingTokens.add(new SourceToken("delimiter", "}"));
//		remainingTokens.add(new SourceToken("newline", "\n"));
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
//	public void allLHS(){
//		
//		demoTokens.clear();
//		removedTokens.clear();
//		remainingTokens.clear();
//		
//		
//		remainingTokens.add(new SourceToken("keyword", "public"));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("keyword", "class"));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("identifier", "GCD"));
//		remainingTokens.add(new SourceToken("delimiter", "{"));
//		
////		remainingTokens.add(new SourceToken("tab", "\t"));
//		remainingTokens.add(new SourceToken("keyword", "public"));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("keyword", "static"));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("keyword", "void"));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("identifier", "main"));
//		remainingTokens.add(new SourceToken("delimiter", "("));
//		remainingTokens.add(new SourceToken("identifier", "String"));
//		remainingTokens.add(new SourceToken("delimiter", "["));
//		remainingTokens.add(new SourceToken("delimiter", "]"));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("identifier", "args"));
//		remainingTokens.add(new SourceToken("delimiter", ")"));
//		remainingTokens.add(new SourceToken("delimiter", "{"));
//		
////		remainingTokens.add(new SourceToken("tab", "\t"));
////		remainingTokens.add(new SourceToken("tab", "\t"));
////		remainingTokens.add(new SourceToken("tab", "\t"));
////		remainingTokens.add(new SourceToken("tab", "\t"));
//		remainingTokens.add(new SourceToken("identifier", "System"));
//		remainingTokens.add(new SourceToken("delimiter", "."));
//		remainingTokens.add(new SourceToken("identifier", "out"));
//		remainingTokens.add(new SourceToken("delimiter", "."));
//		remainingTokens.add(new SourceToken("identifier", "println"));
//		remainingTokens.add(new SourceToken("delimiter", "("));
//		remainingTokens.add(new SourceToken("string_literal", "\"GCD:  \""));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("operator", "+"));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("identifier", "gcd"));
//		remainingTokens.add(new SourceToken("delimiter", "("));
//		remainingTokens.add(new SourceToken("num_literal", "1024"));
//		remainingTokens.add(new SourceToken("delimiter", ","));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("num_literal", "768"));
//		remainingTokens.add(new SourceToken("delimiter", ")"));
//		remainingTokens.add(new SourceToken("delimiter", ")"));
//		remainingTokens.add(new SourceToken("delimiter", ";"));
//		
//		remainingTokens.add(new SourceToken("delimiter", "}"));
//		
//		remainingTokens.add(new SourceToken("keyword", "public"));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("keyword", "static"));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("keyword", "int"));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("identifier", "gcd"));
//		remainingTokens.add(new SourceToken("delimiter", "("));
//		remainingTokens.add(new SourceToken("keyword", "int"));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("identifier", "x"));
//		remainingTokens.add(new SourceToken("delimiter", ","));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("keyword", "int"));
//		remainingTokens.add(new SourceToken("identifier", "y"));
//		remainingTokens.add(new SourceToken("delimiter", ")"));
//		remainingTokens.add(new SourceToken("delimiter", "{"));
//		
//		remainingTokens.add(new SourceToken("tab", "\t"));
//		remainingTokens.add(new SourceToken("keyword", "if"));
//		remainingTokens.add(new SourceToken("delimiter", "("));
//		remainingTokens.add(new SourceToken("identifier", "y"));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("operator", "=="));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("num_literal", "0"));
//		remainingTokens.add(new SourceToken("delimiter", ")"));
//		remainingTokens.add(new SourceToken("delimiter", "{"));
//		
//		remainingTokens.add(new SourceToken("tab", "\t"));
//		remainingTokens.add(new SourceToken("keyword", "return"));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("identifier", "x"));
//		remainingTokens.add(new SourceToken("delimiter", ";"));
//		
//		remainingTokens.add(new SourceToken("delimiter", "}"));
//		
//		remainingTokens.add(new SourceToken("keyword", "return"));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("identifier", "gcd"));
//		remainingTokens.add(new SourceToken("delimiter", "("));
//		remainingTokens.add(new SourceToken("identifier", "y"));
//		remainingTokens.add(new SourceToken("delimiter", ","));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("identifier", "x"));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("operator", "%"));
//		remainingTokens.add(new SourceToken("space", " "));
//		remainingTokens.add(new SourceToken("identifier", "y"));
//		remainingTokens.add(new SourceToken("delimiter", ")"));
//		remainingTokens.add(new SourceToken("delimiter", ";"));
//		
//		remainingTokens.add(new SourceToken("delimiter", "}"));
//		
//		remainingTokens.add(new SourceToken("delimiter", "}"));
//	}

}
