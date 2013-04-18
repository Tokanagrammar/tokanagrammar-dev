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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Tokens are taken out at random, there are no weights whatsoever
 * @author Vy Thao Nguyen
 */
public class SimpleShuffler implements Shuffler
{
    public static final SimpleShuffler INSTANCE = new SimpleShuffler();

    @Override
    public ShuffledSource shuffle(SourceFile src, int toRemove)
    {
        Random rand = new Random();
        int hasRemoved = 0;

        List<List<SourceToken>>  newSrc = buildList(src);
        List<SourceToken> removed = new ArrayList<>(toRemove);
        ArrayList<Position> removable = src.getNonWhitespaces();
        
        // TODO: issue some warning here?
        toRemove = Math.min(toRemove, removable.size());
        
        while (hasRemoved != toRemove)
        {
            int i = rand.nextInt(removable.size());
            Position pos = removable.get(i);
            removable.remove(i);
            
            List<SourceToken> curLine = newSrc.get(pos.getLine());
            SourceToken tk = curLine.get(pos.getPos());
            curLine.set(pos.getPos(), EmptyToken.INSTANCE);
            removed.add(tk);
            ++hasRemoved;
        }
 
        SourceFile shuffled = new JavaSourceFile("UNKNOWN_PATH",
                                                 newSrc,
                                                 src.tokenCount(),
                                                 src.getStyle(),
                                                 src.getClassName());

        return new ShuffledSourceImpl(src, shuffled, removed);
    }

    @Override
    public ShuffledSource shuffle(SourceFile src, int toRemove, String... toks)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    static List<List<SourceToken>> buildList(SourceFile src)
    {
        // TODO: deep copy
        return src.getAll();
    }
}
