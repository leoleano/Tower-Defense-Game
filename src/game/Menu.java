/**
 * A Menu object that's called from the "run" method in GameControl.
 * It sets up the menu background, the towers on the menu, and the text
 * describing score, life count, and money you have and what is required 
 * to buy the towers.
 *
 * @author Leonardo Leano & Tristen Kilgrow
 * @version Fall 2021
 */
package game;

import java.awt.Color;
import java.awt.Graphics;

public class Menu implements Animatable
{
	//Fields
	private GameState state;
	private boolean objectsAdded;
	
	/**
	 * Constructor that sets up the objectsAdded boolean to false
	 * and introduces the state field to grant access.
	 * @param state
	 */
	public Menu (GameState state)
	{
		this.state = state;
		objectsAdded = false;
		
	}

	/**
	 * Updates the menu by adding the towers to the Menu if they're 
	 * not there already.
	 */
	public void update(double timeElapsed)
	{
		if (!objectsAdded)
		{
			//Add the MenuMiner
			state.addGameObject (new MenuMiner(state, 650, 85));
			//Add the MenuKeep
			state.addGameObject(new MenuKeep(state, 850, 85));
			objectsAdded = true;
		}
			
			
	}
	
	/**
	 * Draws the menu background and displays the text 
	 * for the score, life, and money counter and the prices/names
	 * for the towers.
	 */
	public void draw(Graphics g, GameView v)
	{
			 //Draw menu background
	     g.drawImage(ResourceLoader.getLoader().getImage("parchment.jpg"), 600, 0, null);

			 //Draw score counter
			 g.setColor(Color.BLACK);
			 int s = state.getScore();
			 g.drawString("Score: " + s, 820, 20);

			 //Draw life counter
			 g.setColor(Color.BLACK);
			 int l = state.getLife();
			 g.drawString("Life: " + l, 620, 20);

			 //Draw money counter
			 g.setColor(Color.BLACK);
			int m = state.getMoney();
			g.drawString("Money: " + m, 720, 20);
			
			//Draw money price for Miner
			 g.setColor(Color.BLACK);
			g.drawString("Miner, Price: 100", 635, 140);
			
			//Draw money price for Keep
			 g.setColor(Color.BLACK);
			g.drawString("Keep, Price: 200", 800, 140);


	}
}
