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

import edu.umb.cs.entity.Puzzle;
import edu.umb.cs.entity.User;
import edu.umb.cs.service.DatabaseService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * APIs for interacting with the backend
 * @author Vy Thao Nguyen
 */
public class APIs
{   
    private static boolean started = false;
    private static boolean stopped = false;
    public static void start()
    {
        if (started)
            return;
        DatabaseService.openConnection();
    }
    
    public static void stop()
    {
        if (stopped)
            return;
        DatabaseService.closeConnection();
    }
    
    public static User newUser(String username)
    {
        // TODO: implement the warning mechnism
        // ie., generate a unique username to use
        // and issue a warning if the given username already exists
        try
        {
            return DatabaseService.addUser(username);
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
        return DatabaseService.getAllUsers();
    }
    
    /**
     * 
     * @return a list of available puzzles
     */
    public static List<Puzzle> getPuzzles()
    {
        return DatabaseService.getAllPuzzles();
    }
    
    
}
