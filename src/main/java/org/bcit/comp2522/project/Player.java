package org.bcit.comp2522.project;

import processing.data.JSONArray;
import processing.data.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Player {
  protected double exp;
  protected String playerName;
  protected int xPos;
  protected int yPos;
  protected float speedBoost;
  protected boolean wings = false;
  protected boolean isOutOfBounds = false;

  /*
   * The array of positions is used to store the player's positions
   */
  JSONArray positions = new JSONArray();

  //getters
  public double getExp() {
    return exp;
  }

  public String getPlayerName() {
    return playerName;
  }

  public int getXPos() {
    return xPos;
  }

  public int getYPos() {
    return yPos;
  }

  public float getSpeedBoost() {
    return speedBoost;
  }

  public boolean getWings() {
    return wings;
  }

  public boolean getIsOutOfBounds() {
    return isOutOfBounds;
  }

  public void setExp(double exp) {
    this.exp = exp;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public void setXPos(int xPos) {
    this.xPos = xPos;
  }

  public void setYPos(int yPos) {
    this.yPos = yPos;
  }

  public void setSpeedBoost(float speedBoost) {
    this.speedBoost = speedBoost;
  }

public void setWings(boolean wings) {
    this.wings = wings;
  }

  public void setIsOutOfBounds(boolean isOutOfBounds) {
    this.isOutOfBounds = isOutOfBounds;
  }


  public Player(String playerName, int xPos, int yPos, float speedBoost, boolean wings, boolean isOutOfBounds) {
    this.playerName = playerName;
    this.xPos = xPos;
    this.yPos = yPos;
    this.speedBoost = speedBoost;
    this.wings = wings;
    this.isOutOfBounds = isOutOfBounds;
  }

  public void move(char key) {
    //keyboard keys to move player every direction (WASD)
    if (key == 'w') {
      yPos -= 5;
    } else if (key == 'a') {
      xPos -= 5;
    } else if (key == 's') {
      yPos += 5;
    } else if (key == 'd') {
      xPos += 5;
    }

    //made a json object to store the player's position
    JSONObject position = new JSONObject();
    position.setInt("x", xPos);
    position.setInt("y", yPos);

    //add the json object to the json array
    positions.append(position);

  }

  public int takeDamage(int damage) {
    return damage;
  }

  public double expGain(double exp) {
    return exp;
  }




  public static void main(String[] args) {
    Player player = new Player("Player", 0, 0, 0, false, false);
    Scanner scanner = new Scanner(System.in);
    char key = ' ';

    //scan for keyboard input
    while (key != 'q') {
      key = scanner.next().charAt(0);
      player.move(key);
//      System.out.println("Player position: (" + player.getXPos() + ", " + player.getYPos() + ")");
    }

    //prints json array to file
    try (FileWriter file = new FileWriter("positions.json")) {
      file.write(player.positions.toString());
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }

    player.takeDamage(10);
    player.expGain(10);
  }
}
