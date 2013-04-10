package edu.umb.cs.source.std;

import edu.umb.cs.parser.InternalException;
import edu.umb.cs.source.SourceToken;
import edu.umb.cs.source.SourceTokenKind;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Vy Thao Nguyen
 */
public class SeparatorToken  implements SourceToken
{
    private static final Map<String, SourceToken> seps = computeMap();
    private static Map<String, SourceToken> computeMap()
    {
        Map<String, SourceToken> ret = new HashMap<>();
        
        for (String st : Arrays.asList("[", "]", "{", "}", "(", ")", ",", ";", "."))
            ret.put(st, new SeparatorToken(st));
        
        return ret;
    }
    
    public static SourceToken getSep(String img)
    {
        SourceToken tk = seps.get(img);
        if (tk == null)
            throw new InternalException("no such seperator");
        return tk;
    }
    private String img;
    
    SeparatorToken (String img)
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
        return SourceTokenKind.SEPARATOR;
    }

}
