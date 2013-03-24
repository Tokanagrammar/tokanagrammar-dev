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


package edu.umb.cs.source;

import edu.umb.cs.source.std.SimpleShuffler;

/**
 * Each member represents a different algorithm of shuffling
 * @author Vy Thao Nguyen
 */
public enum ShufflerKind 
{
    SIMPLE_SHUFFLER
    {
        @Override
        public Shuffler getShuffler()
        {
            return SimpleShuffler.INSTANCE;
        }
        
        @Override
        public String toString()
        {
            return "This implements a simple shuffling method, in which, some"
                    + "number of tokens are removed randomly. There are weights"
                    + "on any token";
        }
    }
    ;

    // TODO: add more here
    // k-group
    // ...
    public abstract Shuffler getShuffler();
    public abstract String toString();
}
