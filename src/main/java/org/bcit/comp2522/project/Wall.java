//package org.bcit.comp2522.project;
//
//import java.awt.*;
//
//public class Wall implements Collidable {
//  protected int xPos;
//  protected int yPos;
//  protected int wallHeight = 500;
//  protected int wallWidth = 500;
//
//  public Wall(int xPos, int yPos, int wallWidth, int wallHeight) {
//    this.xPos = xPos;
//    this.yPos = yPos;
//    this.wallHeight = wallHeight;
//    this.wallWidth = wallWidth;
//  }
//
//  private Rectangle getGrid() {
//    return new Rectangle(xPos, yPos, wallWidth, wallHeight);
//  }
//
//  @Override
//  public boolean collidesWith(Sprite other) {
//    Rectangle wall = getGrid();
//    Rectangle obj = other.getBoundingBox();
//    return wall.intersects(obj);
//  }
//
//  @Override
//  public void handleCollision(Sprite other) {
//    if (collidesWith(other)) {
//      other.setVelocity(0, 0);
//    }
//  }
//}
