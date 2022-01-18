/**
 * MenuMinerMoving is called when a user clicks on a Miner in the Menu and has sufficient funds. 
 * At that point, an image of Miner is drawn wherever the mouse is until it's placed in a valid spot
 * in the game area.
 *
 * @author Leonardo Leano & Tristen Kilgrow
 * @version Fall 2021
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class MenuMinerMoving extends Tower
{
	//Fields
	private GameState state;

	/**
	 * Constructor that sets up the GameState variable to
	 * grant access to the methods in GameState.
	 * @param state
	 */
	public MenuMinerMoving (GameState state)
	{
		this.state = state;
		
	}
	
	@Override
	public void update (double timeElapsed)
	{
		Path p = ResourceLoader.getLoader().getPath("path.txt"); //Retrieves path.
		Point q = new Point (state.getMouseX(), state.getMouseY()); //Retrieves the point that the mouse is on.
		
		//If the mouse is clicked, is not on the menu area, and is far away enough from the path, 
		//MenuMinerMoving is removed from the game and adds a Miner object to the game.
		if (state.isMouseClicked() && p.distanceToNearestPathNode(q) > 30 && state.getMouseX() < 600)
		{
			state.removeGameObject(this);
			state.addGameObject(new Miner(state, state.getMouseX(), state.getMouseY()));
		}
		
	}

	@Override
	public void draw(Graphics g, GameView v)
	{
		v.drawCenteredImage(g, "minerfacingright.png", state.getMouseX(), state.getMouseY(), 0.5);
		
	}
	
}
