package org.bcit.comp2522.project;

import processing.core.PImage;
import java.util.ArrayList;


/**
 * Handles the menu screens.
 *
 * @author Will Ondrik
 * @author Bardia Timouri
 * @author Gathrean Dela Cruz
 * @version 1.0
 */
public class MenuHandler {

  private final Window window;
  private final Menu menu;
  private final Screen currentScreen;

  boolean inputActive = false;
  private final PImage mainMenuImage;
  private final PImage gameControlsImage;
  private final PImage pausedMenuImage;
  private final PImage endMenuImage;
  private final PImage leaderboardImage;
  private final PImage deathImage;


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

  public void screenScoreHelper(float mouseX, float mouseY) {
    if (menu.continueButton.isClicked(mouseX, mouseY)) {
      menu.setLeaderboardSave(window.getInputText(), window.getScore()); // Saves the inputted text from the player and their score
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

  public void handleMouseClicks(int mouseX, int mouseY) {


    if (!Window.gameOn) {
      if (currentScreen == Screen.START) {
        screenStartHelper(mouseX, mouseY);
      } else if (currentScreen == Screen.LEADERBOARD || currentScreen == Screen.CONTROLS) {
        // Leaderboard/Controls menu - button settings
        if (menu.backButton.isClicked(mouseX, mouseY)) {
          window.setCurrentScreen(Screen.START); // If the back button is pressed from the leaderboard/controls menu
          // Return to start menu
        }
      } else if (currentScreen == Screen.SCORE) {
        // Score menu - button settings
        window.setGameOn(false);
        screenScoreHelper(mouseX, mouseY);

      } else if (currentScreen == Screen.PAUSE) {
        // Pause menu - button settings
        if (menu.resumeButton.isClicked(mouseX, mouseY)) {
          window.setGameOn(true); // If the resume button is clicked, the boolean switch turns the game back on
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
      case START -> {      // Start menu case
        window.image(mainMenuImage, 0, 0, window.width, window.height);
        menu.newGameButton.display();
        menu.leaderboardButton.display();
        menu.controlsButton.display();

      }
      case LEADERBOARD -> {       // Leaderboard menu case
        window.image(leaderboardImage, 0, 0, window.width, window.height);
        displayLeaderboard();
        window.textFont(window.createFont("fonts/Nintendo NES Font.ttf", 5)); // This makes sure back button isn't affected by leaderboard font
        menu.backButton.display();
      }
      case CONTROLS -> {      // Game controls menu case
        window.image(gameControlsImage, window.width / 2f - gameControlsImage.width / 2f, window.height / 2f - gameControlsImage.height / 2f);
        menu.backButton.display();
      }
      case PAUSE -> {       // Paused menu case
        window.image(pausedMenuImage, window.width / 2f - pausedMenuImage.width / 2f, window.height / 2f - pausedMenuImage.height / 2f);
        menu.resumeButton.display();
      }
      case SCORE -> {       // Save score menu case
        inputActive = true;
        window.image(deathImage, 0, 0, window.width, window.height);
        window.saveScore();
        menu.continueButton.display();
      }
      case END -> {      // End menu case
        window.image(endMenuImage, 0, 0, window.width, window.height);
        menu.newGameButton.display();
        menu.leaderboardButton.display();
        menu.controlsButton.display();
        menu.quitButton.display();
      }
    }
  }

  /**
   * Displays the leaderboard.
   * Gets the leaderboard data from the Firebase database.
   */
  public void displayLeaderboard() {
//    int blur = 3;
//    window.filter(window.BLUR, blur);
    window.textAlign(window.CENTER, window.CENTER);
    window.textSize(55);
    window.fill(176, 212, 222);
//    window.text("Leaderboard", window.width / 2f, 30);

    ArrayList<String> leaderboardList = menu.leaderboard.getLeaderboardList();
    window.textAlign(window.LEFT, window.CENTER);
    window.textSize(25);
    window.textFont(window.createFont("Courier New", 25));
    float yPos = 325;

    // For loop that prints out the lines of the leaderboard list
    for (String line : leaderboardList) {
      if (line.isEmpty()) continue;
      window.text(line, window.width / 2f - 225, yPos);
      yPos += 25;
    }
  }
  /**
   * Displays the pause screen.
   */
  public void displayPauseScreen() {
    window.gameOn = false;
//    window.image(pausedMenuImage, window.width / 2f - pausedMenuImage.width / 2f, window.height / 2f - pausedMenuImage.height / 2f);
        window.image(pausedMenuImage, 0, 0, window.width, window.height);
    menu.resumeButton.display();
  }

  public void clearProjectiles() {
    for (Goblin goblin : window.goblins) {
      goblin.axes.clear();
    }
    for (Troll troll : window.trolls) {
      troll.boulders.clear();
    }
    for (Skeleton skeleton : window.skeletons) {
      skeleton.arrows.clear();
    }
  }

  public void clearEnemies(){
    Window.goblins.clear();
    Window.trolls.clear();
    Window.skeletons.clear();
  }
}

