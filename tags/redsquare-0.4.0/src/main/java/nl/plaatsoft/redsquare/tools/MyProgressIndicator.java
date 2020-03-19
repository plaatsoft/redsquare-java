package nl.plaatsoft.redsquare.tools;

import javafx.scene.control.ProgressIndicator;

/**
 * The Class MyProgressIndicator.
 * 
 * @author wplaat
 */
public class MyProgressIndicator extends ProgressIndicator {
	
	/**
	 * Instantiates a new my progress indicator.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public MyProgressIndicator(int x, int y) {
		
		setMinWidth(150);
		setMinHeight(150);
		    		    
		setLayoutX(x);
		setLayoutY(y);		
	}
}
