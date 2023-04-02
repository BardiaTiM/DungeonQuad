package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class CoinManager {

  private PApplet parent;
  private List<Coin> coins;
  private Player player;
  private PImage coinImage;
  public static int score;
  private int spawnTime = 10000; //10 seconds
  private int lastTimeSpawned;

  public CoinManager(PApplet parent, Player player, PImage coinImage) {
    this.parent = parent;
    this.player = player;
    this.coinImage = coinImage;
    this.coins = new ArrayList<>();
    this.lastTimeSpawned = parent.millis();

  }

  private void createCoin() {
    float x = parent.random(parent.width - 100);
    float y = parent.random(parent.height);
    Coin coin = new Coin(x, y, coinImage.height, coinImage.width, (Window) parent, coinImage, player);
    coins.add(coin);
  }


  public void update() {
    int currTime = parent.millis();

    if (currTime - lastTimeSpawned > spawnTime && coins.isEmpty() && SpawningHandler.newWave) {
      createCoin();
      lastTimeSpawned = currTime;
    }

    if (!coins.isEmpty()) {
      Coin coin = coins.get(0);
      coin.collide();


      if (coin.isCollected() || coin.unspawn()) {
        coins.remove(0);

      } else {
        coin.draw();
      }
    }
  }
}
