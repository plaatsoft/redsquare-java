package nl.plaatsoft.redsquare.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import javafx.animation.AnimationTimer;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;

import nl.plaatsoft.redsquare.network.CloudScore;
import nl.plaatsoft.redsquare.network.CloudUser;
import nl.plaatsoft.redsquare.resources.Square;
import nl.plaatsoft.redsquare.resources.Squares;
import nl.plaatsoft.redsquare.common.AppConstants;
import nl.plaatsoft.redsquare.tools.MyButton;
import nl.plaatsoft.redsquare.tools.MyImageView;
import nl.plaatsoft.redsquare.tools.MyLabel;
import nl.plaatsoft.redsquare.tools.MyPanel;
import nl.plaatsoft.redsquare.tools.Score;
import nl.plaatsoft.redsquare.tools.ScoreLocal;

public class Game extends MyPanel {

  private static final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
  private Square blue1;
  private Square blue2;
  private Square blue3;
  private Square blue4;
  private Square red;
  private MyLabel label1;
  private MyLabel label2;
  private MyLabel label3;
  private ArrayList<MyImageView> list = new ArrayList<MyImageView>();
  private AnimationTimer timer1;
  private AnimationTimer timer2;
  private int points;
  private int level;
  private boolean go = true;
  private Date starttime;
  private Date leveltime;
  private double offsetX = 0;
  private double offsetY = 0;
  private int borderSize = AppConstants.BORDER_SIZE;
  private Task<Void> task1;
  private Score score;

  private boolean collisionDetection() {

    if (red.getLayoutX() < borderSize) {
      return true;
    }

    if ((red.getLayoutX() + red.getWidth()) > (AppConstants.WIDTH - borderSize)) {

      return true;
    }

    if (red.getLayoutY() < borderSize) {
      return true;
    }

    if ((red.getLayoutY() + red.getHeight()) > (AppConstants.HEIGHT - borderSize)) {
      return true;
    }

    if (blue1.collision(red)) {
      return true;
    }

    if (blue2.collision(red)) {
      return true;
    }

    if (blue3.collision(red)) {
      return true;
    }

    if (blue4.collision(red)) {
      return true;
    }
    return false;
  }

  private void gameOver() {

    go = false;

    timer1.stop();

    try {
      AudioClip sound = new AudioClip(getClass().getResource("/music/effect2.mp3").toExternalForm());
      sound.play();
    } catch (Exception _) {
    }

    getChildren().add(new MyButton(230, 380, "Exit", 18, Navigator.HOME));
    int y = 50;
    getChildren().add(new MyLabel(0, y, "Game Over", 60, "black", "-fx-font-weight: bold;"));
    y = y + 80;
    getChildren().add(new MyLabel(0, y, "Play Time", 30, "black", "-fx-font-weight: bold;"));
    y = y + 35;
    getChildren().add(new MyLabel(0, y, label2.getText(), 20, "black"));
    y = y + 30;
    getChildren().add(new MyLabel(0, y, "Score", 30, "black", "-fx-font-weight: bold;"));
    y = y + 35;
    getChildren().add(new MyLabel(0, y, label1.getText(), 20, "black"));
    y = y + 30;

    score = new Score(starttime, points, level, CloudUser.getNickname(), "");
    int ranking = ScoreLocal.addScore(score);

    if (ranking < 16) {
      getChildren().add(new MyLabel(0, y, "You reached the " + ranking + "th place in your personal high score!", 20, "black"));
    }

    if (ranking < 6) {
      y = y + 40;
      for (int i = 0; i < (6 - ranking); i++) {
        int x = (AppConstants.WIDTH / 2) - ((6 - ranking) * 64) / 2;
        MyImageView image = new MyImageView(x + (i * 64), y, "images/star.png", 1);
        list.add(image);
        getChildren().add(image);
      }
      timer2.start();
    }

    /* Sent score to cloud server */
    task1 = new Task<Void>() {
      public Void call() {
        CloudScore.set(AppConstants.APP_WS_NAME, AppConstants.APP_VERSION, score);
        return null;
      }
    };
    new Thread(task1).start();
  }

  Game() {
    formatter.setTimeZone(TimeZone.getTimeZone("GMT"));

    Image image1 = new Image("images/background1.png");
    BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
    BackgroundImage backgroundImage = new BackgroundImage(image1, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
    Background background = new Background(backgroundImage);

    blue1 = new Square(Squares.getBlue1(), 1, 1, 1, 1, 1, true);
    blue2 = new Square(Squares.getBlue2(), 1, 1, 1, 0, 1, true);
    blue3 = new Square(Squares.getBlue3(), 1, 1, 0, 1, 1, true);
    blue4 = new Square(Squares.getBlue4(), 1, 1, 0, 0, 1, true);
    red = new Square(Squares.getRed(), 300, 300, 0, 0, 0, false);

    red.setOnMousePressed(me -> {
      if (go) {
        offsetX = me.getSceneX() - red.getPosX();
        offsetY = me.getSceneY() - red.getPosY();
        getScene().setCursor(Cursor.HAND);
      }
    });

    red.setOnMouseDragged(me -> {
      if (go) {
        red.setPosition(me.getSceneX() - offsetX, me.getSceneY() - offsetY);
      }
    });

    red.setOnMouseReleased(me -> getScene().setCursor(Cursor.DEFAULT));

    Canvas canvas = new Canvas(AppConstants.WIDTH - (2 * AppConstants.BORDER_SIZE), AppConstants.HEIGHT - (2 * AppConstants.BORDER_SIZE));
    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.setGlobalAlpha(0.4);
    gc.setFill(Color.WHITE);
    gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    canvas.setLayoutX(AppConstants.BORDER_SIZE);
    canvas.setLayoutY(AppConstants.BORDER_SIZE);

    getChildren().add(new MyLabel(10, 5, "Score: ", 18, "white", "-fx-font-weight: bold;"));
    label1 = new MyLabel(70, 5, "" + score, 18);
    getChildren().add(new MyLabel(240, 5, "Time: ", 18, "white", "-fx-font-weight: bold;"));
    label2 = new MyLabel(300, 5, "00:00:00", 18);
    getChildren().add(new MyLabel(550, 5, "Level: ", 18, "white", "-fx-font-weight: bold;"));
    label3 = new MyLabel(610, 5, "" + level, 18);

    setBackground(background);
    getChildren().add(canvas);
    getChildren().add(blue1);
    getChildren().add(blue2);
    getChildren().add(blue3);
    getChildren().add(blue4);
    getChildren().add(red);
    getChildren().add(label1);
    getChildren().add(label2);
    getChildren().add(label3);

    timer1 = new AnimationTimer() {

      private long lastTime = 0;
      private final double TARGET_FPS = 50.0;
      private final double TIME_PER_FRAME = 1_000_000_000.0 / TARGET_FPS;

      @Override
      public void handle(long now) {

        if (now - lastTime < TIME_PER_FRAME) {
          return; // Skip frame to maintain desired speed
        }
        lastTime = now;

        // Move blue squares
        blue1.move();
        blue2.move();
        blue3.move();
        blue4.move();

        points++;
        label1.setText("" + points);

        Date current = new Date();
        long diff1 = current.getTime() - starttime.getTime();
        long diff2 = current.getTime() - leveltime.getTime();

        if (diff2 > 10000) {
          leveltime = new Date();
          level++;

          blue1.setStep(level);
          blue2.setStep(level);
          blue3.setStep(level);
          blue4.setStep(level);
        }

        label2.setText(formatter.format(diff1));
        label3.setText("" + level);

        if (collisionDetection()) {
          gameOver();
        }
      }
    };


    timer2 = new AnimationTimer() {

      int rotate = 0;

      private long lastTime = 0;
      private final double TARGET_FPS = 50.0;
      private final double TIME_PER_FRAME = 1_000_000_000.0 / TARGET_FPS;

      @Override
      public void handle(long now) {

        if (now - lastTime < TIME_PER_FRAME) {
          return; // Skip frame to maintain desired speed
        }
        lastTime = now;

        /* Rotate stars on screen */
        for (MyImageView image : list) {
          image.setRotate(rotate++);
        }
      }
    };
  }

  public void draw() {

    starttime = new Date();
    leveltime = new Date();
    points = 0;
    level = 1;
    go = true;

    blue1.setPosition(1, 1);
    blue2.setPosition(AppConstants.WIDTH - blue2.getWidth() - 1, 1);
    blue3.setPosition(1, AppConstants.HEIGHT - blue3.getHeight() - 1);
    blue4.setPosition(AppConstants.WIDTH - blue4.getWidth() - 1, AppConstants.HEIGHT - blue4.getHeight() - 1);
    red.setPosition((AppConstants.WIDTH / 2) - (red.getWidth() / 2), (AppConstants.HEIGHT / 2) - (red.getHeight() / 2));

    blue1.setStep(level);
    blue2.setStep(level);
    blue3.setStep(level);
    blue4.setStep(level);

    timer1.start();
  }
}
