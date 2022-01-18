/**
 * Application entry point -- this class represents the starting
 * point for our game.  Main is executed by the 'main' thread of
 * execution.  Behind the scenes, graphical user interfaces (GUIs) have
 * their own thread of execution.  This code builds a GameControl
 * object, then asks the GUI thread to 'run' it.
 * 
 * Once the GameControl object is running in the GUI thread, we let
 * the main thread of execution end.  (Execution reaches the end of 
 * main the main thread terminates.)  Note that the application will
 * keep running (in the GUI thread) as long as the GUI thread exists 
 * and there are GUI objects (the JFrame) on the screen.
 * 
 * @author Leonardo Leano & Tristen Kilgrow
 * @version Fall 2021
 */
package game;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

public class TowerDefense 
{
	public static void main(String[] args) throws InvocationTargetException, InterruptedException 
	{
		// Build a game control object.  Note -- I changed
		// the order of execution slightly.  The constructor
		// no longer sets up the game -- that code is now
		// in a 'run' method.  (Check out the GameControl class.)
		
		GameControl gc = new GameControl();
		
		// It is important to set up GUIs using something
		// called the GUI thread.  The statement below will
		// use the GUI thread and automatically call the
		// 'run' function in our GameControl object.

		
		SwingUtilities.invokeAndWait(gc);
		
		// (The above function call may throw an exception.
		// I added a 'throws' to main to deal with it.)
	}

}
