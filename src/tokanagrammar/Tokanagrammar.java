package tokanagrammar;


import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Tokanagrammar extends Application{
	
	//static Group root = new Group();		//fix this to keep track of everything needing blurring! -mhs
	
    public static void main(String[] args) {
        Application.launch(Tokanagrammar.class, (java.lang.String[]) null);
    }

    @Override
    public void start(Stage primaryStage) {
    	
        try {
            AnchorPane page = (AnchorPane) FXMLLoader.load(Tokanagrammar.class.getResource("../Tokanagrammar.fxml"));
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Tokanagrammar 0.1");
            primaryStage.show();
            
            //root.getChildren().add(page);	// -mhs this is causing roll-overs to stop working, but I'd like to save all layers here.
            //ref BUG --- -mhs experimenting with pause blur here seems to work here, but not in controller where I want it.
            //root.getChildren().get(0).effectProperty().set(new BoxBlur());  //-mhs
        } catch (Exception ex) {
            Logger.getLogger(Tokanagrammar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Getting the group might be helpful in doing any kind of
     * effect for the background.  (pausing is the only thing
     * that comes to mind now... [blurring])
     * @return
     */
    public static Group getRootGroup(){
    	return null;
    	//return root; //-mhs
    }

}
