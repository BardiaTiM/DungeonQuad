package org.bcit.comp2522.project;

/**
 * Goblin class.
 */
public class Goblin extends Waves{

  // Goblin stats
  int axe_speed;
  int fire_rate;
  double axe_damage;

  // Goblin position
  int x_pos;
  int y_pos;

  // Goblin health
  boolean alive = true;

  // Moving the Goblin
  public int Move(int x, int y){
    x_pos = x;
    y_pos = y;
    return 0;
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

  // Checks to see if Goblin is still Alive
  public boolean isAlive() {
    return alive;
  }
}
