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

import edu.umb.cs.parser.ParseException;
import edu.umb.cs.source.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 *
 * @author Vy Thao Nguyen
 */
public class SimpleShufflerTest extends SourceTestBase
{
    private static final File TESTS_PATH = new File("src/test/resources/sources/shuffler");
    
    @Override
    void doTest(File expted, File in) throws FileNotFoundException, IOException
    {
        SourceFile srcFile;
        try
        {
            srcFile = SourceFiles.getSourceFile(in, Language.JAVA);
        }
        catch (ParseException ex)
        {
            fail("parse error");
            return;
        }
        // remove a few tokens
        int toRemove = 6;
        ShuffledSource shuffled = SimpleShuffler.INSTANCE.shuffle(srcFile, toRemove);
        List<SourceToken> removed = shuffled.getRemovedTokens();

        assertEquals(toRemove, removed.size());
        // TODO: for now, just print out the removed tokens and the source
        System.out.println("Testing file: " + in.getAbsolutePath());
        System.out.println("removed tokens: " + removed);
        
        System.out.println("new source: \n");
        List<List<SourceToken>> newSrc = SimpleShuffler.buildList(shuffled.getShuffledSource());
        for (List<SourceToken> line : newSrc)
        {
            for (SourceToken tk : line)
                System.out.printf("%s", tk.image());
            System.out.println();
        }
    }

    @Override
    File getPath()
    {
        return TESTS_PATH;
    }
}
