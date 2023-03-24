//package org.bcit.comp2522.project;
//
///**
// * This is the abstract class for all weapons.
// *
// * @author Bardia Timouri
// * @version 1.0
// */
//abstract class Weapon {
//  public static int UPGRADE_LEVEL;
//  public static int BULLETS_PER_MAG;
//  public static int MAX_MAGS;
//  public static int MAX_BULLETS = BULLETS_PER_MAG * MAX_MAGS;
//
//  /**
//   * Weapon constructor.
//   *
//   * @param upgradeLevel upgrade level
//   * @param bulletsPerMag bullets per mag
//   * @param maxMags max mags
//   */
//  public Weapon(int upgradeLevel, int bulletsPerMag, int maxMags) {
//    UPGRADE_LEVEL = upgradeLevel;
//    BULLETS_PER_MAG = bulletsPerMag;
//    MAX_MAGS = maxMags;
//  }
//
//  public abstract void fire();
//  public abstract void reload();
//}
