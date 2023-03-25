package org.bcit.comp2522.project;

import java.util.List;

public class MenuHandler {

  private Window window;
  private Menu menu;
  private Screen currentScreen;

  public MenuHandler(Window window) {
    this.window = window;
    this.menu = window.getMenu();
    this.currentScreen = window.getCurrentScreen();
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
}

