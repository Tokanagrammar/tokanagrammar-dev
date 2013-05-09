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

import java.io.Serializable;
import javax.persistence.*;

/**
 * @author              Vy Thuy Nguyen
 * @version             2.0-snapshot Mar 23, 2013
 * Last modified:       
 */
@Entity
public class Hint implements Serializable 
{
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
    
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Puzzle puzzle;
    
    private String text;

    public Hint()
    {
    }
    
    public Hint(String text)
    {
        this.text = text;
    }
    
    public Hint(Puzzle puzzle, String text)
    {
        this.puzzle = puzzle;
        this.text = text;
    }
    
    public void setPuzzle(Puzzle p)
    {
        puzzle = p;
    }

    public String getHintContent()
    {
        return text;
    }

    public Puzzle getPuzzle()
    {
        return puzzle;
    }
    
    @Override
    public int hashCode()
    {
        return text.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null || !(obj instanceof Hint))
        {
            return false;
        }
        
        final Hint other = (Hint) obj;
        return text.equals(other.text);
    }
}
