/**
 *  @file
 *  @brief 
 *  @author wplaat
 *
 *  Copyright (C) 2008-2016 PlaatSoft
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, version 3.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package nl.plaatsoft.redsquare.ui;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import nl.plaatsoft.redsquare.tools.Constants;
import nl.plaatsoft.redsquare.tools.ScoreGlobal;

/**
 * The Class Navigator.
 */
public class Navigator {
		
	/** The intro 1. */
	private static Intro1 intro1;
	
	/** The intro 2. */
	private static Intro2 intro2;
	
	/** The home. */
	private static Home home;
	
	/** The game. */
	private static Game game;	
	
	/** The donate. */
	private static Donate donate;
	
	/** The high score 1. */
	private static HighScore1 highScore1;
	
	/** The high score 2. */
	private static HighScore2 highScore2;
	
	/** The credits. */
	private static Credits credits;	
	
	/** The release notes. */
	private static ReleaseNotes releaseNotes;
	
	/** The help. */
	private static Help help;
	
	/** The settings. */
	private static Settings settings;	
	
	/** The scene. */
	private static Scene scene;	
			
	/** The Constant INTRO1. */
	public static final int INTRO1 = 1;
	
	/** The Constant INTRO2. */
	public static final int INTRO2 = 2;
	
	/** The Constant HOME. */
	public static final int HOME = 3;
	
	/** The Constant GAME. */
	public static final int GAME = 4;
	
	/** The Constant DONATE. */
	public static final int DONATE = 5;
	
	/** The Constant LOCAL_HIGHSCORE. */
	public static final int LOCAL_HIGHSCORE = 6;
	
	/** The Constant GLOBAL_HIGHSCORE. */
	public static final int GLOBAL_HIGHSCORE = 7;
	
	/** The Constant CREDITS. */
	public static final int CREDITS = 8;
	
	/** The Constant RELEASE_NOTES. */
	public static final int RELEASE_NOTES = 9;
	
	/** The Constant HELP. */
	public static final int HELP = 10;
	
	/** The Constant SETTINGS. */
	public static final int SETTINGS = 11;
	
	/** The Constant EXIT. */
	public static final int EXIT = 12;
			
	/**
	 * Gets the scene.
	 *
	 * @return the scene
	 */
	public static Scene getScene() {
		return scene;
	}
	
	/**
	 * Go.
	 *
	 * @param page the page
	 */
	public static void go(int page) {
				
		switch (page ) {
		
			case INTRO1:
				intro1 = new Intro1();
				intro1.draw();
				scene = new Scene(intro1, Constants.WIDTH, Constants.HEIGHT);	
				scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
				    public void handle(KeyEvent key) {
				    	Navigator.go(Navigator.INTRO2);			
				    }
				});						
				break;
			
			case INTRO2:
				intro2 = new Intro2();				
				intro2.draw();
				scene.setRoot(intro2);
				scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
				    public void handle(KeyEvent key) {
				    	Navigator.go(Navigator.HOME);			
				    }
				});		
				break;		
				
			case HOME:
				if (home==null) {
					home = new Home();
				}
				home.draw();
				scene.setRoot(home);	
				scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
				    public void handle(KeyEvent key) {
				       // switch it off
				    }
				});		
				break;		
		
			case GAME:
				game = new Game();
				game.draw();		
				scene.setRoot(game);					
				break;		
				
			case LOCAL_HIGHSCORE:
				ScoreGlobal.clear();
				
				highScore1 = new HighScore1();
				highScore1.draw();
				
				scene.setRoot(highScore1);
				break;
				
			case GLOBAL_HIGHSCORE:
				highScore2 = new HighScore2();
				scene.setRoot(highScore2);
				highScore2.draw();
				break;		
				
			case DONATE:
				donate = new Donate();
				scene.setRoot(donate);
				donate.draw();
				break;
								
			case CREDITS:
				credits = new Credits();
				credits.draw();
				scene.setRoot(credits);
				break;	
				
			case RELEASE_NOTES:
				releaseNotes = new ReleaseNotes();
				releaseNotes.draw();
				scene.setRoot(releaseNotes);				
				break;		
				
			case SETTINGS:
				settings = new Settings();
				settings.draw();
				scene.setRoot(settings);			
				break;	
				
			case HELP:
				help = new Help();
				help.draw();
				scene.setRoot(help);			
				break;	
				
			case EXIT:
				Platform.exit();
				break;
		}
	}
}
