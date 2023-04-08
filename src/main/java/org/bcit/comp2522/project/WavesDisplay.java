package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Displays the info UI for the player.
 *
 * @author Gathrean Dela Cruz
 * @version 1.0
 */
public class WavesDisplay {

  private final Window window;

  /**
   * WavesDisplay constructor.
   *
   * @param window Window
   */
  public WavesDisplay(Window window) {
    this.window = window;
  }

  /**
   * Displays the game Waves, Enemies, and Score of the game screen.
   *
   * @param waveNumber   the current wave number
   * @param totalEnemies the total number of enemies in the current wave
   */
  public void display(int waveNumber, int totalEnemies) {
    displayFont();
    displayShadow();                   // under everything
    displayWaveNumber(waveNumber);     // top left
    displayEnemiesCount(totalEnemies); // top left
    displayScore();                    // top right
    displaySpaceStart(waveNumber);     // center
    displayInventory(waveNumber);      // bottom right
  }

  /**
   * Sets the font for the UI to Nintendo NES Font.
   */
  public void displayFont(){
    window.textFont(window.createFont("fonts/Nintendo NES Font.ttf", 20));
  }
  public void displayShadow() {
    PImage backgroundImage = window.loadImage("images/window shadow.png");
    window.image(backgroundImage, 0, window.height - backgroundImage.height);
  }

  /**
   * This method returns the Player's high score and the highest wave number reached.
   *
   * @param highScore  The Player's high score
   * @param waveNumber The highest wave number reached
   */
  public void displayHighScore(int highScore, int waveNumber) {
    window.fill(176, 212, 222);
    window.textFont(window.createFont("fonts/Nintendo NES Font.ttf", 20));

    window.fill(255); // set the text color to white

    // Set the horizontal and vertical alignment to left and top, respectively
    window.textAlign(PApplet.LEFT, PApplet.TOP);

    float xWaves = 10; // Position x at the left edge with 10 pixels margin
    float yWaves = 10; // Position y at the top edge with 10 pixels margin

    window.fill(176, 212, 222); // dungeon quad blue
    window.text("HIGH WAVE: " + waveNumber, xWaves, yWaves);

    // Set the horizontal and vertical alignment to left and top, respectively
    window.textAlign(PApplet.LEFT, PApplet.TOP);

    // Set the horizontal and vertical alignment to right and top, respectively
    window.textAlign(PApplet.RIGHT, PApplet.TOP);

    float xScore = window.width - 10; // Position x just 10 pixels from the right edge
    float yScore = 10; // Position y at the top edge

    window.fill(176, 212, 222); // dungeon quad blue
    window.text("HIGH SCORE: " + highScore, xScore, yScore);
  }

  /**
   * Displays the current wave number in the top left corner of the screen.
   *
   * @param waveNumber the current wave number
   */
  public void displayWaveNumber(int waveNumber){
    float xWaves = 10;
    float yWaves = 10;
    window.fill(245, 0, 0);
    window.text("WAVE " + (waveNumber - 1), xWaves, yWaves);
    window.textAlign(PApplet.LEFT, PApplet.TOP);
  }

  /**
   * Displays the total number of enemies in the current wave in the top left corner of the screen.
   *
   * @param totalEnemies the total number of enemies in the current wave
   */
  public void displayEnemiesCount(int totalEnemies){
    float xEnemies = 10;
    float yEnemies = window.textAscent() + 20;
    window.fill(94, 149, 101); // goblin green
    window.text("ENEMIES: " + totalEnemies, xEnemies, yEnemies);
    window.textAlign(PApplet.RIGHT, PApplet.TOP);
  }

  /**
   * Displays the player's score in the top right corner of the screen.
   */
  public void displayScore(){
    float xScore = window.width - 10;
    float yScore = 10;
    window.fill(176, 212, 222);
    window.text("SCORE: " + Window.score, xScore, yScore);
  }

  /**
   * Displays the "Press Space to Start" text in the center of the screen.
   *
   * @param waveNumber the current wave number
   */
  public void displaySpaceStart(int waveNumber){
    window.fill(255); // white
    if (waveNumber == 1) {
      window.textAlign(PApplet.CENTER, PApplet.CENTER);
      window.text("PRESS 'SPACE' TO START", window.width / 2f, window.height / 2f);
    }
  }

  /**
   * Displays the player's inventory in the bottom right corner of the screen.
   *
   * @param waveNumber the current wave number
   */
  public void displayInventory(int waveNumber) {
    PImage playerGuns;
    if (waveNumber < 11) {
      playerGuns = window.loadImage("images/inventory/player_guns_1.png");
    } else if (waveNumber < 21) {
      playerGuns = window.loadImage("images/inventory/player_guns_2.png");
    } else {
      playerGuns = window.loadImage("images/inventory/player_guns_3.png");
    }
    window.image(playerGuns, window.width - playerGuns.width - 10, window.height - 116, 350, 120);
    window.textAlign(PApplet.LEFT, PApplet.TOP);
  }
}