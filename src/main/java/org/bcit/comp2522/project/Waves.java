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

  Goblin goblin;

  private Window window;

  // List of skeletons, goblins, and trolls
  List<Skeleton> skeletonsList = new ArrayList<>();
  List<Goblin> goblinsList = new ArrayList<>();
  List<Troll> trollsList = new ArrayList<>();

  public Waves(int waveNumber, Window window) {
    this.window = window;
    this.waveNumber = waveNumber;
    goblin = new Goblin(100, 100, 50, window);

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


  /**
   * Constructor.
   *
   * @param window Window
   */




  /**
   * Spawns waves of enemies.
   *
   * @param waveNumber int
   * @param skeletons int
   * @param goblins int
   * @param trolls int
   */
  public void spawnWaves(int waveNumber, int skeletons, int goblins, int trolls) {

    skeletons += waveNumber; // Increase skeletons at a normal rate (every wave)
    goblins += waveNumber / 2; // Increase goblins at a slower rate (every 2 waves)
    trolls += waveNumber / 3; // Increase trolls at an even slower rate (every 3 waves)

    // Spawn waves
//    spawnSkeleton(skeletons);
    spawnGoblin(goblins);
//    spawnTroll(trolls);

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
    Skeleton[] skeletonArray = new Skeleton[skeletons];
    for (int i = 0; i < skeletons; i++) {
      skeletonArray[i] = new Skeleton();
      // Set the skeleton's position to a random location within the window
      skeletonArray[i].x_pos = (int) (Math.random() * window.width);
      skeletonArray[i].y_pos = (int) (Math.random() * window.height);
    }

    // Add the skeletons to the list
    skeletonsList.addAll(Arrays.asList(skeletonArray));
  }

  /**
   * Spawns goblins.
   *
   * @param goblins int
   */
  private void spawnGoblin(int goblins) {
    //draw goblin at a random location
    //call goblin.draw() in a loop until goblins is reached

    int x = 0;

    while(x < goblins) {
      goblin.draw();
      x++;
    }

    goblin.move();



  }

  /**
   * Spawns trolls.
   *
   * @param trolls int
   */
  private void spawnTroll(int trolls) {
    Troll[] trollArray = new Troll[trolls];
    for (int i = 0; i < trolls; i++) {
      trollArray[i] = new Troll();
      // Set the troll's position to a random location within the window
      trollArray[i].x_pos = (int) (Math.random() * window.width);
      trollArray[i].y_pos = (int) (Math.random() * window.height);
    }

    // Add the trolls to the list
    trollsList.addAll(Arrays.asList(trollArray));

  }

}
