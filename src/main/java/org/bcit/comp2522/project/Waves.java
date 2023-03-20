package org.bcit.comp2522.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Waves class.
 * Contains all the enemies in a wave.
 *
 * @author Gathrean Dela Cruz
 * @version 1.0
 */
public class Waves {

  int waveNumber;
  List<Waves> waves;

  static Goblin[] goblin = new Goblin[1];

  static Skeleton[] skeleton = new Skeleton[1];

  static Troll[] troll = new Troll[1];

  private Window window;

  // List of skeletons, goblins, and trolls
  List<Skeleton> skeletonsList = new ArrayList<>();
  List<Goblin> goblinsList = new ArrayList<>();
  List<Troll> trollsList = new ArrayList<>();

  public Waves(int waveNumber, Window window) {
    this.window = window;
    this.waveNumber = waveNumber;

    for (int i = 0; i < 1; i++) {
      goblin[i] = new Goblin(100, 100, 100, i, goblin, window);
    }

    for (int i = 0; i < 1; i++) {
      skeleton[i] = new Skeleton(100, 300, 100, i, skeleton, window);
    }

    for (int i = 0; i < 1; i++) {
      troll[i] = new Troll(100, 500, 100, i, troll, window);
    }

  }

  public static Goblin[] getGoblins() {
    return goblin;
  }

  public static Skeleton[] getSkeletons() {
    return skeleton;
  }

  public static Troll[] getTrolls() {
    return troll;
  }

  public List<Waves> getWaves() {
    return waves;
  }

  public void setWaves(List<Waves> waves) {
    this.waves = waves;
  }

  public int getWaveNumber() {
    return waveNumber;
  }

  public void setWaveNumber(int waveNumber) {
    this.waveNumber = waveNumber;
  }

  // Number of enemies in each wave
  int skeletons = 10; // EASY
  int goblins = 1; // MEDIUM
  int trolls = 3; // HARD
  int enemiesRemaining = skeletons + goblins + trolls; // Total number of enemies in a wave


  public void spawnWaves(int waveNumber, int skeletons, int goblins, int trolls) {

//    skeletons += waveNumber; // Increase skeletons at a normal rate (every wave)
//    goblins += waveNumber / 2; // Increase goblins at a slower rate (every 2 waves)
//    trolls += waveNumber / 3; // Increase trolls at an even slower rate (every 3 waves)

    // Spawn waves
    spawnSkeleton(skeletons);
    spawnGoblin(goblins);
    spawnTroll(trolls);

//    while (enemiesRemaining > 0) {
//      // Check if any enemies are defeated
//      if (allEnemiesDefeated()) {
//        waveNumber++;
//        spawnWaves(waveNumber, skeletons, goblins, trolls); // RECURSIVE CALL -ean
//        return;
//      }
//
////      // Pause between waves
////      try {
////        Thread.sleep(1000); // wait 1 second
////      } catch (InterruptedException e) {
////        e.printStackTrace();
////      }
//    }
  }

  /**
   * Checks if all enemies are defeated.
   *
   * @return boolean
   */
  public boolean allEnemiesDefeated() {
    for (Skeleton skeleton : skeletonsList) {
      if (skeleton.isAlive()) {
        return false;
      }
    }

    for (Goblin goblin : goblinsList) {
      if (goblin.isAlive()) {
        return false;
      }
    }

    for (Troll troll : trollsList) {
      if (troll.isAlive()) {
        return false;
      }
    }

    return true; // All enemies are defeated
  }

  /**
   * Spawns skeletons.
   *
   * @param skeletons int
   */
  private void spawnSkeleton(int skeletons) {
    int x = 0;

    while(x < skeletons) {
      skeleton[x].draw();
      x++;
    }
    skeleton[0].move();
  }

  /**
   * Spawns goblins.
   *
   * @param goblins int
   */
  private void spawnGoblin(int goblins) {
    int x = 0;

    while(x < goblins) {
      goblin[x].draw();
      x++;
    }
    goblin[0].move();
  }



  /**
   * Spawns trolls.
   *
   * @param trolls int
   */
  private void spawnTroll(int trolls) {
    int x = 0;

    while (x < trolls) {
      troll[x].draw();
      x++;
    }
    troll[0].move();
  }

}
