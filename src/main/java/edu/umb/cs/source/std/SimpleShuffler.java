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

import edu.umb.cs.source.*;
import edu.umb.cs.source.std.AutomaticallyParsedJavaSourceFile.SimpleToken;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Tokens are taken out at random, there are no weights whatsoever
 * @author Vy Thao Nguyen
 */
public class SimpleShuffler implements Shuffler
{
    static final SourceToken EMPTY = new SimpleToken("<REMOVED>")
    {
        @Override
        public SourceTokenKind getKind()
        {
            return SourceTokenKind.EMPTY;
        }
    };
    
    public static final SimpleShuffler INSTANCE = new SimpleShuffler();

    @Override
    public ShuffledSource shuffle(SourceFile src, int toRemove)
    {
        System.out.println("to remove: " + toRemove);
        Random rand = new Random();
        // TODO: issue some warning here?
        toRemove = Math.min(toRemove, src.tokenCount());
        int hasRemoved = 0;

        List<List<SourceToken>>  newSrc = buildList(src);
        List<SourceToken> removed = new ArrayList<>(toRemove);
        
        while (hasRemoved != toRemove)
        {
            int lineIndex = rand.nextInt(newSrc.size());
            int tokenIndex = rand.nextInt(newSrc.get(lineIndex).size());
            
            ArrayList<SourceToken> line = (ArrayList<SourceToken>)newSrc.get(lineIndex);
            SourceToken token = line.get(tokenIndex);
            
            // already removed. or is quite spaces
            // (it is not a good idea to remove white spaces)
            SourceTokenKind kind = token.getKind();
            if (kind == SourceTokenKind.EMPTY
                    || kind == SourceTokenKind.SPACE
                    || kind == SourceTokenKind.TAB)
                continue;
            
            removed.add(token);
            newSrc.get(lineIndex).set(tokenIndex, EMPTY);
            ++hasRemoved;
        }
        
        SourceFile shuffled = new ArtificialSourceFile(src.lineCount(),
                                                       src.tokenCount(),
                                                       newSrc);

        return new ShuffledSourceImpl(src, shuffled, removed);
    }

    @Override
    public ShuffledSource shuffle(SourceFile src, int toRemove, String... toks)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    static List<List<SourceToken>> buildList(SourceFile src)
    {
        int lineC = src.lineCount();
        ArrayList<List<SourceToken>> ret = new ArrayList<>(lineC);
            
        for (int n = 0; n < lineC; ++n)
        {
            int tkCount = src.tokenCount(n);
            ArrayList<SourceToken> line = new ArrayList<>(tkCount);
            for (int m = 0; m < tkCount; ++m)
                line.add((src.getToken(n, m)));
            ret.add(line);
        }
        
        return ret;
    }
}
