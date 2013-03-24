package edu.umb.cs.source.std;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vy Thao Nguyen
 */
public abstract class SourceTestBase 
{
    abstract void doTest(File expted, File in) throws FileNotFoundException, IOException;
    abstract File getPath();
    
    @Test
    public void test() throws FileNotFoundException, IOException
    {
        // collect all test cases in the test directories
        // (ie., all input and expected files)
        File[] files = getPath().listFiles();
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
            doTest(files[n], files[n + 1]);
        }
    }
}
