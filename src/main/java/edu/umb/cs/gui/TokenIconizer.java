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

import edu.umb.cs.source.Token;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

//import edu.umb.cs.demo.DemoToken;
//import edu.umb.cs.source.Token;

public class TokenIconizer {
	
	final static int TOKEN_HEIGHT = 32;
//	
//	public static void main(String[] args){
//		IconizedToken iconizedToken = iconizeToken(new DemoToken("keyword", "public"));
//	}
//	
	/**
	 * Take a token and make a graphic representation of it.
	 * @param token
	 * @return an IconizedToken
	 */
//	public static IconizedToken iconizeToken(Token token){
//		
//		int tokenSize = token.getImage().length();
//		String tokenType = token.getType();
//		String tokenColor = null;
//		String textColor = null;
//		short howMany = 0;
//		BufferedImage image = null;
//		Graphics graphics = null;
//		Font font = new Font("Courier New", Font.PLAIN, 16);
//		
//		try{
//			if(tokenType.equals("keyword")){	//actual api wants to use isKeyword etc
//				image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/ui_token_pink.fw.png"));
//				graphics = image.getGraphics();
//				graphics.setColor(Color.blue);
//				System.out.println("got here");
//			}else if(tokenType.equals("literal")){
//				image = ImageIO.read(new URL("images/ui/tokens/ui_token_pink.fw.png"));
//				graphics = image.getGraphics();
//				graphics.setColor(Color.blue);
//			}else if(tokenType.equals("identifier")){
//				image = ImageIO.read(new URL("images/ui/tokens/ui_token_green.fw.png"));
//				graphics = image.getGraphics();
//				graphics.setColor(Color.blue);
//			}else if(tokenType.equals("quotedString")){
//				image = ImageIO.read(new URL("images/ui/tokens/ui_token_green.fw.png"));
//				graphics = image.getGraphics();
//				graphics.setColor(Color.blue);
//			}else if(tokenType.equals("delimiter")){
//				image = ImageIO.read(new URL("images/ui/tokens/ui_token_yellow.fw.png"));
//				graphics = image.getGraphics();
//				graphics.setColor(Color.blue);
//			}else if(tokenType.equals("operator")){
//				image = ImageIO.read(new URL("images/ui/tokens/ui_token_purple.fw.png"));
//				graphics = image.getGraphics();
//				graphics.setColor(Color.blue);
//			}else if(tokenType.equals(null)){
//				//report problems
//			}
//		} catch(IOException e){
//			e.printStackTrace();
//		}
//
//		graphics.setFont(font);
//		centerText(token.getImage(), token.getImage().length(), graphics);
//		graphics.dispose();
//		try {
//			ImageIO.write(image, "png", new File("C:\\Users\\Matt\\Desktop\\test.png"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return null;
//	}
//	
//	private static void centerText(String image, int tokenLen, Graphics graphics){
//	    FontMetrics fm = graphics.getFontMetrics();
//	    int x = (fm.stringWidth(image)) / 2;
//	    int y = (fm.getAscent() + (TOKEN_HEIGHT - (fm.getAscent() + fm.getDescent())) / 2);
//	    graphics.drawString(image, x, y);
//	}
//	

}
