/**
 * A GameControl object represents all of the logic and control needed to 
 * make the game operate.  The control is responsible for setting up the 
 * animation timer, updating positions, dealing with user actions, etc.
 * 
 * There is exactly one GameControl object for the entire game.  (That's
 * it's job - to control the game.)
 * 
 * Leonardo Leano & Tristen Kilgrow
 * Fall 2021
 */
package game;


import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class GameControl implements Runnable, ActionListener
{
	//Fields
	private Timer timer;
	private GameView view;
	private GameState state;
	long previousTime;
	double totalTime;
	double nextStoneRockTime, nextGoldRockTime;
	long StoneRockCount, GoldRockCount;
	private int time;
	int clickcount;
	private GameStart start;
	private double modifier;
	
	/**
	* Constructor -- Gets called from the 'main' thread.  Since building a
	* graphical user interface (GUI) has to happen in the GUI thread, no
	* initialization is done here.  (I moved all the code into the function
	* named 'run' below, and it is called indirectly (invisibly) by the
	* invokeAndWait funciton call in TowerDefense.main
	*/
    public GameControl ()
    {
    	// I moved all the code into a function named 'run' below.
    }

    /**
     * Initializes the game and gets it running.
     */
    public void run ()
    {
    	
    	// Build the game state.
    	state = new GameState ();

    	// Build a view.  Note that the view builds it's own frame, etc.  All the work is there.

    	view = new GameView (state);
    	
    	 
		 


		//Add the Backdrop
		state.addGameObject (new Backdrop());


		//Add the Menu
		state.addGameObject (new Menu(state));
		
	
		 
		//Add the GameStart object, which will display the start screen. 
		start = new GameStart();
		state.addGameObject(start);
		






    	// Start the animation loop.
    	timer = new Timer(16, this);
    	timer.start();


		//Keep track of the start time of the first tick
		previousTime = System.nanoTime();
		
		//Initialize generation parameters
		totalTime = 0.0;
		StoneRockCount = 0;
		GoldRockCount = 0;
		nextStoneRockTime = 1.0;
		nextGoldRockTime = 4.0;
		modifier = 1.0;

    }

    /**
     * Called whenever an action event happens and we are listening to that event.
     */
	public void actionPerformed(ActionEvent e)
	{
		//Increment timer every time actionPerformed is called.
		time++;
		 
		//Calculate current and totalTime to be used for enemy generation.
		long currentTime = System.nanoTime();
		double elapsedTime = (currentTime - previousTime) / 1_000_000_000.0;
		previousTime = currentTime;

		totalTime = totalTime + elapsedTime;		
		
		
		
		//Waits for a mouse click. If given one, it removes the start screen object.
		if (state.isMouseClicked())
		{
			clickcount++;
			state.removeGameObject(start);
		}
		//If there was at least one mouse click, actionPerfromed will now call generateEnemies with no pause.
		if (clickcount >= 1) 
		{
			generateEnemies();
			
		}
		//If there hasn't been a mouse click, this if statement makes sure the timer stays at 0 
		//until there is a mouse click from the user.
		if (clickcount < 1) 
		{
			elapsedTime = 0;
			totalTime = 0;		
		}

		
		//Update the game objects
		state.updateAll(elapsedTime);

		//Make sure to consume mouse click if nothing else has
		state.consumeMouseClick();

		//Draw the game objects
		view.repaint();
	}
	
	/**
	 * This returns time variable to be used for changing the modifier over time.
	 * @return time
	 */
	public int getTime()
	{
		return time;
	}
	 
	/**
	 * Generates enemies (StoneRock & GoldRock) over time and with a modifier that 
	 * changes how frequent they spawn.  
	 */
	public void generateEnemies ()
	{
		if (time % 620 == 0)
		{
			modifier *= 0.8;
		}
		
		if (totalTime > nextStoneRockTime)
		{
			//Add a stone rock
			 
			state.addGameObject(new StoneRock(0.0, state));
			StoneRockCount++;
			 
			//Calculate next stone rock time
			nextStoneRockTime += 1.0 * modifier; // Make each stone rock one second later, with modifier making it quicker over time.
			 
			if (StoneRockCount % 4 == 0) // Every 4 stone rocks, decrease time in between spawns temporarily.
			{
				nextStoneRockTime += 2.0;
			}
			 
			 
		 }
		 
		
		 if (totalTime > nextGoldRockTime)
		 {
			 //Add a gold rock
			 
			 state.addGameObject(new GoldRock(0.0, state));
			 GoldRockCount++;
			 
			 //Calculate next gold rock time
			 
			 nextGoldRockTime += 3.0 * modifier; //Modifier makes spawn time quicker over time.
			 
			 if (GoldRockCount % 3 == 0) //Every 3 Gold Rock spawns, time between decreases. 
			 {
				 nextGoldRockTime -= 2.0;
			 }
		 }
	 }


}
