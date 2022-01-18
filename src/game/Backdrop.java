/**
 * Backdrop object that deals with drawing the backdrop. Doesn't update since
 * backdrop will never change.
 *
 * @author Leonardo Leano & Tristen Kilgrow
 * @version Fall 2021
 */
package game;

import java.awt.Graphics;

public class Backdrop implements Animatable 
{
	/**
	 * Doesn't update the backdrop since it never will change.
	 */
	public void update(double timeElapsed) 
	{
		// It's a backdrop.  Don't mess with it.  No updating needed.
	}
	
	/**
	 * Draws the backdrop.
	 */
	public void draw(Graphics g, GameView v) 
	{
	// Draw the backdrop.
	    
	     g.drawImage(ResourceLoader.getLoader().getImage("path_2.jpg"), 0, 0, null);
	}
}