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

import edu.umb.cs.api.service.DatabaseService;
import edu.umb.cs.parser.BracingStyle;
import edu.umb.cs.parser.ParseException;
import edu.umb.cs.source.Language;
import edu.umb.cs.source.SourceFile;
import edu.umb.cs.source.SourceFiles;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    @Transient
    private SourceFile srcFile;
    
    @Transient
    private List<Hint> cachedHints = null;
    
    @Transient
    private final Language langType = Language.JAVA;  //TODO: Only support JAVA for now
                                                      // This should be settable later
                                                      // when we do support other languages
    
    /**
     * Relative path to file 
     */
    private String filePath;
    
    private String prettyName;
    
    private String expectedResult;
    
    private int lang = langType.ordinal();
    
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, 
                          fetch = FetchType.EAGER, mappedBy = "puzzle")
    private Set<Game> games;
 
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, 
                          fetch = FetchType.EAGER, mappedBy = "puzzle")
    private Set<Hint> hints;
 
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Category category;
    
    public Puzzle()
    {
        
    }
    
    public Puzzle (String path, String expRes, String catName) throws IOException, ParseException
    {
        File file = new File(path);
        if (!file.exists())
            throw new IOException("File Not Found");
        
        prettyName = file.getName();
        filePath = path;
        expectedResult = expRes;
        games = new HashSet<>();
        hints = new HashSet<>();
        category = DatabaseService.findOrCreateCategory(catName);
        category.addPuzzle(this);
    }
    
    public SourceFile getSourceFile(BracingStyle style) throws ParseException, FileNotFoundException
    {
        return SourceFiles.getSourceFile(new File(filePath), langType, style);
    }

    public void addGame(Game g)
    {
        games.add(g);
    }
    
    public Set<Game> getGames()
    {
        return Collections.unmodifiableSet(games);
    }
    
    /**
     * After done adding all the hints call DatabaseService.persistPuzzle(<the puzzle>);
     * @param h 
     */
    public void addHint(Hint h)
    {
        hints.add(h);
    }
    
    /**
     * Same as add
     * @param h 
     */
    public void removeHint(Hint h)
    {
        hints.remove(h);
    }
    
    public List<Hint> getHints()
    {
        if (cachedHints == null)
            cachedHints = new ArrayList<>(hints);
        return cachedHints;
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
        return filePath.equals(other.filePath);
    }
}
