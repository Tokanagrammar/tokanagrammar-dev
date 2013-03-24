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

package edu.umb.cs.api;

import edu.umb.cs.entity.Game;
import edu.umb.cs.entity.Puzzle;
import edu.umb.cs.entity.User;

/**
 * Whenever the application (window) is opened, that is a session.
 * Right now, we only have/support one window being opened at a time.
 * But presumably, we can support multiple sessions.
 * @author Vy Thao Nguyen
 */
public final class Session 
{
    private final User user;

    // package private, must be created through APIs.newSession
    Session (User user)
    {
        this.user = user;
    }
    
    public User getCurrentUser()
    {
        return user;
    }

    public Game newGame(Puzzle puzzle)
    {
        Game game = new Game (user, puzzle);
        user.addGame(game);
        puzzle.addGame(game);   
        return game;
    }
}
