package org.bcit.comp2522.project;

import processing.data.JSONArray;
import processing.data.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static processing.core.PApplet.loadJSONObject;

/**
 * Personal Best class.
 * Reads and writes the highest score and wave number to a JSON file.
 *
 * @author Bardia Timouri
 * @version 1.0
 */
public class PersonalBest {
  /**
   * The highest score and wave number.
   */
  static JSONArray highestScore = new JSONArray();

  /**
   * The highest score and wave number.
   */
  private static int highestScoreValue;

  /**
   * The highest score and wave number.
   */
  private static int highestWaveNumberValue;

  /**
   * PersonalBest constructor.
   */
  public PersonalBest() {

  }

  /**
   * Adds the score to the JSONArray.
   *
   * @param score
   */
  public static void addScore(int score) {
    JSONObject newScore = new JSONObject();
    newScore.setInt("score", score);
    highestScore.append(newScore);
  }

  /**
   * Adds the wave number to the JSONArray.
   *
   * @param waveNumber
   */
  public static void addWaveNumber(int waveNumber) {
    JSONObject newWaveNumber = new JSONObject();
    newWaveNumber.setInt("waveNumber", waveNumber);
    highestScore.append(newWaveNumber);
  }

  /**
   * Reads the highest score and wave number from a JSON file.
   */
  public static void writeToJSON() {
    //find the highest score and wave number
    int highestScoreValue = 0;
    int highestWaveNumberValue = 0;
    for (int i = 0; i < highestScore.size(); i++) {
      JSONObject score = highestScore.getJSONObject(i);
      if (score.hasKey("score") && score.getInt("score") > highestScoreValue) {
        highestScoreValue = score.getInt("score");
      } else if (score.hasKey("waveNumber") && score.getInt("waveNumber") > highestWaveNumberValue) {
        highestWaveNumberValue = score.getInt("waveNumber");
      }
    }

    //create a new JSONObject with the highest score and wave number
    JSONObject highestScoreObject = new JSONObject();
    highestScoreObject.setInt("highestScore", highestScoreValue);
    highestScoreObject.setInt("highestWaveNumber", highestWaveNumberValue);

    //write the highest score and wave number to a file
    try (FileWriter file = new FileWriter("Personal_Best.json")) {
      file.write(highestScoreObject.toString());
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Reads the highest score and wave number from a JSON file.
   */
  public static void readFromJSON() {
    // read the highest score and wave number from a file
    File file = new File("Personal_Best.json");

    if (!file.exists()) {
      // create the file with initial values of 0 for highest score and wave number
      JSONObject initialScores = new JSONObject();
      initialScores.setInt("highestScore", 0);
      initialScores.setInt("highestWaveNumber", 0);

      try (FileWriter fileWriter = new FileWriter(file)) {
        fileWriter.write(initialScores.toString());
        fileWriter.flush();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    // load the highest score and wave number from the file
    JSONObject highestScoreObject = loadJSONObject(file);
    int highestScoreValue = highestScoreObject.getInt("highestScore");
    int highestWaveNumberValue = highestScoreObject.getInt("highestWaveNumber");

    // set the highest score and wave number
    setHighestScore(highestScoreValue);
    setHighestWaveNumber(highestWaveNumberValue);
  }


  /**
   * Sets the highest score.
   *
   * @param score
   */
  public static void setHighestScore(int score) {
    highestScoreValue = score;
  }

  /**
   * Sets the highest wave number.
   *
   * @param waveNumber
   */
  public static void setHighestWaveNumber(int waveNumber) {
    highestWaveNumberValue = waveNumber;
  }

  /**
   * Gets the highest score.
   *
   * @return highestScoreValue
   */
  public static int getHighestScore() {
    return highestScoreValue;
  }

  /**
   * Gets the highest wave number.
   *
   * @return highestWaveNumberValue
   */
  public static int getHighestWaveNumber() {
    return highestWaveNumberValue;
  }


}
