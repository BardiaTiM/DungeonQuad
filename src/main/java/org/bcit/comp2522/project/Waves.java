package org.bcit.comp2522.project;


import java.util.concurrent.ConcurrentLinkedQueue;


/**
 * Waves class.
 * Contains all the enemies in a wave.
 *
 * @author Gathrean Dela Cruz
 * @author Bardia Timouri
 * @version 1.0
 */
public class Waves {


  int waveNumber;
  public ConcurrentLinkedQueue<Skeleton> skeletons =  new ConcurrentLinkedQueue<>();
  public ConcurrentLinkedQueue<Goblin> goblins = new ConcurrentLinkedQueue<>();
  public ConcurrentLinkedQueue<Troll> trolls = new ConcurrentLinkedQueue<>();
  private Window window;


  /**
   * Waves constructor.
   *
   * @param waveNumber wave number
   * @param window     window
   */
  public Waves(int waveNumber, Window window,
               ConcurrentLinkedQueue<Skeleton> skeletons,
               ConcurrentLinkedQueue<Goblin> goblins,
               ConcurrentLinkedQueue<Troll> trolls) {
    this.skeletons = skeletons;
    this.goblins = goblins;
    this.trolls = trolls;
    this.waveNumber = waveNumber;
    this.window = window;
  }


  /**
   * Waves constructor.
   *
   * @param waveNumber wave number
   */
  public Waves(int waveNumber) {
    this.waveNumber = waveNumber;
  }


//  public boolean isWaveOver() {
//    return getSkeletonCount() == 0 && getGoblinCount() == 0 && getTrollCount() == 0;
//  }


  /**
   * Returns the number of Skeletons in the game.
   *
   * @return skeletons.size()
   */
  public int getSkeletonCount() {
    return skeletons.size();
  }


  /**
   * Returns the number of Goblins in the game.
   *
   * @return goblins.size()
   */
  public int getGoblinCount() {
    return goblins.size();
  }


  /**
   * Returns the number of Trolls in the game.
   *
   * @return trolls.size()
   */
  public int getTrollCount() {
    return trolls.size();
  }




  /**
   * Calculates the amount of Skeletons to spawn based on teh current wave number.
   *
   * @return waveNumber
   */
  public float spawnSkeletonAmount() {
    return ((float)waveNumber);
  }




  /**
   * Calculates the amount of Goblins to spawn based on half the current wave number.
   *
   * @return
   */
  public float spawnGoblinAmount() {
    return ((float)waveNumber / 2);
  }




  /**
   * Calculates the amount of Trolls to spawn based on one-fifth of the current wave number.
   * @return
   */
  public float spawnTrollAmount() {
    return ((float)waveNumber / 5);
  }


//  public int totalEnemies(){
//    return (waveNumber + waveNumber / 2 + waveNumber / 5);
//  }


  /**
   * Returns the total number of enenmies.
   *
   * @return getGoblinCount() + getSkeletonCount() + getTrollCount()
   */
  public int totalEnemies() {
    return getGoblinCount() + getSkeletonCount() + getTrollCount();
  }
}
