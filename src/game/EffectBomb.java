/**
 * EffectBomb is an Effect object that deals with the position, age, and damage of the effect.
 *
 * @author Leonardo Leano & Tristen Kilgrow
 * @version Fall 2021
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class EffectBomb implements Animatable
{
	//Fields
	private GameState state;

	private double age;
	private double x, y;
	private double dx, dy;
	private int damage;

	/**
	 * Constructor that sets up the damage, age, and coordinates that are later used
	 * to see where the bomb is supposed to go.
	 * @param state
	 * @param origin
	 * @param dest
	 */
	public EffectBomb(GameState state, Point origin, Point dest)
	{
		this.state = state;
		
		damage = 10;
		age = 0;
		x = origin.x;
		y = origin.y;
		dx = dest.x - origin.x;
		dy = dest.y - origin.y;
	}

	/**
	 * Update basically updates the cannon ball over time.
	 * 
	 */
	public void update(double timeElapsed)
	{
		age += timeElapsed;//Adds how much time has elapsed over time to age.
		if (age > 0.5) //If the effect has existed for more than half of a second, remove from game.
		{
			state.removeGameObject(this);
			return;
		}
		//Coordinates are changed according to time.
		//The "4" is what we used to change how fast it travels, so this travels twice as fast
		//as the cannonball that comes from the Keep tower.
		x += dx * timeElapsed * 4;
		y += dy * timeElapsed * 4;
		
		Point p = new Point ((int) x, (int) y);
		Enemy e = state.findNearestEnemy(p); //Finds the nearest Enemy.
		
		
		//If the enemy exists and is close enough, then we count it as a hit.
		if (e != null && e.getLocation().distance(p) < 30)
		{
			state.removeGameObject(this); //First, remove the effect.
			
			//If the damage of the cannonball is greater than the health of the enemy, 
			//then remove it and adjust score and money.
			if (damage >= e.getHealth())
			{
				state.removeGameObject(e);
				
				//Adjust score and money based on enemy type.
				if (e instanceof StoneRock)
				{
					state.addScore(10);
					state.addMoney(10);
				}

				if (e instanceof GoldRock)
				{
					state.addScore(30);
					state.addMoney(30);
				}
			}
			else //Otherwise, just decrease the health of the enemy.
			{
				e.decreaseHealth(damage);
			}

		}
	}

	@Override
	public void draw(Graphics g, GameView v)
	{

	     // Draw the Bomb.
	
		 v.drawCenteredImage(g, "Bomb.png", (int) x, (int) y, 0.4);

	}


	

}
