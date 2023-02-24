package org.bcit.comp2522.project;

/**
 * Troll class.
 */
public class Troll extends Waves{

  // Troll stats
  int rocks_speed;
  int fire_rate;
  double rocks_damage;

  // Troll position
  int x_pos;
  int y_pos;

  // Troll health
  boolean alive = true;

  // Moving the Troll
  public int Move(int x, int y){
    x_pos = x;
    y_pos = y;
    return 0;
  }

  // Troll's weapon of choice
  public void throwRock(int rocks_speed, int fire_rate, double rocks_damage){
    //throw rock
  }

  // Method to see if Troll is taking damage
  public void takeDamage(double damage) {
    // Update health status when troll takes damage
    if (damage >= 1.0) {
      alive = false;
    }
  }

  // Checks to see if Troll is still Alive
  public boolean isAlive() {
    return alive;
  }
}
