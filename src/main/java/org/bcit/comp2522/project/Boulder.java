package org.bcit.comp2522.project;

import processing.core.PImage;

/**
 * This is the Troll's weapon of choice, the Boulder.
 *
 * @author Gathrean Dela Cruz
 * @version 1.0
 */
public class Boulder {

      private float x;
      private float y;
      private float vy;
      private float size = 10;

      private PImage boulderImage;
      private Window window;

      public Boulder(float x, float y, float vy, float size, Window window) {
            this.x = x;
            this.y = y;
            this.vy = vy;
            this.size = size;
            this.window = window;
            PImage boulderImage = window.loadImage("boulder.png");
            this.boulderImage = boulderImage;
      }

      public void setVelocity(float vy) {
            this.vy = vy * 10;
      }

      public void update() {
            y += vy * 5;
      }

      public void drawBoulder(float x, float y, float diameter) {
            window.image(boulderImage, x, y, diameter, diameter);
      }

      public void draw() {
            this.drawBoulder(this.x, this.y, 100);
      }

}
