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

import edu.umb.cs.source.MetaData;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 *
 * @author Vy Thao Nguyen
 */
public class ParseMetaTest extends SourceTestBase
{
    private static final File PATH = new File ("src/test/resources/sources/metadata");
    
    @Override
    void doTest(File expted, File in) throws FileNotFoundException, IOException
    {
        System.out.println("Running test on: " + expted); 
        MetaData parsedMeta = MetaData.parseMetaData(in.getAbsolutePath());
        
        Scanner scanner = new Scanner(expted);
        assertEquals("Category: ", scanner.nextLine().trim(),
                                   parsedMeta.getCategoryName());
        assertEquals("Expected output: ", scanner.nextLine().trim(),
                                          parsedMeta.getExpectedOutput());
        
        if (scanner.hasNextLine())
        {
            String hints[] = scanner.nextLine().trim().split(",");
            List<String> actualHints = parsedMeta.getHints();
            int size;
            assertEquals("Number of hints: ",
                         hints == null ? 0 : hints.length,
                         size = actualHints == null ? 0 : actualHints.size());
            
            // order does matter
            for (int n = 0; n < size; ++n)
                assertEquals("Hint # " + n + ": ",
                             hints[n].trim(),
                             actualHints.get(n).trim());
        }
        else
            assertTrue("Expect no hints: ", parsedMeta.getHints() == null);
    }

    @Override
    File getPath()
    {
        return PATH;
    }

}
