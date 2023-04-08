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
   * @param x The x-coordinate of the Button
   * @param y The y-coordinate of the Button
   * @param w The width of the Button
   * @param h The height of the Button
   * @param text The text of the Button
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
   * Checks if a mouse click occurred inside a Button.
   *
   * @param mx The x-coordinate of the Mouse
   * @param my The y-coordinate of the Mouse
   * @return true if the mouse click occurred within the Button's bounds
   */
  boolean isClicked(float mx, float my) {
    if (mx >= x && mx <= x + w && my >= y && my <= y + h) {
      return true;
    } else {
      return false;
    }
  }
}