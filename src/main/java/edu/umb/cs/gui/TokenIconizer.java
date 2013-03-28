package edu.umb.cs.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.umb.cs.demo.DemoToken;

public class TokenIconizer {
	
	final static int TOKEN_HEIGHT = 32; //token height is always the same
	final static int MIN_WIDTH = 32;

	/**
	 * Take a token and makes a graphic representation of it.
	 * @param token
	 * @return an IconizedToken
	 */
	public static IconizedToken iconizeToken(DemoToken token){
		
		String tokenType = token.getType();
		BufferedImage image = null; 
		BufferedImage resizedImage = null;
		Graphics graphics = null;
		Font font = new Font("Courier New", Font.PLAIN, 16);
		
		try{
			if(tokenType.equals("keyword")){	//actual api wants to use isKeyword etc
				image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/ui_token_pink.fw.png"));
			}else if(tokenType.equals("literal")){
				image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/ui_token_pink.fw.png"));
			}else if(tokenType.equals("identifier")){
				image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/ui_token_green.fw.png"));
			}else if(tokenType.equals("quotedString")){
				image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/ui_token_green.fw.png"));
			}else if(tokenType.equals("delimiter")){
				image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/ui_token_yellow.fw.png"));
			}else if(tokenType.equals("operator")){
				ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/ui/tokens/ui_token_purple.fw.png"));
			}else if(tokenType.equals(null)){
				//report problems
			}
		} catch(IOException e){
			e.printStackTrace();
		}
		

		
		graphics = image.getGraphics();
		FontMetrics fm = graphics.getFontMetrics();
		
		if(fm.stringWidth(token.getImage()) < 32){
			resizedImage = resizeImage(image, TOKEN_HEIGHT, MIN_WIDTH, Image.SCALE_DEFAULT);
		}else
			resizedImage = resizeImage(image, TOKEN_HEIGHT, fm.stringWidth(token.getImage()) * 3, Image.SCALE_DEFAULT);
		
		graphics = resizedImage.getGraphics();
		graphics.setColor(Color.black);	//change the color later
		graphics.setFont(font);
		centerText(token.getImage(), graphics);
		
		graphics.dispose();
		return new IconizedToken(resizedImage, token);
	}
	
	/**
	 * Center the text on the image
	 * @param image
	 * @param graphics
	 */
	private static void centerText(String image, Graphics graphics){
	    FontMetrics fm = graphics.getFontMetrics();
	    int width = (fm.stringWidth(image))/3;
	    
	    if(width < 16)
	    	width = 10;
	    
	    int height = (fm.getAscent() + (TOKEN_HEIGHT - (fm.getAscent() + fm.getDescent())) / 2);
	    graphics.drawString(image, width, height);
	}
	
	/**
	 * Resizes an image
	 * @param originalImage
	 * @param height
	 * @param width
	 * @param type
	 * @return
	 */
    private static BufferedImage resizeImage(BufferedImage originalImage, int height, int width, int type){
		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();
	 
		return resizedImage;
    }
    
	
	/*
	 * TEST! ---> uncomment the test above to send the image somewhere.
	 */
	public static void main(String[] args){
		IconizedToken test1 = iconizeToken(new DemoToken("keyword", "public"));
		IconizedToken test2 = iconizeToken(new DemoToken("identifier", "Hello, world"));
		IconizedToken test3 = iconizeToken(new DemoToken("delimiter", "{"));
		//IconizedToken test4 = iconizeToken(new DemoToken("stringLit", "\"Hello, world\""));
		
		//test
		try {
			ImageIO.write(test1.getBufferedImage(), "png", new File("C:\\Users\\Matt\\Desktop\\test1.png")); //test the image
			ImageIO.write(test2.getBufferedImage(), "png", new File("C:\\Users\\Matt\\Desktop\\test2.png")); //test the image
			ImageIO.write(test3.getBufferedImage(), "png", new File("C:\\Users\\Matt\\Desktop\\test3.png")); //test the image
			//ImageIO.write(test4.getBufferedImage(), "png", new File("C:\\Users\\Matt\\Desktop\\test4.png")); //test the image
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
