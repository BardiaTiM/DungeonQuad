package org.bcit.comp2522.project;

import java.util.List;

/**
 * Each Wave has a number of enemies.
 */
public class Waves {

  int waveNumber;
  List<Waves> waves;

  final int skeletons = 10;
  final int goblins = 5;
  final int trolls = 3;

  public List<Waves> getWaves(){
    return waves;
  }

  public void setWaves(List<Waves> waves){
    this.waves = waves;
  }

  public int getWaveNumber(){
    return waveNumber;
  }

  public void setWaveNumber(int waveNumber){
    this.waveNumber = waveNumber;
  }

  public void spawnWaves(int skeletons, int goblins, int trolls) {
    while (!Player.isDead()) {
      // Spawn wave of Skeletons (I)
      for (int i = 0; i < skeletons; i++) {
        spawnSkeleton();
      }

      // Spawn wave of Goblins (II)
      for (int i = 0; i < goblins; i++) {
        spawnGoblin();
      }

      // Spawn wave of Trolls (III)
      for (int i = 0; i < trolls; i++) {
        spawnTroll();
      }

      // Pause between waves
      try {
        Thread.sleep(1000); // wait 1 second
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private void spawnSkeleton() {
    // spawn skeleton
  }

  private void spawnGoblin() {
    // spawn goblin
  }

  private void spawnTroll() {
    // spawn troll
  }

  public void removeDefeatedWaves(int skeletons, int goblins, int trolls){
    // remove defeated waves
  }
}
