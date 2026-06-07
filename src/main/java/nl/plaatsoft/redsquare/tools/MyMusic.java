package nl.plaatsoft.redsquare.tools;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MyMusic {

	static MediaPlayer mp;

	private MyMusic() {
	    throw new IllegalStateException("MyMusic class");
    }
	
	public static void play() {
		String path = MyMusic.class.getResource("/music/music.mp3").toExternalForm();
        Media media = new Media(path);
        mp = new MediaPlayer(media);
        mp.setCycleCount(MediaPlayer.INDEFINITE);
        mp.play();
	}
}
