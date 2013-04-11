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

import edu.umb.cs.source.SourceFile;
import edu.umb.cs.source.SourceToken;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A SourceFile built from a list of tokens (not really parsed from a real
 * source file)
 * @author Vy Thao Nguyen
 */
class ArtificialSourceFile implements SourceFile
{
    private final int lineCount;
    private final int tokenCount;
    private final List<List<SourceToken>> srcFile;
    private final List<String> lines;
    private final String rep;
    ArtificialSourceFile(int lineCount, int tokenCount, List<List<SourceToken>> src)
    {
        this.lineCount = lineCount;
        this.tokenCount = tokenCount;
        this.srcFile = src;
        lines = new ArrayList<>(lineCount);
        StringBuilder file = new StringBuilder();
        
        for (List<SourceToken> line : src)
        {
            StringBuilder bd = new StringBuilder();
            for (SourceToken tk : line)
                bd.append(tk.image());
            file.append(bd).append('\n');
            lines.add(bd.toString());
        }
        
        rep = file.toString();
    }

    @Override
    public String getLine(int line)
    {
        return lines.get(line);
    }

    @Override
    public List<SourceToken> getTokens(int line)
    {
        return srcFile.get(line);
    }

    @Override
    public SourceToken getToken(int line, int position)
    {
        return srcFile.get(line).get(position);
    }

    @Override
    public int tokenCount(int line)
    {
        return srcFile.get(line).size();
    }

    @Override
    public int tokenCount()
    {
        return tokenCount;
    }

    @Override
    public int lineCount()
    {
        return lineCount;
    }

    @Override
    public Map<SourceToken, Integer> getStatistic()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String compileAndExecute()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString()
    {
        return rep;
    }
}
