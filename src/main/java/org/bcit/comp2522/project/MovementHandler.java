package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PImage;


/**
 * Handles the movement of the player.
 */
public class MovementHandler {
  private PApplet parent;
  private Player player;
  private SpawningHandler spawningHandler;


  /**
   * Constructs a MovementHandler object.
   * @param parent the PApplet object
   * @param player the Player object
   * @param spawningHandler the SpawningHandler object
   */
  public MovementHandler(PApplet parent, Player player, SpawningHandler spawningHandler) {
    this.parent = parent;
    this.player = player;
    this.spawningHandler = spawningHandler;
  }


  /**
   * Handles the movement of the player.
   * @param key the key pressed
   * @param keyCode the key code
   * @param wingsTime whether wings time is active
   */
  public void handleMovement(char key, int keyCode, boolean wingsTime) {
    if (keyCode == parent.UP || key == 'w' || key == 'W') {
      PImage playerImage;
      if (!wingsTime) {
        playerImage = parent.loadImage("images/player/normal/mcW0.png");
        spawningHandler.onlyOneSpace();
        player.direction.y = -0.8f;
      } else {
        playerImage = parent.loadImage("images/player/wings/mcW1.png");
        player.direction.y = -2; // stop vertical movement
      }
      player.setPlayer(playerImage);
    }
    if (keyCode == parent.DOWN || key == 's' || key == 'S') {
      PImage playerImage;
      if (!wingsTime) {
        playerImage = parent.loadImage("images/player/normal/mcS0.png");
        spawningHandler.onlyOneSpace();
        player.direction.y = 0.8f;
      } else {
        playerImage = parent.loadImage("images/player/wings/mcS1.png");
        player.direction.y = 2; // stop vertical movement
      }
      player.setPlayer(playerImage);
    }
    if (keyCode == parent.LEFT || key == 'a' || key == 'A') {
      PImage playerImage;
      if (!wingsTime) {
        playerImage = parent.loadImage("images/player/normal/mcA0.png");
        spawningHandler.onlyOneSpace();
        player.direction.x = -0.8f;
      } else {
        playerImage = parent.loadImage("images/player/wings/mcA1.png");
        player.direction.x = -2; // stop horizontal movement
      }
      player.setPlayer(playerImage);
    }
    if (keyCode == parent.RIGHT || key == 'd' || key == 'D') {
      PImage playerImage;
      if (!wingsTime) {
        playerImage = parent.loadImage("images/player/normal/mcD0.png");
        spawningHandler.onlyOneSpace();
        player.direction.x = 0.8f;
      } else {
        playerImage = parent.loadImage("images/player/wings/mcD1.png");
        player.direction.x = 2; // stop horizontal movement
      }
      player.setPlayer(playerImage);
    }
  }
}
