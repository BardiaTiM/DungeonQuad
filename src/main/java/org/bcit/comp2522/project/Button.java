package org.bcit.comp2522.project;

import processing.core.*;


/**
 * This class is responsible for creating new Button objects.
 */
public class Button {

  //Variables for button size and content
  float x, y, w, h;
  String text;

  //Displaying buttons in this window
  PApplet parent;


  /**
   * Constructs a Button object with the specified parameters.
   *
   * @param parent The PApplet window that the button will be displayed on.
   * @param x The x-coordinate of the Button's top-left corner
   * @param y The y-coordinate of teh butons
   * @param w
   * @param h
   * @param text
   */
  Button(PApplet parent, float x, float y, float w, float h, String text) {
    this.parent = parent;
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.text = text;
  }


  /**
   * Displays the Button on the screen with the specified dimensions and text.
   *
   * Sets the stroke and fill colors for the Button, and sets the alignment for the Button text.
   */
  void display() {
    parent.stroke(0);
    parent.fill(45, 61, 65); // Button color
    parent.rect(x, y, w, h);
    parent.fill(176, 212, 222); // Text color
    parent.textSize(20);
    parent.textAlign(PConstants.CENTER, PConstants.CENTER);
    parent.text(text, x + w / 2, y + h / 2);
  }


  /**
   * Checks if a mouse click occurred inside of a Button.
   *
   * @param mx
   * @param my
   * @return true/false
   */
  boolean isClicked(float mx, float my) {
    if (mx >= x && mx <= x + w && my >= y && my <= y + h) {
      return true;
    } else {
      return false;
    }
  }
}