package nl.plaatsoft.redsquare.tools;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * The Class MyMusic.
 * 
 * @author wplaat
 */
public class MyMusic {

	/** The mp. */
	static MediaPlayer mp;

	/**
	 * Instantiates a new my music.
	 */
	private MyMusic() {
	    throw new IllegalStateException("MyMusic class");
    }
	
	/**
	 * Play.
	 */
	public static void play() {
		String path = MyMusic.class.getResource("/music/music.mp3").toExternalForm();
        Media media = new Media(path);
        mp = new MediaPlayer(media);
        mp.setCycleCount(MediaPlayer.INDEFINITE);
        mp.play();
	}
	
}
