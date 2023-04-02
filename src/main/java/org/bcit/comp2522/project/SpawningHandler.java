package org.bcit.comp2522.project;


import processing.core.PImage;

import java.util.concurrent.*;

import static processing.awt.ShimAWT.loadImage;

public class SpawningHandler {
  private Window window;
  private Waves waves;
  private ConcurrentLinkedQueue<Skeleton> skeletons;
  private ConcurrentLinkedQueue<Goblin> goblins;
  private ConcurrentLinkedQueue<Troll> trolls;
  private boolean alreadyClicked = false;
  public static int waveNumber = 1;
  public static boolean newWave = false;


  public SpawningHandler(Window window, ConcurrentLinkedQueue<Skeleton> skeletons, ConcurrentLinkedQueue<Goblin> goblins, ConcurrentLinkedQueue<Troll> trolls, int waveNumber) {
    this.window = window;
    this.skeletons = skeletons;
    this.goblins = goblins;
    this.trolls = trolls;
    this.waveNumber = waveNumber;
  }

  public void onlyOneSpace() {
    if (skeletons.isEmpty() && goblins.isEmpty() && trolls.isEmpty()) {
      alreadyClicked = false;
    }
  }

  public void allEnemiesDead() {
    if (skeletons.isEmpty() && goblins.isEmpty() && trolls.isEmpty()) {
      newWave = false;
    } else {
      newWave = true;
    }
  }

  public void handleMonsterSpawning(char key) {
    if (key == ' ' && skeletons.isEmpty() && goblins.isEmpty() && trolls.isEmpty() && !alreadyClicked) {
      alreadyClicked = true;
      Window.score += 10;
      waveNumber += 1;
      window.wingsTime = true;
      PImage PlayerImage = window.loadImage("images/player/wings/mcW1.png");
      window.player.setPlayer(PlayerImage);
      waves = new Waves(waveNumber);
      ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);

      //Skeletons spawn time
      Runnable skeletonTask = new Runnable() {
        final PImage skeletonImage = window.loadImage("images/enemies/skeleton.png");

        float skeletonCount = 0;

        @Override
        public void run() {
          skeletonCount += 1;
          window.wingsTime = false;
          waves = new Waves(waveNumber);
          if (skeletonCount < waves.spawnSkeletonAmount()) {
            executor.schedule(this, 1, TimeUnit.SECONDS);
          }
          if (skeletonCount < waves.spawnSkeletonAmount()) {
            Skeleton skeleton = new Skeleton(100, -500, 100, window.skeletonHealth, true, window, skeletonImage);
            skeletons.add(skeleton);
          }
        }
      };

      executor.schedule(skeletonTask, 1, TimeUnit.SECONDS);

      //Goblins spawn time
      Runnable goblinTask = new Runnable() {
        final PImage goblinImage = window.loadImage("images/enemies/goblin.png");

        float goblinCount = 0;

        @Override
        public void run() {
          goblinCount += 1;
          waves = new Waves(waveNumber);
          if (goblinCount < waves.spawnGoblinAmount()) {
            executor.schedule(this, 2, TimeUnit.SECONDS);
          }
          if (goblinCount < waves.spawnGoblinAmount()) {
            Goblin goblin = new Goblin(100, -750, 150, window.goblinHealth, true, window, goblinImage);
            goblins.add(goblin);
          }

        }
      };

      executor.schedule(goblinTask, 1, TimeUnit.SECONDS);

      //Trolls spawn time
      Runnable trollTask = new Runnable() {
        final PImage trollImage = window.loadImage("images/enemies/troll.png");

        float trollCount = 0;

        @Override
        public void run() {
          trollCount += 1;
          waves = new Waves(waveNumber);
          if (trollCount < waves.spawnTrollAmount()) {
            executor.schedule(this, 4, TimeUnit.SECONDS);
          }
          if (trollCount < waves.spawnTrollAmount()) {
            Troll troll = new Troll(100, -1000, 200, window.goblinHealth, true, window, trollImage);
            trolls.add(troll);
          }
        }
      };

      executor.schedule(trollTask, 1, TimeUnit.SECONDS);
    }
  }

}
