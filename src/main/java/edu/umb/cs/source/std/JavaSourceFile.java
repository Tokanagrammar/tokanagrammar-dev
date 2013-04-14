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

import edu.umb.cs.parser.BracingStyle;
import edu.umb.cs.source.SourceFile;
import edu.umb.cs.source.SourceToken;
import java.util.List;
import java.util.Map;

/**
 * Simple representation of a Java Source File
 * @author Vy Thao Nguyen
 */
public class JavaSourceFile implements SourceFile
{

    private final String path;
    private final List<List<SourceToken>> srcFile;
    private final int tokenCount;
    private final Map<SourceToken, Integer> stats;
    private final BracingStyle style;
    private final String outerMost;
    
    private boolean hasError = true;
    private String output = null;
    
    public JavaSourceFile(String path,
                          List<List<SourceToken>> tokens,
                          int tokenCount,
                          BracingStyle style,
                          String outer)
    {
        this.path = path;
        this.srcFile = tokens;
        this.tokenCount = tokenCount;
        stats = buildStats(srcFile);
        this.style = style;
        outerMost = outer;
    }
    
    // Object interface
    @Override
    public String toString()
    {
        StringBuilder bd = new StringBuilder();
        bd.append("PATH = " ).append(path).append('\n');
        bd.append("SOURCE starts here: \n--------------\n");
        for (List<SourceToken> line : srcFile)
        {
            for (SourceToken tk : line)
            {
                bd.append(tk.image());
            }
            bd.append('\n');
        }
        
        return bd.toString();
    }
    
    // SourceFile interface
    
    @Override
    public BracingStyle getStyle()
    {
        return style;
    }
    
    @Override
    public String getLine(int line)
    {
        return srcFile.get(line).toString();
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
        return srcFile.size();
    }

    @Override
    public Map<SourceToken, Integer> getStatistic()
    {
        return stats;
    }

    @Override
    public String compileAndExecute()
    {
        if (output == null)
        {
            int retcode [] = new int[1];
            output = Utils.compile(srcFile, outerMost, retcode);
            hasError = retcode[0] != 0;
        }
        return output;
    }

    private static Map<SourceToken, Integer> buildStats(List<List<SourceToken>> srcFile)
    {
        return null;
        //throw new UnsupportedOperationException();
    }
}
