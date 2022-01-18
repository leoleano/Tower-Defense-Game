/**
 * GameOver object basically just draws the game over graphic when it's called.
 * It's only called when the life count reaches zero and the game over
 * conditions have been met.
 *
 * @author Leonardo Leano & Tristen Kilgrow
 * @version Fall 2021
 */
package game;

import java.awt.Graphics;

public class GameOver implements Animatable
{

	@Override
	public void update(double timeElapsed)
	{
		// Game over, nothing to do : (
		
	}

	@Override
	public void draw(Graphics g, GameView v)
	{
		g.drawImage(ResourceLoader.getLoader().getImage("gameover.png"), 45, 45, null);
		
	}

}
