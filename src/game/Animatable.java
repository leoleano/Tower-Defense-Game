/**
 * Super class that all objects to be drawn and updated will have.
 *
 * @author Leonardo Leano & Tristen Kilgrow
 * @version Fall 2021
 */
package game;

import java.awt.Graphics;

public interface Animatable
{
	public void update (double timeElapsed); //Used to objects can be updated over time.
	public void draw (Graphics g, GameView v);  // or Graphics2D
}
