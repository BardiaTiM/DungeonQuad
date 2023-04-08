package org.bcit.comp2522.project;

import processing.core.*;

/**
 * This class is responsible for creating new Button objects.
 */
public class Button {
  /**
   * Variables for button size and content.
   */
  float x, y, w, h;

  /**
   * The text of the Button.
   */
  String text;

  /**
   * The PApplet window that the button will be displayed on.
   */
  Window window;

  /**
   * Constructs a Button object with the specified parameters.
   *
   * @param window The PApplet window that the button will be displayed on.
   * @param x      The x-coordinate of the Button
   * @param y      The y-coordinate of the Button
   * @param w      The width of the Button
   * @param h      The height of the Button
   * @param text   The text of the Button
   */
  Button(Window window, float x, float y, float w, float h, String text) {
    this.window = window;
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
    window.stroke(0);
    window.fill(45, 61, 65); // Button color
    window.rect(x, y, w, h);
    window.fill(176, 212, 222); // Text color
    window.textSize(20);
    window.textAlign(PConstants.CENTER, PConstants.CENTER);
    window.text(text, x + w / 2, y + h / 2);
  }

  /**
   * Checks if a mouse click occurred inside a Button.
   *
   * @param mx The x-coordinate of the Mouse
   * @param my The y-coordinate of the Mouse
   * @return true if the mouse click occurred within the Button's bounds
   */
  boolean isClicked(float mx, float my) {
    return mx >= x && mx <= x + w && my >= y && my <= y + h;
  }
}