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
    this.mainMenuImage = window.loadImage("images/menu/background.jpg");
    this.gameControlsImage = window.loadImage("images/menu/controls.jpg");
    this.pausedMenuImage = window.loadImage("images/menu/background.jpg");
    this.endMenuImage = window.loadImage("images/menu/background.jpg");
    this.leaderboardImage = window.loadImage("images/menu/background.jpg");
  }

  public void screenStartHelper(float mouseX, float mouseY) {
    // Start menu - button settings
    if (menu.newGameButton.isClicked(mouseX, mouseY)) {
      window.setGameOn(true); // Activates the game
    } else if (menu.leaderboardButton.isClicked(mouseX, mouseY)) {
      menu.leaderBoardFetch(); // Fetches the leaderboard data
      window.setCurrentScreen(Screen.LEADERBOARD); // Displays the leaderboard menu
    } else if (menu.controlsButton.isClicked(mouseX, mouseY)) {
      window.setCurrentScreen(Screen.CONTROLS); // Displays the controls menu
    }
  }

  public void screenScoreHelper(float mouseX, float mouseY) {
    if (menu.continueButton.isClicked(mouseX, mouseY)) {
      menu.setLeaderboardSave(window.getInputText(), window.getScore()); // Saves the inputted text from the player and their score
      window.inputText = "";
      window.setCurrentScreen(Screen.END); // Displays the end menu
    } else if (menu.leaderboardButton.isClicked(mouseX, mouseY)) {
      menu.leaderBoardFetch();
      window.setCurrentScreen(Screen.LEADERBOARD); // Displays the leaderboard menu
    } else {
      window.setInputActive(true); // Boolean that allows key pressed to work for the players name input (textInput)
    }
  }

  public void handleMouseClicks(int mouseX, int mouseY) {


    if (!window.getGameOn()) {
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
        screenScoreHelper(mouseX, mouseY);
      } else if (currentScreen == Screen.END) {
        // Button settings for end menu
        if (menu.leaderboardButton.isClicked(mouseX, mouseY)) {
          menu.leaderBoardFetch(); // Fetch leaderboard data
          window.setCurrentScreen(Screen.LEADERBOARD); // Displays leaderboard menu
        }
        if (menu.newGameButton.isClicked(mouseX, mouseY)) {
          window.newGame(); // If the new game button is pressed, it resets the game state and starts a new game
        }
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
        window.image(leaderboardImage, 0, 0, window.width, window.height);
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
    menu.resumeButton.display();
  }
}

