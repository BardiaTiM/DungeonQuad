/*package org.bcit.comp2522.project;

import org.bcit.comp2522.project.*;

import java.util.concurrent.Executor;
import java.util.concurrent.ConcurrentLinkedQueue;

public class WavesManager {
  private Waves waves;
  private Window window;
  private int waveNumber;
  private Executor executor;

  private ConcurrentLinkedQueue<Skeleton> skeletons;
  private ConcurrentLinkedQueue<Goblin> goblins;
  private ConcurrentLinkedQueue<Troll> trolls;

  public WavesManager(Window window, Executor executor, ConcurrentLinkedQueue<Skeleton> skeletons, ConcurrentLinkedQueue<Goblin> goblins, ConcurrentLinkedQueue<Troll> trolls) {
    this.window = window;
    this.executor = executor;
    this.skeletons = skeletons;
    this.goblins = goblins;
    this.trolls = trolls;
    waveNumber = 1;
    waves = new Waves(waveNumber, window, skeletons, goblins, trolls);
  }


  public void startWave() {
    waveNumber += 1;
    waves = new Waves(waveNumber);
    spawnSkeletons();
    spawnGoblins();
    spawnTrolls();
  }

  private void spawnSkeletons() {
    float spawnAmount = waves.spawnSkeletonAmount();
    for (int i = 0; i < spawnAmount; i++) {
      Skeleton skeleton = new Skeleton(window) ;
      skeletons.add(skeleton);
    }
  }

  private void spawnGoblins() {
    float spawnAmount = waves.spawnGoblinAmount();
    for (int i = 0; i < spawnAmount; i++) {
      Goblin goblin = new Goblin(window);
      goblins.add(goblin);
    }
  }

  private void spawnTrolls() {
    float spawnAmount = waves.spawnTrollAmount();
    for (int i = 0; i < spawnAmount; i++) {
      Troll troll = new Troll(window);
      trolls.add(troll);
    }
  }
}
*/