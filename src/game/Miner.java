/**
 * Miner class sets up the Miner gameObject which represents the Miner tower.
 * 
 * Mainly deals with the bombs that the tower throws at the closest enemies.
 * @author Leonardo Leano & Tristen Kilgrow
 * @version Fall 2021
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class Miner extends Tower
{
	//Fields
	private GameState state;
	private int x, y;
	private double timeSinceLastShot;

	/**
	 * Constructor that sets up the coordinates, 
	 * initializes the timeSinceLastShot variable, and
	 * introduces the state variable to grant access
	 * to GameState methods.
	 * 
	 * @param state
	 * @param x
	 * @param y
	 */
	public Miner (GameState state, int x, int y)
	{
		this.state = state;
		this.x = x;
		this.y = y;
		
		timeSinceLastShot = 0;
	}

	@Override
	public void update(double timeElapsed)
	{
		
		timeSinceLastShot += timeElapsed;
		
		if (timeSinceLastShot < 1) //If it has been less than one second from the last shot, abort.
			return; 
		
		
		Point towerPoint = new Point (x,y);

		Enemy e = state.findNearestEnemy(towerPoint);

		if (e == null)
			return; //Must've been the wind.

		if (towerPoint.distance(e.getLocation()) > 100 )
			return; //Closest enemy is too far.

		EffectBomb s = new EffectBomb (state, towerPoint, e.getLocation());

		state.addGameObject(s);
		
		timeSinceLastShot = 0; //Resets timer.



	}

	@Override
	public void draw(Graphics g, GameView v)
	{
		v.drawCenteredImage(g, "minerfacingright.png", x, y, 0.5);

	}

}
