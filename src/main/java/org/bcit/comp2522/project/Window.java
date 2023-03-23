package org.bcit.comp2522.project;

import java.io.File;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.core.PVector;


/**
 * Window class.
 *
 * @author Gathrean Dela Cruz
 * @author Bardia Timouri
 * @author Will Ondrik
 * @version 1.0
 */
public class Window extends PApplet {

  private Clip clip;
  static ConcurrentLinkedQueue<Bullet> bullets = new ConcurrentLinkedQueue<>();
  Waves waves;
  Sprite player;
  boolean wingsTime = false;
  boolean isBarFull = false;

  float loadingProgress = 0;
  boolean isLoading = true;
  long loadingStartTime;
  static ConcurrentLinkedQueue<Skeleton> skeletons = new ConcurrentLinkedQueue<>();
  static ConcurrentLinkedQueue<Goblin> goblins = new ConcurrentLinkedQueue<>();
  static ConcurrentLinkedQueue<Troll> trolls = new ConcurrentLinkedQueue<>();

  PImage skeletonImage;
  PImage goblinImage;
  PImage trollImage;


  //Instantiate Firebase database for the leaderboard
  FirebaseLeaderboard leaderboard;

  //Instantiate menu buttons
  Button newGameButton;
  Button leaderboardButton;
  Button controlsButton;
  Button backButton;
  Button quitButton;
  Button continueButton;
  Button resumeButton;
  PFont inputFont;
  String inputText = "";
  boolean inputActive = false;
  int score;

  //Instantiate menu backgrounds
  PImage mainMenuImage;
  PImage gameControlsImage;
  PImage pausedMenuImage;
  PImage endMenuImage;
  PImage leaderboardImage;

  //Variable to handle pausing the game
  boolean gameOn = false;



  //Created an enum to handle the different menu states
  enum Screen {
    START, LEADERBOARD, CONTROLS, END, PAUSE, SCORE
  }


  //Set the current screen to the start menu
  Screen currentScreen = Screen.START;

  /**
   * Sets the size of the window.
   */
  public void settings() {
    size(700, 900);
  }

  PImage backgroundImage;
  float bgX = 0;
  float bgY = 0;
  float scrollSpeed = 1.5f; // Adjust this to control the scrolling speed

  int waveNumber = 1;

  /**
   * Sets up the window.
   */
  public void setup() {

    size(700, 900);
    surface.setTitle("DUNGEON QUAD");

    backgroundImage = loadImage("deep_slate.jpg");


    PImage spriteImage = loadImage("mcW0.png");

    player = new Sprite(300, 700, 50, this, new PVector(0, 0));
    player.setSprite(spriteImage); // Default Sprite


    // Load the MP3 file
    try {
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("dungeon.wav"));
      clip = AudioSystem.getClip();
      clip.open(audioInputStream);
    } catch (Exception e) {
      e.printStackTrace();
    }

    clip.loop(Clip.LOOP_CONTINUOUSLY); // Set the clip to loop indefinitely
    waves = new Waves(waveNumber, this, skeletons, goblins, trolls);

    leaderboard = new FirebaseLeaderboard(this);

    // Set up buttons
    newGameButton = new Button(this, width / 2 - 100, height / 2 - 100, 200, 50, "New Game");
    leaderboardButton = new Button(this, width / 2 - 100, height / 2 - 25, 200, 50, "Leaderboard");
    controlsButton = new Button(this, width / 2 - 100, height / 2 + 50, 200, 50, "Controls");
    backButton = new Button(this, width / 2 - 100, height - 100, 200, 50, "Back");
    quitButton = new Button(this, width / 2 - 100, height / 2 + 125, 200, 50, "Quit");
    continueButton = new Button(this, width / 2  - 100, height / 2 + 100, 200, 50, "Continue");
    resumeButton = new Button(this, width / 2 - 100, height / 2 - 25, 200, 50, "Resume");

    // Set up menu images
    mainMenuImage = loadImage("background.jpg");
    gameControlsImage = loadImage("gamecontrolsjava.jpg");
    pausedMenuImage = loadImage("background.jpg");
    endMenuImage = loadImage("background.jpg");
    leaderboardImage = loadImage("background.jpg");


  }




  /**
   * Draws the scrolling background.
   */
  public void drawBackground() {
    // Calculate the background position based on the player's movement
    if (wingsTime) {
      bgX = scrollSpeed * 2;
      bgY += scrollSpeed * 16;
    } else {
      bgY += scrollSpeed;
      bgX = player.direction.x;
      bgY -= player.direction.y;
    }

    // Tile the background image
    int offsetX = (int) (bgX % backgroundImage.width - backgroundImage.width);
    int offsetY = (int) (bgY % backgroundImage.height - backgroundImage.height);

    // Draw the background image
    for (int x = offsetX; x < width; x += backgroundImage.width) {
      for (int y = offsetY; y < height; y += backgroundImage.height) {
        image(backgroundImage, x, y);
      }
    }

  }


  //Displays the input box on the score menu
  //Allows users to input their names and it's saved to the db automatically when continue is pressed
  void saveScore() {
    //need to display the players score
    text("Score: " + score, width / 2, height / 2 - 150);
    text("Enter your name", width / 2, height / 2 - 70);
    inputFont = createFont("Arial", 20, true);
    textFont(inputFont);
    fill(255);
    rect(width / 2 - 100, height / 2 - 50, 200, 50);
    fill(0);
    textAlign(CENTER, CENTER);
    text(inputText, width / 2, height / 2 - 25);
  }


  //This method restarts the game state
  //Allows the new game to be run from when the newgame button is pressed
  public void newGame() {
    player.x = 200;
    player.y = 500;
    player.direction = new PVector(0, 0);
    waves = new Waves(waveNumber);
    bullets.clear();
    gameOn = true;
    currentScreen = Screen.START;
    score = 0;
  }


  //Method that displays the contents of the leaderboard
  //Gets the leaderboard data from Firebase database
  private void displayLeaderboard() {
    textAlign(CENTER, CENTER);
    textSize(32);
    fill(255,0,0);
    text("Leaderboard", width / 2, 30);

    ArrayList<String> leaderboardList = leaderboard.getLeaderboardList();
    textAlign(LEFT, CENTER);
    textSize(25);
    textFont(createFont("Courier New", 20));
    float yPos = 225;

    //For loop that prints out the lines of the leaderboard list
    for (String line : leaderboardList) {
      if (line.isEmpty()) continue;
      text(line, width / 2 - 100, yPos);
      yPos += 25;
    }
  }




  /**
   * Draws the window, different menu states, player, and bullets.
   * The scrolling background is also drawn.
   */
  public void draw() {

    drawBackground(); // Draw the scrolling background

    player.draw();
    player.update(player.direction);


    for (Bullet bullet : bullets) {     // Draw all the bullets in the list
      bullet.draw();
      bullet.update();
      bullet.collide();
    }
    if (currentScreen == Screen.PAUSE){
      switch(currentScreen) {
        case PAUSE:
        gameOn = false;
        image(pausedMenuImage, width / 2 - pausedMenuImage.width / 2, height / 2 - pausedMenuImage.height / 2);
        resumeButton.display();
        break;
      }
    }
    if (!gameOn) {
      switch (currentScreen) {

        //Start menu case
        case START:
          image(mainMenuImage, 0, 0, width, height);
          newGameButton.display();
          leaderboardButton.display();
          controlsButton.display();
          break;

        //Leaderboard menu case
        case LEADERBOARD:
          image(leaderboardImage, 0, 0, width, height);
          displayLeaderboard();
          backButton.display();
          break;

        //Game controls menu case
        case CONTROLS:
          image(gameControlsImage, width / 2 - gameControlsImage.width / 2, height / 2 - gameControlsImage.height / 2);
          backButton.display();
          break;

        //Paused menu case
        case PAUSE:
          image(pausedMenuImage, width / 2 - pausedMenuImage.width / 2, height / 2 - pausedMenuImage.height / 2);
          resumeButton.display();
          break;

          //Save score menu case
        case SCORE:
          inputActive = true;
          image(leaderboardImage, 0, 0, width, height);
          saveScore();
          continueButton.display();
          break;

        //End menu case
        case END:
          image(endMenuImage, 0, 0, width, height);
          newGameButton.display();
          leaderboardButton.display();
          controlsButton.display();
          quitButton.display();
          break;

      }
    } else {
      // Calculate the background position based on the player's movement
      bgY += scrollSpeed;
      bgX -= player.direction.x;
      bgY -= player.direction.y;

      // Tile the background image
      int offsetX = (int) (bgX % backgroundImage.width - backgroundImage.width);
      int offsetY = (int) (bgY % backgroundImage.height - backgroundImage.height);

      for (int x = offsetX; x < width; x += backgroundImage.width) {
        for (int y = offsetY; y < height; y += backgroundImage.height) {
          image(backgroundImage, x, y);
        }
      }

      player.draw();
      player.update(player.direction);


      // Draw all the bullets in the list
      for (Bullet bullet : bullets) {
        bullet.draw();
        bullet.update();
        bullet.collide();
      }
    }
    drawLoadingBar(); // Draw the loading bar

    for (Skeleton skeleton : skeletons) {
      skeleton.draw();
      skeleton.move();
    }

    for (Goblin goblin : goblins) {
      goblin.draw();
      goblin.move();
    }

    for (Troll troll : trolls) {
      troll.draw();
      troll.move();
    }
  

  }

  public void newWave(){
    if(skeletons.isEmpty() && goblins.isEmpty() && trolls.isEmpty()){
      System.out.println("Wave " + waveNumber + " is over!");
    }
    redraw();
  }

  /**
   * Draws the loading bar.
   */
  public void drawLoadingBar() {
    final int barWidth = 100;
    final int barHeight = 20;
    final int barX = 10;
    final int barY = 10;
    final int barBorder = 5;

    long currentTime = System.currentTimeMillis();

    if (isLoading) { // When the bar is loading

      loadingProgress = (float) (currentTime - loadingStartTime) / 3000; // During Wings
      if (loadingProgress >= 1) { // When the bar is full
        isLoading = false;
        loadingStartTime = currentTime;
        isBarFull = true;
//        wingsTime = false;
      }
    } else { // When the bar is unloading

      loadingProgress = 1 - (float) (currentTime - loadingStartTime) / 6000; // Waiting for Wings
      if (loadingProgress <= 0) { // When the bar is empty
        isLoading = true;
        loadingStartTime = currentTime;
//        wingsTime = true;
      }
    }
    strokeWeight(barBorder);
    stroke(0);
    fill(170, 212, 218);

    // Draw the background of the loading bar
    rect(barX, barY, barWidth, barHeight);

    // Draw the progress of the loading bar
    fill(0);
    rect(barX + barWidth - barBorder - (barWidth - 2 * barBorder) * loadingProgress,
        barY + barBorder,
        (barWidth - 2 * barBorder) * loadingProgress,
        barHeight - 2 * barBorder);

    // Draw the text inside the loading bar
    textAlign(CENTER, CENTER);
    fill(255);
    textSize(16);
    text("Wings Time!", barX + barWidth / 2f, barY + barHeight / 2f + 25f);
  }

  /**
   * Moves the player based on the key pressed.
   */
  public void keyPressed() {

    if (inputActive) {
      if (key >= 'a' && key <= 'z' || key >= 'A' && key <= 'Z' || key >= '0' && key <= '9' || key == ' ') {
        if (inputText.length() < 20) {
          inputText += key;
        }
      } else if (key == BACKSPACE && inputText.length() > 0) {
        inputText = inputText.substring(0, inputText.length() - 1);
      }
    }
    //!gameOn keeps the game from running when the menus are being used
    if (!gameOn) {
      // Code for handling input during menu screens
    } else if (currentScreen != Screen.PAUSE && currentScreen != Screen.SCORE) {
    if (keyCode == UP || key == 'w' || key == 'W') {
      if (Sprite.y - player.speed > 0) {
        PImage spriteImage;
        if (!wingsTime) {
          spriteImage = loadImage("mcW0.png");
          player.direction.y = -0.8f;
        } else {
          spriteImage = loadImage("mcW1.png");
          player.direction.y = -2;
        }
        player.setSprite(spriteImage);
      }
    }
    if (keyCode == DOWN || key == 's' || key == 'S') {
      if (Sprite.y + player.speed < height) {
        PImage spriteImage;
        if (!wingsTime) {
          spriteImage = loadImage("mcS0.png");
          player.direction.y = 0.8f;
        } else {
          spriteImage = loadImage("mcS1.png");
          player.direction.y = 2;
        }
        player.setSprite(spriteImage);
      }
    }
    if (keyCode == LEFT || key == 'a' || key == 'A') {
      if (Sprite.x - player.speed > 0) {
        PImage spriteImage;
        if (!wingsTime) {
          spriteImage = loadImage("mcA0.png");
          player.direction.x = -0.8f;
        } else {
          spriteImage = loadImage("mcA1.png");
          player.direction.x = -2;
        }
        player.setSprite(spriteImage);
      }
    }
    if (keyCode == RIGHT || key == 'd' || key == 'D') {
      if (Sprite.x + player.speed < width) {
        PImage spriteImage;
        if (!wingsTime) {
          spriteImage = loadImage("mcD0.png");
          player.direction.x = 0.8f;
        } else {
          spriteImage = loadImage("mcD1.png");
          player.direction.x = 2;
        }
        player.setSprite(spriteImage);
      }
    }

        // Handle pausing and resuming the game
        if (key == 'p' || key == 'P') {
          if (currentScreen == Screen.PAUSE) {
            gameOn = true;
            currentScreen = Screen.START;
          } else if (currentScreen != Screen.SCORE) {
            currentScreen = Screen.PAUSE;
          }
        }

    if (key == ' ' && skeletons.isEmpty() && goblins.isEmpty() && trolls.isEmpty()) {
      waveNumber += 1;
      wingsTime = true;
      waves = new Waves(waveNumber);
      System.out.println("Wave " + waveNumber + " has begun!");
      System.out.println("Skeletons: " + waves.spawnSkeletonAmount());
      System.out.println("Goblins: " + waves.spawnGoblinAmount());
      System.out.println("Trolls: " + waves.spawnTrollAmount());
      ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);

      //Skeletons spawn time
      Runnable skeletonTask = new Runnable() {
        Window window = Window.this;
        PImage skeletonImage = loadImage("skeleton.png");


        float skeletonCount = 0;
        @Override
        public void run() {
          skeletonCount += 1;
          wingsTime = false;
          waves = new Waves(waveNumber);
          if(skeletonCount < waves.spawnSkeletonAmount()){
            executor.schedule(this, 1, TimeUnit.SECONDS);
          }
          if (skeletonCount < waves.spawnSkeletonAmount()) {
            Skeleton skeleton = new Skeleton(100, 100, 100, 1, window, skeletonImage);
            skeletons.add(skeleton);
          }
        }
      };

      executor.schedule(skeletonTask, 1, TimeUnit.SECONDS);

      //Goblins spawn time
      Runnable goblinTask = new Runnable() {
        Window window = Window.this;
        PImage goblinImage = loadImage("goblin.png");

        float goblinCount = 0;
        @Override
        public void run() {
          goblinCount += 1;
          waves = new Waves(waveNumber);
          if(goblinCount < waves.spawnGoblinAmount()){
            executor.schedule(this, 1, TimeUnit.SECONDS);
          }
          if (goblinCount < waves.spawnGoblinAmount()) {
            Goblin goblin = new Goblin(100, 300, 150, 1, window, goblinImage);
            goblins.add(goblin);
          }

        }
      };

      executor.schedule(goblinTask, 1, TimeUnit.SECONDS);

      //Trolls spawn time
      Runnable trollTask = new Runnable() {
        Window window = Window.this;
        PImage trollImage = loadImage("troll.png");

        float trollCount = 0;
        @Override
        public void run() {
          trollCount += 1;
          waves = new Waves(waveNumber);
          if(trollCount < waves.spawnTrollAmount()){
            executor.schedule(this, 1, TimeUnit.SECONDS);
          }
          if (trollCount < waves.spawnTrollAmount()) {
            Troll troll = new Troll(100, 100, 250, 1, window, trollImage);
            trolls.add(troll);
          }
        }
      };

      executor.schedule(trollTask, 1, TimeUnit.SECONDS);
    }
  }

    redraw();
  }

  /**
   * Stops the player when the arrow keys are released.
   */
  public void keyReleased() {
    if (gameOn){
if (keyCode == UP || keyCode == DOWN
        || key == 's' || key == 'S'
        || key == 'w' || key == 'W') {
      player.direction.y = 0;
    }
    if (keyCode == LEFT || keyCode == RIGHT
        || key == 'a' || key == 'A'
        || key == 'd' || key == 'D') {
      player.direction.x = 0;
    }
    redraw();
  }
  }


  /**
   * Creates a new bullet when the mouse is pressed.
   */
  public void mousePressed() {
    if (!gameOn){ //If the game isn't running, the mouse clicks will register on the menu buttons

      //Start menu - button settings
      if (currentScreen == Screen.START) {
        if (newGameButton.isClicked(mouseX, mouseY)) {
            gameOn = true; //Activates the game
        } else if (leaderboardButton.isClicked(mouseX, mouseY)) {
          leaderboard.fetchLeaderboardData(); //Fetches the leaderboard data
          currentScreen = Screen.LEADERBOARD; //Displays the leaderboard menu
        } else if (controlsButton.isClicked(mouseX, mouseY)) {
          currentScreen = Screen.CONTROLS; //Displays the controls menu
        }
        //Leaderboard/Controls menu - button settings
      } else if (currentScreen == Screen.LEADERBOARD || currentScreen == Screen.CONTROLS) {
        if (backButton.isClicked(mouseX, mouseY)) {
          currentScreen = Screen.START; //If the back button is pressed from the leaderboard/controls menu
                                        //Return to start menu
        }
        //Score menu - button settings
      } else if (currentScreen == Screen.SCORE) {
        if (continueButton.isClicked(mouseX, mouseY)) {
          leaderboard.savePlayerToDatabase(inputText, score); //Saves the inputted text from the player and their score
          inputText = "";
          currentScreen = Screen.END; //Displays the end menu
        } else if (leaderboardButton.isClicked(mouseX, mouseY)) {
          leaderboard.fetchLeaderboardData();
          currentScreen = Screen.LEADERBOARD; //Displays the leaderboard menu
        } else {
          inputActive = true; //Boolean that allows key pressed to work for the players name input (textInput)
        }
        //Button settings for end menu
      } else if (currentScreen == Screen.END) {
        if (leaderboardButton.isClicked(mouseX, mouseY)) {
          leaderboard.fetchLeaderboardData(); //Fetch leaderboard data
          currentScreen = Screen.LEADERBOARD; //Displays leaderboard menu
        }
        if (newGameButton.isClicked(mouseX, mouseY)){
          newGame(); //If the new game button is pressed, it resets the game state and starts a new game
        }
        //Pause menu - button settings
      } else if (currentScreen == Screen.PAUSE){
        if (resumeButton.isClicked(mouseX,mouseY)){
          gameOn = true; //If the resume button is clicked, the boolean switch turns the game back on
          currentScreen = Screen.START; //Sets the current menu back to the start menu
        }
      }

    } else {
        if (mouseButton == LEFT) {
          // Create a new bullet object and set its initial position to the current position of the player
          Bullet bullet = new Bullet((player.x + 50), (player.y + 40), 0, 0, 10, goblins, skeletons, trolls, this);

          float dx = mouseX - player.x;
          float dy = mouseY - player.y;
          float distance = sqrt(dx * dx + dy * dy);
          float vx = dx / distance;
          float vy = dy / distance;

          // Set the velocity of the new bullet
          bullet.setVelocity(vx, vy);

          bullets.add(bullet);
        }
      }
    }


  public float getWidth() {
    return width;
  }

  public float getHeight() {
    return height;
  }

  public PImage getSkeletonImage() {
    return skeletonImage;
  }

  public static void main(String[] args) {
    PApplet.main("org.bcit.comp2522.project.Window");
  }

  /**
   * Stops the clip when the program is stopped.
   */
  public void stop() {
    clip.stop();
    clip.close();
    super.stop();
  }


}
