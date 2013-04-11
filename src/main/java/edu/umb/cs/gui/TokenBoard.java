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

package edu.umb.cs.gui;

import edu.umb.cs.source.ShuffledSource;
import edu.umb.cs.source.SourceFile;
import java.util.LinkedList;

import javafx.scene.layout.Pane;

public class TokenBoard {
	
	private static Pane pane;
	
	private static final TokenBoard tokenBoard = new TokenBoard();
	
	private TokenBoard(){}
	
	/**
	 * There can only be one instance of the TokenBoard
	 * at any time.  This uses the Singleton pattern to ensure this.
	 * @return
	 */
	public static TokenBoard getInstance(){
		pane = Controller.getTokenBoard();
		return tokenBoard;
	}
		
	
	/**
	 * 
	 * @param lhsTokens
	 */
	public void settleTokenBoard(SourceFile src){
		System.out.println(src.toString());
	}
}
