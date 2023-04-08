package org.bcit.comp2522.project;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import processing.core.PApplet;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FirebaseLeaderboard {

  private PApplet pApplet;
  private final ArrayList<String> leaderboardList = new ArrayList<>();

  public FirebaseLeaderboard(PApplet pApplet) {
    this.pApplet = pApplet;

    //Initialize the Firebase Admin SDK
    FileInputStream serviceAccount = null;
    try {
      serviceAccount = new FileInputStream("key/dungeonkey.json");
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }

    FirebaseOptions options = null;
    try {
      options = new FirebaseOptions.Builder()
          .setCredentials(GoogleCredentials.fromStream(serviceAccount))
          .setDatabaseUrl("https://dungeonquad-872af-default-rtdb.firebaseio.com/")
          .build();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    FirebaseApp.initializeApp(options);

    fetchLeaderboardData();
  }

  /*
  Method to get leaderboard list
   */
  public ArrayList<String> getLeaderboardList() {
    return leaderboardList;
  }

  public void savePlayerToDatabase(String playerName, int playerScore) {
    //Create references to the database and pushing new player data into the database
    DatabaseReference leaderboardRef = FirebaseDatabase.getInstance().getReference("leaderboard");
    DatabaseReference newPlayerRef = leaderboardRef.push();

    //Create a player data map to store the player's name and score
    Map<String, Object> playerData = new HashMap<>();
    playerData.put("name", playerName);
    playerData.put("score", playerScore);

    newPlayerRef.setValueAsync(playerData);
  }

  public void fetchLeaderboardData() {
    //Create reference to the leaderboard collection
    DatabaseReference leaderboardRef = FirebaseDatabase.getInstance().getReference("leaderboard");
    //Query that fetches the top 10 scores
    Query topScores = leaderboardRef.orderByChild("score").limitToLast(10);

    //Fetch leaderboard data using an event listener
    topScores.addListenerForSingleValueEvent(
        new ValueEventListener() {
          public void onDataChange(DataSnapshot dataSnapshot) {
            leaderboardList.clear(); //Clear the previous leaderboard data
            int rank = 10;
            for (DataSnapshot entry : dataSnapshot.getChildren()) {
              PlayerDB player = entry.getValue(PlayerDB.class);
              String name = player.getName();
              int score = player.getScore();

              //Format of displaying player rank, name and score in the leaderboard
              String formattedEntry = String.format("%-5s%-20s%d", rank, name, score);
              //Add the entry at the beginning of the list
              leaderboardList.add(0, formattedEntry);
              rank--;
            }
          }


          /*
          If there's a database error - print message.
           */
          @Override
          public void onCancelled(DatabaseError databaseError) {
            System.out.println("Database error");
          }
        });
  }

}