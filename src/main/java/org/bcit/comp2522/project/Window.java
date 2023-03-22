package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.io.File;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


/**
 * Window class.
 *
 * @author Gathrean Dela Cruz
 * @author Bardia Timouri
 * @version 1.0
 */
public class Window extends PApplet {

  private Clip clip;

  static ConcurrentLinkedQueue<Bullet> bullets = new ConcurrentLinkedQueue<>();
  Waves waves;
  Sprite player;
  boolean wingsTime = false;
  boolean isBarFull = false;
//  boolean isXKeyPressed = false;

  float loadingProgress = 0;
  boolean isLoading = true;
  long loadingStartTime;

  /**
   * Sets the size of the window.
   */
  public void settings() {
    size(700, 900);
  }

  PImage backgroundImage;
  float bgX = 0;
  float bgY = 0;
  float scrollSpeed = 1.5f; // Adjust this to control the scrolling speed

  /**
   * Sets up the window.
   */
  public void setup() {

//    loadingStartTime = millis();

    size(700, 900);
    surface.setTitle("DUNGEON QUAD");

    backgroundImage = loadImage("deep_slate.jpg");

    PImage spriteImage = loadImage("mcW0.png");

    player = new Sprite(300, 700, 50, this, new PVector(0, 0));
    player.setSprite(spriteImage); // Default Sprite

    waves = new Waves(1, Window.this);

    // Load the MP3 file
    try {
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("dungeon.wav"));
      clip = AudioSystem.getClip();
      clip.open(audioInputStream);
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Set the clip to loop indefinitely
    clip.loop(Clip.LOOP_CONTINUOUSLY);
  }

  public void drawBackground() {
    // Calculate the background position based on the player's movement
    if(wingsTime){
      bgX = scrollSpeed * 2;
      bgY += scrollSpeed * 16;
    } else {
      bgY += scrollSpeed;
      bgX = player.direction.x;
      bgY -= player.direction.y;
    }

    // Tile the background image
    int offsetX = (int) (bgX % backgroundImage.width - backgroundImage.width);
    int offsetY = (int) (bgY % backgroundImage.height - backgroundImage.height);

    // Draw the background image
    for (int x = offsetX; x < width; x += backgroundImage.width) {
      for (int y = offsetY; y < height; y += backgroundImage.height) {
        image(backgroundImage, x, y);
      }
    }
  }

  /**
   * Draws the window, player, and bullets.
   * The scrolling background is also drawn.
   */
  public void draw() {

    drawBackground(); // Draw the scrolling background

    player.draw();
    player.update(player.direction);
    waves.spawnWaves(1, 1, 1, 1);

    for (Bullet bullet : bullets) {     // Draw all the bullets in the list
      bullet.draw();
      bullet.update();
      bullet.collide();
    }

    drawLoadingBar(); // Draw the loading bar
  }

  public void drawLoadingBar() {

    // Calculate the loading progress
    long currentTime = System.currentTimeMillis();

    if (isLoading) { // When the bar is loading

      // This determines how long Player gets to be in Wings Time.
      loadingProgress = (float) (currentTime - loadingStartTime) / 3000;

      if (loadingProgress >= 1) { // When the bar is full
        isLoading = false;
        loadingStartTime = currentTime;
        isBarFull = true;
        wingsTime = false;
      }
    } else { // When the bar is unloading

      // This determines how long Player has to wait for Wings Time.
      loadingProgress = 1 - (float) (currentTime - loadingStartTime) / 6000;

      if (loadingProgress <= 0) { // When the bar is empty
        isLoading = true;
        loadingStartTime = currentTime;
        wingsTime = true;
      }
    }

    // Draw the loading bar
    int barWidth = 100;
    int barHeight = 20;
//    int barX = (width - barWidth) / 2;
    int barX = 10;
    int barY = 10;
    int barBorder = 5;

    strokeWeight(barBorder);
    stroke(0);
    fill(170, 212, 218);

    // Draw the background of the loading bar
    rect(barX, barY, barWidth, barHeight);

    // Draw the progress of the loading bar
    fill(0);
    rect(barX + barWidth - barBorder - (barWidth - 2 * barBorder) * loadingProgress, barY + barBorder, (barWidth - 2 * barBorder) * loadingProgress, barHeight - 2 * barBorder);

    // Draw the text inside the loading bar
    textAlign(CENTER, CENTER);
    fill(255);
    textSize(16);
    text("Wings Time!", barX + barWidth / 2f, barY + barHeight / 2f + 25f);
  }

  public void keyPressed() {
    if (keyCode == UP || key == 'w' || key == 'W') {
      if (player.y - player.speed > 0) {
        PImage spriteImage;
        if(!wingsTime) {
          spriteImage = loadImage("mcW0.png");
          player.direction.y = -0.8f;
        } else {
          spriteImage = loadImage("mcW1.png");
          player.direction.y = -2;
        }
        player.setSprite(spriteImage);
      }
    }
    if (keyCode == DOWN || key == 's' || key == 'S') {
      if (player.y + player.speed < height) {
        PImage spriteImage;
        if(!wingsTime) {
          spriteImage = loadImage("mcS0.png");
          player.direction.y = 0.8f;
        } else {
          spriteImage = loadImage("mcS1.png");
          player.direction.y = 2;
        }
        player.setSprite(spriteImage);
      }
    }
    if (keyCode == LEFT || key == 'a' || key == 'A') {
      if (player.x - player.speed > 0) {
        PImage spriteImage;
        if (!wingsTime) {
          spriteImage = loadImage("mcA0.png");
          player.direction.x = -0.8f;
        } else {
          spriteImage = loadImage("mcA1.png");
          player.direction.x = -2;
        }
        player.setSprite(spriteImage);
      }
    }
    if (keyCode == RIGHT || key == 'd' || key == 'D') {
      if (player.x + player.speed < width) {
        PImage spriteImage;
        if (!wingsTime) {
          spriteImage = loadImage("mcD0.png");
          player.direction.x = 0.8f;
        } else {
          spriteImage = loadImage("mcD1.png");
          player.direction.x = 2;
        }
        player.setSprite(spriteImage);
      }
    }
    redraw();
  }

  /**
   * Stops the player when the arrow keys are released.
   */
  public void keyReleased() {
    if (keyCode == UP || key == 'w' || keyCode == DOWN || key == 's' || key == 'W' || key == 'S') {
      player.direction.y = 0;
    }
    if (keyCode == LEFT || key == 'a' || keyCode == RIGHT || key == 'd' || key == 'A' || key == 'D') {
      player.direction.x = 0;
    }
    redraw();
  }

  /**
   * Creates a new bullet when the mouse is pressed.
   */
  public void mousePressed() {
    if (mouseButton == LEFT) {
      // Create a new bullet object and set its initial position to the current position of the player

      Bullet bullet = new Bullet((player.x + 50), (player.y + 40), 0, 0, 10, Waves.getGoblins(),
          Waves.getSkeletons(), Waves.getTrolls(), this);

      float dx = mouseX - player.x;
      float dy = mouseY - player.y;
      float distance = sqrt(dx * dx + dy * dy);
      float vx = dx / distance;
      float vy = dy / distance;

      // Set the velocity of the new bullet
      bullet.setVelocity(vx, vy);

      bullets.add(bullet);
    }
  }

  public float getWidth() {
    return width;
  }

  public static void main(String[] args) {
    PApplet.main("org.bcit.comp2522.project.Window");
  }

  public void stop() {
    // Stop and close the clip
    clip.stop();
    clip.close();

    super.stop();
  }


}
