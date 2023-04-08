package org.bcit.comp2522.project;

/* This class is used to save the players name and score to the database.
    *
    * @author Will Ondrik
    * @author Gathrean Dela Cruz
    * @version 1.0
    */
public class PlayerDB {

  private String name;
  private int score;


  public PlayerDB() {
  }


  /**
   * Constructs a new PlayerDB object to store Player data.
   *
   * @param name  The Player's name
   * @param score The Player's score
   */
  public PlayerDB(String name, int score) {
    this.name = name;
    this.score = score;
  }

  /**
   * Gets the players name.
   *
   * @return name of the player
   */
  public String getName() {
    return name;
  }




  /**
   * Sets the players name.
   *
   * @param name name of the player
   */
  public void setName(String name) {
    this.name = name;
  }




  /**
   * Returns the Player's score.
   *
   * @return score of the player
   */
  public int getScore() {
    return score;
  }


  /**
   * Sets the Player's score.
   * @param expectedScore The expected score of the Player
   */
  public void setScore(int expectedScore) {
    this.score = expectedScore;
  }
}
