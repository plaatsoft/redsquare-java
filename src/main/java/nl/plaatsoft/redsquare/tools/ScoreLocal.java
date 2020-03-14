package nl.plaatsoft.redsquare.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * The Class ScoreLocal.
 * 
 * @author wplaat
 */
public class ScoreLocal {

	/** The list. */
	private static ArrayList<Score> list = new ArrayList<Score>();
		
	/**
	 * Instantiates a new score local.
	 */
	private ScoreLocal() {
	    throw new IllegalStateException("ScoreLocal class");
    }
	
	/**
	 * Adds the score.
	 *
	 * @param score the score
	 * @return the int
	 */
	public static int addScore(Score score) {
		list.add(score);
		
		sort();
		
		int count = 0;
		Iterator<Score> iter = list.iterator();    	
		while (iter.hasNext()) {
			count++;
			if (score == (Score) iter.next()) {
				break;
			}
		}
		
		// Return highscore place
		return count;
	}
	
	/**
	 * Sort.
	 */
	private static void sort() {
        ScoreSort comparator = new ScoreSort();
        Collections.sort(list, comparator);
	}
	
	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public static List<Score> getScore() {
		
		sort();
		return list;
	}
}
