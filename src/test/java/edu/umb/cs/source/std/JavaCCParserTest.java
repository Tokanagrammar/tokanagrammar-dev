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

import edu.umb.cs.parser.JavaParser;
import edu.umb.cs.parser.ParseException;
import edu.umb.cs.source.SourceFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vy Thao Nguyen
 */
public class JavaCCParserTest extends SourceTestBase
{
    private static final File TESTS_PATH = new File("src/test/resources/sources/javaccparse");
    
    @Override
    void doTest(File expted, File in) throws FileNotFoundException, IOException
    {
        FileInputStream fin = new FileInputStream(in);
        
        JavaParser parser = new JavaParser(fin);
        try
        {
            SourceFile inParsed = parser.parseJava();
            System.out.println(inParsed);   
        }
        catch (ParseException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    @Override
    File getPath()
    {
        return TESTS_PATH;
    }
}
