package org.bcit.comp2522.project;


import processing.core.PApplet;
import processing.core.PImage;


/**
 * This class creates the Control menu screen. It displays the different key and mouse controls used to operate the game.
 */
public class Controls {
  private PApplet controlsApplet;
  private PImage controlsImage;




  /**
   * Constructs a new Controls menu screen.
   *
   * @param p the game Window
   * @param screenWidth  the screens width
   * @param screenHeight the screens height
   */
  public Controls(PApplet p, int screenWidth, int screenHeight) {
    this.controlsApplet = p;
    controlsImage = p.loadImage("controls.png");


  }




  /**
   * Draws the Control menu screen.
   */
  public void draw() {
    controlsApplet.image(controlsImage, 0, 0, controlsApplet.width, controlsApplet.height);
  }
}
