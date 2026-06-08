package nl.plaatsoft.redsquare.resources;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import nl.plaatsoft.redsquare.common.AppConstants;

public class Square extends ImageView {

  private int directionHorizontal;
  private int directionVertical;
  private double x;
  private double y;
  private final double width;
  private final double height;
  private int step;
  private final boolean sound;

  public void setPosition(double x, double y) {
    this.x = x;
    this.y = y;
    setLayoutX(x);
    setLayoutY(y);
  }

  public Square(Image image, int x, int y, int directionHorizontal, int directionVertical, int step, boolean sound) {

    setImage(image);
    this.x = x;
    this.y = y;
    this.directionHorizontal = directionHorizontal;
    this.directionVertical = directionVertical;
    this.step = step;
    this.sound = sound;
    this.width = getImage().getWidth();
    this.height = getImage().getHeight();

    setLayoutX(x);
    setLayoutY(y);
  }

  public void move() {
    if (directionHorizontal == 1) {
      x += step;
      if (x > (AppConstants.WIDTH - width)) {
        directionHorizontal = 0;
        x = AppConstants.WIDTH - width;
        collisionSound();
      }
    } else {
      x -= step;
      if (x <= 0) {
        directionHorizontal = 1;
        x = 0;
        collisionSound();
      }
    }

    if (directionVertical == 1) {
      y += step;
      if (y > (AppConstants.HEIGHT - height)) {
        directionVertical = 0;
        y = AppConstants.HEIGHT - height;
        collisionSound();
      }
    } else {
      y -= step;
      if (y <= 0) {
        directionVertical = 1;
        collisionSound();
        y = 0;
      }
    }

    setLayoutX(x);
    setLayoutY(y);
  }

  private void collisionSound() {
    if (sound) {
      try {
        AudioClip sound = new AudioClip(getClass().getResource("/music/effect1.mp3").toExternalForm());
        sound.play();
      } catch (Exception _) {
      }
    }
  }

  public boolean collision(Square red) {

    boolean collision = false;

    if (red.getPosX() < x + width && red.getPosX() + red.getWidth() > x &&
      red.getPosY() < y + height && red.getHeight() + red.getPosY() > y) {
      collision = true;
    }
    return collision;
  }

  public int getStep() {
    return step;
  }

  public void setStep(int step) {
    this.step = step;
  }

  public int getPosX() {
    return (int) x;
  }

  public int getPosY() {
    return (int) y;
  }

  public int getWidth() {
    return (int) width;
  }

  public int getHeight() {
    return (int) height;
  }
}
