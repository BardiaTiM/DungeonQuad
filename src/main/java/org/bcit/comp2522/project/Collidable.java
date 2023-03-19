package org.bcit.comp2522.project;

public interface Collidable {
  public boolean collidesWith(Sprite other);
  public void handleCollision(Sprite other);

}