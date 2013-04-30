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

import edu.umb.cs.source.SourceToken;
import edu.umb.cs.source.SourceTokenKind;
import edu.umb.cs.source.std.EmptyToken;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import static edu.umb.cs.source.SourceTokenKind.*;

/**
 * Handles the tokens that go on the LHS
 * @author Matt
 *
 */
public class LHSTokenIconizer {
	
	final static int TOKEN_HEIGHT = 20; //token height is always the same
	final static int MIN_WIDTH = 32;	//the minimum token width
	
	final static Font TOKEN_FONT = new Font("Courier New", Image.SCALE_DEFAULT, 12);
	
	private static int index;
	
	static final Map<SourceTokenKind, Color> tokenFontColors = computeColorsMap();
	
	private static Map<SourceTokenKind, Color> computeColorsMap()
	{
		EnumMap<SourceTokenKind, Color> ret = new EnumMap<>(SourceTokenKind.class);

		ret.put(KEYWORD, Color.MAGENTA);
		ret.put(NUM_LITERAL, Color.black);
		ret.put(CHAR_LITERAL, Color.orange);
		ret.put(IDENTIFIER, Color.red);
		ret.put(STRING_LITERAL, Color.orange);
		ret.put(SEPARATOR, Color.black);
		ret.put(OPERATOR, Color.black);
		return Collections.unmodifiableMap(ret);
	}
        
        /**
         * Take a token and makes a graphic representation of it.
         * @param token
         * @return an IconizedToken
         */
        public static LHSIconizedToken iconizeToken(SourceToken token){

        	SourceTokenKind tokenKind = token.kind();
        	BufferedImage image = null; 
        	BufferedImage finalImage = null;
        	Graphics graphics = null;

        	try{ 
        		if(tokenKind == SourceTokenKind.EMPTY)
        			image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/removed_.fw.png"));
        		else
        			image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/noop_.fw.png"));

        	} catch(IOException e){
        		e.printStackTrace();
        	}

        	graphics = image.getGraphics();
        	FontMetrics fm = graphics.getFontMetrics(TOKEN_FONT);

        	switch(tokenKind)
        	{
        	case EMPTY:
        		finalImage = finalizeImage(image, EmptyToken.INSTANCE,TOKEN_HEIGHT, MIN_WIDTH, Image.SCALE_DEFAULT);
        		break;

        	case TAB:
        		finalImage = finalizeImage(image, token,TOKEN_HEIGHT, 20 , Image.SCALE_DEFAULT);
        		break;

        	default:
        		finalImage = finalizeImage(image, token,TOKEN_HEIGHT, fm.stringWidth(token.image()), Image.SCALE_DEFAULT);
        	}

        	WritableImage writableImage = SwingFXUtils.toFXImage(finalImage, null);

        	return new LHSIconizedToken(writableImage, token, index++);
        }
	
	/**
	 * Creates a "pretty" clear token so the user can tell it's a placed token.
	 * @param token
	 * @return an IconizedToken
	 */
	public static LHSIconizedToken createSingleIconizedToken(SourceToken token, int newIndex){

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
                    case CHAR_LITERAL:
                    case NUM_LITERAL:
                        image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/lucentGreen.fw.png"));
                        break;
                        
                    case SEPARATOR:
                        image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/lucentYellow.fw.png"));
                        break;
                        
                    case OPERATOR:
                        image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/lucentPurple.fw.png"));
                        break;
                        
                    default:
                        System.out.println("Unexpected token kind: " + kind);
                        // TODO: handle error
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            
            graphics = image.getGraphics();
            FontMetrics fm = graphics.getFontMetrics(TOKEN_FONT);
            
   

            if(fm.stringWidth(token.image()) < 25 || token.kind() == SourceTokenKind.EMPTY)
                finalImage = finalizeImage(image, token,TOKEN_HEIGHT, MIN_WIDTH, Image.SCALE_DEFAULT);
            else
                finalImage = finalizeImage(image, token,TOKEN_HEIGHT, fm.stringWidth(token.image()) + imagePadding, Image.SCALE_DEFAULT);
            
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

		String tokenImage = token.image();
		SourceTokenKind kind = token.kind();

		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();
		FontMetrics fm = g.getFontMetrics(TOKEN_FONT);

		int strWidth = (fm.stringWidth(tokenImage));
		int imageWidth = resizedImage.getWidth();

		int textBegin = (imageWidth - strWidth) / 2;
		int textHeight = (fm.getAscent() + (TOKEN_HEIGHT - (fm.getAscent() + fm.getDescent())) / 2 );

		g.drawImage(originalImage, 0, 0, width, height, null);
		g.setColor(tokenFontColors.get(kind));
		g.setFont(TOKEN_FONT);
		if (kind != SourceTokenKind.EMPTY)
			g.drawString(tokenImage, textBegin, textHeight);

		g.dispose();
		return resizedImage;
	}
    
    // temp
    public static void resetIndex()
    {
        index = 0;
    }
    
}
