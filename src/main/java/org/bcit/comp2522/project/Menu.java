package org.bcit.comp2522.project;

import processing.core.PImage;

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

  //Instantiate menu backgrounds
  PImage mainMenuImage;
  PImage gameControlsImage;
  PImage pausedMenuImage;
  PImage endMenuImage;
  PImage leaderboardImage;



  //constructor
  public Menu(Window window, Button newGameButton, Button leaderboardButton, Button controlsButton,
              Button backButton, Button quitButton,
              Button continueButton, Button resumeButton) {
    this.window = window;
    leaderboard = new FirebaseLeaderboard(window);
    this.newGameButton = newGameButton;
    this.leaderboardButton = leaderboardButton;
    this.controlsButton = controlsButton;
    this.backButton = backButton;
    this.quitButton = quitButton;
    this.continueButton = continueButton;
    this.resumeButton = resumeButton;


  }



  public void menuButtons() {

    this.newGameButton = new Button(window, window.getWidth() / 2 - 100, window.getHeight() / 2 - 100, 200, 50, "New Game");
    this.leaderboardButton = new Button(window, window.getWidth() / 2 - 100, window.getHeight() / 2 - 25, 200, 50, "Leaderboard");
    this.controlsButton = new Button(window, window.getWidth() / 2 - 100, window.getHeight() / 2 + 50, 200, 50, "Controls");
    this.backButton = new Button(window, window.getWidth() / 2 - 100, window.getHeight() - 100, 200, 50, "Back");
    this.quitButton = new Button(window, window.getWidth() / 2 - 100, window.getHeight() / 2 + 125, 200, 50, "Quit");
    this.continueButton = new Button(window, window.getWidth() / 2 - 100, window.getHeight() / 2 + 100, 200, 50, "Continue");
    this.resumeButton = new Button(window, window.getWidth() / 2 - 100, window.getHeight() / 2 - 25, 200, 50, "Resume");


  }

  public void displayNewGameButton() {
    newGameButton.display();
  }

  public void displayLeaderboardButton() {
    leaderboardButton.display();
  }

  public void displayControlsButton() {
    controlsButton.display();
  }

  public void displayBackButton() {
    backButton.display();
  }

  public void displayQuitButton() {
    quitButton.display();
  }

  public void displayContinueButton() {
    continueButton.display();
  }

  public void displayResumeButton() {
    resumeButton.display();
  }

  public boolean newGameButtonISClicked(float mouseX, float mouseY) {
    return newGameButton.isClicked(mouseX, mouseY);
  }

  public boolean leaderboardButtonISClicked(float mouseX, float mouseY) {
    return leaderboardButton.isClicked(mouseX, mouseY);
  }

  public boolean controlsButtonISClicked(float mouseX, float mouseY) {
    return controlsButton.isClicked(mouseX, mouseY);
  }

  public boolean backButtonISClicked(float mouseX, float mouseY) {
    return backButton.isClicked(mouseX, mouseY);
  }

  public boolean resumeButtonISClicked(float mouseX, float mouseY) {
    return resumeButton.isClicked(mouseX, mouseY);
  }

  public boolean continueButtonISClicked(float mouseX, float mouseY) {
    return continueButton.isClicked(mouseX, mouseY);
  }

  public void leaderBoardFetch() {
    leaderboard.fetchLeaderboardData();
  }

  public void setLeaderboardSave(String name, int score) {
    leaderboard.savePlayerToDatabase(name, score);
  }






}
