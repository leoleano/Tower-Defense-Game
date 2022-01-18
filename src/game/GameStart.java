/**
 * Similar to GameOver, GameStart draws the start graphic to instruct the user
 * to click anywhere on the screen to start the game.
 * This is only called at the beginning of the game, before anything else happens.
 *
 * @author Leonardo Leano & Tristen Kilgrow
 * @version Fall 2021
 */
package game;

import java.awt.Graphics;

public class GameStart implements Animatable
{

	@Override
	public void update(double timeElapsed)
	{
		// Game over, nothing to do : (
		
	}

	@Override
	public void draw(Graphics g, GameView v)
	{
		g.drawImage(ResourceLoader.getLoader().getImage("gamestart.png"), 45, 45, null);
		
	}

}
