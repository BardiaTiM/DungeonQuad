package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CoinManager {
  private PApplet parent;
  private List<Coin> coins;
  private int coinCounter;
  private Sprite player;
  private PImage coinImage;

  public CoinManager(PApplet parent, Sprite player, PImage coinImage) {
    this.parent = parent;
    this.player = player;
    this.coinImage = coinImage;
    this.coins = new ArrayList<>();
    this.coinCounter = 0;
  }

  private void createCoin() {
    float x = parent.random(parent.width);
    float y = parent.random(parent.height);
    Coin coin = new Coin(x, y, coinImage.height, coinImage.width, (Window) parent, coinImage, player);
    coins.add(coin);
  }

  public void update() {
    if (coinCounter < 10 && coins.size() < 6) {
      createCoin();
      coinCounter++;
    }

    Iterator<Coin> coinIterator = coins.iterator();
    while (coinIterator.hasNext()) {
      Coin coin = coinIterator.next();
      coin.collide();

      if (coin.isCollected || coin.unspawn()) {
        coinIterator.remove();
      } else {
        coin.draw();
      }
    }
  }
}
