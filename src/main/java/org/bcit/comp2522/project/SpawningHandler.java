package org.bcit.comp2522.project;

import java.util.concurrent.*;
import processing.core.PImage;

/**
 * This class handles the spawning of Enemies in the Window.
 * It uses a combination of ConcurrentLinkedQueues and
 * ScheduledExecutorService to handle the enemy spawning.
 *
 * @author Bardia Timouri
 * @author Will Ondrik
 */
public class SpawningHandler {
  /**
   * The window.
   */
  private final Window window;

  /**
   * The waves.
   */
  private Waves waves;

  /**
   * The skeletons.
   */
  private final ConcurrentLinkedQueue<Skeleton> skeletons;

  /**
   * The goblins.
   */
  private final ConcurrentLinkedQueue<Goblin> goblins;

  /**
   * The trolls.
   */
  private final ConcurrentLinkedQueue<Troll> trolls;

  /**
   * The already clicked.
   */
  private boolean alreadyClicked = false;

  /**
   * The wave number.
   */
  public static int waveNumber;

  /**
   * The new wave.
   */
  public static boolean newWave = false;


  /**
   * SpawningHandler Constructor.
   *
   * @param window     the window
   * @param skeletons  the skeletons
   * @param goblins    the goblins
   * @param trolls     the trolls
   * @param waveNumber the wave number
   */
  public SpawningHandler(Window window, ConcurrentLinkedQueue<Skeleton> skeletons,
                         ConcurrentLinkedQueue<Goblin> goblins,
                         ConcurrentLinkedQueue<Troll> trolls, int waveNumber) {
    this.window = window;
    this.skeletons = skeletons;
    this.goblins = goblins;
    this.trolls = trolls;
    SpawningHandler.waveNumber = waveNumber;
  }

  /**
   * Sets alreadyClicked to false if all enemies have been killed.
   */
  public void onlyOneSpace() {
    if (skeletons.isEmpty() && goblins.isEmpty() && trolls.isEmpty()) {
      alreadyClicked = false;
    }
  }

  /**
   * Sets newWave to true if there are no enemies.
   */
  public void allEnemiesDead() {
    newWave = !skeletons.isEmpty() || !goblins.isEmpty() || !trolls.isEmpty();
  }

  /**
   * Handles the spawning of the enemies when the space bar is pressed.
   *
   * @param key the key
   */
  public void handleMonsterSpawning(char key) {
    if (key == ' ' && skeletons.isEmpty() && goblins.isEmpty()
        && trolls.isEmpty() && !alreadyClicked) {
      spawnSettings();
      ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);

      // Runnable object for spawning Skeletons.
      Runnable skeletonTask = new Runnable() {
        final PImage skeletonImage = window.loadImage("images/enemies/skeleton.png");
        float skeletonCount = 0;

        /**
         * Spawns Skeletons and schedules the next wave.
         */
        @Override
        public void run() {
          skeletonCount += 1;
          window.wingsTime = false;
          waves = new Waves(waveNumber);
          if (skeletonCount < waves.spawnSkeletonAmount()) {
            executor.schedule(this, 1, TimeUnit.SECONDS);
          }
          if (skeletonCount < waves.spawnSkeletonAmount()) {
            Skeleton skeleton = new Skeleton(100, -500, 100, window.skeletonHealth,
                true, window, skeletonImage);
            skeletons.add(skeleton);
          }
        }
      };

      // Schedules the Skeleton wave to be spawned
      executor.schedule(skeletonTask, 1, TimeUnit.SECONDS);

      // Runnable object for spawning Goblins.
      Runnable goblinTask = new Runnable() {
        final PImage goblinImage = window.loadImage("images/enemies/goblin.png");
        float goblinCount = 0;

        /**
         * Spawns Goblins and schedules the next wave.
         */
        @Override
        public void run() {
          goblinCount += 1;
          waves = new Waves(waveNumber);
          if (goblinCount < waves.spawnGoblinAmount()) {
            executor.schedule(this, 2, TimeUnit.SECONDS);
          }
          if (goblinCount < waves.spawnGoblinAmount()) {
            Goblin goblin = new Goblin(100, -750, 150, window.goblinHealth,
                true, window, goblinImage);
            goblins.add(goblin);
          }

        }
      };

      //Schedules the Goblin wave to be spawned
      executor.schedule(goblinTask, 1, TimeUnit.SECONDS);


      // Runnable object for spawning Trolls.
      Runnable trollTask = new Runnable() {
        final PImage trollImage = window.loadImage("images/enemies/troll.png");
        float trollCount = 0;


        /**
         * Spawns Trolls and schedules the next wave.
         */
        @Override
        public void run() {
          trollCount += 1;
          waves = new Waves(waveNumber);
          if (trollCount < waves.spawnTrollAmount()) {
            executor.schedule(this, 4, TimeUnit.SECONDS);
          }
          if (trollCount < waves.spawnTrollAmount()) {
            Troll troll = new Troll(100, -1000, 200, window.goblinHealth,
                true, window, trollImage);
            trolls.add(troll);
          }
        }
      };

      //Schedules the Troll wave to be spawned
      executor.schedule(trollTask, 1, TimeUnit.SECONDS);
    }
  }

  /**
   * Sets the spawn settings.
   */
  public void spawnSettings() {
    alreadyClicked = true;
    Window.score += 10;
    waveNumber += 1;
    window.wingsTime = true;
    PImage PlayerImage = window.loadImage("images/player/wings/mcW1.png");
    window.player.setPlayer(PlayerImage);
    waves = new Waves(waveNumber);
  }
}
