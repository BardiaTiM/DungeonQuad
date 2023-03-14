package org.bcit.comp2522.project;

import java.util.ArrayList;
import java.util.Random;

public class CoinGenerator {
  private int screenWidth = 600;
  private int screenHeight = 800;
  private int coinWidth = 50;
  private int coinHeight = 50;

  public CoinGenerator(int screenHeight, int screenWidth, int coinHeight, int coinWidth) {
    this.screenHeight = screenHeight;
    this.screenWidth = screenWidth;
    this.coinHeight = coinHeight;
    this.coinWidth = coinWidth;
  }

  // static method that generates an ArrayList of coins with random positions and a specified number
  public ArrayList<Coins> generateCoins(int count) {
    ArrayList<Coins> coins = new ArrayList<>();
    Random rand = new Random();

    for (int i = 0; i < count; i++) {
      // generate random x and y positions for the coin within the screen bounds
      int xPos = rand.nextInt(screenWidth - coinWidth);
      int yPos = rand.nextInt(screenHeight - coinHeight);
      // randomly decide if the coin is a bonus
      boolean bonus = rand.nextBoolean();
      Coins coin = new Coins(xPos, yPos, false, bonus);
      coins.add(coin);
    }

    return coins;
  }
}
