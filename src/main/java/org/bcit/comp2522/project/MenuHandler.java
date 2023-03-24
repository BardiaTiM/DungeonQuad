package org.bcit.comp2522.project;
public class MenuHandler {

  private Window window;
  private Menu menu;
  private Screen currentScreen;

  public MenuHandler(Window window) {
    this.window = window;
    this.menu = window.getMenu();
    this.currentScreen = window.getCurrentScreen();
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
}

