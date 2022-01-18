/**
 * A Music class that takes a .wav file format and has a method to play the audio clip
 *
 * This class was completed as part of checkpoint #4.  (You shouldn't
 * need to change it.)
 *
 * @author Leonardo Leano @ Tristen Kilgrow
 * @version Fall 2021
 */
package game;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music
{

    // to store current position
    Long currentFrame;
    Clip clip;

    // current status of clip
    String status;

    AudioInputStream audioInputStream;
    static String filePath = "src/resources/losslife.wav";

    // constructor to initialize streams and clip
    public Music()
        throws UnsupportedAudioFileException,
        IOException, LineUnavailableException
    {
        // create AudioInputStream object
        audioInputStream =
                AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

        // create clip reference
        clip = AudioSystem.getClip();

        // open audioInputStream to the clip
        clip.open(audioInputStream);
    }



    // Method to play the audio
    public void play()
    {
        //start the clip
        clip.start();

        status = "play";
    }




}
