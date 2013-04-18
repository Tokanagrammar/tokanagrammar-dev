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
import edu.umb.cs.source.Output;
import edu.umb.cs.source.SourceToken;
import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vy Thao Nguyen
 */
public class Utils 
{
    private static File tempDir;
    static
    {
        tempDir = new File("tmp");
        tempDir.mkdirs();
    }

    public static Output compile(String src, String outer)
    {
        // remove the package (to avoid having to create dirs)
        if (src.startsWith("package"))
        {
            int n = 0;
            for (; n < src.length(); ++n)
            {
                if (src.charAt(n) == ';')
                {
                    src = src.substring(n+1);
                    break;
                }
            }
        }
        try
        {
            int retcode = 0;
            String fn = outer + ".java";

            // write the source to file
            FileWriter fstream = new FileWriter(tempDir.getAbsolutePath() + "/" + fn);
            BufferedWriter outFile = new BufferedWriter(fstream);
            outFile.write(src);
            //Close the output stream
            outFile.close();

            // compile the source
            Process p = Runtime.getRuntime().exec("javac " + fn, null, tempDir);            
            try
            {
                p.waitFor();
                retcode = p.exitValue();
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
                return new Output(ex.getMessage(), true);
            }

            if (retcode != 0)
            {
                // cannot compile!
                return new Output(CharStreams.toString(new InputStreamReader(p.getErrorStream())),
                                  true);
            }
            
            // execute the source
            p = Runtime.getRuntime().exec("java " + outer, null, tempDir);
            try
            {
                p.waitFor();
                retcode = p.exitValue();
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
                return new Output(ex.getMessage(), true);
            }
            
            if (retcode != 0)
            {
                // something wrong!
                return new Output(CharStreams.toString(new InputStreamReader(p.getErrorStream())),
                                  true);
            }

            // otherwise, return the output
            String ret = CharStreams.toString(new InputStreamReader(p.getInputStream()));

            // TODO: clean up the temp files ?
            return new Output(ret, false);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            return new Output(ex.getMessage(), true);
        }
    }
    
    static Output compile(List<List<SourceToken>> src, String outer)
    {
        try
        {
            int retcode = 0;
            String fn = outer + ".java";

            // write the source to file
            FileWriter fstream = new FileWriter(tempDir.getAbsolutePath() + "/" + fn);
            BufferedWriter outFile = new BufferedWriter(fstream);

            for (List<SourceToken> line : src)
                for (SourceToken tk : line)
                    outFile.write(tk.image());            
            //Close the output stream
            outFile.close();

            // compile the source
            Process p = Runtime.getRuntime().exec("javac " + fn, null, tempDir);            
            try
            {
                p.waitFor();
                retcode = p.exitValue();
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
                return new Output(ex.getMessage(), true);
            }

            if (retcode != 0)
            {
                // cannot compile!
                return new Output(CharStreams.toString(new InputStreamReader(p.getErrorStream())),
                                  true);
            }
            
            // execute the source
            p = Runtime.getRuntime().exec("java " + outer, null, tempDir);
            try
            {
                p.waitFor();
                retcode = p.exitValue();
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
                return new Output(ex.getMessage(), true);
            }
            
            if (retcode != 0)
            {
                // something wrong!
                return new Output(CharStreams.toString(new InputStreamReader(p.getErrorStream())),
                                  true);
            }

            // otherwise, return the output
            String ret = CharStreams.toString(new InputStreamReader(p.getInputStream()));

            // TODO: clean up the temp files ?
            return new Output(ret, false);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            return new Output(ex.getMessage(), true);
        }
    }
}
