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
  WavesDisplay wavesDisplay;
  static ConcurrentLinkedQueue<Skeleton> skeletons = new ConcurrentLinkedQueue<>();
  static ConcurrentLinkedQueue<Goblin> goblins = new ConcurrentLinkedQueue<>();
  static ConcurrentLinkedQueue<Troll> trolls = new ConcurrentLinkedQueue<>();


  /**** PLAYER: ****/
  Player player;
  boolean wingsTime = false;
  PImage coinImage;
  CoinManager coinManager;
  SpawningHandler spawningHandler;
  MovementHandler movementHandler;


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
    surface.setTitle("DUNGEON QUAD");
    background = new Background(this);
    PImage PlayerImage = loadImage("images/player/normal/mcW0.png");

    menuHandler = new MenuHandler(this);
    movementHandler = new MovementHandler(this, player, spawningHandler);
    musicPlayer = new MusicPlayer("music/dungeon.wav");
    spawningHandler = new SpawningHandler(this, skeletons, goblins, trolls, waveNumber);
    waves = new Waves(waveNumber, this, skeletons, goblins, trolls);
    wavesDisplay = new WavesDisplay(this);


    Bullet bullet = new Bullet(1, 800, this);
    bullets.add(bullet);
    player = new Player(350, 400, 50, this, new PVector(0, 0));
    player.setPlayer(PlayerImage); // Default Player

    musicPlayer.play();
    setupMenu();
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
    fill(181); // death red
    textFont(createFont("fonts/Nintendo NES Font.ttf", 20));

    text("FINAL SCORE: " + score, width / 2f, height / 2f - 100);
    text("ENTER YOUR NAME", width / 2f, height / 2f - 70);

    inputFont = createFont("fonts/Nintendo NES Font.ttf", 20, true);
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

    // Reset game state variables
    setCurrentScreen(Screen.START);
    inputActive = false;
    menuHandler.inputActive = false;
    player.direction.x = 0;
    player.direction.y = 0;
    bullets.clear();
    Bullet bullet = new Bullet(1, 800, this);
    bullets.add(bullet);
    Player.health = 10;

    waveNumber = 1;
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
    if (Player.health <= 0) {
      gameOn = false;
    }

    background.draw(wingsTime, player);
    coinManager.update(); // Update the coin manager
    drawPlayer(); // Draw the player
    drawBullets(); // Draw the bullets
    drawEnemies(); // Draw the enemies
    wavesDisplay.displayWaves(SpawningHandler.waveNumber, waves.totalEnemies());
    spawningHandler.allEnemiesDead();
    player.displayHealth();
  }


  /**
   * 2. Draws the player.
   */
  public void drawPlayer() {
    player.draw();
    player.update(player.direction);
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
    if (gameOn) {
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
    movementHandler.handleMovement(key, keyCode, wingsTime);
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
    if (key == 'p' || key == 'P') {
      if (currentScreen == Screen.PAUSE) {
        gameOn = true;
        currentScreen = Screen.START;
      } else if (currentScreen != Screen.SCORE) {
        currentScreen = Screen.PAUSE;
      } else {
        currentScreen = Screen.START;
        gameOn = true; // Update gameOn variable
      }
      if (currentScreen == Screen.START) {
        gameOn = true; // Update gameOn variable
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
        Bullet bullet = new Bullet((Player.x + 50), (Player.y + 40), 0, 0, 10, goblins, skeletons, trolls, player, this);

        float dx = mouseX - Player.x - 50;
        float dy = mouseY - Player.y - 50;
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

  /**
   * Returns the current state of the game.
   *
   * @return true if the game is on, false otherwise.
   */
  public boolean getGameOn() {
    return gameOn;
  }


  /**
   * Sets the state of the game to the specified value.
   *
   * @param gameOn
   */
  public void setGameOn(boolean gameOn) {
    Window.gameOn = gameOn;
  }


  /**
   * Returns the Menu instance associated with the game Window.
   *
   * @return menu
   */
  public Menu getMenu() {
    return menu;
  }


  /**
   * Returns the current screen being displayed in the game Window.
   *
   * @return currentScreen
   */
  public Screen getCurrentScreen() {
    return currentScreen;
  }


  /**
   * Sets the current screen to be displayed in the game Window.
   *
   * @param currentScreen the current menu screen
   */
  public void setCurrentScreen(Screen currentScreen) {
    this.currentScreen = currentScreen;
  }


  /**
   * Sets the input active that of the game Window to the specified value.
   *
   * @param inputActive input active allows the Player to input their name into the inputText box without operating the game
   */
  public void setInputActive(boolean inputActive) {
    this.inputActive = inputActive;
  }


  /**
   * Returns the current text entered by the user in the game Window.
   *
   * @return inputText the Player's name that will be used to save with their score
   */
  public String getInputText() {
    return inputText;
  }


  /**
   * Returns the current score of the Player in the game.
   *
   * @return score the current Player's score
   */
  public int getScore() {
    return score;
  }

  // ------------------------------------------ //
  // The following are used in Waves.java //
  // ------------------------------------------ //

  /**
   * Return the width of the game Window.
   *
   * @return width the Window width
   */
  public float getWidth() {
    return width;
  }


  /**
   * Returns the height of the game Window.
   *
   * @return height the Window height
   */
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
