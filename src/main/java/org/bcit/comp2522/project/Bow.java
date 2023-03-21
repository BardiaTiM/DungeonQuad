package org.bcit.comp2522.project;

/**
 * This is the Skeleton's weapon of choice, the Bow.
 *
 * @author Gathrean Dela Cruz
 * @author Heeho Ryou
 * @version 1.0
 */
public class Bow extends Weapon{

  /**
   * Bow constructor.
   *
   * @param upgradeLevel upgrade level
   * @param bulletsPerMag bullets per mag
   * @param maxMags max mags
   */
  public Bow(int upgradeLevel, int bulletsPerMag, int maxMags) {
    super(upgradeLevel, bulletsPerMag, maxMags);
  }

  @Override
  public void fire() {
    //
  }

  @Override
  public void reload() {
    //
  }
}
