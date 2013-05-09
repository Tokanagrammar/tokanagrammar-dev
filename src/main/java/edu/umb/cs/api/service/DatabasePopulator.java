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

/**
 * @author              Vy Thuy Nguyen
 * @version             1.0 May 8, 2013
 * Last modified:       
 */
public class DatabasePopulator
{   
    private static final String LOG = "config/log_db_populator.txt";
    private static final String PRODUCTION_DB = "tokanagrammar";
    private static boolean started = false;
    private static boolean stopped = false;

    public static void main (String[] args)
    {
        doPopulate();
    }
    
    /**
     * Start a database connection
     * Call this before using any of the method in this class
     */
    private static void start()
    {
        if (started)
        {
            return;
        }
        else
        {
            DatabaseService.openConnection(PRODUCTION_DB);
            started = true;
        }
    }
    
    private static void stop()
    {
        if (stopped)
        {
            return;
        }
        else
        {
            DatabaseService.closeConnection();
            stopped = true;
        }
    }
    
    public static void doPopulate()
    {
        start();
    }
}
