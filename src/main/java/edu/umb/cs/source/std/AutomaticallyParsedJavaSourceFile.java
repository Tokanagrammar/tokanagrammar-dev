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
import edu.umb.cs.source.SourceTokenKind;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * This represents a Java source file in a very naive way,
 * that is, 'token' is defined as a list of characters not separated by any 
 * whitespace.
 * 
 * 
 * @author Vy Thao Nguyen
 */
public class AutomaticallyParsedJavaSourceFile implements SourceFile
{
    /**
     * Simple representation of a token.
     * Everything is treated as an identifer
     * (hence, they will be 'painted' with the same shade!)
     */
    static class SimpleToken implements SourceToken
    {
        private final String image;
        private final boolean isKeyword;
        SimpleToken(String img)
        {
            image = img;
            isKeyword = keywords.contains(img);
        }
        
        // SourceFile interface 
        
        @Override
        public String image()
        {
            return image;
        }

        @Override
        public SourceTokenKind getKind()
        {
            return isKeyword ? SourceTokenKind.KEYWORD : SourceTokenKind.IDENTIFIER;
        }

        // Object interface
        
        @Override
        public boolean equals(Object other)
        {
            if (other == null || !(other instanceof SourceToken))
                return false;
            return image.equals(((SourceToken)other).image());
        }
        
        @Override
        public int hashCode()
        {
            return image.hashCode();
        }
        
        @Override
        public String toString()
        {
            return image;
        }
    }
    
    private final String path;
    private final List<List<SourceToken>> srcFile;
    private final List<String> lines;
    private final int tokenCount;
    private final Map<SourceToken, Integer> stats;
    private String rep = null;
    
    public AutomaticallyParsedJavaSourceFile(String path) throws FileNotFoundException
    {
        this.path = path;
        stats = new HashMap<>();
        
        FileReader inFile = new FileReader(path);
        Scanner in = new Scanner(inFile);

        int c = 0;
        srcFile = new ArrayList<>();
        String line;
        lines = new ArrayList<>();
        while (in.hasNextLine())
        {
            line = in.nextLine();
            List<SourceToken> wholeLine = new ArrayList<>();
            for (String tk : line.split("\\s++"))
            {
                SourceToken tok = new SimpleToken(tk);
                if (stats.containsKey(tok))
                    stats.put(tok, stats.get(tok) + 1);
                wholeLine.add(tok);
            }
            c += wholeLine.size();
            srcFile.add(wholeLine);
            lines.add(line);
        }
        tokenCount = c;
    }
    
    // --- SourceFile states ---
    
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
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public String toString()
    {
        if (rep == null)
            rep = buildRep();
        return rep;
    }
    
    // for testing
    String buildFromSrc()
    {
        StringBuilder bd = new StringBuilder();
        for (List<SourceToken> line : srcFile)
        {
            for(SourceToken tk : line)
                bd.append(tk.image()).append(' ');
            bd.append('\n');
        }
        return bd.toString();
    }
    
    // ---- private helpers -----------
    
    private String buildRep()
    {
        StringBuilder ret = new StringBuilder();
        for (String line : lines)
        {
            ret.append(line);
            ret.append('\n');
        }
        return ret.toString();
    }
    
    private static final Set<String> keywords;
    static
    {
        keywords = new HashSet<>();
        keywords.addAll(
                    Arrays.asList(
                        "abstract", "assert",
                        "boolean", "break", "byte",
                        "case", "catch", "char", "const","continue",
                        "do", "double",
                        "else", "enum", "extends",
                        "final", "finally", "float", "for",
                        "goto",
                        "if", "implements", "import", "instanceof", "int", "interface",
                        "long",
                        "native", "new",
                        "package", "private", "protected", "public",
                        "return",
                        "short", "static", "strictfp", "super", "switch", "synchronized",
                        "this", "throw", "throws", "transient", "try",
                        "void", "volatile",
                        "while"));
    }
}
