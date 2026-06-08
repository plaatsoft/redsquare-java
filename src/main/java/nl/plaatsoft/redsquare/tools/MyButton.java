package nl.plaatsoft.redsquare.tools;

import javafx.scene.control.Button;
import nl.plaatsoft.redsquare.ui.Navigator;

public class MyButton extends Button {

  public MyButton(int x, int y, String value, int fontSize, final int page) {

    setText(value);
    setPrefWidth(180);
    setStyle("-fx-font-size:" + fontSize + "px;");

    setLayoutX(x);
    setLayoutY(y);

    setOnAction(event -> Navigator.go(page));
  }
}
