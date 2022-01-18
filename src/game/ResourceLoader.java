/**
 * A ResourceLoader object that helps any other file
 * both load in files and the path created for the game.
 *
 * @author Leonardo Leano & Tristen Kilgrow
 * @version Fall 2021
 */
package game;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class ResourceLoader
{
    //Fields
	private Map <String, BufferedImage> imageMap;
    private Map <String, Path> pathMap;
    static private ResourceLoader instance;

	private ResourceLoader ()
    {
		// Set up anything this resource loader needs.
		imageMap = new HashMap<String, BufferedImage>();
        pathMap = new HashMap <String, Path>();

    }
	
	/**
	 * Retrieves an image from the maps set up by the constructor.
	 * This is used whenever an image is trying to be displayed, usually
	 * by drawCenteredImage. 
	 * 
	 * @param filename
	 * @return
	 */
    public BufferedImage getImage (String filename)
    {
      BufferedImage image = null;

      for (Map.Entry <String, BufferedImage> entry : imageMap.entrySet())
      {
          if (entry.getKey().equals(filename))
          {
            return entry.getValue();
          }
      }


      //Uses Classloader to retrieve the image using a file name and then places it 
      //into the imageMap.
    	try
    	{
	    	ClassLoader loader = this.getClass().getClassLoader();
	    	InputStream is = loader.getResourceAsStream("resources/" + filename);
	    	image = javax.imageio.ImageIO.read(is);
	    	imageMap.put(filename, image);



    	}
    	catch (IOException e)
    	{
    		//In the case that the filename provided is incorrect, this print
    		//will appear.
    		System.out.println("Could not load the backdrop or path.");
    		System.exit(0);
    	}
		    return image;


    }
    
    /**
     * Whenever the path is required, this will return it when provided
     * the filename for the path desired.
     * 
     * Looks through pathMap to find it. If not, it uses ClassLoader and Scanner to load 
     * the path into pathMap.
     * @param filename
     * @return
     */
    public Path getPath (String filename)
    {
      Path path = null;

      for (Map.Entry <String, Path> entry : pathMap.entrySet())
      {
          if (entry.getKey().equals(filename))
          {
            return entry.getValue();
          }
      }

      ClassLoader loader = this.getClass().getClassLoader();
      Scanner pathScanner = new
      Scanner(loader.getResourceAsStream("resources/" + filename));
      path = new Path(pathScanner);



	   pathMap.put(filename, path);

	   return path;
    }
    
    /**
     * A getter that returns the instance of ResourceLoader.
     * @return
     */
    static public ResourceLoader getLoader()
    {
      if (instance == null)
        instance = new ResourceLoader ();

      return instance;
    }


}
