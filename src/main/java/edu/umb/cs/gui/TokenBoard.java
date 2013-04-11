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

package edu.umb.cs.gui;

import edu.umb.cs.source.SourceFile;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TokenBoard {
	
	private static Pane pane;
	
        private static VBox vBox = new VBox();
        static
        {
            vBox.setPadding(new Insets(5,5,5,10));
        }

	private static final TokenBoard tokenBoard = new TokenBoard();
	
	private TokenBoard(){}
	
	/**
	 * There can only be one instance of the TokenBoard
	 * at any time.  This uses the Singleton pattern to ensure this.
	 * @return
	 */
	public static TokenBoard getInstance(){
		pane = Controller.getTokenBoard();
                pane.getChildren().add(vBox);
		return tokenBoard;
	}
	
        public void clear()
        {
            pane.getChildren().removeAll(pane.getChildren());
            vBox.getChildren().removeAll(vBox.getChildren());
        }
	
	/**
	 * 
	 * @param lhsTokens
	 */
	public void settleTokenBoard(SourceFile src){
            
                
            Font font = new Font(10);
            Text t = new Text(src.toString());
            t.setFont(font);
            t.setFill(Color.BLUE);
            vBox.getChildren().add(t);
/*            
            int nLines = src.lineCount();
            ObservableList<Node> children = vBox.getChildren();
            for (int n = 0; n < nLines; ++n)
            {
                List<SourceToken> line = src.getTokens(n);
                Font font = new Font(10);
                
                HBox hBox = new HBox();
                for (SourceToken tk : line)
                {
                    System.out.print(tk.image());
                    Text t = new Text(tk.image());
                    t.setFont(font);
                    t.setWrappingWidth(530);
                    switch(tk.getKind())
                    {
                        case KEYWORD:
                            t.setFill((Color.BLUE));
                            break;
                        case NUM_LITERAL:
                            t.setFill((Color.BLACK));
                            break;
                        case CHAR_LITERAL:
                            t.setFill((Color.ORANGE));
                            break; 
                        case STRING_LITERAL:
                            t.setFill((Color.ORANGE));
                            break;
                        case OPERATOR:
                            t.setFill((Color.BLACK));
                            break;
                        case SEPARATOR:
                            t.setFill((Color.BLACK));
                            break;
                        case IDENTIFIER:
                            t.setFill((Color.RED));
                            break;
                        case TAB:
                        case SPACE:
                        case EMPTY:
                            t.setFill((Color.BLACK));
                            break;
                    }
                    t.setText(tk.image());
                    hBox.getChildren().add(t);
                }
                System.out.println();
                children.add(hBox);
            }
            * 
            */
	}
}
