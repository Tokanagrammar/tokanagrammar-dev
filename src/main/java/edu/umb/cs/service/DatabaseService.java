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

package edu.umb.cs.service;

import edu.umb.cs.entity.Puzzle;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author              Vy Thuy Nguyen
 * @version             1.0 Mar 23, 2013
 * Last modified:       
 */
public class DatabaseService 
{ 
    private static Map<String, String> properties = new HashMap<String, String>();
    private static String dbName = "Tokanagrammar.odb";
    private static String partialURL = "$objectdb/db/";
    private static EntityManagerFactory emf;
    private static EntityManager em;
    
    /**
     * Start database connection with default settings
     * dbName = Tokanagrammar
     * partialURL = "$objectdb/db/"
     */
    public void openConnection()
    {
        dbName = "Tokanagrammar.odb";
        partialURL = "$objectdb/db/";
        emf = Persistence.createEntityManagerFactory(partialURL + dbName, properties);
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }
    
    public static void openConnection(String newDbName, String newPartialURL, String user, String password)
    {
        dbName = newDbName;
        partialURL = newPartialURL;
        properties.put("javax.persistence.jdbc.user", user);
        properties.put("javax.persistence.jdbc.password", password);
        emf = Persistence.createEntityManagerFactory(partialURL + dbName, properties);
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }
    
    public void closeConnection()
    {
        em.getTransaction().commit();
        em.close();
        emf.close();;
    }

    
    //List of avail. puzzles
    
    //List of users
    
    //Given user_id, return points (all timem)
    
    //Given user_id, return games
    
    //Given user_id and puzzle_id, return points of prev games with this puzzle
    
    //====SETTERS===
    
    /**
     * 
     * @param filePath the path to the source file (relative to execution directory)
     * @param expResult expected result of the program
     * @param metaData meta data
     * @return true if the source file can be found and added correctly
     */
    public boolean addPuzzle(String filePath, String expResult, String metaData)
    {
        try
        {
            Puzzle p = new Puzzle(filePath, expResult, metaData);
            
        }
        catch (IOException exc)
        {
            return false;
        }
        
        return true;
    }
    
    
}
