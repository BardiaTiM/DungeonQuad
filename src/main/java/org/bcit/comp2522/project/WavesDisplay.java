package org.bcit.comp2522.project;

import processing.core.PApplet;

/**
 * WavesDisplay class.
 *
 * @author Gathrean Dela Cruz
 * @version 1.0
 */
public class WavesDisplay {
  private final PApplet parent;

  /**
   * WavesDisplay constructor.
   *
   * @param parent Window
   */
  public WavesDisplay(PApplet parent) {
    this.parent = parent;
  }

  /**
   * Displays the waves.
   *
   * @param waveNumber wave number
   * @param totalEnemies total enemies
   */
  public void displayWaves(int waveNumber, int totalEnemies) {
    parent.textSize(30);
    parent.textAlign(PApplet.CENTER, PApplet.CENTER);
    parent.text("WAVE " + (waveNumber - 1)
            + "\n ENEMIES IN THIS ROUND:" + totalEnemies,
        parent.width / 2f, parent.height / 8f - 50);
    if (waveNumber == 1) {
      parent.text("Press 'SPACE' to start new wave", parent.width / 2f, parent.height / 2f);
    }
  }
}

