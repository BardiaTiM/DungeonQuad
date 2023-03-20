package org.bcit.comp2522.project;

import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Skeleton class.
 */
public class Skeleton {

  float x = 2;

  float y = 2;

  float diameter = 1;

  private Skeleton[] skeleton;

  // Goblin stats
  int axe_speed;
  int fire_rate;
  double axe_damage;

  // Goblin position
  int x_pos;
  int y_pos;
  int id;

  // Goblin health
  boolean alive = true;

  // Goblin direction
  boolean movingRight = true;

  private final Window window;


  public Skeleton(float x, float y, float diameter,int id, Skeleton[] skeleton, Window window) {
    this.x = x;
    this.y = y;
    this.diameter = diameter;
    this.window = window;
    this.skeleton = skeleton;
    this.id = id;
  }

  // Moving the Goblin
  public void move() {
    // Goblin moves right by default
    if (movingRight) {
      // Move Goblin to the right
      if (this.x_pos + 4 < window.getWidth()) {
        this.x_pos += 4;
        this.x = x_pos;
      }
      // Change direction when Goblin reaches the right side
      else {
        movingRight = false;
      }
    } else {
      // Move Goblin to the left
      if (this.x_pos - 4 > 0) {
        this.x_pos -= 4;
        this.x = x_pos;
      }
      // Change direction when Goblin reaches the left side
      else {
        movingRight = true;
      }
    }
  }





  // Goblin's weapon of choice
  public void throwAxe(int axe_speed, int fire_rate, double axe_damage){
    //throw axe
  }

  // Method to see if Goblin is taking damage
  public void takeDamage(double damage) {
    // Update health status when goblin takes damage
    if (damage >= 1.0) {
      alive = false;
    }
  }

  public void drawPlayer(float x, float y, float diameter) {
    PImage skeletonImage = window.loadImage("skeleton.png"); // replace "goblin.png" with the filename of your image
    window.image(skeletonImage, x - diameter / 2, y - diameter / 2, diameter, diameter);
  }

  public void draw() {
    this.drawPlayer(this.x, this.y, this.diameter);
  }

  // Checks to see if Goblin is still Alive
  public boolean isAlive() {
    return alive;
  }
}
