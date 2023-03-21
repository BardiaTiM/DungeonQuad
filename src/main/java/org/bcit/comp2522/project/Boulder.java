package org.bcit.comp2522.project;

/**
 * This is the Troll's weapon of choice, the Boulder.
 *
 * @author Gathrean Dela Cruz
 * @version 1.0
 */
public class Boulder extends Weapon{

      /**
       * Boulder constructor.
       *
       * @param upgradeLevel  upgrade level
       * @param bulletsPerMag bullets per mag
       * @param maxMags       max mags
       */
      public Boulder(int upgradeLevel, int bulletsPerMag, int maxMags) {
          super(upgradeLevel, bulletsPerMag, maxMags);
      }

      public void fire() {
        //
      }

      @Override
      public void reload() {
        //
      }
}
