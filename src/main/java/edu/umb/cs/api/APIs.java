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

import edu.umb.cs.entity.User;
import edu.umb.cs.api.service.DatabaseService;
import edu.umb.cs.entity.Hint;
import edu.umb.cs.entity.Puzzle;
import edu.umb.cs.parser.InternalException;
import edu.umb.cs.source.ShuffledSource;
import edu.umb.cs.source.Shuffler;
import edu.umb.cs.source.ShufflerKind;
import edu.umb.cs.source.SourceFile;
import java.util.List;

/**
 * APIs for interacting with the backend
 * @author Vy Thao Nguyen
 */
public class APIs
{   
    private static final String PRODUCTION_DB = "tokanagrammar";
    private static boolean started = false;
    private static boolean stopped = false;
    
    private static final String TEST_DB = "test";
    private static boolean testStarted = false;
    private static boolean testStopped = false;

    public static void start()
    {
        if (started)
            return;
        started = true;
        DatabaseService.openConnection(PRODUCTION_DB);
    }
    
    public static void stop()
    {
        if (stopped)
            return;
        stopped = true;
        DatabaseService.closeConnection();
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

    public static void removeAllRecords()
    {
        checkStarted();
        DatabaseService.deleteAll();
    }


    public static void addPuzzle(String filePath, String expResult, String metaD, Hint...hints)
    {
	DatabaseService.addPuzzle(filePath, expResult, metaD, hints);
    }

    public static User newUser(String username)
    {
        checkStarted();
        
        // TODO: implement the warning mechnism
        // ie., generate a unique username to use
        // and issue a warning if the given username already exists
        try
        {
            User user = DatabaseService.addUser(username);
            DatabaseService.persistUser(user);
            return user;
        }
        catch (Exception ex)
        {
            
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
    
    /**
     * 
     * @return a list of available puzzles
     */
    public static List<Puzzle> getPuzzles()
    {
        checkStarted();
        return DatabaseService.getAllPuzzles();
    }
    
    private static void checkStarted()
    {
        if (!started && !testStarted)
            throw new InternalException("Service must be started before being used");
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
    public static ShuffledSource shuffle (Puzzle puzzle)
    {
        return shuffle(puzzle, DEFAULT_PERCENT);
    }

    /**
     * 
     * @param puzzle
     * @param percentToRemove
     * @return 
     */
    public static ShuffledSource shuffle (Puzzle puzzle, int percentToRemove)
    {
        SourceFile src = puzzle.getSourceFile();
        return getDefaultShuffler().getShuffler().shuffle(src,
                                                          percentToRemove / 100 * src.tokenCount());
    }
    
    private static final int DEFAULT_PERCENT = 5;
}
