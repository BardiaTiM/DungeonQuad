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


/**
 * This class is used to interact with the Firebase Realtime Database to save and retrieve Player data.
 */
public class FirebaseLeaderboard {


  //Processing pApplet for drawing in the Window
  private PApplet pApplet;


  //Arraylist to store the leaderboard data
  private ArrayList<String> leaderboardList = new ArrayList<>();


  //String variable to hold the text of the leaderboard
  private String leaderboardText = "";




  /**
   * Constructor to initialize the Firebase Admin SDK and fetch leaderboard data from Firebase Realtime Database.
   *
   * @param pApplet the game Window
   */
  public FirebaseLeaderboard(PApplet pApplet) {
    this.pApplet = pApplet;


    //Initialize the Firebase Admin SDK
    FileInputStream serviceAccount =
        null;
    try {
      //Reads in the Google Cloud Service Account key file
      serviceAccount = new FileInputStream("dungeonkey.json");
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }


    FirebaseOptions options = null;
    try {
      options = new FirebaseOptions.Builder()
          //Authenticare with Google Cloud using teh service account key file
          .setCredentials(GoogleCredentials.fromStream(serviceAccount))


          //URL of the Firebase Realtime Database to connect to
          .setDatabaseUrl("https://dungeonquad-872af-default-rtdb.firebaseio.com/")
          .build();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }


    //Initialize the Firebase application
    FirebaseApp.initializeApp(options);


    //Fetch the leaderboard data from the Firebase Realtime Database
    fetchLeaderboardData();
  }


  /**
   * Method to get the leaderboard list.
   *
   * @return leaderboardList
   */
  public ArrayList<String> getLeaderboardList() {
    return leaderboardList;
  }




  /**
   * Method to save Player data to the Firebase Realtime Database.
   *
   * @param playerName the Player's name
   * @param playerScore the Player's score
   */
  public void savePlayerToDatabase(String playerName, int playerScore) {
    //Reference to the "leaderboard" collection in the Firebase database
    DatabaseReference leaderboardRef = FirebaseDatabase.getInstance().getReference("leaderboard");


    //Create a new reference for the new player's data
    DatabaseReference newPlayerRef = leaderboardRef.push();


    //Create a new HashMap object to store the player's data
    Map<String, Object> playerData = new HashMap<>();


    //Adds the Player's name to the HashMap
    playerData.put("name", playerName);


    //Adds the Player's score to HashMap
    playerData.put("score", playerScore);


    //Add the Player's data to the database asynchronously
    newPlayerRef.setValueAsync(playerData);
  }




  /**
   * Method to fetch the leaderboard data from the Firebase Realtime Database and update the leaderboard list.
   */
  public void fetchLeaderboardData() {
    //Reference to the "leaderboard" collection in the Firebase database
    DatabaseReference leaderboardRef = FirebaseDatabase.getInstance().getReference("leaderboard");


    //Query that retrieves the top 10 scores in the "leaderboard" collection, ordered by score
    Query topScores = leaderboardRef.orderByChild("score").limitToLast(10);


    //Fetch leaderboard data using an event listener
    topScores.addListenerForSingleValueEvent(


        //Asynchronous event listener for the database reference
        new ValueEventListener() {


          /**
           * This method is called once with the initial value and again whenever the data at this location is updated.
           *
           * @param dataSnapshot The current data at the location
           */
          public void onDataChange(DataSnapshot dataSnapshot) {
            leaderboardList.clear(); //Clear the previous leaderboard data
            int rank = 10; //Set the initial rank to 0


            //Loop through the top 10 scores
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




          /**
           * Method that prints an error message if there's a database error during the data fetch.
           *
           * @param databaseError A description of the error that occurred
           */
          @Override
          public void onCancelled(DatabaseError databaseError) {
            System.out.println("Database error");
          }
        });
  }


}
