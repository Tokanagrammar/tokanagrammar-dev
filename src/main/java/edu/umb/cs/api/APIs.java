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

package edu.umb.cs.api;

import edu.umb.cs.api.service.DatabaseService;
import edu.umb.cs.entity.Category;
import edu.umb.cs.entity.Puzzle;
import edu.umb.cs.entity.User;
import edu.umb.cs.parser.InternalException;
import edu.umb.cs.source.*;
import edu.umb.cs.source.std.Utils;
import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * APIs for interacting with the backend
 * @author Vy Thao Nguyen
 */
public class APIs
{   
    private static final String LOG = "config/log.txt";
    private static final String PRODUCTION_DB = "tokanagrammar";
    private static boolean started = false;
    private static boolean stopped = false;
    
    private static final String TEST_DB = "test";
    private static boolean testStarted = false;
    private static boolean testStopped = false;

    private static final String VERSION = findVersion();
    private static final  PrintStream stdout = System.out;
    private static final PrintStream logStream = getLogStream();
    
    private static PrintStream getLogStream()
    {
        try
        {
            return new PrintStream(new FileOutputStream(LOG, true), true);
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(APIs.class.getName()).log(Level.SEVERE, null, ex);
            return stdout;
        }
    }

    private static String findVersion()
    {
        String ret = "UNKNOWN";
        try
        {
            FileInputStream fis = new FileInputStream(new File("config/version.txt"));
            Scanner sc = new Scanner(fis);
            ret = sc.next();
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }

        return ret;
    }

    public static void start()
    {
        if (started)
            return;
        started = true;
        // redirect stdout to log file
        System.setOut(logStream);
        
        System.out.println("\n=================================");
        System.out.println("Application started on: " + new Date());
        DatabaseService.openConnection(PRODUCTION_DB);
        
        // TEMP remoe this when we have the 'real' code
        // for populating the db
        // (For now, just wipe out everything and insert data 
       removeAllRecords();

       File dir = new File("puzzles");
       assert dir.isDirectory() : "directory puzzles not exist";
       
       for (File f : dir.listFiles())
       {
           MetaData meta = null;
            try
            {
                meta = MetaData.parseMetaData(f.getAbsolutePath());
            }
            catch (FileNotFoundException ex)
            {
                ex.printStackTrace();
            }
            
            if (meta != null)
                DatabaseService.addPuzzle(f.getAbsolutePath(),
                                          meta.getExpectedOutput(),
                                          meta.getCategoryName(), 
                                          meta.getHints());
       }
    }
    
    public static void stop()
    {
        if (stopped)
            return;
        stopped = true;
        System.out.println("Application stopped: " + new Date());
        System.setOut(stdout);
        DatabaseService.closeConnection();
    }

    public static String getVersion()
    {
        return VERSION;
    }

    public static List<Category> getCategories()
    {
        return DatabaseService.getAllCategories();
    }

    public static void removeAllRecords()
    {
        checkStarted();
        DatabaseService.deleteAll();
    }


    public static void addPuzzle(String filePath, String expResult, String metaD, String catName, String...hints)
    {
	DatabaseService.addPuzzle(filePath, expResult, catName, Arrays.asList(hints));
    }

    public static User newUser(String username)
    {
        checkStarted();
        try
        {
            User user = DatabaseService.addUser(username);
            return user;
        }
        catch (Exception ex)
        {
            // TODO: implement the warning mechnism
            // ie., generate a unique username to use
            // and issue a warning if the given username already exists
            return null;
        }
    }
    /**
     * start a new session with a new generated username
     * @return 
     */
    public static Session newSession()
    {
        checkStarted();
        // TODO:
        // create new user
        throw new UnsupportedOperationException("not supported yet");
    }
    
    /**
     * start a new session with an existing user
     * @param user
     * @return a new session
     */
    public static Session newSession(User user)
    {
        return new Session(user);
    }
    
    /**
     * 
     * @return a list of existing users
     */
    public static List<User> getUsers()
    {
        checkStarted();
        return DatabaseService.getAllUsers();
    }
    
    // temp
    public static Output compile(String src, String name)
    {
        return Utils.compile(src, name);
    }

    /**
     * 
     * @return a list of available puzzles
     */
    public static List<Puzzle> getPuzzles()
    {
        checkStarted();
        return DatabaseService.getAllPuzzles();
    }

    public static ShufflerKind getDefaultShuffler()
    {
        return ShufflerKind.SIMPLE_SHUFFLER;
    }
    
    /**
     * Remove some <pre>DEFAULT_PERCENT</pre> of tokens.
     * @param puzzle
     * @return 
     */
    public static ShuffledSource shuffle (SourceFile src)
    {
        return shuffle(src, DEFAULT_PERCENT);
    }

    /**
     * 
     * @param src
     * @param percentToRemove
     * @return 
     */
    public static ShuffledSource shuffle (SourceFile src, int percentToRemove)
    {
        if (percentToRemove > MAX_TO_REMOVE_PERCENT)
            percentToRemove = MAX_TO_REMOVE_PERCENT;
        
        int total = src.tokenCount();
        return getDefaultShuffler().getShuffler().shuffle(src,
                                                          (int)(percentToRemove / 100.0 * total));
    }
    
     // for testing
    
    static void startTest()
    {
        if (testStarted)
            return;
        
        testStarted = true;
        DatabaseService.openConnection(TEST_DB);
        removeAllRecords();
    }

    static void stopTest()
    {
        if (testStopped)
            return;
        
        testStopped = true;
        DatabaseService.closeConnection();
    }

    // private helpres 
        
    private static void checkStarted()
    {
        if (!started && !testStarted)
            throw new InternalException("Service must be started before being used");
    }
    
    private static final int MAX_TO_REMOVE_PERCENT = 80;
    private static final int DEFAULT_PERCENT = 10;
}

