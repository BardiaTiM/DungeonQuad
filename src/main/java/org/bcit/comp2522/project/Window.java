package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class Window extends PApplet {

  ArrayList<Bullet> bullets = new ArrayList<Bullet>();
  ArrayList<Sprite> sprites;

  Waves waves;

  Sprite player;

  Bullet bullet;


  public void settings() {
    size(700, 900);
  }

  public void setup() {
    background(0);

<<<<<<< HEAD
    player = new Sprite(100, 100, 50, this, new PVector(0, 0));
=======
    player = new Sprite(500, 700, 50, this, new PVector(0, 0));
>>>>>>> parent of 41e6d1b (Created moving background.)
    waves = new Waves(1, Window.this);

  }

  public void draw() {
    background(0);

    player.draw();
    player.update(player.direction);
    waves.spawnWaves(1, 1, 1, 1);

    // Draw all the bullets in the list
    for (Bullet bullet : bullets) {
      bullet.draw();
      bullet.update();
    }
  }

  public void keyPressed() {
    if (keyCode == UP || key == 'w') {
      player.direction.y = -1;
    }
    if (keyCode == DOWN || key == 's') {
      player.direction.y = 1;
    }
    if (keyCode == LEFT || key == 'a') {
      player.direction.x = -1;
    }
    if (keyCode == RIGHT || key == 'd') {
      player.direction.x = 1;
    }
    redraw();
  }

  public void keyReleased() {
    if (keyCode == UP || key == 'w' || keyCode == DOWN || key == 's') {
      player.direction.y = 0;
    }
    if (keyCode == LEFT || key == 'a' || keyCode == RIGHT || key == 'd') {
      player.direction.x = 0;
    }
    redraw();
  }

  public void mousePressed() {
    if (mouseButton == LEFT) {
      // Create a new bullet object and set its initial position to the current position of the player
      bullet = new Bullet(player.x, player.y, 0, 0, 10, this);

      float dx = mouseX - player.x;
      float dy = mouseY - player.y;
      float distance = sqrt(dx*dx + dy*dy);
      float vx = dx / distance;
      float vy = dy / distance;

      // Set the velocity of the new bullet
      bullet.setVelocity(vx, vy);

      // Add the new bullet to the list of bullets
      bullets.add(bullet);
    }
  }

  public static void main(String[] args) {
    PApplet.main("org.bcit.comp2522.project.Window");
  }
}
