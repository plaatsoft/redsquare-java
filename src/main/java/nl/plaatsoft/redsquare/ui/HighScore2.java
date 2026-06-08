package nl.plaatsoft.redsquare.ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

import java.text.SimpleDateFormat;
import java.util.Iterator;

import nl.plaatsoft.redsquare.network.CloudScore;
import nl.plaatsoft.redsquare.tools.MyButton;
import nl.plaatsoft.redsquare.tools.MyImageView;
import nl.plaatsoft.redsquare.tools.MyLabel;
import nl.plaatsoft.redsquare.tools.MyPanel;
import nl.plaatsoft.redsquare.tools.Score;
import nl.plaatsoft.redsquare.tools.ScoreGlobal;

public class HighScore2 extends MyPanel {

  private static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");

  private int y;
  private int lines;
  private Task<Void> task;

  public void draw() {
    Image image1 = new Image("images/background1.png");
    BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
    BackgroundImage backgroundImage = new BackgroundImage(image1, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
    setBackground(new Background(backgroundImage));

    y = 0;
    getChildren().add(new MyLabel(0, y, "Ultimate High Score", 50, "white", "-fx-font-weight: bold;"));

    y += 60;
    getChildren().add(new MyLabel(30, y, "Nr", 30));
    getChildren().add(new MyLabel(80, y, "Date", 30));
    getChildren().add(new MyLabel(300, y, "Score", 30));
    getChildren().add(new MyLabel(400, y, "Nickname", 30));

    MyButton button1 = new MyButton(230, 420, "Close", 18, Navigator.HOME);

    getChildren().add(button1);

    task = new Task<Void>() {
      public Void call() {
        CloudScore.getGlobal();
        return null;
      }
    };

    task.stateProperty().addListener((observable, oldValue, newState) -> {
      if (newState == State.SUCCEEDED) {
        showTable();
      }
    });

    new Thread(task).start();
  }

  private void showTable() {
    y = 80;
    lines = 1;
    for (Score score : ScoreGlobal.getScore()) {
      y += 20;
      getChildren().add(new MyLabel(30, y, "" + lines, 20));
      getChildren().add(new MyLabel(80, y, formatter.format(score.getTimestamp()), 20));
      getChildren().add(new MyLabel(300, y, "" + score.getScore(), 20));

      if (!score.getCountry().isEmpty()) {
        try {
          getChildren().add(new MyImageView(397, y + 4, "images/flags/" + score.getCountry() + ".png", 0.7));
        } catch (Exception _) {
          // flag filename not found
        }
      }
      getChildren().add(new MyLabel(430, y, score.getNickname(), 20));

      if (++lines > 15) {
        break;
      }
    }
  }
}
