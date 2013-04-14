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

import com.google.common.io.CharStreams;
import edu.umb.cs.parser.BracingStyle;
import edu.umb.cs.parser.ParseException;
import edu.umb.cs.source.Language;
import edu.umb.cs.source.SourceFiles;
import java.io.*;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Vy Thao Nguyen
 */
public class CompileExecuteTest extends SourceTestBase
{
    private static final File PATH = new File ("src/test/resources/sources/compile");
    
    @Override
    void doTest(File expted, File in) throws FileNotFoundException, IOException
    {
        try
        {
            String exp = CharStreams.toString(new InputStreamReader(new FileInputStream(expted)));
            String actual = SourceFiles.getSourceFile(in,
                                                      Language.JAVA,
                                                      BracingStyle.ALLMAN)
                                          .compileAndExecute();
            assertEquals(exp, actual);
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (ParseException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    File getPath()
    {
        return PATH;
    }

}
