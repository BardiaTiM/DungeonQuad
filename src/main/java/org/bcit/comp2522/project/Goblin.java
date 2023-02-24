package org.bcit.comp2522.project;

public class Goblin extends Waves{

  int axe_speed;
  int fire_rate;
  double axe_damage;

  int x_pos;
  int y_pos;

  public int Move(int x, int y){
    x_pos = x;
    y_pos = y;
    return 0;
  }
}
