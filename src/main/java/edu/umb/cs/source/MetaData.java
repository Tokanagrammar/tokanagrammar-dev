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
package edu.umb.cs.source;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Vy Thao Nguyen
 */
public class MetaData 
{
    public static char META_MARKER = '#';

    /**
     * The file MUST have this format:
     * TODO: for now, assume output can be on one line
     * 
     * # <category_name>
     * # <expected_output>
     * # (<hint> )*
     * 
     * @param path to the source file
     * @return a MetaData object
     */
    public static MetaData parseMetaData(String path) throws FileNotFoundException
    {
        Scanner input = new Scanner(new File(path));
        String catName, exp, hints = null;
        
        try
        {
            if (!isMeta(catName = input.nextLine().trim())
                    || !isMeta(exp = input.nextLine().trim()))
                return null;
            if (input.hasNextLine() && !isMeta(hints = input.nextLine().trim()))
                hints = null;
        }
        catch(NoSuchElementException ex)
        {
            return null;
        }

        // remove the hash symbols
        catName = catName.substring(1).trim().toUpperCase();
        exp = exp.substring(1).trim();
        
        List<String> hintsList = null;
        if (hints != null)
            hintsList = Arrays.asList(hints.substring(1).split(","));

        return new MetaData(path, hintsList, exp, catName);
    }

    private static boolean isMeta(String line)
    {
        return !line.isEmpty() && line.charAt(0) == META_MARKER;
    }

    private final String path;
    private final List<String> hints;
    private final String expectedOutput;
    private final String categoryName;

    protected MetaData(String path,
                    List<String> hints,
                    String expectedOutput,
                    String categoryName)
    {
        this.path = path;
        this.hints = hints;
        this.expectedOutput = expectedOutput;
        this.categoryName = categoryName;
    }
    
    public String getPath()
    {
        return path;
    }
    
    public List<String> getHints()
    {
        return hints;
    }
    
    public String getExpectedOutput()
    {
        return expectedOutput;
    }
    
    public String getCategoryName()
    {
        return categoryName;
    }
}
