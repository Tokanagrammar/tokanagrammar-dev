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

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.*;


/**
 * @author              Vy Thuy Nguyen
 * @version             2.0-snapshot Mar 23, 2013
 * Last modified:       
 */
@Entity
public class User implements Serializable 
{
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;
    
    private String name;
    
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.PERSIST}, 
                          fetch = FetchType.EAGER, mappedBy = "user")
    private ArrayList<Game> games;
    
    
}
