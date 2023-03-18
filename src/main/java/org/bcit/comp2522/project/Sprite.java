package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PVector;

public class Sprite {

  float x = 5;
  float y = 5;
  float diameter = 1;
  protected double maxHealth;
  protected double speed;
  protected double size;
  protected boolean isDead;
  protected boolean isFiring;
  protected boolean isReloading;
  private Weapon weapon;

  PApplet canvas;
  PVector direction;

  private Window window;

  public Sprite(double maxHealth, double speed, double size, Weapon weapon, PVector direction) {
    this.maxHealth = maxHealth;
    this.speed = speed;
    this.size = size;
    this.weapon = weapon;
    this.direction = direction;
  }

  public Sprite(float x, float y, float diameter, Window window, PVector direction) {
    this.x = x;
    this.y = y;
    this.diameter = diameter;
    this.window = window; // set the window variable of the sprite
    this.direction = direction;
    this.speed = 15; // set a faster speed for the player sprite
  }

  public void drawPlayer(float x, float y, float diameter) {
    window.ellipse(x, y, diameter, diameter);
  }

  public void draw() {
    this.drawPlayer(this.x, this.y, this.diameter);

  }

  public void update(PVector direction) {
    this.x += direction.x * this.speed;
    this.y += direction.y * this.speed;
  }

}