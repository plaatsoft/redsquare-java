package nl.plaatsoft.redsquare.ui;

import javafx.animation.AnimationTimer;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import nl.plaatsoft.redsquare.network.CloudProduct;
import nl.plaatsoft.redsquare.network.CloudScore;
import nl.plaatsoft.redsquare.network.CloudUser;
import nl.plaatsoft.redsquare.tools.MyImageView;
import nl.plaatsoft.redsquare.tools.MyLabel;
import nl.plaatsoft.redsquare.tools.MyPanel;

import static nl.plaatsoft.redsquare.common.AppConstants.TIME_PER_FRAME;

public class Intro1 extends MyPanel {

  private MyImageView imageView1;

  public void draw() {

    Image image1 = new Image("images/background1.png");
    BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
    BackgroundImage backgroundImage = new BackgroundImage(image1, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
    setBackground(new Background(backgroundImage));

    getChildren().add(new MyLabel(0, 30, "Created by PlaatSoft", 26));
    getChildren().add(new MyLabel(0, 70, "www.plaatsoft.nl", 26));
    imageView1 = new MyImageView(80, 150, "images/logo1.png", 1);
    getChildren().add(imageView1);
    getChildren().add(new MyLabel(0, 410, "This software is open source and may be copied, distributed or modified", 16));
    getChildren().add(new MyLabel(0, 430, "under the terms of the GNU General Public License (GPL) version 3", 16));

    setOnMousePressed(t -> Navigator.go(Navigator.INTRO2));

    AnimationTimer timer = new AnimationTimer() {

      float size = (float) 0.025;

      private long lastTime = 0;

      @Override
      public void handle(long now) {
        if (now - lastTime < TIME_PER_FRAME) {
          return; // Skip frame to maintain desired speed
        }
        lastTime = now;

        size += 0.025;
        if (size >= 1) {
          size = 1;
        }
        imageView1.setScaleX(size);
        imageView1.setScaleY(size);
      }
    };

    Task<Void> task = new Task<Void>() {
      public Void call() {
        CloudProduct.getPid();
        CloudUser.getUid();
        CloudScore.getLocal();
        return null;
      }
    };

    timer.start();
    new Thread(task).start();
  }
}
