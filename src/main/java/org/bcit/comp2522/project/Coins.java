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

  public Coins(int xPos, int yPos, boolean isCollected, boolean bonus, int screenWidth, int screenHeight, int coinWidth, int coinHeight) {
    this.xPos = xPos;
    this.yPos = yPos;
    this.isCollected = isCollected;
    this.bonus = bonus;
    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;
    this.coinWidth = coinWidth;
    this.coinHeight = coinHeight;
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
      if (bonus) {
        ((Sprite) other).addBonus();
      } else {
        ((Sprite) other).addCoin();
      }
    }
  }

  // static method that generates an ArrayList of coins with random positions and a specified number
  public static ArrayList<Coins> generateCoins(int count, int screenWidth, int screenHeight, int coinWidth, int coinHeight) {
    ArrayList<Coins> coins = new ArrayList<>();
    Random rand = new Random();

    for (int i = 0; i < count; i++) {
      // generate random x and y positions for the coin within the screen bounds
      int xPos = rand.nextInt(screenWidth - coinWidth);
      int yPos = rand.nextInt(screenHeight - coinHeight);
      // randomly decide if the coin is a bonus
      boolean bonus = rand.nextBoolean();
      Coins coin = new Coins(xPos, yPos, false, bonus, screenWidth, screenHeight, coinWidth, coinHeight);
      coins.add(coin);
    }

    return coins;
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
