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

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;

import edu.umb.cs.source.SourceToken;
import edu.umb.cs.source.SourceTokenKind;

/**
 * @author Matt
 */
public class RHSTokenIconizer {
	
    final static int TOKEN_HEIGHT = 32; //token height is always the same
    final static int MIN_WIDTH = 32;	//the minimum token width
    private static int index;
    private RHSTokenIconizer(){}

    
    /**
    * Takes a list of SourceToken intended for the RHS and creates
    * RHSIconizedTokens from them.
    * @param tokens
    * @return a list of IconizedTokens
    */
    public static List<RHSIconizedToken> iconizeTokens(List<SourceToken> tokens)
    {
        index = 0;
        LinkedList<RHSIconizedToken> iconizedTokens = new LinkedList<RHSIconizedToken>();

        for (SourceToken token : tokens)
            iconizedTokens.add(iconizeToken(token));

        return iconizedTokens;
    }

	
	/**
	 * Take a token and makes a graphic representation of it.
	 * This is a sub-routine of iconizeTokens(..).
	 * @param token
	 * @return an IconizedToken
	 */
	 
    private static RHSIconizedToken iconizeToken(SourceToken token)
    {

        SourceTokenKind kind = token.kind();
        BufferedImage image = null;
        BufferedImage finalImage = null;
        Graphics graphics = null;
        int imagePadding = 20;

        try
        {
            switch(kind)
            {
                case KEYWORD:
                    image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/ui_token_pink.fw.png"));
                    break;
                case IDENTIFIER:
                    image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/ui_token_green.fw.png"));
                    break;
                case STRING_LITERAL:
                case NUM_LITERAL:
                case CHAR_LITERAL:
                    image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/ui_token_green.fw.png"));
                    break;                    
                case SEPARATOR:
                    image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/ui_token_yellow.fw.png"));
                    break;
                case OPERATOR:
                    image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/ui_token_purple.fw.png"));
                    break;
                    
                default:
                    System.out.println("Unsupported token: " + kind);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        graphics = image.getGraphics();
        FontMetrics fm = graphics.getFontMetrics();

        if (fm.stringWidth(token.image()) < 25)
            finalImage = finalizeImage(image, token, TOKEN_HEIGHT, MIN_WIDTH, Image.SCALE_DEFAULT);
        else
            finalImage = finalizeImage(image, token, TOKEN_HEIGHT, fm.stringWidth(token.image()) + imagePadding, Image.SCALE_DEFAULT);

        WritableImage writableImage = SwingFXUtils.toFXImage(finalImage, null);

        return new RHSIconizedToken(writableImage, token, index++);
    }
	
	/**
	 * Creates a "pretty" clear token so the user can tell it's a placed token.
	 * @param token
	 * @return an IconizedToken
	 */
    public static RHSIconizedToken createSingleIconizedToken(SourceToken token, int newIndex)
    {

        SourceTokenKind kind = token.kind();
        BufferedImage image = null;
        BufferedImage finalImage = null;
        Graphics graphics = null;
        int imagePadding = 20;

        
        try
        {
            switch(kind)
            {
                case KEYWORD:
                    image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/lucentPink.fw.png"));
                    break;
                case IDENTIFIER:
                    image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/lucentGreen.fw.png"));
                    break;
                case STRING_LITERAL:
                case NUM_LITERAL:
                case CHAR_LITERAL:
                    image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/lucentGreen.fw.png"));
                    break;                    
                case SEPARATOR:
                    image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/lucentYellow.fw.png"));
                    break;
                case OPERATOR:
                    image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/lucentPurple.fw.png"));
                    break;
                    
                default:
                    System.out.println("Unsupported token: " + kind);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        graphics = image.getGraphics();
        FontMetrics fm = graphics.getFontMetrics();

        if (fm.stringWidth(token.image()) < 25)
            finalImage = finalizeImage(image, token, TOKEN_HEIGHT, MIN_WIDTH, Image.SCALE_DEFAULT);
        else
            finalImage = finalizeImage(image, token, TOKEN_HEIGHT, fm.stringWidth(token.image()) + imagePadding, Image.SCALE_DEFAULT);
        WritableImage writableImage = SwingFXUtils.toFXImage(finalImage, null);
        return new RHSIconizedToken(writableImage, token, newIndex);
    }

	
	/**
	 * Resizes an image and adds the string text.
	 * @param originalImage
	 * @param height
	 * @param width
	 * @param type
	 * @return an image to put on the GameBoard
	 */
    private static BufferedImage finalizeImage(Image originalImage,
                                               SourceToken token,
                                               int height,
                                               int width,
                                               int type)
    {
	
    	String tokenImage = token.image();
    	SourceTokenKind kind = token.kind();
    	
    	Font font = new Font("Arial", type, 12);
		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();
	    FontMetrics fm = g.getFontMetrics();
	    
	    int strWidth = (fm.stringWidth(tokenImage));
	    int imageWidth = resizedImage.getWidth();
	    
	    int textBegin = (imageWidth - strWidth) / 2 - 1;
	    int textHeight = (fm.getAscent() + (TOKEN_HEIGHT - (fm.getAscent() + fm.getDescent())) / 2 - 3);
	    
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.setColor(LHSTokenIconizer.tokenFontColors.get(kind));
	    g.setFont(font);
	    g.drawString(tokenImage, textBegin, textHeight);
		g.dispose();
	 
		return resizedImage;
		
    }
    
    public static void resetIndex()
    {
        index = 0;
    }
}
