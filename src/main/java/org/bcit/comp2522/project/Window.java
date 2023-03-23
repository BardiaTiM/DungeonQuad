package org.bcit.comp2522.project;

import java.io.File;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;


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

  float loadingProgress = 0;
  boolean isLoading = true;
  long loadingStartTime;
  static ConcurrentLinkedQueue<Skeleton> skeletons = new ConcurrentLinkedQueue<>();
  static ConcurrentLinkedQueue<Goblin> goblins = new ConcurrentLinkedQueue<>();
  static ConcurrentLinkedQueue<Troll> trolls = new ConcurrentLinkedQueue<>();

  PImage skeletonImage;
  PImage goblinImage;
  PImage trollImage;


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

  int waveNumber = 1;

  /**
   * Sets up the window.
   */
  public void setup() {

    size(700, 900);
    surface.setTitle("DUNGEON QUAD");

    backgroundImage = loadImage("deep_slate.jpg");


    PImage spriteImage = loadImage("mcW0.png");

    player = new Sprite(300, 700, 50, this, new PVector(0, 0));
    player.setSprite(spriteImage); // Default Sprite


    // Load the MP3 file
    try {
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("dungeon.wav"));
      clip = AudioSystem.getClip();
      clip.open(audioInputStream);
    } catch (Exception e) {
      e.printStackTrace();
    }

    clip.loop(Clip.LOOP_CONTINUOUSLY); // Set the clip to loop indefinitely
    waves = new Waves(waveNumber, this, skeletons, goblins, trolls);


  }




  /**
   * Draws the scrolling background.
   */
  public void drawBackground() {
    // Calculate the background position based on the player's movement
    if (wingsTime) {
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


    for (Bullet bullet : bullets) {     // Draw all the bullets in the list
      bullet.draw();
      bullet.update();
      bullet.collide();
    }
    drawLoadingBar(); // Draw the loading bar

    for (Skeleton skeleton : skeletons) {
      skeleton.draw();
      skeleton.move();
    }

    for (Goblin goblin : goblins) {
      goblin.draw();
      goblin.move();
    }

    for (Troll troll : trolls) {
      troll.draw();
      troll.move();
    }

  }

  public void newWave(){
    if(skeletons.isEmpty() && goblins.isEmpty() && trolls.isEmpty()){
      System.out.println("Wave " + waveNumber + " is over!");
    }
    redraw();
  }

  /**
   * Draws the loading bar.
   */
  public void drawLoadingBar() {
    final int barWidth = 100;
    final int barHeight = 20;
    final int barX = 10;
    final int barY = 10;
    final int barBorder = 5;

    long currentTime = System.currentTimeMillis();

    if (isLoading) { // When the bar is loading

      loadingProgress = (float) (currentTime - loadingStartTime) / 3000; // During Wings
      if (loadingProgress >= 1) { // When the bar is full
        isLoading = false;
        loadingStartTime = currentTime;
        isBarFull = true;
//        wingsTime = false;
      }
    } else { // When the bar is unloading

      loadingProgress = 1 - (float) (currentTime - loadingStartTime) / 6000; // Waiting for Wings
      if (loadingProgress <= 0) { // When the bar is empty
        isLoading = true;
        loadingStartTime = currentTime;
//        wingsTime = true;
      }
    }
    strokeWeight(barBorder);
    stroke(0);
    fill(170, 212, 218);

    // Draw the background of the loading bar
    rect(barX, barY, barWidth, barHeight);

    // Draw the progress of the loading bar
    fill(0);
    rect(barX + barWidth - barBorder - (barWidth - 2 * barBorder) * loadingProgress,
        barY + barBorder,
        (barWidth - 2 * barBorder) * loadingProgress,
        barHeight - 2 * barBorder);

    // Draw the text inside the loading bar
    textAlign(CENTER, CENTER);
    fill(255);
    textSize(16);
    text("Wings Time!", barX + barWidth / 2f, barY + barHeight / 2f + 25f);
  }

  /**
   * Moves the player based on the key pressed.
   */
  public void keyPressed() {
    if (keyCode == UP || key == 'w' || key == 'W') {
      if (Sprite.y - player.speed > 0) {
        PImage spriteImage;
        if (!wingsTime) {
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
      if (Sprite.y + player.speed < height) {
        PImage spriteImage;
        if (!wingsTime) {
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
      if (Sprite.x - player.speed > 0) {
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
      if (Sprite.x + player.speed < width) {
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

    if (key == ' ' && skeletons.isEmpty() && goblins.isEmpty() && trolls.isEmpty()) {
      waveNumber += 1;
      wingsTime = true;
      waves = new Waves(waveNumber);
      System.out.println("Wave " + waveNumber + " has begun!");
      System.out.println("Skeletons: " + waves.spawnSkeletonAmount());
      System.out.println("Goblins: " + waves.spawnGoblinAmount());
      System.out.println("Trolls: " + waves.spawnTrollAmount());
      ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);

      //Skeletons spawn time
      Runnable skeletonTask = new Runnable() {
        Window window = Window.this;
        PImage skeletonImage = loadImage("skeleton.png");


        float skeletonCount = 0;
        @Override
        public void run() {
          skeletonCount += 1;
          wingsTime = false;
          waves = new Waves(waveNumber);
          if(skeletonCount < waves.spawnSkeletonAmount()){
            executor.schedule(this, 1, TimeUnit.SECONDS);
          }
          if (skeletonCount < waves.spawnSkeletonAmount()) {
            Skeleton skeleton = new Skeleton(100, 100, 100, 1, window, skeletonImage);
            skeletons.add(skeleton);
          }
        }
      };

      executor.schedule(skeletonTask, 1, TimeUnit.SECONDS);

      //Goblins spawn time
      Runnable goblinTask = new Runnable() {
        Window window = Window.this;
        PImage goblinImage = loadImage("goblin.png");

        float goblinCount = 0;
        @Override
        public void run() {
          goblinCount += 1;
          waves = new Waves(waveNumber);
          if(goblinCount < waves.spawnGoblinAmount()){
            executor.schedule(this, 1, TimeUnit.SECONDS);
          }
          if (goblinCount < waves.spawnGoblinAmount()) {
            Goblin goblin = new Goblin(100, 300, 150, 1, window, goblinImage);
            goblins.add(goblin);
          }

        }
      };

      executor.schedule(goblinTask, 1, TimeUnit.SECONDS);

      //Trolls spawn time
      Runnable trollTask = new Runnable() {
        Window window = Window.this;
        PImage trollImage = loadImage("troll.png");

        float trollCount = 0;
        @Override
        public void run() {
          trollCount += 1;
          waves = new Waves(waveNumber);
          if(trollCount < waves.spawnTrollAmount()){
            executor.schedule(this, 1, TimeUnit.SECONDS);
          }
          if (trollCount < waves.spawnTrollAmount()) {
            Troll troll = new Troll(100, 100, 250, 1, window, trollImage);
            trolls.add(troll);
          }
        }
      };

      executor.schedule(trollTask, 1, TimeUnit.SECONDS);
    }

    redraw();
  }

  /**
   * Stops the player when the arrow keys are released.
   */
  public void keyReleased() {
    if (keyCode == UP || keyCode == DOWN
        || key == 's' || key == 'S'
        || key == 'w' || key == 'W') {
      player.direction.y = 0;
    }
    if (keyCode == LEFT || keyCode == RIGHT
        || key == 'a' || key == 'A'
        || key == 'd' || key == 'D') {
      player.direction.x = 0;
    }
    redraw();
  }


  /**
   * Creates a new bullet when the mouse is pressed.
   */
  public void mousePressed() {
    if (mouseButton == LEFT) {

      Bullet bullet = new Bullet((player.x + 50), (player.y + 40), 0, 0, 10, goblins, skeletons, trolls, this);

      float dx = mouseX - player.x - 50;
      float dy = mouseY - player.y - 40;
      float distance = sqrt(dx * dx + dy * dy);
      float vx = dx / distance;
      float vy = dy / distance;
      bullet.setVelocity(vx, vy);       // Set the velocity of the new bullet

      bullets.add(bullet);
    }
  }

  public float getWidth() {
    return width;
  }

  public float getHeight() {
    return height;
  }

  public PImage getSkeletonImage() {
    return skeletonImage;
  }

  public static void main(String[] args) {
    PApplet.main("org.bcit.comp2522.project.Window");
  }

  /**
   * Stops the clip when the program is stopped.
   */
  public void stop() {
    clip.stop();
    clip.close();
    super.stop();
  }


}
