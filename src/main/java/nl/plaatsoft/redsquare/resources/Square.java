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

package nl.plaatsoft.redsquare.resources;

import org.apache.log4j.Logger;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import nl.plaatsoft.redsquare.tools.Constants;

/**
 * The Class Square.
 * 
 * @author wplaat
 */
public class Square extends ImageView{
	
	/** The Constant log. */
	final static Logger log = Logger.getLogger( Square.class);
	
	/** The direction horizontal. */
	private int directionHorizontal;
	
	/** The direction vertical. */
	private int directionVertical;
	
	/** The x. */
	private double x;
	
	/** The y. */
	private double y;
	
	/** The width. */
	private double width;
	
	/** The height. */
	private double height;
	
	/** The step. */
	private int step;
	
	/** The sound. */
	private boolean sound;
	
	/**
	 * Sets the position.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public void setPosition(double x, double y) {
		
		this.x=x;
		this.y=y;	
		
		setLayoutX(x);
		setLayoutY(y);
	}
	
	/**
	 * Instantiates a new square.
	 *
	 * @param image the image
	 * @param x the x
	 * @param y the y
	 * @param dirHor the dir hor
	 * @param dirVer the dir ver
	 * @param step the step
	 * @param sound the sound
	 */
	public Square(Image image ,int x, int y, int dirHor, int dirVer, int step, boolean sound) {

		setImage(image);
		this.x = x;
		this.y = y;		
		this.width = getImage().getWidth();
		this.height = getImage().getHeight();
		this.directionHorizontal = dirHor;
		this.directionVertical = dirVer;
		this.step = step;
		this.sound = sound;
		
		setLayoutX(x);
		setLayoutY(y);
	}
		
	/**
	 * Collision sound.
	 */
	private void collisionSound() {
		
		if (sound) {	
			AudioClip sound = new AudioClip(getClass().getResource("/music/effect1.mp3").toExternalForm());
			sound.play();
		}
	}
	
	/**
	 * Move.
	 */
	public void move() {
			
		if (directionHorizontal==1) {
			x += step;			
			if (x>(Constants.WIDTH-width)) {
				directionHorizontal=0;
				x = Constants.WIDTH - width;
				collisionSound();
			}
		} else {
			x -= step;
			if (x<=0) {
				directionHorizontal=1;
				x=0;
				collisionSound();
			}
		}
		
		if (directionVertical==1) {
			y += step;
			if (y>(Constants.HEIGHT-height)) {
				directionVertical=0;
				y=Constants.HEIGHT - height;
				collisionSound();
			}
		} else {
			y -= step;
			if (y<=0) {
				directionVertical=1;
				collisionSound();
				y=0;
			}
		}

		setLayoutX(x);
		setLayoutY(y);
	}	
	
	/**
	 * Collision.
	 *
	 * @param red the red
	 * @return true, if successful
	 */
	public boolean collision(Square red) {
		
	    boolean collision=false;
	    
	    if (red.getPosX() < x + width &&  red.getPosX() + red.getWidth() > x &&
	    	red.getPosY() < y + height && red.getHeight() + red.getPosY() > y) {
	    	collision = true;
	  	}
	    return collision;
	}	
	
	/**
	 * Gets the step.
	 *
	 * @return the step
	 */
	public int getStep() {
		return step;
	}

	/**
	 * Sets the step.
	 *
	 * @param step the new step
	 */
	public void setStep(int step) {
		this.step = step;
	}
	
	/**
	 * Gets the pos X.
	 *
	 * @return the pos X
	 */
	public int getPosX() {		
		return (int) x;
	}
	
	/**
	 * Gets the pos Y.
	 *
	 * @return the pos Y
	 */
	public int getPosY() {		
		return (int) y;
	}
	
	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public int getWidth() {		
		return (int) width;
	}
	
	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public int getHeight() {		
		return (int) height;		
	}
}
