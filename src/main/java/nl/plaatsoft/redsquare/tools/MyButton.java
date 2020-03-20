package nl.plaatsoft.redsquare.tools;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import nl.plaatsoft.redsquare.ui.Navigator;

/**
 * The Class MyButton.
 * 
 * @author wplaat
 */
public class MyButton extends Button {

	/**
	 * Instantiates a new my button.
	 *
	 * @param x the x
	 * @param y the y
	 * @param value the value
	 * @param fontSize the font size
	 * @param page the page
	 */
	public MyButton(int x, int y, String value, int fontSize, final int page) {
		
		setText(value);
	    setPrefWidth(180);
	    setStyle("-fx-font-size:"+fontSize+"px;");	     
	    	  
		setLayoutX(x);
		setLayoutY(y);
		
	    setOnAction(new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent event) {
            	Navigator.go(page);
            }
        });
	}     
}
