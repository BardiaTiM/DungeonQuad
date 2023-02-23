package org.bcit.comp2522.project;

public class Gun extends Weapon{
  public static int UPGRADE_LEVEL;
  public static int BULLETS_PER_MAG;
  public static int MAX_MAGS;
  public static int MAX_BULLETS = BULLETS_PER_MAG * MAX_MAGS;

  //constructor
  public Gun(int upgradeLevel, int bulletsPerMag, int maxMags) {
    super(upgradeLevel, bulletsPerMag, maxMags);
    UPGRADE_LEVEL = upgradeLevel;
    BULLETS_PER_MAG = bulletsPerMag;
    MAX_MAGS = maxMags;
  }

  public void fire() {
    if (MAX_BULLETS > 0) {
      MAX_BULLETS--;
      System.out.println("Bang!");
    } else {
      System.out.println("Click!");
    }
  }

  public void reload() {
    if (MAX_MAGS > 0) {
      MAX_MAGS--;
      MAX_BULLETS = BULLETS_PER_MAG;
      System.out.println("Reloading...");
    } else {
      System.out.println("Out of ammo!");
    }
  }
}
