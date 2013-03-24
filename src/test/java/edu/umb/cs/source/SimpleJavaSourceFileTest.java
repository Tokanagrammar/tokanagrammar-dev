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
package test.java.edu.umb.cs.source;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import static org.junit.Assert.assertEquals;
import main.java.edu.umb.cs.source.std.SimpleJavaSourceFile;
import org.junit.Test;

/**
 * @author Vy Thao Nguyen
 */
public class SimpleJavaSourceFileTest
{
    private static final File TESTS_PATH = new File("src/test/resources/sources");

    @Test
    public void doTest() throws FileNotFoundException, IOException
    {
        // collect all test cases in the test directories
        // (ie., all input and expected files)
        File[] files = TESTS_PATH.listFiles();
        Arrays.sort(files,
                    new Comparator<File>()
                    {
                        @Override
                        public int compare(File t, File t1)
                        {
                            return t.getName().compareTo(t1.getName());
                        }
                    });

        for (int n = 0; n < files.length; n += 2)
        {
            // input file ends with '.java'
            // expected file ends with '.expected'
            // Hence, input files always come AFTER its expected file
            SimpleJavaSourceFile srcFile 
                = new SimpleJavaSourceFile(files[n + 1].getAbsolutePath());

            // build expected
            Scanner expected = new Scanner(files[n]);
            StringBuilder expectedStr = new StringBuilder();
            while (expected.hasNextLine())
                expectedStr.append(expected.nextLine()).append('\n');                    
  
            // check scanned file against the expected
            assertEquals(expectedStr.toString(), srcFile.toString());
        }
    }
}
