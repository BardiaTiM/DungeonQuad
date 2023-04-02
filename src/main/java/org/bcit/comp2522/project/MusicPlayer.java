package org.bcit.comp2522.project;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * This class is responsible for playing background music in the game.
 *
 * @author Gathrean Dela Cruz
 */
public class MusicPlayer {
  private Clip clip;


  /**
   * Constructs a MusicPlayer object and loads an audio file from the specified path.
   *
   * @param musicFilePath the file path of the audio file
   */
  public MusicPlayer(String musicFilePath) {
    try {
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(musicFilePath));
      clip = AudioSystem.getClip();
      clip.open(audioInputStream);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  /**
   * Starts playing the audio clip, and sets it to loop continuously.
   */
  public void play() {
    clip.start();
    clip.loop(Clip.LOOP_CONTINUOUSLY); // Set the clip to loop indefinitely
  }


  /**
   * Stops the audio clip and closes it.
   */
  public void stop() {
    clip.stop();
    clip.close();
  }


  /**
   * Returns the current position of the audio clip in microseconds.
   * @return
   */
  public long getMicrosecondPosition() {
    return clip.getMicrosecondPosition();
  }


  /**
   * Starts playing the audio clip from its current position.
   */
  public void start() {
    clip.start();
  }


  /**
   * Sets the position of the audio clip to the specified position in microseconds
   *
   * @param clipPosition the position to set teh audio clip to, in microseconds
   */
  public void setMicrosecondPosition(long clipPosition) {
    clip.setMicrosecondPosition(clipPosition);
  }
}
