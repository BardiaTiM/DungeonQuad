package org.bcit.comp2522.project;

import processing.core.*;

public class Button {

  //Variables for button size and content
  float x, y, w, h;
  String text;

  //Displaying buttons in this window
  PApplet parent;

  Button(PApplet parent, float x, float y, float w, float h, String text) {
    this.parent = parent;
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.text = text;
  }

  //Formatting for the displayed buttons
  void display() {
    parent.stroke(0);
    parent.fill(45, 61, 65); // Button color
    parent.rect(x, y, w, h);
    parent.fill(176, 212, 222); // Text color
    parent.textSize(20);
    parent.textAlign(PConstants.CENTER, PConstants.CENTER);
    parent.text(text, x + w / 2, y + h / 2);
  }

  /*
  * This method checks if a click occurred inside a button
  * If it does, return true
  */
  boolean isClicked(float mx, float my) {
    if (mx >= x && mx <= x + w && my >= y && my <= y + h) {
      return true;
    } else {
      return false;
    }
  }

}