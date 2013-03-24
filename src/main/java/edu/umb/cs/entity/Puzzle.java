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

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

/**
 * @author              Vy Thuy Nguyen
 * @version             2.0-snapshot Mar 23, 2013
 * Last modified:       
 */
@Entity
public class Puzzle implements Serializable 
{
    /**
     * The id of the puzzle
     */
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;
    
    /**
     * Relative path to file 
     */
    private String filePath;
    
    private String prettyName;
    
    private String expectedResult;
    
    private String metaData;
    
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.PERSIST}, 
                          fetch = FetchType.EAGER, mappedBy = "puzzle")
    private HashSet<Game> games;
 
    public Puzzle()
    {
        
    }
    
    public Puzzle (String path, String expRes, String mdata) throws IOException
    {
        File file = new File(path);
        if (!file.exists())
            throw new IOException("File Not Found");
        
        prettyName = file.getName();
        filePath = path;
        expectedResult = expRes;
        metaData = mdata;
        games = new HashSet<Game>();
    }
    
    public void addGame(Game g)
    {
        games.add(g);
    }
    
    public Set<Game> getGames()
    {
        return Collections.unmodifiableSet(games);
    }
    
    @Override
    public String toString()
    {
        return prettyName;
    }
    
    @Override
    public int hashCode()
    {
        return filePath.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null || !(obj instanceof Puzzle))
        {
            return false;
        }
        
        Puzzle other = (Puzzle) obj;
        if (this.filePath.equals(other.filePath))
        {
            return true;
        }
        
        return false;
    }
}
