package nl.plaatsoft.redsquare.ui;

import javafx.animation.AnimationTimer;
import javafx.concurrent.Task;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

import nl.plaatsoft.redsquare.network.CloudVersion;
import nl.plaatsoft.redsquare.resources.Square;
import nl.plaatsoft.redsquare.resources.Squares;
import nl.plaatsoft.redsquare.common.AppConstants;
import nl.plaatsoft.redsquare.tools.MyButton;
import nl.plaatsoft.redsquare.tools.MyLabel;
import nl.plaatsoft.redsquare.tools.MyPanel;

import static nl.plaatsoft.redsquare.common.AppConstants.TIME_PER_FRAME;

public class Home extends MyPanel {

  private Square blue1;
  private Square blue2;
  private Square blue3;
  private Square blue4;
  private Square red;
  private AnimationTimer timer;
  private MyLabel label3;
  private String upgrade;
  private Task<Void> task;

  Home() {

    int step = 1;

    blue1 = new Square(Squares.getBlue1(), 30, 30, 1, 1, step, false);
    blue2 = new Square(Squares.getBlue2(), 500, 30, 1, 0, step, false);
    blue3 = new Square(Squares.getBlue3(), 30, 400, 0, 1, step, false);
    blue4 = new Square(Squares.getBlue4(), 500, 400, 0, 0, step, false);
    red = new Square(Squares.getRed(), 10, 10, 0, 0, 2, false);

    getChildren().add(blue1);
    getChildren().add(blue2);
    getChildren().add(blue3);
    getChildren().add(blue4);
    getChildren().add(red);

    Image image1 = new Image("images/background1.png");
    BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
    BackgroundImage backgroundImage = new BackgroundImage(image1, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
    Background background = new Background(backgroundImage);
    setBackground(background);

    getChildren().add(new MyLabel(30, 30, AppConstants.APP_NAME + " v" + AppConstants.APP_VERSION, 30, "white", "-fx-font-weight: bold;"));
    getChildren().add(new MyLabel(30, 70, AppConstants.APP_BUILD, 20));
    getChildren().add(new MyLabel(30, 420, "", 20, "white"));
    int y = 30;
    getChildren().add(new MyButton(430, y, "Play", 18, Navigator.GAME));
    y += 45;
    getChildren().add(new MyButton(430, y, "High Score", 18, Navigator.LOCAL_HIGHSCORE));
    y += 45;
    getChildren().add(new MyButton(430, y, "Settings", 18, Navigator.SETTINGS));
    y += 45;
    getChildren().add(new MyButton(430, y, "Help", 18, Navigator.HELP));
    y += 45;
    getChildren().add(new MyButton(430, y, "Credits", 18, Navigator.CREDITS));
    y += 45;
    getChildren().add(new MyButton(430, y, "Release Notes", 18, Navigator.RELEASE_NOTES));
    y += 45;
    getChildren().add(new MyButton(430, y, "Donate", 18, Navigator.DONATE));
    y = AppConstants.HEIGHT - 70;
    getChildren().add(new MyButton(430, y, "Exit", 18, Navigator.EXIT));

    timer = new AnimationTimer() {

      private long lastTime = 0;

      @Override
      public void handle(long now) {

        if (now - lastTime < TIME_PER_FRAME) {
          return; // Skip frame to maintain desired speed
        }
        lastTime = now;

        blue1.move();
        blue2.move();
        blue3.move();
        blue4.move();
        red.move();
        if (upgrade!=null) {
          label3 = new MyLabel(30, 420, upgrade, 20, "red");
        }
      }
    };

    task = new Task<>() {
      public Void call() {
        upgrade = CloudVersion.get();
        return null;
      }
    };
  }

  public void draw() {
    timer.start();
    new Thread(task).start();
  }
}
