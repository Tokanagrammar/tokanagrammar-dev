package edu.umb.cs.source.std;

import edu.umb.cs.source.SourceToken;
import edu.umb.cs.source.SourceTokenKind;

/**
 *
 * @author Vy Thao Nguyen
 */
public class CharLiteralToken implements SourceToken
{
    private final String img;
    
    public CharLiteralToken(String img)
    {
        this.img = img;
    }

    @Override
    public String image()
    {
        return img;
    }

    @Override
    public SourceTokenKind getKind()
    {
        return SourceTokenKind.CHAR_LITEARL;
    }

}
