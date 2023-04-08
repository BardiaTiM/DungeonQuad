package org.bcit.comp2522.project;

import processing.core.PImage;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles the movement of the player.
 *
 * @author Bardia Timouri
 * @author Gathrean Dela Cruz
 */
public class MovementHandler {

  private final Window window;
  private final Player player;
  private final SpawningHandler spawningHandler;
  private final Map<String, PImage> imageMap = new HashMap<>();

  /**
   * Constructs a MovementHandler object.
   *
   * @param window          the Window object
   * @param player          the Player object
   * @param spawningHandler the SpawningHandler object
   */
  public MovementHandler(Window window, Player player, SpawningHandler spawningHandler) {
    this.window = window;
    this.player = player;
    this.spawningHandler = spawningHandler;
    loadImages();
  }

  /**
   * Loads the images for the player.
   */
  public void loadImages(){
    imageMap.put("up_normal", window.loadImage("images/player/normal/mcW0.png"));
    imageMap.put("up_wings", window.loadImage("images/player/wings/mcW1.png"));
    imageMap.put("down_normal", window.loadImage("images/player/normal/mcS0.png"));
    imageMap.put("down_wings", window.loadImage("images/player/wings/mcS1.png"));
    imageMap.put("left_normal", window.loadImage("images/player/normal/mcA0.png"));
    imageMap.put("left_wings", window.loadImage("images/player/wings/mcA1.png"));
    imageMap.put("right_normal", window.loadImage("images/player/normal/mcD0.png"));
    imageMap.put("right_wings", window.loadImage("images/player/wings/mcD1.png"));
  }

  /**
   * Handles the movement of the player.
   *
   * @param key       the key pressed
   * @param keyCode   the key code
   * @param wingsTime whether wings time is active
   */
  public void handleMovement(char key, int keyCode, boolean wingsTime) {

    // UP
    if (keyCode == window.UP || key == 'w' || key == 'W') {
      PImage playerImage;
      if (!wingsTime) {
        playerImage = imageMap.get("up_normal");
        spawningHandler.onlyOneSpace();
        player.direction.y = -0.8f;
      } else {
        playerImage = imageMap.get("up_wings");
        player.direction.y = -2; // stop vertical movement
      }
      player.setPlayer(playerImage);
    }

    // DOWN
    if (keyCode == window.DOWN || key == 's' || key == 'S') {
      PImage playerImage;
      if (!wingsTime) {
        playerImage = imageMap.get("down_normal");
        spawningHandler.onlyOneSpace();
        player.direction.y = 0.8f;
      } else {
        playerImage = imageMap.get("down_wings");
        player.direction.y = 2; // stop vertical movement
      }
      player.setPlayer(playerImage);
    }

    // LEFT
    if (keyCode == window.LEFT || key == 'a' || key == 'A') {
      PImage playerImage;
      if (!wingsTime) {
        playerImage = imageMap.get("left_normal");
        spawningHandler.onlyOneSpace();
        player.direction.x = -0.8f;
      } else {
        playerImage = imageMap.get("left_wings");
        player.direction.x = -2; // stop horizontal movement
      }
      player.setPlayer(playerImage);
    }

    // RIGHT
    if (keyCode == window.RIGHT || key == 'd' || key == 'D') {
      PImage playerImage;
      if (!wingsTime) {
        playerImage = imageMap.get("right_normal");
        spawningHandler.onlyOneSpace();
        player.direction.x = 0.8f;
      } else {
        playerImage = imageMap.get("right_wings");
        player.direction.x = 2; // stop horizontal movement
      }
      player.setPlayer(playerImage);
    }
  }
}
