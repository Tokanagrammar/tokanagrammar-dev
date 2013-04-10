package edu.umb.cs.source.std;

import edu.umb.cs.source.SourceToken;
import edu.umb.cs.source.SourceTokenKind;

/**
 *
 * @author Vy Thao Nguyen
 */
public class SourceTokenBase implements SourceToken
{
    private final String img;
    private final SourceTokenKind kind;
    private final String rep;
    SourceTokenBase(String img, SourceTokenKind kind)
    {
        this.img = img;
        this.kind = kind;
        rep = "<" + kind.name() + ": " + img + ">";
    }

    @Override
    public String image()
    {
        return img;
    }

    @Override
    public SourceTokenKind getKind()
    {
        return kind;
    }

    // Object interface
    @Override
    public String toString()
    {
        return rep;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (o != null && o instanceof SourceToken)
            return ((SourceToken)o).image().equals(img);
        else
            return false;
    }

    @Override
    public int hashCode()
    {
        return img.hashCode();
    }
}
