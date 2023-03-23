//package org.bcit.comp2522.project;
//
//import processing.core.PApplet;
//
//public class Walls extends PApplet{
//  private final float x;
//  private final float y;
//  private final float width;
//  private final float height;
//
//  public Walls(float x, float y, float width, float height) {
//    this.x = x;
//    this.y = y;
//    this.width = width;
//    this.height = height;
//  }
//
//  public void draw() {
//    rect(x, y, width, height);
//  }
//
//  public boolean intersects(Sprite sprite) {
//    return x < Sprite.getX() + sprite.getWidth() &&
//        x + width > Sprite.getX() &&
//        y < Sprite.getY() + sprite.getHeight() &&
//        y + height > Sprite.getY();
//  }
//}
