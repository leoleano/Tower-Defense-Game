/**
 * MenuMiner is used to both draw an image of the Miner tower onto the Menu
 * and to wait to see if the user clicks on the image. If so, and if the user
 * has enough money, then a MenuMinerMoving will be created. 
 *
 * @author Leonardo Leano & Tristen Kilgrow
 * @version Fall 2021
 */
package game;

import java.awt.Graphics;

public class MenuMiner extends Tower
{
	//Fields
	private GameState state;
	private int x, y;
	
	/**
	 * Constructor that sets up the coordinates and sets up the
	 * GameState variable to grant access to methods in GameState.
	 * 
	 * @param state
	 * @param x
	 * @param y
	 */
	public MenuMiner (GameState state, int x, int y)
	{
		this.state = state;
		this.x = x;
		this.y = y;
		
	}
	
	@Override
	public void update(double timeElapsed)
	{
		//Checks if the mouse is clicked, around the area of the Menu Miner tower, and if the user has sufficient funds.
		if (state.getMouseX() >= x && state.getMouseX() < x + 28 &&
			state.getMouseY() >= y && state.getMouseY() < y + 32 &&
			state.isMouseClicked() && state.getMoney() >= 100) 
		{
			state.addGameObject(new MenuMinerMoving(state));
			state.decreaseMoney(100);
		}
		
	}

	@Override
	public void draw(Graphics g, GameView v)
	{
		v.drawCenteredImage(g, "minerfacingright.png", x, y, 0.5);
		
	}
	
}
