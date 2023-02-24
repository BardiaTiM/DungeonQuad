package org.bcit.comp2522.project;

/**
 * Skeleton class.
 */
public class Skeleton extends Waves{

  // Skeleton stats
  int arrow_speed;
  int fire_rate;
  double arrow_damage;

  // Skeleton position
  int x_pos;
  int y_pos;

  // Skeleton health
  boolean alive = true;

  // Moving the skeleton
  public int Move(int x, int y){
    x_pos = x;
    y_pos = y;
    return 0;
  }

  // Skeleton's weapon of choice
  public void shootArrow(int arrow_speed, int fire_rate, double arrow_damage){
    //throw arrow
  }

  // Method to see if skeleton is taking damage
  public void takeDamage(double damage) {
    // Update health status when skeleton takes damage
    if (damage >= 1.0) {
      alive = false;
    }
  }

  // Checks to see if skeleton is still Alive
  public boolean isAlive() {
    return alive;
  }
}
