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
   * @param skeletons  skeletons
   * @param goblins    goblins
   * @param trolls     trolls
   */
  public void displayWaves(int waveNumber, int skeletons, int goblins, int trolls) {
    parent.fill(255); // set the text color to white
    parent.textSize(30);

    // Set an offset from the top left corner of the screen
    float xOffsetW = 50;
    float yOffsetW = 10;

    parent.text("WAVE " + (waveNumber - 1), xOffsetW, yOffsetW);

    // Set an offset from the top left corner of the screen
    float xOffsetSGT = 25;
    float yOffsetSGT = 70;
    parent.text("S:" + skeletons + "\nG:" + goblins + "\nT:" + trolls,
        xOffsetSGT, yOffsetSGT + parent.textAscent());

    if (waveNumber == 1) {
      parent.textAlign(PApplet.CENTER, PApplet.CENTER);
      parent.text("Press 'SPACE' to start new wave",
          parent.width / 2f, parent.height / 2f);
    }
  }

  public void displayWaves(int waveNumber, int totalEnemies) {
    parent.fill(255); // set the text color to white
    parent.textSize(30);

    // Set an offset from the top left corner of the screen
    float xOffsetW = 50;
    float yOffsetW = 10;

    parent.text("WAVE " + (waveNumber - 1), xOffsetW, yOffsetW);

    // Set an offset from the top left corner of the screen
    float xOffsetSGT = 75;
    float yOffsetSGT = 10;
    parent.text("ENEMIES: " + totalEnemies, xOffsetSGT, yOffsetSGT + parent.textAscent());

    if (waveNumber == 1) {
      parent.textAlign(PApplet.CENTER, PApplet.CENTER);
      parent.text("Press 'SPACE' to start new wave",
          parent.width / 2f, parent.height / 2f);
    }

    float xScore = 640;
    float yScore = 10;
    parent.textSize(30);
    parent.text("Score: " + Window.score, xScore, yScore);
  }
}

