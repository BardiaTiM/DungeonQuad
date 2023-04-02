package org.bcit.comp2522.project;


import processing.core.PImage;


/**
 * This class creates the Background image and makes it look like its scrolling.
 *
 * @author Gathrean Dela Cruz
 */
public class Background {
  private final PImage image;
  private final Window window;
  float bgX = 0;
  float bgY = 0;
  float scrollSpeed = 1.5f;


  /**
   * Constructs the background image.
   *
   * @param window the game Window
   */
  public Background(Window window) {
    this.window = window;
    image = window.loadImage("images/deep_slate.jpg");
  }


  /**
   * Draws wings on the Player when wingsTime is activated.
   *
   * @param wingsTime activated at the start of a new wave
   * @param player    the current Player
   */
  public void draw(Boolean wingsTime, Player player) {
    if (wingsTime) {
      bgX = scrollSpeed * 2;
      bgY += scrollSpeed * 12;
    } else {
      bgY += scrollSpeed;
      bgX = player.direction.x;
      bgY -= player.direction.y;
    }


    // Tile the background image
    int offsetX = (int) (bgX % image.width - image.width);
    int offsetY = (int) (bgY % image.height - image.height);


    // Draw the background image
    for (int x = offsetX; x < window.width; x += image.width) {
      for (int y = offsetY; y < window.height; y += image.height) {
        window.image(image, x, y);
      }
    }
  }
}