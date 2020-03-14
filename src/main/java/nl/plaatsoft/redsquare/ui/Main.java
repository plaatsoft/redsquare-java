package nl.plaatsoft.redsquare.ui;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import nl.plaatsoft.redsquare.tools.Constants;
import nl.plaatsoft.redsquare.tools.MyMusic;

/**
 * The Class Main.
 * 
 * @author wplaat
 */
public class Main extends Application {
		
	/** The Constant log. */
	private static final Logger log = LogManager.getLogger(Main.class);
	
    /**
     * Start.
     *
     * @param primaryStage the primary stage
     */
    @Override
    public void start(Stage primaryStage) {
    	    	   
    	setUserAgentStylesheet(STYLESHEET_MODENA);
    	    	
    	Navigator.go(Navigator.INTRO1);
       	     	    
        primaryStage.setTitle(Constants.APP_NAME+" v"+Constants.APP_VERSION);
        primaryStage.setScene(Navigator.getScene());
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("images/logo3.png"));
        primaryStage.show();
        
        MyMusic.play();        
    }
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {

    	log.info(Constants.APP_NAME+" v"+Constants.APP_VERSION+" start");
    	
    	String version = System.getProperty("java.version");    
        String[] parts = version.split("_");
        if (((parts[0].equals("1.7.0") && Integer.parseInt(parts[1])<70)) || ((parts[0].equals("1.8.0") && Integer.parseInt(parts[1])<100))) {   
            JOptionPane.showMessageDialog(null, "Java v"+version+" is to old. Please upgrade!");
            System.exit(1);
        }        
        launch(args);
        
        log.info(Constants.APP_NAME+" v"+Constants.APP_VERSION+" end");
    }    
}