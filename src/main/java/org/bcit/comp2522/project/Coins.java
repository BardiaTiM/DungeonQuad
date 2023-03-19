package org.bcit.comp2522.project;
import java.util.ArrayList;
import java.util.Random;

public class Coins implements Collidable {
  // x and y positions of the coin
  private int xPos;
  private int yPos;
  // boolean indicating whether the coin has been collected or not
  private boolean isCollected;
  // boolean indicating whether the coin is a bonus or not
  private boolean bonus;
  // width and height of the screen
  private int screenWidth = 600;
  private int screenHeight = 800;
  // width and height of the coin
  private int coinWidth = 50;
  private int coinHeight = 50;
  // number of coins collected
  private int collectedCoins = 0;

  public Coins(int xPos, int yPos, boolean isCollected, boolean bonus) {
    this.xPos = xPos;
    this.yPos = yPos;
    this.isCollected = isCollected;
    this.bonus = bonus;
  }

  @Override
  public boolean collidesWith(Sprite other) {
    if (other instanceof Sprite) {
      Sprite sprite = (Sprite) other;
      // get coordinates of the sprite
      int spriteLeft = sprite.getX();
      int spriteRight = spriteLeft + sprite.getWidth();
      int spriteTop = sprite.getY();
      int spriteBottom = spriteTop + sprite.getHeight();
      // get coordinates of the coin
      int coinLeft = xPos;
      int coinRight = coinLeft + coinWidth;
      int coinTop = yPos;
      int coinBottom = coinTop + coinHeight;
      // check if the coin collides with the sprite
      return coinRight >= spriteLeft && coinLeft <= spriteRight && coinBottom >= spriteTop && coinTop <= spriteBottom;
    }
    return false;
  }

  @Override
  public void handleCollision(Sprite other) {
    if (other instanceof Sprite && !isCollected) {
      isCollected = true;
      collectedCoins++;

      // add coin or bonus to sprite depending on whether the coin is a bonus or not
      // need to use the addcoin method to add to the players score
      // addbonus can be used for a temporary powerup/bonus - needs to be implemented
      if (bonus) {
        ((Sprite) other).addBonus();
      } else {
        ((Sprite) other).addCoin();
      }
    }
  }


  // getter method for isCollected field
  public boolean isCollected() {
    return isCollected;
  }

  // getter method for collectedCoins field
  public int getCollectedCoins() {
    return collectedCoins;
  }
}
