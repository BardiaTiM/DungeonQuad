package org.bcit.comp2522.project;

import processing.core.PImage;

import java.util.ArrayList;

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

  ArrayList<Button> buttons;

  //Instantiate menu backgrounds
  PImage mainMenuImage;
  PImage gameControlsImage;
  PImage pausedMenuImage;
  PImage endMenuImage;
  PImage leaderboardImage;



  //constructor
  public Menu(Window window) {
    this.window = window;
    leaderboard = new FirebaseLeaderboard(window);
    menuButtons();
  }



  public void menuButtons() {
    float width = window.getWidth() / 2 - 100;
    this.newGameButton     = new Button(window, window.getWidth() / 2 - 100, window.getHeight() / 2 - 100 , 200, 50, "New Game");
    this.leaderboardButton = new Button(window, window.getWidth() / 2 - 100, window.getHeight() / 2 - 25  , 200, 50, "Leaderboard");
    this.controlsButton    = new Button(window, window.getWidth() / 2 - 100, window.getHeight() / 2 + 50  , 200, 50, "Controls");
    this.backButton        = new Button(window, window.getWidth() / 2 - 100, window.getHeight() - 100     , 200, 50, "Back");
    this.quitButton        = new Button(window, window.getWidth() / 2 - 100, window.getHeight() / 2 + 125 , 200, 50, "Quit");
    this.continueButton    = new Button(window, window.getWidth() / 2 - 100, window.getHeight() / 2 + 100 , 200, 50, "Continue");
    this.resumeButton      = new Button(window, window.getWidth() / 2 - 100, window.getHeight() / 2 - 25  , 200, 50, "Resume");


  }

  public void leaderBoardFetch() {
    leaderboard.fetchLeaderboardData();
  }

  public void setLeaderboardSave(String name, int score) {
    leaderboard.savePlayerToDatabase(name, score);
  }






}
