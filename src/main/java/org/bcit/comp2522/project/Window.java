package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Window extends PApplet {

  static ConcurrentLinkedQueue<Bullet> bullets = new ConcurrentLinkedQueue<>();

  ArrayList<Sprite> sprites;

  Waves waves;

  Sprite player;

  Bullet bullet;


  public void settings() {
    size(700, 900);
  }

  PImage backgroundImage;
  float bgX = 0;
  float bgY = 0;
  float scrollSpeed = 2; // Adjust this to control the scrolling speed


  public void setup() {
    size(700, 900);
    backgroundImage = loadImage("deepslate3.jpg");
//    background(0);
    player = new Sprite(500, 700, 50, this, new PVector(0, 0));
    waves = new Waves(1, Window.this);
  }

  public void draw() {
    // Calculate the background position based on the player's movement
    bgY += scrollSpeed;
    bgX -= player.direction.x;
    bgY -= player.direction.y;

    // Tile the background image
    for (int x = (int) (bgX % backgroundImage.width - backgroundImage.width); x < width; x += backgroundImage.width) {
      for (int y = (int) (bgY % backgroundImage.height - backgroundImage.height); y < height; y += backgroundImage.height) {
        image(backgroundImage, x, y);
      }
    }

//    // Update the scroll speed based on the player's movement
//    scrollSpeed += player.direction.y * 0.1f; // Adjust the factor to control the scrolling sensitivity
//    scrollSpeed = constrain(scrollSpeed, 0, 5); // Limit the scroll speed to a reasonable range
//
//    // Calculate the background position based on the scrolling speed
//    bgY -= scrollSpeed;

    player.draw();
    player.update(player.direction);
    waves.spawnWaves(1, 1, 1, 1);

    // Draw all the bullets in the list
    for (Bullet bullet : bullets) {
      bullet.draw();
      bullet.update();
      bullet.collide();
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
      Bullet bullet = new Bullet(player.x, player.y, 0, 0, 10, Waves.getGoblins(), this);

      float dx = mouseX - player.x;
      float dy = mouseY - player.y;
      float distance = sqrt(dx*dx + dy*dy);
      float vx = dx / distance;
      float vy = dy / distance;

      // Set the velocity of the new bullet
      bullet.setVelocity(vx, vy);

      bullets.add(bullet);
    }
  }


  public static void main(String[] args) {
    PApplet.main("org.bcit.comp2522.project.Window");
  }

  public float getWidth() {
    return width;
  }

  public void removeBullet(Bullet bullet) {
    bullets.remove(bullet);
  }
}
