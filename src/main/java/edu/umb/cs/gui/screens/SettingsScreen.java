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

package edu.umb.cs.gui.screens;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import edu.umb.cs.gui.GUI;
import edu.umb.cs.parser.BracingStyle;

/**
 * A window to control settings.
 * @author Matt
 */
public class SettingsScreen extends SecondaryScreen{

	private static Pane bracesStylePane;
	private static ObservableList<Node> bracesStyles;
	
	private static Button setBtn;

	
	@Override
	public void setupScreen(){
		super.setupLargeScreen("fxml/Settings.fxml");
		populateFeatures();
	}

	/**
	 * Populate the pane(s) with the appropriate setting controls.
	 */
	public void populateFeatures(){
		
		BracingStyle curBracingStyle = GUI.getInstance().getCurBracingStyle();

		bracesStylePane  = SettingsScreenController.getLeftPane();
		setBtn = SettingsScreenController.getSetBtn();

		int rowHeight = 35;
		int rowCount = 0;

		bracesStyles = bracesStylePane.getChildren();

		final Label bracesStyleText = new Label("Braces Style");
		final RadioButton rbAllman = new RadioButton("Allman");
		final RadioButton rbKR = new RadioButton("K & R");
		
		bracesStyles.add(bracesStyleText);
		bracesStyles.add(rbAllman);
		bracesStyles.add(rbKR);

		String css = (		"-fx-font-size: 20;" +
							"-fx-base: rgb(153, 153, 50);" +
							"-fx-text-fill: rgb(255, 255, 90);" );

		bracesStyleText.setLayoutX(10);
		bracesStyleText.setLayoutY(rowHeight * rowCount);
		bracesStyleText.setStyle(css);
		rowCount++;
		
		rbAllman.setLayoutX(10);
		rbAllman.setLayoutY(rowHeight * rowCount);
		rbAllman.setStyle(css);
		rowCount++;

		rbKR.setLayoutX(10);
		rbKR.setLayoutY(rowHeight * rowCount);
		rbKR.setStyle(css);
		rowCount++;

		if(curBracingStyle.equals(BracingStyle.K_AND_R))
			rbKR.setSelected(true); 
		else
			rbAllman.setSelected(true);
		

		setBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				if(rbKR.isSelected())
					GUI.getInstance().setCurBracingStyle(BracingStyle.K_AND_R);

				else if(rbAllman.isSelected())
					GUI.getInstance().setCurBracingStyle(BracingStyle.ALLMAN);

				GUI.getInstance().getOutputPanel().infoMessage("Bracing Style Set to " + GUI.getInstance().getCurBracingStyle());
				tearDown();
				GUI.getInstance().blurOff();
			}
		});



		//---Allman
		rbAllman.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
				//rbAllman.setSelected(true);
				rbKR.setSelected(false);
			}
		});
		rbAllman.setOnMouseEntered(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				rbAllman.setEffect(new Glow(0.5));
			}
		});
		rbAllman.setOnMouseExited(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				rbAllman.setEffect(new Glow(0.0));
			}
		});

		//---KR
		rbKR.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
				//rbKR.setSelected(true);
				rbAllman.setSelected(false);
			}
		});
		rbKR.setOnMouseEntered(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				rbKR.setEffect(new Glow(0.5));
			}
		});
		rbKR.setOnMouseExited(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				rbKR.setEffect(new Glow(0.0));
			}
		});

	}
}


//code removed from gui
//private static BracingStyle getBracingStyle()
//{
//    // allman
//    JRadioButton allman = new JRadioButton("ALLMAN");
//    allman.addActionListener(new AllmanListener());
//    
//    // K&R
//    JRadioButton kr = new JRadioButton("K&R");
//    kr.addActionListener(new KRListener());;
//
//    ButtonGroup group = new ButtonGroup();
//    group.add(allman);
//    group.add(kr);
//    group.setSelected(allman.getModel(), true);
//    style = BracingStyle.ALLMAN;
//    final JComponent[] ops = new JComponent[]
//    {
//        allman,
//        kr
//    };
//    
//    javax.swing.JOptionPane.showMessageDialog(null, ops, "Choose a bracing style", JOptionPane.PLAIN_MESSAGE);
//    return style;
//}
//
//private static class AllmanListener implements ActionListener
//{
//    @Override
//    public void actionPerformed(ActionEvent e)
//    {
//        style = BracingStyle.ALLMAN;
//    }   
//}
//
//private static class KRListener implements ActionListener
//{
//    @Override
//    public void actionPerformed(ActionEvent e)
//    {
//        style = BracingStyle.K_AND_R;
//    }   
//}
