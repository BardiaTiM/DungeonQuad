package org.bcit.comp2522.project;

public interface Moveable {

  public Player player;
  public ArrayList<Enemy> Enemies;

  public void moveUP();
  public void moveDown();
  public void moveRight();
  public void moveLeft();
}
