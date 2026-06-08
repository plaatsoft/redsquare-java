package nl.plaatsoft.redsquare.tools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ScoreGlobal {

  private static final ArrayList<Score> list = new ArrayList<>();

  private ScoreGlobal() {
    throw new IllegalStateException("ScoreGlobal class");
  }

  public static void addScore(Score score) {
    list.add(score);
  }

  public static List<Score> getScore() {
    return list;
  }

  public static void clear() {
    Iterator<Score> iter = list.iterator();
    while (iter.hasNext()) {
      iter.next();
      iter.remove();
    }
  }
}
