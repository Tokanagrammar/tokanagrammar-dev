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

/**
 * 
 * @author Vy Thao Nguyen
 */
public interface Shuffler
{
    /**
     * Remove <pre>toRemove</pre> number of tokens from <pre>src</pre>
     * Any token has the same probability of being removed.
     * 
     * Note: Two tokens that *look* the same are still counted as two.
     * For instance, if src hypothetically looks like this:
     * 
     * <pre>
     * public int public void
     * private int main
     * </pre>
     * 
     * There are two 'public' tokens, but these still counts as two different tokens.
     * 
     * What makes up the 'id' for a token is its line and column positions, and
     * NOT what it looks like
     * 
     * @param src
     * @return 
     */
    public ShuffledSource shuffle(SourceFile src, int toRemove);
    
    /**
     * Similar to shuffle(SourceFile, int), but this places more weights
     * on the tokens included in <code>toks</code>, hence these are more likely
     * to be removed
     * 
     * @param src
     * @param toks
     * @return 
     */
    public ShuffledSource shuffle(SourceFile src, int toRemove, String...toks);
}
