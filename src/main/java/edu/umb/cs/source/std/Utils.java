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
import edu.umb.cs.source.SourceToken;
import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vy Thao Nguyen
 */
class Utils 
{
    private static File tempDir;
    static
    {
        tempDir = new File("tmp");
        tempDir.mkdirs();
    }

    static String compile(List<List<SourceToken>> src, String outer, int retcode[])
    {
        try
        {
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
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
                retcode[0] = -1;
                return ex.getMessage();
            }

            if ((retcode[0] = p.exitValue()) != 0)
            {
                // cannot compile!
                return CharStreams.toString(new InputStreamReader(p.getErrorStream()));
            }
            
            // execute the source
            p = Runtime.getRuntime().exec("java " + outer, null, tempDir);
            try
            {
                p.waitFor();
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
                retcode[0] = -1;
                return ex.getMessage();
            }
            
            if ((retcode[0] = p.exitValue()) != 0)
            {
                // something wrong!
                return CharStreams.toString(new InputStreamReader(p.getErrorStream()));
            }

            // otherwise, return the output
            String ret = CharStreams.toString(new InputStreamReader(p.getInputStream()));

            // TODO: clean up the temp files ?
            return ret;
        }
        catch (IOException ex)
        {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            retcode[0] = -3;
            return ex.getMessage();
        }
    }
}
