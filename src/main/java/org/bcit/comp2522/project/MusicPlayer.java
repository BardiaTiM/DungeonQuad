package org.bcit.comp2522.project;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class MusicPlayer {
  private Clip clip;

  public MusicPlayer(String musicFilePath) {
    try {
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(musicFilePath));
      clip = AudioSystem.getClip();
      clip.open(audioInputStream);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void play() {
    clip.loop(Clip.LOOP_CONTINUOUSLY); // Set the clip to loop indefinitely
  }

  public void stop() {
    clip.stop();
    clip.close();
  }

}
