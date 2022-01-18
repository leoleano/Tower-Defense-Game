/**
 * MenuKeep is used to both draw an image of the Keep tower onto the Menu
 * and to wait to see if the user clicks on the image. If so, and if the user
 * has enough money, then a MenuKeepMoving will be created. 
 *
 * @author Leonardo Leano & Tristen Kilgrow
 * @version Fall 2021
 */
package game;

import java.awt.Graphics;

public class MenuKeep extends Tower
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
	public MenuKeep (GameState state, int x, int y)
	{
		this.state = state;
		this.x = x;
		this.y = y;
		
	}
	
	@Override
	public void update(double timeElapsed)
	{
		//Checks if the mouse is clicked, around the area of the Menu Keep tower, and if the user has sufficient funds.
		if (state.getMouseX() >= x && state.getMouseX() < x + 28 &&
			state.getMouseY() >= y && state.getMouseY() < y + 32 &&
			state.isMouseClicked() && state.getMoney() >= 200)
		{
			state.addGameObject(new MenuKeepMoving(state));
			state.decreaseMoney(200); //Keep costs more because it does more damage.
		}
		
	}

	@Override
	public void draw(Graphics g, GameView v)
	{
		v.drawCenteredImage(g, "keep.png", x, y, 1.5);
		
	}
	
}
