package org.bcit.comp2522.project;

public class PlayerDB {
  private String name;
  private int score;

  public PlayerDB() {
  }

  public PlayerDB(String name, int score) {
    this.name = name;
    this.score = score;
  }

  //Gets the players name to save to the database
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  //Gets the players score so it can be saved to the database
  public int getScore() {
    return score;
  }

}