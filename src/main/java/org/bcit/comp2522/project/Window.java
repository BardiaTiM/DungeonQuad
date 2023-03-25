package org.bcit.comp2522.project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.core.PVector;

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
  int waveNumber = 1;
  static ConcurrentLinkedQueue<Skeleton> skeletons = new ConcurrentLinkedQueue<>();
  static ConcurrentLinkedQueue<Goblin> goblins = new ConcurrentLinkedQueue<>();
  static ConcurrentLinkedQueue<Troll> trolls = new ConcurrentLinkedQueue<>();

  /**** PLAYER: ****/
  Sprite player;
  boolean wingsTime = false;
  PImage skeletonImage;
  PImage goblinImage;
  PImage trollImage;
  PImage coinImage;

  CoinManager coinManager;

  /**** MENU: ****/
  Menu menu;
  public static boolean gameOn = false;   //Variable to handle pausing the game
  Screen currentScreen = Screen.START;   //Set the current screen to the start menu
  PImage mainMenuImage;   //Instantiate menu backgrounds
  PImage gameControlsImage;   //Instantiate menu backgrounds
  PImage pausedMenuImage;   //Instantiate menu backgrounds
  PImage endMenuImage;  //Instantiate menu backgrounds
  PImage leaderboardImage;   //Instantiate menu backgrounds

  /**** SCORE: ****/
  boolean showWaveText = true; //Variable to handle displaying the wave number
  Button newGameButton;
  Button leaderboardButton;
  Button controlsButton;
  Button backButton;
  Button quitButton;
  Button continueButton;
  Button resumeButton;
  PFont inputFont;
  String inputText = "";
  boolean inputActive = false;

  /**** LEADERBOARD: ****/
  FirebaseLeaderboard leaderboard;
  public static int score;

  /**** BACKGROUND: ****/
  PImage backgroundImage;
  float bgX = 0;
  float bgY = 0;
  float scrollSpeed = 1.5f; // Adjust this to control the scrolling speed

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

    backgroundImage = loadImage("images/deep_slate.jpg");

    PImage spriteImage = loadImage("images/player/normal/mcW0.png");
    PImage coinImage = loadImage("images/coin.png");

    player = new Sprite(350, 400, 50, this, new PVector(0, 0));
    player.setSprite(spriteImage); // Default Sprite

    musicPlayer = new MusicPlayer("music/dungeon.wav");
    musicPlayer.play();

    waves = new Waves(waveNumber, this, skeletons, goblins, trolls);

    setupMenu();
  }

  /**
   * Sets up the menu.
   */
  public void setupMenu() {
    menu = new Menu(this, newGameButton, leaderboardButton, controlsButton, backButton, quitButton, continueButton, resumeButton);
    menu.menuButtons();

    coinImage = loadImage("images/coin.png");
    //Set up Coin Manager
    coinManager = new CoinManager(this, player, coinImage);

    // Set up menu images
    mainMenuImage = loadImage("images/menu/background.jpg");
    gameControlsImage = loadImage("images/menu/controls.jpg");
    pausedMenuImage = loadImage("images/menu/background.jpg");
    endMenuImage = loadImage("images/menu/background.jpg");
    leaderboardImage = loadImage("images/menu/background.jpg");


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
      displayPauseScreen();
    } else if (!gameOn) {                // Option 2
      displayMenuScreen();
    } else {                             // Option 3
      displayGameScreen();
    }
  }

  // draw() Option 1 :

  /**
   * Displays the pause screen.
   */
  private void displayPauseScreen() {
    gameOn = false;
    image(pausedMenuImage, width / 2f - pausedMenuImage.width / 2f, height / 2f - pausedMenuImage.height / 2f);
    menu.displayResumeButton();
  }

  // draw() Option 2 :

  /**
   * draw() Option 2: Displays the menu screen.
   */
  private void displayMenuScreen() {
    switch (currentScreen) {
      case START -> {      // Start menu case
        image(mainMenuImage, 0, 0, width, height);
        menu.displayNewGameButton();
        menu.displayLeaderboardButton();
        menu.displayControlsButton();
      }
      case LEADERBOARD -> {       // Leaderboard menu case
        image(leaderboardImage, 0, 0, width, height);
        displayLeaderboard();
        menu.displayBackButton();
      }
      case CONTROLS -> {      // Game controls menu case
        image(gameControlsImage, width / 2f - gameControlsImage.width / 2f, height / 2f - gameControlsImage.height / 2f);
        menu.displayBackButton();
      }
      case PAUSE -> {       // Paused menu case
        image(pausedMenuImage, width / 2f - pausedMenuImage.width / 2f, height / 2f - pausedMenuImage.height / 2f);
        menu.displayResumeButton();
      }
      case SCORE -> {       // Save score menu case
        inputActive = true;
        image(leaderboardImage, 0, 0, width, height);
        saveScore();
        menu.displayContinueButton();
      }
      case END -> {      // End menu case
        image(endMenuImage, 0, 0, width, height);
        menu.displayNewGameButton();
        menu.displayLeaderboardButton();
        menu.displayControlsButton();
        menu.displayQuitButton();
      }
    }
  }

  /**
   * Displays the leaderboard.
   * Gets the leaderboard data from the Firebase database.
   */
  private void displayLeaderboard() {
    int blur = 3;
    filter(BLUR, blur);
    textAlign(CENTER, CENTER);
    textSize(55);
    fill(255, 0, 0);
    text("Leaderboard", width / 2f, 30);

    ArrayList<String> leaderboardList = menu.leaderboard.getLeaderboardList();
    textAlign(LEFT, CENTER);
    textSize(25);
    textFont(createFont("Courier New", 25));
    float yPos = 325;

    // For loop that prints out the lines of the leaderboard list
    for (String line : leaderboardList) {
      if (line.isEmpty()) continue;
      text(line, width / 2f - 225, yPos);
      yPos += 25;
    }
  }

  /**
   * draw() Option 3: Displays the game screen.
   */
  private void displayGameScreen() {
    drawBackground(); // Draw the scrolling background
    if (player.health <= 0) {
      currentScreen = Screen.SCORE;
      gameOn = false;

    }
    coinManager.update(); // Update the coin manager
    drawPlayer(); // Draw the player
    displayWaves(); // Display the wave number
    drawBullets(); // Draw the bullets
    drawEnemies(); // Draw the enemies
  }

  // draw() Option 3 :

  /**
   * 1. Draws the scrolling background.
   */
  public void drawBackground() {
    // Calculate the background position based on the player's movement
    if (wingsTime) {
      bgX = scrollSpeed * 2;
      bgY += scrollSpeed * 12;
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
    } else if (!gameOn) { // !gameOn keeps the game from running when the menus are being used
      handleMenuScreens();
    } else {
      handleMovement();
      handlePausing();
      handleMonsterSpawning();
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
   * Handles the spawning of monsters.
   */
  private void handleMonsterSpawning() {
    if (key == ' ' && skeletons.isEmpty() && goblins.isEmpty() && trolls.isEmpty()) {

      waveNumber += 1;
      wingsTime = true;
      waves = new Waves(waveNumber);
      ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);

      //Skeletons spawn time
      Runnable skeletonTask = new Runnable() {
        final Window window = Window.this;
        final PImage skeletonImage = loadImage("images/enemies/skeleton.png");


        float skeletonCount = 0;

        @Override
        public void run() {
          skeletonCount += 1;
          wingsTime = false;
          waves = new Waves(waveNumber);
          if (skeletonCount < waves.spawnSkeletonAmount()) {
            executor.schedule(this, 1, TimeUnit.SECONDS);
          }
          if (skeletonCount < waves.spawnSkeletonAmount()) {
            Skeleton skeleton = new Skeleton(100, -500, 100, true, window, skeletonImage);
            skeletons.add(skeleton);
          }
        }
      };

      executor.schedule(skeletonTask, 1, TimeUnit.SECONDS);

      //Goblins spawn time
      Runnable goblinTask = new Runnable() {
        final Window window = Window.this;
        final PImage goblinImage = loadImage("images/enemies/goblin.png");

        float goblinCount = 0;

        @Override
        public void run() {
          goblinCount += 1;
          waves = new Waves(waveNumber);
          if (goblinCount < waves.spawnGoblinAmount()) {
            executor.schedule(this, 2, TimeUnit.SECONDS);
          }
          if (goblinCount < waves.spawnGoblinAmount()) {
            Goblin goblin = new Goblin(100, -750, 150, true, window, goblinImage);
            goblins.add(goblin);
          }

        }
      };

      executor.schedule(goblinTask, 1, TimeUnit.SECONDS);

      //Trolls spawn time
      Runnable trollTask = new Runnable() {
        final Window window = Window.this;
        final PImage trollImage = loadImage("images/enemies/troll.png");

        float trollCount = 0;

        @Override
        public void run() {
          trollCount += 1;
          waves = new Waves(waveNumber);
          if (trollCount < waves.spawnTrollAmount()) {
            executor.schedule(this, 4, TimeUnit.SECONDS);
          }
          if (trollCount < waves.spawnTrollAmount()) {
            Troll troll = new Troll(100, -1000, 200, true, window, trollImage);
            trolls.add(troll);
          }
        }
      };

      executor.schedule(trollTask, 1, TimeUnit.SECONDS);
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

  /**
   * Handles the input for the menu screens.
   */
  private void handleMenuScreens() {
    // Code for handling input during menu screens
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