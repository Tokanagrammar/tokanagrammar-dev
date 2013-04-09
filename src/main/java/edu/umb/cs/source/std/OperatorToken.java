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
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Vy Thao Nguyen
 */
public class OperatorToken implements SourceToken
{
    private static final Map<String, SourceToken> instances
                = new HashMap<String, SourceToken>();

    public static SourceToken getOperator(String img)
    {
        SourceToken tk = instances.get(img);
        if (tk == null)
            instances.put(img, tk = new OperatorToken(img));
        return tk;
    }

    private final String image;
    
    private OperatorToken (String img)
    {
        image = img;
    }

    @Override
    public String image()
    {
        return image;
    }

    @Override
    public boolean isKeyWord()
    {
        return false;
    }

    @Override
    public boolean isLiteral()
    {
        return false;
    }

    @Override
    public boolean isIdentifier()
    {
        return true;
    }

    @Override
    public boolean isQuotedString()
    {
        return false;
    }

    @Override
    public boolean isTab()
    {
        return false;
    }

    @Override
    public boolean isSpace()
    {
        return false;
    }

    @Override
    public boolean isEmpty()
    {
        return false;
    }

}
