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

package edu.umb.cs.entity;

import java.util.Calendar;
import java.util.TimeZone;
import javax.persistence.*;

/**
 * @author              Vy Thuy Nguyen
 * @version             2.0-snapshot Mar 23, 2013
 * Last modified:       
 */
@Entity
public class Game 
{
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
    
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private User user;
    
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Puzzle puzzle;
    
    private long point;
    
    /**
     * Number of seconds since epoch
     */
    private long timestamp;
    
    public Game()
    {
        
    }
    
    public Game(User u, Puzzle p)
    {
        user = u;
        puzzle = p;
        point = 0;
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        calendar.set(2011, Calendar.OCTOBER, 1);
        timestamp = calendar.getTimeInMillis() / 1000L;
        
    }
    
    public User getUser()
    {
        return user;
    }
    
    public Puzzle getPuzzle()
    {
        return puzzle;
    }
    
    public int getPoint()
    {
        return point;
    }
    
    public void setPoint(int p)
    {
        point = p;
    }
    
    public int hashCode()
    {
        return id;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null || !(obj instanceof Game))
        {
            return false;
        }
        
        Game other = (Game) obj;
        if (this.id != other.id)
        {
            return false;
        }
        return true;
    }
}
