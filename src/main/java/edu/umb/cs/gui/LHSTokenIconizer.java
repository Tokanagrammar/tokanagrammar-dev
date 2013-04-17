package edu.umb.cs.gui;

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


import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;

import edu.umb.cs.demo.SourceToken;

/**
 * Handles the tokens that go on the LHS
 * @author Matt
 *
 */
public class LHSTokenIconizer {
	
	final static int TOKEN_HEIGHT = 32; //token height is always the same
	final static int MIN_WIDTH = 32;	//the minimum token width
	
	private static int index;
	
	private static HashMap<String, Color> tokenFontColor;
	
	/**
	 * The primary method of TokenIconizer does all iconizing at once, for the
	 * entire LinkedList of pased tokens.
	 * @param tokens
	 * @return
	 */
	public static LinkedList<LHSIconizedToken> iconizeTokens(List<SourceToken> tokens){
		
		index = 0;
		
		LinkedList<LHSIconizedToken> iconizedTokens = new LinkedList<LHSIconizedToken>();
		
		tokenFontColor = new HashMap<String, Color>();
    	
    	//LHS no-ops -- no-ops will be standard text, though still must be
    	//tokenized.
    	tokenFontColor.put("keyword", Color.MAGENTA);
    	tokenFontColor.put("num_literal", Color.black);
    	tokenFontColor.put("char_literal", Color.orange);
    	tokenFontColor.put("string_literal", Color.orange);
    	tokenFontColor.put("operator", Color.black);
    	tokenFontColor.put("delimiter", Color.black);
    	tokenFontColor.put("identifier", Color.red);
    	
    	tokenFontColor.put("tab", Color.black);
    	tokenFontColor.put("space", Color.black);
    	
    	//NOTE::: LHS removed -- removed tokens will be clear when put on the
    	//tokenBoard

    	for(SourceToken token: tokens)
    		iconizedTokens.add( iconizeToken(token));
    	
		return iconizedTokens;
	}
	
	
	/**
	 * Take a token and makes a graphic representation of it.
	 * @param token
	 * @return an IconizedToken
	 */
	private static LHSIconizedToken iconizeToken(SourceToken token){
		
		String tokenType = token.getType();
		BufferedImage image = null; 
		BufferedImage finalImage = null;
		Graphics graphics = null;

		try{ 
			if(tokenType.equals("removed"))
				image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/removed_.fw.png"));
			else
				image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/noop_.fw.png"));

		} catch(IOException e){
			e.printStackTrace();
		}
		
		graphics = image.getGraphics();
		FontMetrics fm = graphics.getFontMetrics();
		
		if(tokenType.equals("removed"))
			finalImage = finalizeImage(image, new SourceToken("removed", ""),TOKEN_HEIGHT, MIN_WIDTH, Image.SCALE_DEFAULT);
		else if(tokenType.equals("tab")){
			finalImage = finalizeImage(image, token,TOKEN_HEIGHT, 20 , Image.SCALE_DEFAULT);
		}else if(tokenType.equals("newline")){
			finalImage = finalizeImage(image, token,TOKEN_HEIGHT, 1, Image.SCALE_DEFAULT);
		}else
			finalImage = finalizeImage(image, token,TOKEN_HEIGHT, fm.stringWidth(token.getImage()) +2 , Image.SCALE_DEFAULT);

		
		WritableImage writableImage = SwingFXUtils.toFXImage(finalImage, null);
		
		return new LHSIconizedToken(writableImage, token, index++);
	}
	
	/**
	 * Creates a "pretty" clear token so the user can tell it's a placed token.
	 * @param token
	 * @return an IconizedToken
	 */
	public static LHSIconizedToken createSingleIconizedToken(SourceToken token, int newIndex){
		
		String tokenType = token.getType();
		BufferedImage image = null; 
		BufferedImage finalImage = null;
		Graphics graphics = null;
		int imagePadding = 20;

		//TODO put this info in a table
		try{
			if(tokenType.equals("keyword")){	
				image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/lucentPink.fw.png"));
			}else if(tokenType.equals("identifier")){
				image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/lucentGreen.fw.png"));
			}else if(tokenType.equals("string_literal")  || tokenType.equals("num_literal") || tokenType.equals("char_literal")){
				image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/lucentGreen.fw.png"));
			}else if(tokenType.equals("delimiter")){
				image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/lucentYellow.fw.png"));
			}else if(tokenType.equals("operator")){
				image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/lucentPurple.fw.png"));
			}else
				System.out.println("throw");
		} catch(IOException e){
			e.printStackTrace();
		}
		
		graphics = image.getGraphics();
		FontMetrics fm = graphics.getFontMetrics();
		
		if(fm.stringWidth(token.getImage()) < 25)
			finalImage = finalizeImage(image, token,TOKEN_HEIGHT, MIN_WIDTH, Image.SCALE_DEFAULT);
		else
			finalImage = finalizeImage(image, token,TOKEN_HEIGHT, fm.stringWidth(token.getImage()) + imagePadding, Image.SCALE_DEFAULT);
		
		WritableImage writableImage = SwingFXUtils.toFXImage(finalImage, null);
		
		return new LHSIconizedToken(writableImage, token, newIndex);
	}

	
	/**
	 * Resizes an image, adds the string text, and adds occurrence subscript.
	 * @param originalImage
	 * @param height
	 * @param width
	 * @param type
	 * @return an image worth putting in the LDZ
	 */
    private static BufferedImage finalizeImage(	Image originalImage, 
    											SourceToken token, 
    											int height, 
    											int width, 
    											int type){
    	
    	String tokenImage = token.getImage();
    	String tokenType = token.getType();
    	
    	Font font = new Font("Arial", type, 12);
		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();
	    FontMetrics fm = g.getFontMetrics();
	    
	    int strWidth = (fm.stringWidth(tokenImage));
	    int imageWidth = resizedImage.getWidth();
	    
	    int textBegin = (imageWidth - strWidth) / 2 - 1;
	    int textHeight = (fm.getAscent() + (TOKEN_HEIGHT - (fm.getAscent() + fm.getDescent())) / 2 - 3);
	    
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.setColor(tokenFontColor.get(tokenType));
	    g.setFont(font);
	    g.drawString(tokenImage, textBegin, textHeight);
		g.dispose();
	 
		return resizedImage;
    }
    
}
