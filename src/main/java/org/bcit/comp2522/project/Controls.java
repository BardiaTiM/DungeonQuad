package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PImage;

public class Controls {

  private PApplet controlsApplet;
  private PImage controlsImage;

  //Grabbing an image to display when the game controls button is clicked
  public Controls(PApplet p, int screenWidth, int screenHeight) {
    this.controlsApplet = p;
    controlsImage = p.loadImage("controls.png");

  }

  //Draw the controls image
  public void draw() {
    controlsApplet.image(controlsImage, 0, 0, controlsApplet.width, controlsApplet.height);

  }
}