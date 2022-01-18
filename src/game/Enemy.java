/**
 * Super class that all enemy objects will extend.
 * Deals with the health and position of the enemy, and sets up some abstract methods.
 *
 * @author Leonardo Leano & Tristen Kilgrow
 * @version Fall 2021
 */
package game;

import java.awt.Point;

public abstract class Enemy implements Animatable
{
	//Fields
	protected double position;
	protected int health;
	
	/**
	 * Constructor that sets up the position to what's provided. 
	 * This is usually where the position along the path where the
	 * enemy will spawn.
	 * @param p
	 */
   public Enemy (double p)
   {
     position = p;
   }
   
   /**
    * Useful method that returns the position where the enemy is along the path
    * through a Point.
    * @return
    */
	public Point getLocation ()
	{
	   Point p = ResourceLoader.getLoader().getPath("path.txt").getPathPosition(position);
	   return p;
	}
	
	/**
	 * Abstract method that we wanted each enemy to have no matter what.
	 * Since each enemy will have a health, the game needs a getter for it.
	 * @return
	 */
	abstract public int getHealth ();
	
	/**
	 * Abstract method that we wanted each enemy to have no matter what.
	 * Since each enemy will have a health, the game needs method to decrease it.
	 * @return
	 */
	abstract public void decreaseHealth(int amount);

}
