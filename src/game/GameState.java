/**
 * A GameState object represents the current 'state' of the game.  This
 * includes things like score, tower positions, etc., but also includes
 * smaller details like mouse location and mouse click information (that
 * we have recorded).  Also, this object will hold a List of all the
 * things that move, update, or interact with the screen.
 *
 * The idea is that everything that is unique about a single game's
 * session is here.  If you were to save everything stored here to a file, and
 * then reload it later, the game would be in exactly the same 'state'
 * as it was before.
 *
 * There is exactly one GameState object for the entire game.  (It's
 * purpose is to hold the data that changes as the game changes.)
 *
 * @author Leonardo Leano & Tristen Kilgrow
 * @version Fall 2021
 */
package game;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class GameState
{
	
	// Fields
	List<Animatable> gameObjects;
	List<Animatable> objectsToRemove;
	List<Animatable> objectsToAdd;

    int mouseX, mouseY;
    boolean mouseClicked;
    boolean GameOver;
    int money;
    int score;
    int lifecount;
    
	 
	/**
	 * Constructor that's called from the GameControl "run" method which sets everything up.
	 * Initializes the gameObjects ArrayList system and sets logical default values to fields
	 * such as score, money, and life count.
	 */
    public GameState ()
	  {
	    gameObjects = new ArrayList<Animatable>();
	    objectsToRemove = new ArrayList<Animatable>();
	    objectsToAdd = new ArrayList<Animatable>();
	    money = 120;
	    score = 0;
	    lifecount = 70;
	    mouseX = mouseY = 0;
	    mouseClicked = false;
	    
	  }
	  
    /**
     * Called with an Animatable object as parameter.
     * Placing it into the objectsToAdd ArrayList, which later gets 
     * transferred to the main gameObjects ArrayList.
     * @param a
     */
	  public void addGameObject (Animatable a)
	  {
	    objectsToAdd.add(a);
	  }
	  
	  /**
	   * Called with an Animatable object as a parameter.
	   * This method removes the object from the game first by adding it to the 
	   * objectsToRemove ArrayList.
	   * @param a
	   */
	  public void removeGameObject (Animatable a)
	  {
	    objectsToRemove.add(a);
	  }

	/**
	 * Essentially just helps methods in GameView that deal with mouse movement by returning
	 * the mouse coordinates at the time of call.
	 * @param x
	 * @param y
	 */
    public void setMouseLocation (int x, int y)
    {
      mouseX = x;
      mouseY = y;
    }
    
    /**
     * A getter that returns the x-coordinate of the mouse.
     * @return
     */
    public int getMouseX ()
    {
      return mouseX;
    }
    
    /**
     * A getter that returns the y-coordinate of the mouse.
     * @return
     */
    public int getMouseY ()
    {
      return mouseY;
    }
    
    /**
     * A setter that sets the mouseClicked variable to true.
     * Helps GameView methods such as mousePressed()
     */
    public void setMouseClicked ()
    {
      mouseClicked = true;
    }
    
    /**
     * A getter that returns the mouseClicked variable, usually
     * used to check whether the mouse is clicked or not.
     * @return
     */
    public boolean isMouseClicked ()
    {
      return mouseClicked;
    }
    
    /**
     * Makes sure the mouseClicked variable is set to false when called, 
     * or else the game would register a mouseClick every tick non-stop.
     */
    public void consumeMouseClick ()
    {
      mouseClicked = false;
    }
    
    /**
     * When called, it reduces the life count by a certain amount, 
     * which is passed through the parameter.
     * @param i
     */
	public void reduceLife (int i)
	{
		lifecount -= i;
	}

	/**
	 * A getter that returns the life count amount.
	 * @return
	 */
	public int getLife ()
	{
		return lifecount;
	}
	
	/**
	 * A getter that returns the score amount.
	 * @return
	 */
    public int getScore ()
    {
      return score;
    }
    
    /**
     * A setter that increments the score by an amount passed
     * through the parameter.
     * @param amount
     */
    public void addScore (int amount)
    {
      score += amount;
    }
    
    /**
     * A getter that returns the money amount.
     * @return
     */
	public int getMoney()
	{
		return money;
	}
	
	/**
	 * A setter/incrementer that increases the money by an amount
	 * passed through the parameter.
	 * @param amount
	 */
    public void addMoney (int amount)
	{
		money += amount;
	}
    
    /**
     * A setter that decreases the money by an amount
     * passed through the parameter.
     * @param amount
     */
	public void decreaseMoney(int amount)
	{
		money -= amount;
	}
	
	/**
	 * A setter that sets the GameOver variable to either true or false.
	 * Usually only used to set it to be true when the game is over.
	 * @param GameOverState
	 */
    public void setGameOver (boolean GameOverState)
    {
      GameOver = GameOverState;
    }
    
    

	 /**
	  * Is called in actionPerformed from GameControl.
	  * Essentially updates all the gameObjects in the game, while
	  * continually checking if the requirements for the game to be 
	  * over have been met.
	  * @param timeElapsed
	  */
	 public void updateAll(double timeElapsed)
	 {
		 
		 //Check to see if game is over. If so, don't update anything. If not, keep going and update everything.
		 if (!GameOver)
	     {
			 
			// Update everything
		    for (Animatable a : gameObjects)
		    	a.update(timeElapsed);
		    
		    //Check to see if life count has reached 0 or less. If so, game over condition has been
		    //met and the GameOver object should both be drawn and the GameOver variable set to true.
		    if (lifecount <= 0)
		    {
		    	lifecount = 0;
		    	GameOver = true;
		    	addGameObject(new GameOver());
		    }

		    // Remove the things that need removing.
		    gameObjects.removeAll(objectsToRemove);
		    objectsToRemove.clear();
		    gameObjects.addAll(objectsToAdd);
		    objectsToAdd.clear();

	    }
	  }
	 
	 /**
	  * Goes through all the gameObjects and calls their draw methods.
	  * @param g
	  * @param view
	  */
	 public void drawAll(Graphics g, GameView view)
	 {
	   for (Animatable a : gameObjects)
		   a.draw(g, view);
	 }
	 
	 /**
	  * Used by towers. Essentially goes through all the Enemy objects
	  * in the gameObjects ArrayList and finds the closest one to the Point provided.
	  * @param p
	  * @return the enemy object that's closest to the point provided.
	  */
	  public Enemy findNearestEnemy(Point p)
	  {
		  Enemy closest = null;

		  for (Animatable a : gameObjects)
		  {
				if (a instanceof Enemy)
				{
					Enemy e = (Enemy) a;

					if (closest == null)
					{
						closest = e;
					}
					else
					{
						Point currentClosestPosition = closest.getLocation();
						Point enemyPosition = e.getLocation();

						double distanceToCurrentClosest = p.distance(currentClosestPosition);
						double distanceToEnemy = p.distance(enemyPosition);

						if (distanceToEnemy < distanceToCurrentClosest)
						 	closest = e;
					}
				}
			}

			return closest;


	  }
}
