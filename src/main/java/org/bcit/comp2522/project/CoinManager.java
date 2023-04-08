package org.bcit.comp2522.project;


import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.List;

/**
 * This class creates and updates the game Coins.
 *
 * @author Will Ondrik
 */
public class CoinManager {

  private final PApplet parent;
  private final List<Coin> coins;
  private final Player player;
  private final PImage coinImage;
  public static int score;
  private int lastTimeSpawned;




  /**
   * CoinManager constructor.
   * Creates a CoinManager object.
   *
   * @param parent the game Window
   * @param player the Player object
   * @param coinImage the image for the Coin
   */
  public CoinManager(PApplet parent, Player player, PImage coinImage) {
    this.parent = parent;
    this.player = player;
    this.coinImage = coinImage;
    this.coins = new ArrayList<>();
    this.lastTimeSpawned = parent.millis();
  }




  /**
   * This method creates a Coin and adds it to the coins arraylist.
   */
  private void createCoin() {
    float x = parent.random(parent.width - 100);
    float y = parent.random(parent.height);
    Coin coin = new Coin(x, y, coinImage.height, coinImage.width, (Window) parent, coinImage, player);
    coins.add(coin);
  }




  /**
   * This method updates the state of the Coins in the Window.
   */
  public void update() {
    final int spawnTime = 10000; //10 seconds

    //Variable for the current time
    int currTime = parent.millis();

    //If it's time to spawn a new coin, there are no Coins in the Window and a new wave is occurring - Create a Coin.
    //Set lastTimeSpawned to equal the current time - This makes sure that Coins are spawning every 10 seconds.
    if (currTime - lastTimeSpawned > spawnTime && coins.isEmpty() && SpawningHandler.newWave) {
      createCoin();
      lastTimeSpawned = currTime;
    }


    //If the Coin is in the Window, the arraylist is checked for a collision with the Player.
    if (!coins.isEmpty()) {
      Coin coin = coins.get(0);
      coin.collide();


      //If a Coin is collected or isn't collected by the Player within 6 seconds, it's removed from the arraylist and Window.
      if (coin.isCollected() || coin.unspawn()) {
        coins.remove(0);


        //If the Coin is still in play, it is drawn.
      } else {
        coin.draw();
      }
    }
  }
}
