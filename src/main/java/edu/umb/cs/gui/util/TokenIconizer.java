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

package edu.umb.cs.gui.util;

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
import java.util.Map.Entry;
import java.util.Set;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;

import edu.umb.cs.demo.DemoToken;
import edu.umb.cs.gui.IconizedToken;

/**
 * 
 * @author Matt
 *
 */
public class TokenIconizer {
	
	final static int TOKEN_HEIGHT = 32; //token height is always the same
	final static int MIN_WIDTH = 32;	//the minimum token width
	
	private static HashMap<DemoToken, Integer> occurrenceTable = new HashMap<DemoToken, Integer>();
	private static HashMap<String, Color> tokenFontColor = new HashMap<String, Color>();
	
	
	/**
	 * The primary method of TokenIconizer does all iconizing at once, for the
	 * entire LinkedList of pased tokens.
	 * @param tokens
	 * @return
	 */
	public static LinkedList<IconizedToken> iconizeTokens(List<DemoToken> tokens){
		
		LinkedList<IconizedToken> iconizedTokens = new LinkedList<IconizedToken>();
		Set<Entry<DemoToken, Integer>> entrySet;
    	
    	tokenFontColor.put("keyword", Color.yellow);
    	tokenFontColor.put("literal", Color.white);
    	tokenFontColor.put("identifier", Color.red );
    	tokenFontColor.put("quotedString", Color.blue);
    	tokenFontColor.put("refType", Color.blue);
    	tokenFontColor.put("delimiter", Color.black);
    	tokenFontColor.put("operator", Color.white);
		
		//load the occurrence table with all 0's
		for(int k=0; k< tokens.size(); k++)
			//We know we have at least one of each that are passed to this method.
			occurrenceTable.put(tokens.get(k), 1);  
		
		//mark the appropriate location in the occurrence table if we find a duplicate
		for(int i=0; i< tokens.size(); i++){
			DemoToken comparer = tokens.get(i);
			for(int j= i+1; j< tokens.size(); j++){
				DemoToken compareTo = tokens.get(j);
				if(compareTo.equals(comparer))
					occurrenceTable.put(comparer, occurrenceTable.get(comparer) + 1) ;
			}
		}
		
		entrySet = occurrenceTable.entrySet();
		
		for(Entry<DemoToken, Integer> entry : entrySet)
			iconizedTokens.add( iconizeToken((DemoToken) entry.getKey(), (Integer) entry.getValue()) );
		
		return iconizedTokens;
	}
	
	
	/**
	 * Take a token and makes a graphic representation of it.
	 * @param token
	 * @return an IconizedToken
	 */
	private static IconizedToken iconizeToken(DemoToken token, Integer occurrences){
		
		String tokenType = token.getType();
		BufferedImage image = null; 
		BufferedImage finalImage = null;
		Graphics graphics = null;
		int imagePadding = 20;
		
		//TODO put this info in a table
		try{
			if(tokenType.equals("keyword")){	//actual api wants to use isKeyword etc once i get used to the real api
				image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/ui_token_pink.fw.png"));
			}else if(tokenType.equals("literal")){
				image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/ui_token_pink.fw.png"));
			}else if(tokenType.equals("identifier")){
				image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/ui_token_green.fw.png"));
			}else if(tokenType.equals("quotedString")){
				image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/ui_token_green.fw.png"));
			}else if(tokenType.equals("refType")){
				image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/ui_token_green.fw.png"));
			}else if(tokenType.equals("delimiter")){
				image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/ui_token_yellow.fw.png"));
			}else if(tokenType.equals("operator")){
				image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/ui_token_purple.fw.png"));
			}else if(tokenType.equals(null)){
				System.out.println("Token type " + tokenType + " is not valid.");
			}
		} catch(IOException e){
			e.printStackTrace();
		}
		
		graphics = image.getGraphics();
		FontMetrics fm = graphics.getFontMetrics();
		
		if(fm.stringWidth(token.getImage()) < 25)
			finalImage = finalizeImage(image, token, occurrences, TOKEN_HEIGHT, MIN_WIDTH, Image.SCALE_DEFAULT);
		else
			finalImage = finalizeImage(image, token, occurrences, TOKEN_HEIGHT, fm.stringWidth(token.getImage()) + imagePadding, Image.SCALE_DEFAULT);

		WritableImage writableImage = SwingFXUtils.toFXImage(finalImage, null);
		
		return new IconizedToken(writableImage, token, occurrences);
	}

	
	/**
	 * Resizes an image, adds the string text, and adds occurrence subscript.
	 * @param originalImage
	 * @param height
	 * @param width
	 * @param type
	 * @return
	 */
    private static BufferedImage finalizeImage(BufferedImage originalImage, DemoToken token, Integer occurrences, int height, int width, int type){

    	String tokenImage = token.getImage();
    	String tokenType = token.getType();
    	Font font = new Font("Arial", type, 11);
		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();
	    FontMetrics fm = g.getFontMetrics();
	    
	    int strWidth = (fm.stringWidth(tokenImage));
	    int imageWidth = resizedImage.getWidth();
	    
	    int textBegin = (imageWidth - strWidth) / 2 - 1;
	    int textHeight = (fm.getAscent() + (TOKEN_HEIGHT - (fm.getAscent() + fm.getDescent())) / 2 - 3);
	    
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.setColor(tokenFontColor.get(tokenType));  //color set in tokenFontColor
	    g.setFont(font);
	    g.drawString(tokenImage, textBegin, textHeight); 

    	//for multiple occurrences, draw subscript
	    if(occurrences > 1){
		    try {
				BufferedImage numOnSubscript = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/subscript.fw.png"));
				g.drawImage(numOnSubscript, width - 20, height-20, 20, 20, null);
				g.setColor(Color.white);
				g.drawString(occurrences.toString(), width - 11, (height-20)*2 + 5);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
		g.dispose();
	 
		return resizedImage;
		
    }
    
}
