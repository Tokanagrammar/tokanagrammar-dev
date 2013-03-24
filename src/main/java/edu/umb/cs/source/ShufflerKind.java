

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
