/**
 * A GameView object represents the graphical user interface to
 * our game.  It contains the code for building the JFrame (the
 * game window) and setting up the JPanels and menus.  It also
 * listens for events and sends them to the GameControl object
 * when they arrive.
 *
 * There is exactly one GameView object for the entire game.  (It's
 * purpose is to handle drawing and events.)
 *
 * In checkpoint #1, functionality that belonged in GameState and
 * GameControl was placed in GameView for convenience.  We'll
 * separate most of this out in checkpoint #2.
 *
 * @author Leonardo Leano & Tristen Kilgrow
 * @version Fall 2021
 */
package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameView extends JPanel implements MouseListener, ActionListener, MouseMotionListener
{
	// This constant is needed to get rid of a warning.  It won't matter to us.

	private static final long serialVersionUID = 1L;

	// Fields -- These variables will be part of the GameView object (that we make in GameControl).

	private GameState state;


	/**
	 * Our GameView constructor.  The 'view' is the GUI (Graphical User Interface) and
	 * this constructor builds a JFrame (window) so the user can see our 'drawing'.
	 */
    public GameView (GameState state)
    {
		this.state = state;


    	// Build the frame.  The frame object represents the application 'window'.

    	JFrame frame = new JFrame ("Tower Defense 2021");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    	// Add a drawing area to the frame (a panel).  Note that 'this' object IS the
    	// panel that we need, so we add it.

    	JPanel p = this;
    	frame.setContentPane(p);

    	// Set the size of 'this' panel to match the size of the backdrop.

    	Dimension d = new Dimension(900, 600);
    	this.setMinimumSize(d);
    	this.setPreferredSize(d);
    	this.setMaximumSize(d);

    	// Allow the JFrame to layout the window (by 'packing' it) and make it visible.

    	frame.pack();
    	frame.setVisible(true);
    	this.addMouseListener(this);
		this.addMouseMotionListener(this);





    	// This panel can send mouse events to any object that wants to 'listen' to those
    	// events.  I've removed the lines of code for the mouse listener and timer,
    	// feel free to re-add them as needed.
    }

    /**
     * Draws our game.  This function will be called automatically when Java needs to
     * repaint our window.  Use the repaint() function call (on this object) to cause
     * this function to be executed.
     *
     * @param Graphics g  the Graphics object to use for drawing
     */
    public void paint (Graphics g)
    {


    	state.drawAll(g, this);

    }

    /* The following methods are required for mouse events.  I've collapsed some of them to
     * make it easier to see which one you need.  Also note:  You'll need to register
     * 'this' object as a listener to its own events.  See the missing code in the
     * constructor.
     */

	public void mousePressed(MouseEvent e)
	{
		state.setMouseClicked();
	}

    public void mouseClicked(MouseEvent e) { }
	public void mouseReleased(MouseEvent e) { }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }

    /* The following method is required for action events.  You'll need to set up
     * the timer in the constructor in order for this method to be automatically
     * called.  Re-add the missing code in the constructor.
     */

	public void actionPerformed(ActionEvent e)
	{

	}

	/**
	*Method that listens to MouseEvent, in this case if the mouse was dragged.
	*Once it has been dragged, setMouseLocation is called with the coordinates.
	*
	*@param e
	*/
	@Override
	public void mouseDragged(MouseEvent e)
	{
		state.setMouseLocation(e.getX(), e.getY());

	}

	/**
	*Method that listens to MouseEvent, in this case if the mouse was moved.
	*Once it has been dragged, setMouseLocation is called with the coordinates.
	*
	*@param e
	*/
	@Override
	public void mouseMoved(MouseEvent e)
	{
		state.setMouseLocation(e.getX(), e.getY());

	}

	/**
	 *Is called whenever we wanted to draw an image on the screen.
	 *Draws a centered image at the provided coordinates by utilizing ResourceLoader, width,
	 *height, and Graphics.
	 *
	 * @param g
	 * @param imageName
	 * @param x
	 * @param y
	 * @param scale
	 */
	public void drawCenteredImage (Graphics g, String imageName, int x, int y, double scale)
	{
		BufferedImage image  = ResourceLoader.getLoader().getImage(imageName);
		int width = image.getWidth();
		int height = image.getHeight();
		int centerX = x - (int) (width/2*scale);
		int centerY = y - (int) (height/2*scale);

		g.drawImage(image, centerX, centerY, (int)(width*scale), (int)(height*scale), null);
	}
}
