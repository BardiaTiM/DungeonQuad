package org.bcit.comp2522.project;

public class Wall implements Collidable {
  protected int xPos;
  protected int yPos;
  protected int wallHeight = 500;
  protected int wallWidth = 500;


  public Wall (int xPos, int yPos, int wallWidth, int wallHeight){
    this.xPos = xPos;
    this.yPos = yPos;
    this.wallHeight = wallHeight;
    this.wallWidth = wallWidth;
  }

  private Rectangle getGrid(){
    return new Rectangle(xPos, yPos, wallWidth, wallHeight);
  }


  /**
   * This checks if the other sprite collides with the game walls.
   * @param other
   * @return true/false
   */
  @Override
  public boolean collidesWith(Sprite other){


  }


  @Override
  public void handleCollision(Sprite other){

  }



}