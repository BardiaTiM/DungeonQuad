package org.bcit.comp2522.project;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import processing.core.PImage;

public class SkeletonManager {
  private ConcurrentLinkedQueue<Skeleton> skeletons = new ConcurrentLinkedQueue<>();
  private Window window;

  public SkeletonManager(Window window) {
    this.window = window;
  }

  public void drawSkeletons() {
    for (Skeleton skeleton : skeletons) {
      skeleton.draw();
      skeleton.move();
    }
  }

  public void spawnSkeletons(int waveNumber) {
    PImage skeletonImage = window.loadImage("skeleton.png");
    int spawnSkeletonAmount = (int) new Waves(waveNumber).spawnSkeletonAmount();

    ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
    Runnable skeletonTask = new Runnable() {
      int skeletonCount = 0;

      @Override
      public void run() {
        skeletonCount++;
        if (skeletonCount < spawnSkeletonAmount) {
          executor.schedule(this, 1, TimeUnit.SECONDS);
          Skeleton skeleton = new Skeleton(100, 100, 100, true, window, skeletonImage);
          skeletons.add(skeleton);
        }
      }
    };

    executor.schedule(skeletonTask, 1, TimeUnit.SECONDS);
  }
}
