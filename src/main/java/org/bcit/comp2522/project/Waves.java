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
  public Waves(int waveNumber, Window window, ConcurrentLinkedQueue<Skeleton> skeletons, ConcurrentLinkedQueue<Goblin> goblins, ConcurrentLinkedQueue<Troll> trolls) {
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

  public int getSkeletonCount() {
    return skeletons.size();
  }
  public int getGoblinCount() {
    return goblins.size();
  }
  public int getTrollCount() {
    return trolls.size();
  }

//  public void increaseWaveNumber() {
//    if (getSkeletonCount() == 0 && getGoblinCount() == 0 && getTrollCount() == 0) {
//      waveNumber++;
//    }
//  }

  public float spawnSkeletonAmount() {
    return ((float)waveNumber);
  }
  public float spawnGoblinAmount() {
    return ((float)waveNumber / 2);
  }
  public float spawnTrollAmount() {
    return ((float)waveNumber / 5);
  }

//  public int totalEnemies(){
//    return (waveNumber + waveNumber / 2 + waveNumber / 5);
//  }
  public int totalEnemies() {
    return getGoblinCount() + getSkeletonCount() + getTrollCount();
  }
}
