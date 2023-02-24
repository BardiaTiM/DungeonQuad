package org.bcit.comp2522.project;

import java.util.List;

public class Waves {

  int waveNumber;
  List<Waves> waves;

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

  public void spawnWaves(){
    //spawn waves
  }

  public void removeDefeatedWaves(){
    // remove defeated waves
  }
}
