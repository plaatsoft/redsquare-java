package nl.plaatsoft.redsquare.tools;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The Class MyImageView.
 * 
 * @author wplaat
 */
public class MyImageView extends ImageView {

	/**
	 * Instantiates a new my image view.
	 *
	 * @param x the x
	 * @param y the y
	 * @param resource the resource
	 * @param scale the scale
	 */
	public MyImageView(int x, int y, String resource, double scale) {
		        
		Image image = new Image(resource);
		setImage(image);
		setLayoutX(x);
		setLayoutY(y);
		setScaleX(scale);
		setScaleY(scale);
	}
}
