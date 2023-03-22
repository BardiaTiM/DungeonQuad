package org.bcit.comp2522.project;

import org.bcit.comp2522.project.Sprite;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Walls extends PApplet{
  private float x;
  private float y;
  private float width;
  private float height;

  public Walls(float x, float y, float width, float height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  public void draw() {
    rect(x, y, width, height);
  }

  public boolean intersects(Sprite sprite) {
    return x < sprite.getX() + sprite.getWidth() &&
        x + width > sprite.getX() &&
        y < sprite.getY() + sprite.getHeight() &&
        y + height > sprite.getY();
  }
}
