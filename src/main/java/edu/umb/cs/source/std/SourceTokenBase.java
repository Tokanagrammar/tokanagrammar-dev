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
package edu.umb.cs.source.std;

import edu.umb.cs.source.SourceToken;
import edu.umb.cs.source.SourceTokenKind;

/**
 *
 * @author Vy Thao Nguyen
 */
public class SourceTokenBase implements SourceToken
{
    private final String img;
    private final SourceTokenKind kind;
    private final String rep;
    SourceTokenBase(String img, SourceTokenKind kind)
    {
        this.img = img;
        this.kind = kind;
        rep = "<" + kind.name() + ": " + img + ">";
    }

    @Override
    public String image()
    {
        return img;
    }

    @Override
    public SourceTokenKind getKind()
    {
        return kind;
    }

    // Object interface
    @Override
    public String toString()
    {
        return rep;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (o != null && o instanceof SourceToken)
            return ((SourceToken)o).image().equals(img);
        else
            return false;
    }

    @Override
    public int hashCode()
    {
        return img.hashCode();
    }
}
