package org.bcit.comp2522.project;

abstract class Weapon {
  public static int UPGRADE_LEVEL;
  public static int BULLETS_PER_MAG;
  public static int MAX_MAGS;
  public static int MAX_BULLETS = BULLETS_PER_MAG * MAX_MAGS;

  //constructor
  public Weapon(int upgradeLevel, int bulletsPerMag, int maxMags) {
    UPGRADE_LEVEL = upgradeLevel;
    BULLETS_PER_MAG = bulletsPerMag;
    MAX_MAGS = maxMags;
  }

  public abstract void fire();
  public abstract void reload();
}
