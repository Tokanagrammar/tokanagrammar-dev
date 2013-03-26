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

import edu.umb.cs.source.ShuffledSource;
import edu.umb.cs.source.Shuffler;
import edu.umb.cs.source.SourceFile;
import edu.umb.cs.source.Token;
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
    static final Token EMPTY = new SimpleToken("<REMOVED>")
    {
        @Override
        public boolean isEmpty()
        {
            return true;
        }
    };
    
    public static final SimpleShuffler INSTANCE = new SimpleShuffler();

    @Override
    public ShuffledSource shuffle(SourceFile src, int toRemove)
    {
        Random rand = new Random();
        // TODO: issue some warning here?
        toRemove = Math.min(toRemove, src.tokenCount());
        int hasRemoved = 0;

        List<List<Token>>  newSrc = buildList(src);
        List<Token> removed = new ArrayList<Token>(toRemove);
        
        while (hasRemoved != toRemove)
        {
            int lineIndex = rand.nextInt(newSrc.size());
            int tokenIndex = rand.nextInt(newSrc.get(lineIndex).size());
            
            ArrayList<Token> line = (ArrayList<Token>)newSrc.get(lineIndex);
            Token token = line.get(tokenIndex);
            
            // already removed.
            if (token.isEmpty())
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

    static List<List<Token>> buildList(SourceFile src)
    {
        int lineC = src.lineCount();
        ArrayList<List<Token>> ret = new ArrayList<List<Token>>(lineC);
        
        for (int n = 0; n < lineC; ++n)
        {
            int tkCount = src.tokenCount(n);
            ArrayList<Token> line = new ArrayList<Token>(tkCount);
            for (int m = 0; m < tkCount; ++m)
                line.add((src.getToken(n, m)));
            ret.add(line);
        }
        
        return ret;
    }
}
