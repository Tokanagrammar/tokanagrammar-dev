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
 * This is for demo only. 
 */
public class DemoSource {
	
	//tokens
	LinkedList<SourceToken> tokenBoardTokens;
	
	LinkedList<SourceToken> tokenBayTokens;
	
	//getMetaData
	String metaData;
	
	//get category of this source file
	LinkedList<String> categories;
	
	//getHints
	String[] hints;
	
	public DemoSource(	LinkedList<SourceToken> tokenBoardTokens,
						LinkedList<SourceToken> tokenBayTokens,
						String metaData,
						LinkedList<String> categories,
						String[] hints){ 
		this.tokenBoardTokens = tokenBoardTokens;
		this.tokenBayTokens = tokenBayTokens;
		this.metaData = metaData;
		this.categories = categories;
		this.hints = hints;
	}

	
	public String getMetaData(){
		return metaData;
	}
	
	public LinkedList<String> getCategories(){
		return categories;
	}
	
	public String[] getHints(){
		return hints;
	}
	
	public LinkedList<SourceToken> getTokenBayTokens(){
		return tokenBayTokens;
	}
	
	public LinkedList<SourceToken> getTokenBoardTokens(){
		return tokenBoardTokens;
	}
}
