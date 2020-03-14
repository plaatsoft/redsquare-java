package nl.plaatsoft.redsquare.ui;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import nl.plaatsoft.redsquare.tools.MyImageView;
import nl.plaatsoft.redsquare.tools.MyLabel;
import nl.plaatsoft.redsquare.tools.MyPanel;

/**
 * The Class Intro2.
 * 
 * @author wplaat
 */
public class Intro2 extends MyPanel {

	/** The image view 1. */
	private MyImageView imageView1;
	
	/**
	 * Draw.
	 */
	public void draw() {	
		
		Image image1 = new Image("images/background2.png");
		BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
		BackgroundImage backgroundImage = new BackgroundImage(image1, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);														
		setBackground(new Background(backgroundImage));
		
		getChildren().add(new MyLabel(0,30,"For more information visit",26));
		getChildren().add(new MyLabel(0,70,"www.plaatsoft.nl",26));
		imageView1 = new MyImageView(200,150, "images/logo2.png",1);		
		getChildren().add(imageView1);
		getChildren().add(new MyLabel(0,410,"RedSquare is also available for Nintendo Wii",16));
		
		setOnMousePressed(new EventHandler<MouseEvent>() {

	        public void handle(MouseEvent t) {
				Navigator.go(Navigator.HOME);						
	        }
	    });
				
		AnimationTimer timer = new AnimationTimer() {			 
		float size = (float) 0.025;
			 	
	    @Override
	    public void handle(long now) {
	            	
	       	size+=0.025;
	       	if (size>=1) {
	       		size=1;
	       	}
	       	imageView1.setScaleX(size);
	       	imageView1.setScaleY(size);
	      }
	    };		
	    
		timer.start();
	}
}
