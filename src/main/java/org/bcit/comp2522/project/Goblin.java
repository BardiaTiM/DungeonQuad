package org.bcit.comp2522.project;


/**
 * Goblin class.
 */
public class Goblin {

  float x = 2;

  float y = 2;

  float diameter = 1;

  // Goblin stats
  int axe_speed;
  int fire_rate;
  double axe_damage;

  // Goblin position
  int x_pos;
  int y_pos;

  // Goblin health
  boolean alive = true;

  private final Window window;


  public Goblin(float x, float y, float diameter, Window window) {
    this.x = x;
    this.y = y;
    this.diameter = diameter;
    this.window = window;
  }


  // Moving the Goblin
  public void move() {
    //move left and right slowly
    x += 5;
    if (x > 1000) {
      x = 0;
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
    window.ellipse(x, y, diameter, diameter);
  }

  public void draw() {
    this.drawPlayer(this.x, this.y, this.diameter);
  }

  // Checks to see if Goblin is still Alive
  public boolean isAlive() {
    return alive;
  }
}
