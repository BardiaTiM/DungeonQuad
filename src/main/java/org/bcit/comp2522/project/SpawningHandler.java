package org.bcit.comp2522.project;

import org.bcit.comp2522.project.Goblin;
import org.bcit.comp2522.project.Skeleton;
import org.bcit.comp2522.project.Troll;
import org.bcit.comp2522.project.Window;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.concurrent.*;

import static processing.awt.ShimAWT.loadImage;

public class SpawningHandler {
  private Window window;
  private Waves waves;
  private ConcurrentLinkedQueue<Skeleton> skeletons;
  private ConcurrentLinkedQueue<Goblin> goblins;
  private ConcurrentLinkedQueue<Troll> trolls;
  public static int waveNumber = 1;
  public static boolean wingsTime = false;

  public SpawningHandler(Window window, ConcurrentLinkedQueue<Skeleton> skeletons, ConcurrentLinkedQueue<Goblin> goblins, ConcurrentLinkedQueue<Troll> trolls, int waveNumber) {
    this.window = window;
    this.skeletons = skeletons;
    this.goblins = goblins;
    this.trolls = trolls;
    this.waveNumber = waveNumber;
  }

  public void handleMonsterSpawning(char key) {

    if (key == ' ' && skeletons.isEmpty() && goblins.isEmpty() && trolls.isEmpty()) {

      waveNumber += 1;
      wingsTime = true;
      waves = new Waves(waveNumber);
      ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);

      //Skeletons spawn time
      Runnable skeletonTask = new Runnable() {
        final PImage skeletonImage = window.loadImage("images/enemies/skeleton.png");


        float skeletonCount = 0;

        @Override
        public void run() {
          skeletonCount += 1;
          wingsTime = false;
          waves = new Waves(waveNumber);
          if (skeletonCount < waves.spawnSkeletonAmount()) {
            executor.schedule(this, 1, TimeUnit.SECONDS);
          }
          if (skeletonCount < waves.spawnSkeletonAmount()) {
            Skeleton skeleton = new Skeleton(100, -500, 100, true, window, skeletonImage);
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
            Goblin goblin = new Goblin(100, -750, 150, true, window, goblinImage);
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
            Troll troll = new Troll(100, -1000, 200, true, window, trollImage);
            trolls.add(troll);
          }
        }
      };

      executor.schedule(trollTask, 1, TimeUnit.SECONDS);
    }
  }

}

