
package edu.umb.cs.source.std;

import edu.umb.cs.source.ShuffledSource;
import edu.umb.cs.source.SourceFile;
import edu.umb.cs.source.Token;
import java.util.List;

/**
 *
 * @author Vy Thao Nguyen
 */
public class ShuffledSourceImpl implements ShuffledSource
{
    private final SourceFile original;
    private final SourceFile shuffled;
    private final List<Token> removed;
    
    public ShuffledSourceImpl(SourceFile original, SourceFile shuffled, List<Token> removed)
    {
        this.original = original;
        this.shuffled = shuffled;
        this.removed = removed;
    }

    @Override
    public SourceFile getOrinalSource()
    {
        return original;
    }

    @Override
    public SourceFile getShuffledSource()
    {
        return shuffled;
    }

    @Override
    public List<Token> getRemovedTokens()
    {
        return removed;
    }
}
