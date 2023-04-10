package org.bcit.comp2522.project;

import java.util.ArrayList;
import processing.core.PImage;

/**
 * Handles the menu screens.
 *
 * @author Will Ondrik
 * @author Bardia Timouri
 * @author Gathrean Dela Cruz
 * @version 1.0
 */
public class MenuHandler {
  /**
   * The game window.
   */
  private final Window window;

  /**
   * The menu object.
   */
  private final Menu menu;

  /**
   * The current screen.
   */
  private final Screen currentScreen;

  /**
   * The main menu image.
   */
  private final PImage mainMenuImage;

  /**
   * The game controls image.
   */
  private final PImage gameControlsImage;

  /**
   * The paused menu image.
   */
  private final PImage pausedMenuImage;

  /**
   * The end menu image.
   */
  private final PImage endMenuImage;

  /**
   * The leaderboard image.
   */
  private final PImage leaderboardImage;

  /**
   * The death image.
   */
  private final PImage deathImage;

  /**
   * The input active boolean.
   */
  public boolean inputActive = false;

  /**
   * Constructor - initializes the Window object and sets the menu images.
   *
   * @param window the game window
   */
  public MenuHandler(Window window) {
    window.textFont(window.createFont("fonts/Nintendo NES Font.ttf", 10));
    this.window = window;
    this.menu = window.getMenu();
    this.currentScreen = window.getCurrentScreen();
    this.mainMenuImage = window.loadImage("images/menu/dungeon_start.jpg");
    this.gameControlsImage = window.loadImage("images/menu/controlsv2.jpg");
    this.pausedMenuImage = window.loadImage("images/menu/paused.png");
    this.endMenuImage = window.loadImage("images/menu/background.jpg");
    this.leaderboardImage = window.loadImage("images/menu/leaderboard.jpg");
    this.deathImage = window.loadImage("images/menu/death.png");
  }

  /**
   * Helper function for the Start menu screen.
   *
   * @param mouseX the x-coordinate of the mouse
   * @param mouseY the y-coordinate of the mouse
   */
  public void screenStartHelper(float mouseX, float mouseY) {
    // Start menu - button settings
    if (menu.newGameButton.isClicked(mouseX, mouseY)) {
      window.setGameOn(true);
    } else if (menu.leaderboardButton.isClicked(mouseX, mouseY)) {
      menu.leaderBoardFetch(); // Fetches the leaderboard data
      window.setCurrentScreen(Screen.LEADERBOARD); // Displays the leaderboard menu
      window.setGameOn(false);
    } else if (menu.controlsButton.isClicked(mouseX, mouseY)) {
      window.setCurrentScreen(Screen.CONTROLS); // Displays the controls menu
    }
  }

  /**
   * Helper function for the Score menu screen.
   *
   * @param mouseX the x-coordinate of the mouse
   * @param mouseY the y-coordinate of the mouse
   */
  public void screenScoreHelper(float mouseX, float mouseY) {
    if (menu.continueButton.isClicked(mouseX, mouseY)) {
      // Saves the inputted text from the player and their score
      menu.setLeaderboardSave(window.getInputText(), window.getScore());
      PersonalBest.writeToJSON(); // Writes the data to the JSON file
      window.inputText = "";
      clearEnemies();
      clearProjectiles();
      window.newGame();
      window.setCurrentScreen(Screen.START); // Displays the end menu
    } else if (menu.backButton.isClicked(mouseX, mouseY)) {
      menu.leaderBoardFetch();
    } else {
      window.setInputActive(true);
    }
  }

  /**
   * Handles mouse clicks in the game.
   *
   * @param mouseX the x-coordinate of the mouse
   * @param mouseY the y-coordinate of the mouse
   */
  public void handleMouseClicks(int mouseX, int mouseY) {
    if (!Window.gameOn) {
      if (currentScreen == Screen.START) {
        screenStartHelper(mouseX, mouseY);
      } else if (currentScreen == Screen.LEADERBOARD || currentScreen == Screen.CONTROLS) {
        // Leaderboard/Controls menu - button settings
        if (menu.backButton.isClicked(mouseX, mouseY)) {
          // If the back button is pressed from the leaderboard/controls menu
          window.setCurrentScreen(Screen.START);
          // Return to start menu
        }
      } else if (currentScreen == Screen.SCORE) {
        // Score menu - button settings
        window.setGameOn(false);
        screenScoreHelper(mouseX, mouseY);

      } else if (currentScreen == Screen.PAUSE) {
        // Pause menu - button settings
        if (menu.resumeButton.isClicked(mouseX, mouseY)) {
          // If the resume button is clicked, the boolean switch turns the game back on
          window.setGameOn(true);
          window.setCurrentScreen(Screen.START); // Sets the current menu back to the start menu
        }
      }
    }
  }

  /**
   * draw() Option 2: Displays the menu screen.
   */
  public void draw() {
    switch (window.currentScreen) {
      case START -> drawStart(); // Start menu case
      case LEADERBOARD -> drawLeaderboard(); // Leaderboard menu case
      case CONTROLS -> drawControls(); // Game controls menu case
      case PAUSE -> drawPause(); // Paused menu case
      case SCORE -> drawScore(); // Save score menu case
      case END -> drawEnd(); // End menu case
    }
  }

  /**
   * Displays the start menu.
   */
  public void drawStart() {
    window.image(mainMenuImage, 0, 0, window.width, window.height);
    menu.newGameButton.display();
    menu.leaderboardButton.display();
    menu.controlsButton.display();
  }

  /**
   * Displays the leaderboard menu.
   */
  public void drawLeaderboard() {
    window.image(leaderboardImage, 0, 0, window.width, window.height);
    displayLeaderboard();
    window.textFont(window.createFont("fonts/Nintendo NES Font.ttf", 5));
    menu.backButton.display();
  }

  /**
   * Displays the game control menu.
   */
  public void drawControls() {
    window.image(gameControlsImage, window.width / 2f - gameControlsImage.width / 2f,
        window.height / 2f - gameControlsImage.height / 2f);
    menu.backButton.display();
  }

  /**
   * Displays the paused menu.
   */
  public void drawPause() {
    window.image(pausedMenuImage, window.width / 2f - pausedMenuImage.width / 2f,
        window.height / 2f - pausedMenuImage.height / 2f);
    menu.resumeButton.display();
  }

  /**
   * Displays the save score menu.
   */
  public void drawScore() {
    inputActive = true;
    window.image(deathImage, 0, 0, window.width, window.height);
    window.saveScore();
    menu.continueButton.display();
  }

  /**
   * Displays the end menu.
   */
  public void drawEnd() {
    window.image(endMenuImage, 0, 0, window.width, window.height);
    menu.newGameButton.display();
    menu.leaderboardButton.display();
    menu.controlsButton.display();
    menu.quitButton.display();
  }

  /**
   * Displays the leaderboard.
   * Gets the leaderboard data from the Firebase database.
   */
  public void displayLeaderboard() {
    window.textAlign(window.CENTER, window.CENTER);
    window.textSize(55);
    window.fill(176, 212, 222);

    ArrayList<String> leaderboardList = menu.leaderboard.getLeaderboardList();
    window.textAlign(window.LEFT, window.CENTER);
    window.textSize(25);
    window.textFont(window.createFont("Courier New", 25));

    // For loop that prints out the lines of the leaderboard list
    float yPos = 325;
    for (String line : leaderboardList) {
      if (line.isEmpty()) {
        continue;
      }
      window.text(line, window.width / 2f - 225, yPos);
      yPos += 25;
    }
  }

  /**
   * Displays the pause screen.
   */
  public void displayPauseScreen() {
    Window.gameOn = false;
    window.image(pausedMenuImage, 0, 0, window.width, window.height);
    menu.resumeButton.display();
  }

  /**
   * Removes enemy projectiles from the game Window.
   */
  public void clearProjectiles() {
    for (Goblin ignored : Window.goblins) {
      Goblin.axes.clear();
    }
    for (Troll ignored : Window.trolls) {
      Troll.boulders.clear();
    }
    for (Skeleton ignored : Window.skeletons) {
      Skeleton.arrows.clear();
    }
  }

  /**
   * Removes the enemies from the game Window.
   */
  public void clearEnemies() {
    Window.goblins.clear();
    Window.trolls.clear();
    Window.skeletons.clear();
  }
}

