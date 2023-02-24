package org.bcit.comp2522.project;

/**
 * Troll class.
 */
public class Troll extends Waves{
  int rocks_speed;
  int fire_rate;
  double rocks_damage;

  int x_pos;
  int y_pos;

  public int Move(int x, int y){
    x_pos = x;
    y_pos = y;
    return 0;
  }

  public void throwRock(int rocks_speed, int fire_rate, double rocks_damage){
    //throw rock
  }
}
