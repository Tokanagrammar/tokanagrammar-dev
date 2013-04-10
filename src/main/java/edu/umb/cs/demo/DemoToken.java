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

public class DemoToken {
	
	
	private String type;
	private String image;
	
	
	public DemoToken(String type, String image){
		this.type = type;
		this.image = image;
	}
	
	public String getType(){
		return type;
	}
	
	public String getImage(){
		return image;
	}

	
	/**
	 * Compare two tokens to see if they're equal.  This
	 * will be used to determine if a "number subscript" needs
	 * to be put on a token. 
	 */
    public boolean equals(Object obj) {
    	if(obj instanceof DemoToken)
    		return 	type.equals(((DemoToken) obj).getType()) &&
    				image.equals(((DemoToken) obj).getImage());
    	else return false;

    }
    
    public int hashCode() {
    	return image.hashCode() + type.hashCode();
    }
    
    @Override
    public String toString(){
    	String string = "[type: " + type + " image: " + image + "]";
    	
		return string;
    }

}



