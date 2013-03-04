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
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This represents a Java source file in a very naive way,
 * that is, 'token' is defined as a list of characters not separated by any 
 * whitespace.
 * 
 * 
 * @author Vy Thao Nguyen
 */
public class SimpleJavaSourceFile implements SourceFile
{
    private final String path;
    private final ArrayList<ArrayList<String>> srcFile;
    private final int tokenCount;
    private final Map<String, Integer> stats;
    private final String rep;
    
    public SimpleJavaSourceFile(String path) throws FileNotFoundException
    {
        this.path = path;
        stats = new HashMap<String, Integer>();
        
        FileReader inFile = new FileReader(path);
        Scanner in = new Scanner(inFile);
        
        int c = 0;
        srcFile = new ArrayList<ArrayList<String>>();
        ArrayList<String> lines;
        String line;
        while (in.hasNextLine())
        {
            line = in.nextLine();
            lines = new ArrayList<String>();
            for (String tk : line.split("\\s++"))
            {
                if (stats.containsKey(tk))
                    stats.put(tk, stats.get(tk) + 1);
                lines.add(tk);
            }
            c += lines.size();
            srcFile.add(lines);
        }
        tokenCount = c;
        rep = buildRep(srcFile);
    }
    
    // --- SourceFile states ---
    
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
    
    @Override
    public String toString()
    {
        return rep;
    }
    
    // ---- private helpers -----------
    
    private static String buildRep(ArrayList<ArrayList<String>> srcFile)
    {
        StringBuilder rep = new StringBuilder();
        for (ArrayList<String> line : srcFile)
        {
            for (String tk : line)
                rep.append(tk).append(' ');
            rep.append('\n');
        }
        return rep.toString();
    }
}