package edu.umb.cs.source;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
