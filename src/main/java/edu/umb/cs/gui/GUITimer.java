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

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class GUITimer {
	
	private static Integer milliseconds;
	static Label label = new Label();
	static Timeline digitalTime; 		//timer listener
	static Pane pane;
	
	//private String timerState = "";
	
	public enum TimerState {
		RUNNING,
		STOPPED,
		PAUSED
	}
	
	private static TimerState curTimerState = TimerState.STOPPED;
	
	private static final GUITimer timer = new GUITimer();
	
	private GUITimer() {};

	public static GUITimer getInstance(){
		return timer;
	}
	
	{
		pane  = Controller.getTimer();
		milliseconds = 0;
		digitalTime = new Timeline(
				new KeyFrame(Duration.seconds(0),
						new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent actionEvent) {
						Integer second = (milliseconds / 100) % 60;
						Integer minute = ((milliseconds / 100) /60) % 60;
						Integer hour = (((milliseconds / 100) /60) / 60) % 100;
						String hourString   = pad(2, '0', hour.toString());
						String minuteString = pad(2, '0', minute.toString());
						String secondString = pad(2, '0', second.toString());
						label.setText("       \t " + hourString + ":" + minuteString + ":" + secondString);// + ":" + msString);
						
						milliseconds++;
					}
				}
						),
						new KeyFrame(Duration.seconds(0.01)) //0.01 second per tick
				);
		digitalTime.setCycleCount(Animation.INDEFINITE);
		
		
		label.setStyle(			"-fx-font-size: 25;" +
			    				"-fx-base: rgb(153, 153, 50);" +
			    				"-fx-text-fill: rgb(255, 255, 90);" );
		
		pane.getChildren().add(label);
	}
	
	
	private static String pad(int fieldWidth, char padChar, String s) {
			StringBuilder sb = new StringBuilder();
			for (int i = s.length(); i < fieldWidth; i++) {
				sb.append(padChar);
			}
			sb.append(s);
	
			return sb.toString();
	}
	public void start(){
		curTimerState = TimerState.RUNNING;
		label.setVisible(true);
		digitalTime.play();
	}

	public void stop(){
		curTimerState = TimerState.STOPPED;
		digitalTime.stop();
	}
	public void pause(){
		curTimerState = TimerState.PAUSED;
		digitalTime.stop();
	}
	public void reset(){
		curTimerState = TimerState.STOPPED;
		digitalTime.stop();
		milliseconds = 0;
		hideTimer();
	}
	
	public void hideTimer()
	{
		label.setVisible(false);
	}
	
	public TimerState getTimerState(){
		return curTimerState;
	}
}
