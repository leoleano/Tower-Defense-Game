/**
 * A basic class that extends Enemy to inherit its methods
 * StoneRock is the basic enemy type with its own health and speed
 *
 * This class was completed as part of checkpoint #4.  (You shouldn't
 * need to change it.)
 *
 * @author Leonardo Leano @ Tristen Kilgrow
 * @version Fall 2021
 */
package game;


import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class StoneRock extends Enemy
{

	private GameState state;
	private int health;

  /**
	* a constructor that takes in a position and GameState to
	* create the enemy object StoneRock at a given position
	*
	*@param double "p" starting position and GameState state
	* a parameter that grants access to GameState methods
	*
	**/
	public StoneRock(double p, GameState state)
	{
		super(p);
		this.state = state;
		health = 10;
	}

	@Override
	public void update(double timeElapsed)
	{
		position = position + 0.060 * timeElapsed;

	    if (position > 0.99)
	    {
	    	state.removeGameObject(this);
	    	state.reduceLife(1);
	    	try
			{
				Music wee = new Music();
				wee.play();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    }


	}

	@Override
	public void draw(Graphics g, GameView v)
	{


     Point p = getLocation();

     // Draw the stone rock.

     v.drawCenteredImage(g, "stonerock.png", p.x, p.y, 1.0);


	}

	@Override
	public int getHealth()
	{
		return 10;
	}

	@Override
	public void decreaseHealth(int amount)
	{
		health -= amount;
	}

}
