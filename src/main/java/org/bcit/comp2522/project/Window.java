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
  static ConcurrentLinkedQueue<Bullet> bullets;

  /**** ENEMIES: ****/
  Waves waves;
  WavesDisplay wavesDisplay;
  static ConcurrentLinkedQueue<Skeleton> skeletons;
  public int skeletonHealth;
  static ConcurrentLinkedQueue<Goblin> goblins;
  public int goblinHealth;
  static ConcurrentLinkedQueue<Troll> trolls;
  public int trollHealth;

  /**** PLAYER: ****/
  Player player;
  public int playerHealth;
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

  /**** MAGIC NUMBERS: ****/
  //Magic numbers for character health
  private static final int PLAYER_HEALTH = 10;
  private static final int SKELETON_HEALTH = 3;
  private static final int GOBLIN_HEALTH = 5;
  private static final int TROLL_HEALTH = 10;

  //Wave number magic number
  private static final int WAVE_NUMBER = 1;

  //Player direction magic numbers
  private static final int PLAYER_DIRECTION_X = 0;
  private static final int PLAYER_DIRECTION_Y = 0;

  //Magic numbers
  private static final int ZERO = 0;
  private static final int ONE = 1;
  private static final float TWO_FLOAT = 2f;
  private static final int TEN = 10;
  private static final int TWENTY = 20;
  private static final int TWENTY_FIVE = 25;
  private static final int FORTY = 40;
  private static final int FIFTY = 50;
  private static final int EIGHTY = 80;
  private static final int ONE_HUNDRED = 100;
  private static final int ONE_HUNDRED_TEN = 110;
  private static final int ONE_HUNDRED_FIFTY = 150;
  private static final int ONE_HUNDRED_EIGHTY_ONE = 181;
  private static final int TWO_HUNDRED = 200;
  private static final int TWO_HUNDRED_FIFTY_FIVE = 255;
  private static final int THREE_HUNDRED_FIFTY = 350;
  private static final int FOUR_HUNDRED = 400;
  private static final int SEVEN_HUNDRED = 700;
  private static final int EIGHT_HUNDRED = 800;



  // ------------------ //
  //  Windows Set Up    //
  // ------------------ //

  /**
   * Sets the size of the window.
   */
  public void settings() {
    playerHealth = PLAYER_HEALTH;
    skeletonHealth = SKELETON_HEALTH;
    goblinHealth = GOBLIN_HEALTH;
    trollHealth = TROLL_HEALTH;
    waveNumber = WAVE_NUMBER;

    size(SEVEN_HUNDRED, EIGHT_HUNDRED);
  }

  /**
   * Sets up the window.
   */
  public void setup() {
    setupQueues();
    setupSpawningHandler();
    setupSurface();
    setupPlayer();
    setupBackground();
    setupMusicPlayer();
    setupWaves();
    setupMenuAndHandlers();
    setupWavesDisplay();
  }

  /**
   * This method sets up the enemies queues as well as the bullets queue.
   */
  private void setupQueues() {
    skeletons = new ConcurrentLinkedQueue<>();
    goblins = new ConcurrentLinkedQueue<>();
    trolls = new ConcurrentLinkedQueue<>();
    bullets = new ConcurrentLinkedQueue<>();
  }

  /**
   * This method sets up the Spawning Handler.
   */
  private void setupSpawningHandler() {
    spawningHandler = new SpawningHandler(this, skeletons, goblins, trolls, waveNumber);
  }

  /**
   * This method sets up the game Window title.
   */
  private void setupSurface() {
    surface.setTitle("DUNGEON QUAD");
  }

  /**
   * This method sets up the Player's image, the Player object and the Movement Handler.
   */
  private void setupPlayer() {
    PImage PlayerImage = loadImage("images/player/normal/mcW0.png");
    player = new Player(THREE_HUNDRED_FIFTY, FOUR_HUNDRED, FIFTY, playerHealth, this, new PVector(ZERO, ZERO));
    player.setPlayer(PlayerImage); // Default Player
    movementHandler = new MovementHandler(this, player, spawningHandler);
  }

  /**
   * This method sets up the background for the game window.
   */
  private void setupBackground() {
    background = new Background(this);
  }

  /**
   * This method sets up the Music Player.
   */
  private void setupMusicPlayer() {
    musicPlayer = new MusicPlayer("music/dungeon.wav");
    musicPlayer.play();
  }

  /**
   * This method sets up the enemy waves.
   */
  private void setupWaves() {
    waves = new Waves(waveNumber, this, skeletons, goblins, trolls);
  }

  /**
   * This method sets up the Menu and Menu Handler.
   */
  private void setupMenuAndHandlers() {
    setupMenu();
    menuHandler = new MenuHandler(this);
  }

  /**
   * This method sets up the game window to display the Wave Number.
   */
  private void setupWavesDisplay() {
    wavesDisplay = new WavesDisplay(this);
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
    fill(ONE_HUNDRED_EIGHTY_ONE); // death red
    textFont(createFont("fonts/Nintendo NES Font.ttf", TWENTY));

    text("FINAL SCORE: " + score, width / TWO_FLOAT + ONE_HUNDRED_FIFTY, height / TWO_FLOAT - ONE_HUNDRED_TEN);
    text("ENTER YOUR NAME", width / TWO_FLOAT + ONE_HUNDRED_FIFTY, height / TWO_FLOAT - EIGHTY);

    inputFont = createFont("fonts/Nintendo NES Font.ttf", TWENTY, true);
    textFont(inputFont);
    fill(TWO_HUNDRED_FIFTY_FIVE);
    rect(width / TWO_FLOAT - ONE_HUNDRED, height / TWO_FLOAT - FIFTY, TWO_HUNDRED, FIFTY);
    fill(ZERO);
    textAlign(CENTER, CENTER);
    text(inputText, width / TWO_FLOAT, height / TWO_FLOAT - TWENTY_FIVE);
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
    player.direction.x = PLAYER_DIRECTION_X;
    player.direction.y = PLAYER_DIRECTION_Y;
    bullets.clear();
    Bullet bullet = new Bullet(ONE, EIGHT_HUNDRED, this);
    bullets.add(bullet);
    Player.health = playerHealth;

    waveNumber = WAVE_NUMBER;
    score = ZERO;
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
      PersonalBest.readFromJSON();
      wavesDisplay.displayHighScore(PersonalBest.getHighestScore(), PersonalBest.getHighestWaveNumber());
    } else {                             // Option 3
      displayGameScreen();
    }
  }

  /**
   * draw() Option 3: Displays the game screen.
   */
  private void displayGameScreen() {
    if (Player.health <= ZERO) {
      gameOn = false;
    }
    PersonalBest.addScore(score);
    PersonalBest.addWaveNumber(SpawningHandler.waveNumber);
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
        player.direction.y = PLAYER_DIRECTION_Y;
      }
      if (keyCode == LEFT || keyCode == RIGHT
          || key == 'a' || key == 'A'
          || key == 'd' || key == 'D') {
        player.direction.x = PLAYER_DIRECTION_X;
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
        Bullet bullet = new Bullet((Player.x + FIFTY), (Player.y + FORTY), ZERO, ZERO, TEN, goblins, skeletons, trolls, player, this, waveNumber);

        float dx = mouseX - Player.x - FIFTY;
        float dy = mouseY - Player.y - FIFTY;
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
      if (inputText.length() < TWENTY) {
        inputText += key;
      }
    } else if (key == BACKSPACE && inputText.length() > ZERO) {
      inputText = inputText.substring(ZERO, inputText.length() - ONE);
    }
  }

  // ------------------------------------------ //
  // The following are used in MenuHandler.java //
  // ------------------------------------------ //

  /**
   * Sets the state of the game to the specified value.
   *
   * @param gameOn Returns true if game is on, false otherwise
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