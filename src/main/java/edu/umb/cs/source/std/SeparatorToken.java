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

import edu.umb.cs.parser.InternalException;
import edu.umb.cs.source.SourceToken;
import edu.umb.cs.source.SourceTokenKind;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Vy Thao Nguyen
 */
public class SeparatorToken  implements SourceToken
{
    private static final Map<String, SourceToken> seps = computeMap();
    private static Map<String, SourceToken> computeMap()
    {
        Map<String, SourceToken> ret = new HashMap<>();
        
        for (String st : Arrays.asList("[", "]", "{", "}", "(", ")", ",", ";", "."))
            ret.put(st, new SeparatorToken(st));
        
        return ret;
    }
    
    public static SourceToken getSep(String img)
    {
        SourceToken tk = seps.get(img);
        if (tk == null)
            throw new InternalException("no such seperator");
        return tk;
    }
    private String img;
    
    SeparatorToken (String img)
    {
        this.img = img;
    }

    @Override
    public String image()
    {
        return img;
    }

    @Override
    public SourceTokenKind getKind()
    {
        return SourceTokenKind.SEPARATOR;
    }

}
