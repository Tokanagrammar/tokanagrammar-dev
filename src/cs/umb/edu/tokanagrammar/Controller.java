package cs.umb.edu.tokanagrammar;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * Merge model and View Here
 *
 */
public class Controller implements Initializable{
	
	//global
	@FXML
	AnchorPane mainFrame;
	@FXML 
	Pane legal_drag_zone;	//global
	
	//control panels
	@FXML
	Button runButton;
	@FXML
	Button stopButton;
	@FXML
	Button pauseButton;
	@FXML
	Button skipButton;
	@FXML
	Button catalogButton;
	@FXML
	Button difficultyButton;
	@FXML
	Button resetBoardButton;
	@FXML
	Button logoButton;	//control panels
	
	//board
	@FXML
	TextArea board;	//board
	
	/**
	 * mhs to look into-- this may not be needed.  See initialize interface documentation.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println(this.getClass().getSimpleName() + ".initialize"); //See javadoc as this probably isn't necessary.
	}

	
	
	
	
	//--------------------------------------------------------------------------------
	//GUI BUTTONS
	//--------------------------------------------------------------------------------
    /**
     * Called when the run button is fired.
     *
     * @param event the action event.
     */
	public void runFired(ActionEvent event){
		System.out.println("runFired");
	}
	
    /**
     * Called when the stop button is fired.
     *
     * @param event the action event.
     */
	public void stopFired(ActionEvent event){
		System.out.println("stopFired");
	}
	
    /**
     * Called when the pause button is fired.
     *
     * @param event the action event.
     */
	public void pauseFired(ActionEvent event){
		System.out.println("pauseFired");
		
		//stop the timer if it's running
		
		//blur current screen
        
		//open the 'continue?' dialog box
		
	}
	
    /**
     * Called when the skip button is fired.
     *
     * @param event the action event.
     */
	public void skipFired(ActionEvent event){
		System.out.println("skipFired");
	}
	
    /**
     * Called when the catalog button is fired.
     *
     * @param event the action event.
     */
	public void categoryFired(ActionEvent event){
		System.out.println("catalogFired");
	}
	
    /**
     * Called when the difficulty button is fired.
     *
     * @param event the action event.
     */
	public void difficultyFired(ActionEvent event){
		System.out.println("difficultyFired");
	}
	
    /**
     * Called when the resetBoard button is fired.
     *
     * @param event the action event.
     */
	public void resetBoardFired(ActionEvent event){
		System.out.println("resetBoardFired");
	}
	
    /**
     * Called when the logoButton is fired.
     *
     * @param event the action event.
     */
	public void logoFired(ActionEvent event){
		System.out.println("logoFired");
	}
	//--------------------------------------------------------------------------------
	//END GUI BUTTONS
	//--------------------------------------------------------------------------------
}
