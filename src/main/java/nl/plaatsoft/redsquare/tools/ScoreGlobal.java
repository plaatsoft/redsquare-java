package nl.plaatsoft.redsquare.tools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The Class ScoreGlobal.
 * 
 * @author wplaat
 */
public class ScoreGlobal {

	/** The list. */
	private static ArrayList<Score> list = new ArrayList<Score>();
		
	/**
	 * Instantiates a new score global.
	 */
	private ScoreGlobal() {
	    throw new IllegalStateException("ScoreGlobal class");
    }
	
	/**
	 * Adds the score.
	 *
	 * @param score the score
	 */
	public static void addScore(Score score) {
		list.add(score);
	}
		
	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public static List<Score> getScore() {
		
		return list;
	}
	
	/**
	 * Clear.
	 */
	public static void clear() {
		
		Iterator<Score> iter = list.iterator();    	
		while (iter.hasNext()) {
			iter.next();
			iter.remove();
		}
	}
}
