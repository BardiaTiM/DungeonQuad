import org.bcit.comp2522.project.FirebaseLeaderboard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirebaseLeaderBoardTest {
  private PApplet pApplet;
  private FirebaseLeaderboard firebaseLeaderboard;

  @BeforeEach
  void setUp() {
    pApplet = new PApplet();
    firebaseLeaderboard = new FirebaseLeaderboard(pApplet);
  }

  @Test
  void testSavePlayerToDatabase() {
    String playerName = "John";
    int playerScore = 10;
    firebaseLeaderboard.savePlayerToDatabase(playerName, playerScore);

    ArrayList<String> leaderboardList = firebaseLeaderboard.getLeaderboardList();
    boolean isPlayerSaved = false;

    for (String entry : leaderboardList) {
      if (entry.contains(playerName) && entry.contains(Integer.toString(playerScore))) {
        isPlayerSaved = true;
        break;
      }
    }

    assertFalse(isPlayerSaved, "Player data not saved to database.");
  }

}