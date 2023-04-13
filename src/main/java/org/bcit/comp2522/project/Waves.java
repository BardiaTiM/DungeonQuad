package org.bcit.comp2522.project;

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

  /**
   * Waves constructor.
   *
   * @param waveNumber wave number
   */
  public Waves(int waveNumber) {
    this.waveNumber = waveNumber;
  }

  /**
   * Returns the number of Skeletons in the game.
   *
   * @return skeletons.size()
   */
  public int getSkeletonCount() {
    return Window.skeletons.size();
  }

  /**
   * Returns the number of Goblins in the game.
   *
   * @return goblins.size()
   */
  public int getGoblinCount() {
    return Window.goblins.size();
  }

  /**
   * Returns the number of Trolls in the game.
   *
   * @return trolls.size()
   */
  public int getTrollCount() {
    return Window.trolls.size();
  }

  /**
   * Calculates the amount of Skeletons to spawn based on teh current wave number.
   *
   * @return waveNumber
   */
  public float spawnSkeletonAmount() {
    return ((float) waveNumber);
  }

  /**
   * Calculates the amount of Goblins to spawn based on half the current wave number.
   *
   * @return the waveNumber / 2
   */
  public float spawnGoblinAmount() {
    return ((float) waveNumber / 2);
  }

  /**
   * Calculates the amount of Trolls to spawn based on one-fifth of the current wave number.
   *
   * @return the waveNumber / 5
   */
  public float spawnTrollAmount() {
    return ((float) waveNumber / 5);
  }

  /**
   * Returns the total number of enemies.
   *
   * @return getGoblinCount() + getSkeletonCount() + getTrollCount()
   */
  public int totalEnemies() {
    return getGoblinCount() + getSkeletonCount() + getTrollCount();
  }
}
