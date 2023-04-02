package org.bcit.comp2522.project;

import processing.core.PImage;

import java.util.ArrayList;

/**
 * The Menu class contains methods for creating and positioning menu buttons, fetching leaderboard data,
 * and saving Player data to the Firebase Realtime Database.
 *
 * @author Will Ondrik
 * @author Bardia Timouri
 * @author Gathrean Dela Cruz
 */
public class Menu {

  //Instantiate Firebase database for the leaderboard
  FirebaseLeaderboard leaderboard;
  Window window;

  //Instantiate menu buttons
  Button newGameButton;
  Button leaderboardButton;
  Button controlsButton;
  Button backButton;
  Button quitButton;
  Button continueButton;
  Button resumeButton;


  /**
   * Menu constructor.
   *
   * @param window the window that the menu will be drawn on
   */
  public Menu(Window window) {
    this.window = window;
    leaderboard = new FirebaseLeaderboard(window);
    menuButtons();
  }


  /**
   * Creates the menu buttons, sets their position and label text.
   */
  public void menuButtons() {
    window.textFont(window.createFont("fonts/Nintendo NES Font.ttf", 5));
    int buttonW = 300;
    int buttonH = 50;
    float halfWindowWidth = window.getWidth() / 2 - buttonW / 2f; // Adjust for button width
    float halfWindowHeight = window.getHeight() / 2;

    // Set horizontal and vertical alignment to center
    window.textAlign(window.CENTER, window.CENTER);

    this.newGameButton     = new Button(window, halfWindowWidth, halfWindowHeight - 100   , buttonW, buttonH, "NEW GAME");
    this.leaderboardButton = new Button(window, halfWindowWidth, halfWindowHeight - 25    , buttonW, buttonH, "LEADERBOARD");
    this.controlsButton    = new Button(window, halfWindowWidth, halfWindowHeight + 50    , buttonW, buttonH, "CONTROLS");
    this.backButton        = new Button(window, halfWindowWidth, window.getHeight() - 100 , buttonW, buttonH, "BACK");
    this.quitButton        = new Button(window, halfWindowWidth, halfWindowHeight + 125   , buttonW, buttonH, "QUIT");
    this.continueButton    = new Button(window, halfWindowWidth, window.getHeight() - 100   , buttonW, buttonH, "CONTINUE");
    this.resumeButton      = new Button(window, halfWindowWidth, halfWindowHeight - 25    , buttonW, buttonH, "RESUME");
  }


  /**
   * Fetches the leaderboard data from the Firebase Realtime Database.
   */
  public void leaderBoardFetch() {
    leaderboard.fetchLeaderboardData();
  }


  /**
   * Saves a Player's name and score to the Firebase Realtime Database.
   *
   * @param name the Player's name
   * @param score the Player's score
   */
  public void setLeaderboardSave(String name, int score) {
    leaderboard.savePlayerToDatabase(name, score);
  }

}
