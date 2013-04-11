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

package edu.umb.cs.api.service;

import edu.umb.cs.entity.Hint;
import edu.umb.cs.entity.Puzzle;
import edu.umb.cs.entity.User;
import edu.umb.cs.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
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
     * Call this when program starts
     * 
     * dbName = Tokanagrammar
     * partialURL = "$objectdb/db/"
     * 
     * @param String newName
     */
    public static void openConnection(String newName)
    {
        //dbName = "Tokanagrammar.odb";
        dbName = newName + ".odb";
        partialURL = "$objectdb/db/";
        emf = Persistence.createEntityManagerFactory(partialURL + dbName, properties);
        em = emf.createEntityManager();
    }

    /**
     * Start database connection with given settings
     * Call this when program starts.
     * 
     * @param newDbName
     * @param newPartialURL
     * @param user
     * @param password 
     */
    public static void openConnection(String newDbName, String newPartialURL, String user, String password)
    {
        dbName = newDbName;
        partialURL = newPartialURL;
        properties.put("javax.persistence.jdbc.user", user);
        properties.put("javax.persistence.jdbc.password", password);
        emf = Persistence.createEntityManagerFactory(partialURL + dbName, properties);
        em = emf.createEntityManager();
        
        // TODO replace all em.createQuery().exeucte... 
        // with prepare state.
        // ie., create those queries only once and execute them when needed
        // (This saves A LOT of time in parsing)
    }
    
    /**
     * Commit all everything and close connection
     * Call this upon exiting the program
     */
    public static void closeConnection()
    {   
        EntityTransaction t = em.getTransaction();
        if (t.isActive())
            t.commit();
        em.close();
        emf.close();
    }
    
    /**
     * Delete everything from db.
     */
    public static void deleteAll()
    {
        EntityTransaction t = em.getTransaction();
        boolean success  = false;
        try
        {
            t.begin();
            em.createQuery("DELETE FROM User u").executeUpdate();
            em.createQuery("DELETE FROM Puzzle p").executeUpdate();
            em.createQuery("DELETE FROM Game g").executeUpdate();
            success = true;
        }
        finally
        {
            if (success)
                t.commit();
            else
                t.rollback();
        }
    }

    /**
     * 
     * @return a list of all puzzles in the database
     */
    public static List<Puzzle> getAllPuzzles()
    {
        return em.createQuery("SELECT p FROM Puzzle p", Puzzle.class).getResultList();
    }
    
    //List of users
    public static List<User> getAllUsers()
    {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
    
    /**
     * Add a new puzzle to the database
     * 
     * @param filePath the path to the source file (relative to execution directory)
     * @param expResult expected result of the program
     * @param metaData meta data
     * @return true if the source file can be found and added correctly
     */
    public static boolean addPuzzle(String filePath, String expResult, String metaData, Hint... hints)
    {
        boolean success = false;
        EntityTransaction t = em.getTransaction();
        try
        {
            t.begin();
            Puzzle p = new Puzzle(filePath, expResult, metaData);
            for (Hint h : hints)
                p.addHint(h);
            em.persist(p);
            success = true;
        }
        catch (IOException | ParseException exc)
        {
            return false;
        }
        finally
        {
            if (success)
                t.commit();
            else
                t.rollback();
        }
        return true;
    }
    
    /**
     * Remove the puzzle with given name from db
     * 
     * @param filePath
     * @return true if there is such puzzle, false otherwise; 
     */
    public static boolean removePuzzle(String filePath)
    {
        int count = em.createQuery("DELETE FROM Puzzle p WHERE p.filePath = :filePath", Puzzle.class)
                     .setParameter("filePath", filePath)
                     .executeUpdate();
        return (count == 0 ? false : true);
    }
    
    /**
     * 
     * @deprecated
     */
    private static void persistPuzzle(Puzzle p)
    {
        em.persist(p);
        em.getTransaction().commit();
    }
    /**
     * 
     * @param username
     * @return true if the username has already existed
     */
    public static boolean usernameExists(String username)
    {
        List<User> exist = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                       .setParameter("username", username)
                       .getResultList();
        
        if (exist.size() > 0)
            return true;
        else 
            return false;
    }
    
    /**
     * Add a new user to the database
     * Will throw exception if username has already been used.
     * 
     * @param username 
     * @return the user just got created
     * @throws Exception if username is not available
     */
    public static User addUser(String username) throws Exception
    {
        if (usernameExists(username))
            throw new Exception("Username has already existed");
        
        User u = new User(username);
        em.persist(u);
        em.getTransaction().commit();
        return u;
    }
    
    public static void removeUser(User u)
    {
        
    }
    
    /**
     * 
     * @deprecated 
     */
    public static void persistUser(User u)
    {
        em.persist(u);
        em.getTransaction().commit();
    }
    
    public static void dropDatabase(String name)
    {
        
    }
}
