package nl.plaatsoft.redsquare.tools;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import nl.plaatsoft.redsquare.network.CloudScore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyMusic {

	private static final Logger log = LogManager.getLogger( MyMusic.class);

	static MediaPlayer mp;

	private MyMusic() {
	    throw new IllegalStateException("MyMusic class");
    }
	
	public static void play() {
		try {
			String path = MyMusic.class.getResource("/music/music.mp3").toExternalForm();
			Media media = new Media(path);
			mp = new MediaPlayer(media);
			mp.setCycleCount(MediaPlayer.INDEFINITE);
			mp.play();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
