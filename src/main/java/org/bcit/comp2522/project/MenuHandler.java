package org.bcit.comp2522.project;

import processing.core.PImage;

import java.util.ArrayList;

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


  public MenuHandler(Window window) {
    this.window = window;
    this.menu = window.getMenu();
    this.currentScreen = window.getCurrentScreen();
    this.mainMenuImage = window.loadImage("background.jpg");
    this.gameControlsImage = window.loadImage("gamecontrolsjava.jpg");
    this.pausedMenuImage = window.loadImage("background.jpg");
    this.endMenuImage = window.loadImage("background.jpg");
    this.leaderboardImage = window.loadImage("background.jpg");
  }

  public void handleMouseClicks(int mouseX, int mouseY) {
    if (!window.getGameOn()) {
      if (currentScreen == Screen.START) {
        // Start menu - button settings
        if (menu.newGameButtonISClicked(mouseX, mouseY)) {
          window.setGameOn(true); // Activates the game
        } else if (menu.leaderboardButtonISClicked(mouseX, mouseY)) {
          menu.leaderBoardFetch(); // Fetches the leaderboard data
          window.setCurrentScreen(Screen.LEADERBOARD); // Displays the leaderboard menu
        } else if (menu.controlsButtonISClicked(mouseX, mouseY)) {
          window.setCurrentScreen(Screen.CONTROLS); // Displays the controls menu
        }
      } else if (currentScreen == Screen.LEADERBOARD || currentScreen == Screen.CONTROLS) {
        // Leaderboard/Controls menu - button settings
        if (menu.backButtonISClicked(mouseX, mouseY)) {
          window.setCurrentScreen(Screen.START); // If the back button is pressed from the leaderboard/controls menu
          // Return to start menu
        }
      } else if (currentScreen == Screen.SCORE) {
        // Score menu - button settings
        if (menu.continueButtonISClicked(mouseX, mouseY)) {
          menu.setLeaderboardSave(window.getInputText(), window.getScore()); // Saves the inputted text from the player and their score
          window.inputText = "";
          window.setCurrentScreen(Screen.END); // Displays the end menu
        } else if (menu.leaderboardButtonISClicked(mouseX, mouseY)) {
          menu.leaderBoardFetch();
          window.setCurrentScreen(Screen.LEADERBOARD); // Displays the leaderboard menu
        } else {
          window.setInputActive(true); // Boolean that allows key pressed to work for the players name input (textInput)
        }
      } else if (currentScreen == Screen.END) {
        // Button settings for end menu
        if (menu.leaderboardButtonISClicked(mouseX, mouseY)) {
          menu.leaderBoardFetch(); // Fetch leaderboard data
          window.setCurrentScreen(Screen.LEADERBOARD); // Displays leaderboard menu
        }
        if (menu.newGameButtonISClicked(mouseX, mouseY)) {
          window.newGame(); // If the new game button is pressed, it resets the game state and starts a new game
        }
      } else if (currentScreen == Screen.PAUSE) {
        // Pause menu - button settings
        if (menu.resumeButtonISClicked(mouseX, mouseY)) {
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
        menu.displayNewGameButton();
        menu.displayLeaderboardButton();
        menu.displayControlsButton();
      }
      case LEADERBOARD -> {       // Leaderboard menu case
        window.image(leaderboardImage, 0, 0, window.width, window.height);
        displayLeaderboard();
        menu.displayBackButton();
      }
      case CONTROLS -> {      // Game controls menu case
        window.image(gameControlsImage, window.width / 2f - gameControlsImage.width / 2f, window.height / 2f - gameControlsImage.height / 2f);
        menu.displayBackButton();
      }
      case PAUSE -> {       // Paused menu case
        window.image(pausedMenuImage, window.width / 2f - pausedMenuImage.width / 2f, window.height / 2f - pausedMenuImage.height / 2f);
        menu.displayResumeButton();
      }
      case SCORE -> {       // Save score menu case
        inputActive = true;
        window.image(leaderboardImage, 0, 0, window.width, window.height);
        window.saveScore();
        menu.displayContinueButton();
      }
      case END -> {      // End menu case
        window.image(endMenuImage, 0, 0, window.width, window.height);
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
  public void displayLeaderboard() {
    int blur = 3;
    window.filter(window.BLUR, blur);
    window.textAlign(window.CENTER, window.CENTER);
    window.textSize(55);
    window.fill(255, 0, 0);
    window.text("Leaderboard", window.width / 2f, 30);

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
    window.image(pausedMenuImage, window.width / 2f - pausedMenuImage.width / 2f, window.height / 2f - pausedMenuImage.height / 2f);
    menu.displayResumeButton();
  }
}

