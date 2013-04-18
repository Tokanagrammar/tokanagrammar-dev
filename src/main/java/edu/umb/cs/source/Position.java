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
package edu.umb.cs.source;

/**
 *
 * @author Vy Thao Nguyen
 */
public class Position 
{
    private final int line;
    private final int pos;
    
    public Position (int line, int pos)
    {
        this.line = line;
        this.pos = pos;
    }

    public int getLine()
    {
        return line;
    }
    
    public int getPos()
    {
        return pos;
    }
    
    public boolean equals(Object other)
    {
        if (other != null && other instanceof Position)
        {
            Position oPos = (Position)other;
            return oPos.line == line && oPos.pos == pos;
        }
        else
            return false;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 59 * hash + line;
        hash = 59 * hash + pos;
        return hash;
    }
}
