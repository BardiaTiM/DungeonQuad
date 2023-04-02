package org.bcit.comp2522.project;


import processing.core.PApplet;
import processing.core.PImage;


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
   * Displays the game Waves, Enemies, and Score of the game screen.
   *
   * @param waveNumber
   * @param totalEnemies
   */
  public void displayWaves(int waveNumber, int totalEnemies) {


    // Load the image file
    PImage backgroundImage = parent.loadImage("images/window shadow.png");


    // Draw the image below all the text
    parent.image(backgroundImage, 0, parent.height - backgroundImage.height);


    parent.fill(176, 212, 222);
    parent.textFont(parent.createFont("fonts/Nintendo NES Font.ttf", 20));


    parent.fill(255); // set the text color to white


    // Set the horizontal and vertical alignment to left and top, respectively
    parent.textAlign(PApplet.LEFT, PApplet.TOP);


    float xWaves = 10; // Position x at the left edge with 10 pixels margin
    float yWaves = 10; // Position y at the top edge with 10 pixels margin


    parent.fill(245, 0, 0); // troll boulder red
    parent.text("WAVE " + (waveNumber - 1), xWaves, yWaves);


    // Set the horizontal and vertical alignment to left and top, respectively
    parent.textAlign(PApplet.LEFT, PApplet.TOP);


    float xEnemies = 10; // Position x at the left edge with 10 pixels margin
    float yEnemies = yWaves + parent.textAscent() + 10; // Position y below WAVES: text with 10 pixels margin


    parent.fill(94, 149, 101); // goblin green
    parent.text("ENEMIES: " + totalEnemies, xEnemies, yEnemies);


    // Set the horizontal and vertical alignment to right and top, respectively
    parent.textAlign(PApplet.RIGHT, PApplet.TOP);


    float xScore = parent.width - 10; // Position x just 10 pixels from the right edge
    float yScore = 10; // Position y at the top edge


    parent.fill(176, 212, 222); // dungeon quad blue
    parent.text("SCORE: " + Window.score, xScore, yScore);


    parent.fill(255); // white
    if (waveNumber == 1) {
      parent.textAlign(PApplet.CENTER, PApplet.CENTER);
      parent.text("PRESS 'SPACE' TO START", parent.width / 2f, parent.height / 2f);
    }
  }
}
