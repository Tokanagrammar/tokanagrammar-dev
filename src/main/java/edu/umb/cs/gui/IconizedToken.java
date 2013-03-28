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

import javafx.scene.image.Image;
//import javafx.scene.image.WritableImage;
import edu.umb.cs.demo.DemoToken;

public class IconizedToken {

	/**The dynamically created image representation of a token**/
	private Image image;
	/**The original token**/
	private DemoToken token;

	public IconizedToken(Image image, DemoToken token) {
		this.image = image;
		this.token = token;
	}


	public Image getImage(){
		return image;
	}

	public DemoToken getDemoToken(){
		return token;
	}

}