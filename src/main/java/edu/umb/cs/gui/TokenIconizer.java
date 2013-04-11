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

import edu.umb.cs.parser.InternalException;
import edu.umb.cs.source.SourceToken;
import edu.umb.cs.source.SourceTokenKind;
import static edu.umb.cs.source.SourceTokenKind.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.*;
import java.util.Map.Entry;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;

/**
 *
 * @author Matt
 *
 */
public class TokenIconizer
{

    final static int TOKEN_HEIGHT = 32;     //token height is always the same
    final static int MIN_WIDTH = 32;	//the minimum token width
    private static final Map<SourceTokenKind, Color> tokenTextColors = computeColorsMap();
    private static final Map<SourceTokenKind, Image> tokenImages = computeImagesMap();
    private static final int defaultPadding = 20;
    
    private static Map<SourceTokenKind, Color> computeColorsMap()
    {
        EnumMap<SourceTokenKind, Color> ret = new EnumMap<>(SourceTokenKind.class);

        ret.put(KEYWORD, Color.blue);
        ret.put(NUM_LITERAL, Color.white);
        ret.put(CHAR_LITERAL, Color.white);
        ret.put(IDENTIFIER, Color.red);
        ret.put(STRING_LITERAL, Color.blue);
        ret.put(SEPARATOR, Color.black);
        ret.put(OPERATOR, Color.white);

        return Collections.unmodifiableMap(ret);
    }

    private static Map<SourceTokenKind, Image> computeImagesMap()
    {
        try
        {
            EnumMap<SourceTokenKind, Image> ret = new EnumMap<>(SourceTokenKind.class);

            ret.put(KEYWORD,ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/ui_token_pink.fw.png")));
            ret.put(NUM_LITERAL, ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/ui_token_pink.fw.png")));
            ret.put(CHAR_LITERAL, ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/ui_token_pink.fw.png")));
            ret.put(IDENTIFIER, ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/ui_token_green.fw.png")));
            ret.put(STRING_LITERAL, ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/ui_token_green.fw.png")));
            ret.put(SEPARATOR, ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/ui_token_yellow.fw.png")));
            ret.put(OPERATOR, ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/ui_token_purple.fw.png")));
            // TODO: ref-type (This is actually IDENTIFIER for now)
            //ret.put(REFTYPE, ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/ui_token_green.fw.png")));

            return Collections.unmodifiableMap(ret);
        }
        catch (IOException ex)
        {
            throw new InternalException("IOException while initialising TokenIconizer: " + ex.getMessage());
        }
    }

    private TokenIconizer()
    {
    }

    /**
     * The primary method of TokenIconizer does all iconizing at once, for the
     * entire LinkedList of pased tokens.
     *
     * @param tokens
     * @return
     */
    public static List<IconizedToken> iconizeTokens(List<SourceToken> tokens)
    {
        //store all the newly iconized tokens here
        List<IconizedToken> iconizedTokens = new LinkedList<>();

        // statistic table
        Map<SourceToken, Integer> stats = new HashMap<>();

        // count the occurrences
        for (SourceToken tk : tokens)
        {
            Integer c = stats.put(tk, 1);
            if (c != null)            // previously seen
                stats.put(tk, c + 1); // increment the count
        }

        // now iconise them
        for (Entry<SourceToken, Integer> entry : stats.entrySet())
            iconizedTokens.add(iconizeToken(entry.getKey(), entry.getValue()));

        return iconizedTokens;
    }

    /**
     * Take a token and makes a graphic representation of it.
     *
     * @param token
     * @return an IconizedToken
     */
    private static IconizedToken iconizeToken(SourceToken token, Integer count)
    {
        Image image = tokenImages.get(token.getKind());
        if (image == null)
            throw new InternalException("No such token: " + token);
        
        Graphics graphics = image.getGraphics();
        FontMetrics fm = graphics.getFontMetrics();
        BufferedImage finalImage
                = finalizeImage(image,
                                token,
                                count,
                                TOKEN_HEIGHT,
                                fm.stringWidth(token.image()) < 25
                                    ? MIN_WIDTH
                                    : fm.stringWidth(token.image()) + defaultPadding,
                                Image.SCALE_DEFAULT);
        
        WritableImage writableImage = SwingFXUtils.toFXImage(finalImage, null);
        return new IconizedToken(writableImage, token, count);
    }

    /**
     * Resizes an image, adds the string text, and adds occurrence subscript.
     *
     * @param originalImage
     * @param height
     * @param width
     * @param type
     * @return
     */
    private static BufferedImage finalizeImage(Image originalImage,
                                               SourceToken token,
                                               Integer occurrences,
                                               int height,
                                               int width,
                                               int type)
    {
        String tkImage = token.image(); // textual representation of the token
                                        // (not to be confused with 'image')
        
        Font font = new Font("Arial", type, 11);
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        FontMetrics fm = g.getFontMetrics();

        int strW = fm.stringWidth(tkImage);
        int imageW = resizedImage.getWidth(); // same as width arg?
        
        int textBegin = (imageW - strW) / 2 - 1;
        int textHeight = fm.getAscent() + (TOKEN_HEIGHT - (fm.getAscent() + fm.getDescent())) / 2 - 3;
        
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.setColor(tokenTextColors.get(token.getKind()));
        g.setFont(font);
        g.drawString(tkImage, textBegin, textHeight);

        //for multiple occurrences, draw subscript
        if (occurrences > 1)
        {
            try
            {
                Image numOnSubscript 
                        = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/subscript.fw.png"));
                g.drawImage(numOnSubscript, width - 20, height - 20, 20, 20, null);
                g.setColor(Color.white);
                g.drawString(occurrences.toString(), width - 11, (height - 20) * 2 + 5);
            }
            catch (IOException e)
            {
                throw new InternalException("IOException while drawing subscript: " + e.getMessage());
            }
        }

        g.dispose();
        return resizedImage;
    }
}
