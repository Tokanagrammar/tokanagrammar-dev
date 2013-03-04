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
package cs.umb.edu.source.std;

import cs.umb.edu.source.SourceFile;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Simple representation of a Java Source File
 * @author Vy Thao Nguyen
 */
public class JavaSourceFile implements SourceFile
{

    private final String path;
    private final ArrayList<ArrayList<String>> srcFile;
    private final int tokenCount;
    private final Map<String, Integer> stats;
    
    public JavaSourceFile(String path,
                          ArrayList<ArrayList<String>> tokens,
                          int tokenCount)
            throws FileNotFoundException
    {
        this.path = path;
        this.srcFile = tokens;
        this.tokenCount = tokenCount;
        stats = buildStats(srcFile);
    }
    
    @Override
    public String getLine(int line)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getToken(int line, int position)
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
    public Map<String, Integer> getStatistic()
    {
        return stats;
    }

    @Override
    public String compileAndExecute()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static Map<String, Integer> buildStats(ArrayList<ArrayList<String>> srcFile)
    {
        throw new UnsupportedOperationException();
    }
}
