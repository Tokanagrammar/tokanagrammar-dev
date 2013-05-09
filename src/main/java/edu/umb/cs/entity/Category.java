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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * @author              Vy Thuy Nguyen
 * Last modified:       
 */
@Entity
public class Category 
{
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
    
    private String name;
    
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
               fetch = FetchType.EAGER, mappedBy = "category")
    private Set<Puzzle> puzzles;
    
    public Category()
    {
        
    }
    
    public Category(String name)
    {
        this.name = name.toUpperCase();
        this.puzzles = new HashSet<>();
    }
    
    public String getName()
    {
        return this.name;
    }

    
    public void addPuzzle(Puzzle p)
    {
        this.puzzles.add(p);
    }
    
    public int puzzlesCount()
    {
        return puzzles.size();
    }

    public Set<Puzzle> getPuzzles()
    {
        return Collections.unmodifiableSet(puzzles);
    }
    
    public int hashCode()
    {
        return name.hashCode();
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (obj == null || !(obj instanceof Category))
        {
            return false;
        }
        
        Category catOther = (Category) obj;
        return name.equals(catOther.name);
    }
    
    public String toString()
    {
        return getName();
    }
}
