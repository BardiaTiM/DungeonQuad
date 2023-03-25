package org.bcit.comp2522.project;

import java.util.concurrent.ConcurrentLinkedQueue;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.core.PVector;
import static org.bcit.comp2522.project.SpawningHandler.waveNumber;


/**
 * Window class.
 *
 * @author Gathrean Dela Cruz
 * @author Bardia Timouri
 * @author Will Ondrik
 * @version 1.0
 */
public class Window extends PApplet {

  /**** MUSIC: ****/
  private MusicPlayer musicPlayer;

  /**** BULLETS: ****/
  static ConcurrentLinkedQueue<Bullet> bullets = new ConcurrentLinkedQueue<>();

  /**** ENEMIES: ****/
  Waves waves;
  static ConcurrentLinkedQueue<Skeleton> skeletons = new ConcurrentLinkedQueue<>();
  static ConcurrentLinkedQueue<Goblin> goblins = new ConcurrentLinkedQueue<>();
  static ConcurrentLinkedQueue<Troll> trolls = new ConcurrentLinkedQueue<>();

  /**** PLAYER: ****/
  Sprite player;
  boolean wingsTime = false;
  PImage coinImage;
  CoinManager coinManager;
  SpawningHandler spawningHandler;

  /**** MENU: ****/
  Menu menu;
  MenuHandler menuHandler;
  public static boolean gameOn = false;   //Variable to handle pausing the game
  Screen currentScreen = Screen.START;   //Set the current screen to the start menu

  /**** SCORE: ****/
  PFont inputFont;
  String inputText = "";
  boolean inputActive = false;
  public static int score;

  /**** BACKGROUND: ****/
  Background background;

  // ------------------ //
  //  Windows Set Up    //
  // ------------------ //
  /**
   * Sets the size of the window.
   */
  public void settings() {
    size(700, 800);
  }

  /**
   * Sets up the window.
   */
  public void setup() {


    spawningHandler = new SpawningHandler(this, skeletons, goblins, trolls, waveNumber);


    surface.setTitle("DUNGEON QUAD");


    PImage spriteImage = loadImage("images/player/normal/mcW0.png");
    background = new Background(this);



    player = new Sprite(350, 400, 50, this, new PVector(0, 0));
    player.setSprite(spriteImage); // Default Sprite

    musicPlayer = new MusicPlayer("music/dungeon.wav");
    musicPlayer.play();

    waves = new Waves(waveNumber, this, skeletons, goblins, trolls);

    setupMenu();
    menuHandler = new MenuHandler(this);
  }

  /**
   * Sets up the menu.
   */
  public void setupMenu() {
    menu = new Menu(this);
    menu.menuButtons();
    coinImage = loadImage("images/coin.png");
    coinManager = new CoinManager(this, player, coinImage);
  }

  /**
   * Displays the input box on the score menu.
   * Allows users to input their names.
   * It's saved to the Database automatically when continue is pressed.
   */
  void saveScore() {
    //need to display the players score
    text("Score: " + Coin.score, width / 2, height / 2 - 150);
    text("Enter your name", width / 2, height / 2 - 70);
    inputFont = createFont("Arial", 20, true);
    textFont(inputFont);
    fill(255);
    rect(width / 2f - 100, height / 2f - 50, 200, 50);
    fill(0);
    textAlign(CENTER, CENTER);
    text(inputText, width / 2f, height / 2f - 25);
  }

  /**
   * Restarts the game state.
   * Allows the new game to be run from when the new game button is pressed.
   */
  public void newGame() {
    Sprite.x = 200;
    Sprite.y = 500;
    player.direction = new PVector(0, 0);
    waves = new Waves(waveNumber);
    bullets.clear();
    gameOn = true;
    currentScreen = Screen.START;
    score = 0;
  }

  // --------------------------------------------- //
  // Displays and drawing the elements of the game //
  // --------------------------------------------- //

  /**
   * Draws the window, different menu states, player, and bullets.
   * The scrolling background is also drawn.
   */
  public void draw() {
    if (currentScreen == Screen.PAUSE) { // Option 1
      menuHandler.displayPauseScreen();
    } else if (!gameOn) {                // Option 2
      menuHandler.draw();
    } else {                             // Option 3
      displayGameScreen();
    }
  }


  /**
   * draw() Option 3: Displays the game screen.
   */
  private void displayGameScreen() {
    if (player.health <= 0) {
      gameOn = false;
    }

    background.draw(wingsTime, player);
    coinManager.update(); // Update the coin manager
    drawPlayer(); // Draw the player
    displayWaves(); // Display the wave number
    drawBullets(); // Draw the bullets
    drawEnemies(); // Draw the enemies
  }


  // draw() Option 3 :

  /**
   * 2. Draws the player.
   */
  public void drawPlayer() {
    player.draw();
    player.update(player.direction);
    player.displayHealth();
  }
  /**
   * 3. Adds text on top of the screen that displays the current wave number.
   */
  public void displayWaves() {
    textSize(30);
    textAlign(CENTER, CENTER);
    text("WAVE " + waveNumber
            + "\n ENEMIES IN THIS ROUND:" + waves.totalEnemies(),
        width / 2f, height / 8f - 50);
  }

  /**
   * 4. Draws the Bullets.
   */
  public void drawBullets() {
    for (Bullet bullet : bullets) {     // Draw all the bullets in the list
      bullet.draw();
      bullet.update();
      bullet.collide();
    }
  }

  /**
   * 5. Draws the enemies.
   */
  public void drawEnemies() {
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
    for (Arrow arrow : Skeleton.arrows) {
      arrow.draw();
      arrow.update();
    }
    for (Axe axe : Goblin.axes) {
      axe.draw();
      axe.update();
    }
    for (Boulder boulder : Troll.boulders) {
      boulder.draw();
      boulder.update();
    }
  }

  // ----------------------- //
  // Handles the key presses //
  // ----------------------- //

  /**
   * Handles all the keyPresses methods.
   */
  public void keyPressed() {
    if (inputActive) {
      handleInputText();
    } else {
      handleMovement();
      handlePausing();
      spawningHandler.handleMonsterSpawning(key);
    }
    redraw();
  }

  /**
   * Handles the movement of the player.
   */
  private void handleMovement() {
    if (keyCode == UP || key == 'w' || key == 'W') {
      if (Sprite.y - player.speed > 0) {
        PImage spriteImage;
        if (!wingsTime) {
          spriteImage = loadImage("images/player/normal/mcW0.png");
          player.direction.y = -0.8f;
        } else {
          spriteImage = loadImage("images/player/wings/mcW1.png");
          player.direction.y = -2;
        }
        player.setSprite(spriteImage);
      }
    }
    if (keyCode == DOWN || key == 's' || key == 'S') {
      if (Sprite.y + player.speed < height) {
        PImage spriteImage;
        if (!wingsTime) {
          spriteImage = loadImage("images/player/normal/mcS0.png");
          player.direction.y = 0.8f;
        } else {
          spriteImage = loadImage("images/player/wings/mcS1.png");
          player.direction.y = 2;
        }
        player.setSprite(spriteImage);
      }
    }
    if (keyCode == LEFT || key == 'a' || key == 'A') {
      if (Sprite.x - player.speed > 0) {
        PImage spriteImage;
        if (!wingsTime) {
          spriteImage = loadImage("images/player/normal/mcA0.png");
          player.direction.x = -0.8f;
        } else {
          spriteImage = loadImage("images/player/wings/mcA1.png");
          player.direction.x = -2;
        }
        player.setSprite(spriteImage);
      }
    }
    if (keyCode == RIGHT || key == 'd' || key == 'D') {
      if (Sprite.x + player.speed < width) {
        PImage spriteImage;
        if (!wingsTime) {
          spriteImage = loadImage("images/player/normal/mcD0.png");
          player.direction.x = 0.8f;
        } else {
          spriteImage = loadImage("images/player/wings/mcD1.png");
          player.direction.x = 2;
        }
        player.setSprite(spriteImage);
      }
    }
  }

  /**
   * Stops the player when the arrow keys are released.
   */
  public void keyReleased() {
    if (gameOn) {
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
  }

  /**
   * Handles the pausing of the game.
   */
  private void handlePausing() {
    long clipPosition = 0;
    if (key == 'p' || key == 'P') {
      if (currentScreen == Screen.PAUSE) {
        gameOn = true;
        currentScreen = Screen.START;
      } else if (currentScreen != Screen.SCORE) {
        currentScreen = Screen.PAUSE;
        clipPosition = musicPlayer.getMicrosecondPosition();
        musicPlayer.stop();
      } else {
        currentScreen = Screen.START;
        gameOn = true; // Update gameOn variable
        musicPlayer.setMicrosecondPosition(clipPosition);
        musicPlayer.start();
      }
      if (currentScreen == Screen.START) {
        gameOn = true; // Update gameOn variable
        musicPlayer.setMicrosecondPosition(clipPosition);
        musicPlayer.start();
      }
    }
  }


  /**
   * Creates a new bullet when the mouse is pressed.
   */
  public void mousePressed() {
    if (!gameOn) { //If the game isn't running, the mouse clicks will register on the menu buttons
      MenuHandler menuHandler = new MenuHandler(this);
      menuHandler.handleMouseClicks(mouseX, mouseY);
    } else {
      if (mouseButton == LEFT) {
        // Create a new bullet object and set its initial position to the current position of the player
        Bullet bullet = new Bullet((player.x + 50), (player.y + 40), 0, 0, 10, goblins, skeletons, trolls, player, this);

        float dx = mouseX - Sprite.x;
        float dy = mouseY - Sprite.y;
        float distance = sqrt(dx * dx + dy * dy);
        float vx = dx / distance;
        float vy = dy / distance;

        // Set the velocity of the new bullet
        bullet.setVelocity(vx, vy);

        bullets.add(bullet);
      }
    }
  }

  /**
   * Handles the input for the text box.
   */
  private void handleInputText() {
    if (key >= 'a' && key <= 'z' || key >= 'A' && key <= 'Z' || key >= '0' && key <= '9' || key == ' ') {
      if (inputText.length() < 20) {
        inputText += key;
      }
    } else if (key == BACKSPACE && inputText.length() > 0) {
      inputText = inputText.substring(0, inputText.length() - 1);
    }
  }



  // ------------------------------------------ //
  // The following are used in MenuHandler.java //
  // ------------------------------------------ //

  public boolean getGameOn() {
    return gameOn;
  }

  public void setGameOn(boolean gameOn) {
    this.gameOn = gameOn;
    if (!gameOn) {
      currentScreen = Screen.PAUSE;
    } else {
      currentScreen = Screen.START;
    }
  }

  public Menu getMenu() {
    return menu;
  }

  public Screen getCurrentScreen() {
    return currentScreen;
  }

  public void setCurrentScreen(Screen currentScreen) {
    this.currentScreen = currentScreen;
  }

  public void setInputActive(boolean inputActive) {
    this.inputActive = inputActive;
  }

  public String getInputText() {
    return inputText;
  }

  public int getScore() {
    return score;
  }

  // ------------------------------------------ //
  // The following are used in Waves.java //
  // ------------------------------------------ //

  public float getWidth() {
    return width;
  }

  public float getHeight() {
    return height;
  }


  // ------------ //
  // -- Other -- //
  // ----------- //

  /**
   * Main method.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    PApplet.main("org.bcit.comp2522.project.Window");
  }

  /**
   * Stops the clip when the program is stopped.
   */
  public void stop() {
    musicPlayer.stop();
    super.stop();
  }
}
