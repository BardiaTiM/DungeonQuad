package org.bcit.comp2522.project;


import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
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

  static ConcurrentLinkedQueue<Bullet> bullets = new ConcurrentLinkedQueue<>();

  ArrayList<Sprite> sprites;

  Waves waves;

  Sprite player;

  Bullet bullet;

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
  float scrollSpeed = 2; // Adjust this to control the scrolling speed

  /**
   * Sets up the window.
   */
  public void setup() {
    size(700, 900);
    backgroundImage = loadImage("deep_slate.jpg");
    PImage spriteImage = loadImage("mcW0.png");
    player = new Sprite(500, 700, 50, this, new PVector(0, 0));
    player.setSprite(spriteImage); // set the default player sprite
    waves = new Waves(1, Window.this);

    // Set up leaderboard
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
    waves = new Waves(1, Window.this);
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
      waves.spawnWaves(1, 1, 1, 1);

      // Draw all the bullets in the list
      for (Bullet bullet : bullets) {
        bullet.draw();
        bullet.update();
        bullet.collide();
      }
    }
  }
  /**
   * Moves the player when the arrow keys are pressed.
   */
  public void keyPressed() {
    //Handles user text input in the score menu and adds it to the input text variable
    //So it can be saved to the database
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
      // Handle player movement and sprite change
      if (keyCode == UP || key == 'w') {
        if (player.y - player.speed > 0) {
          player.direction.y = -1;
          PImage spriteImage = loadImage("mcW0.png");
          player.setSprite(spriteImage);
        }
      }
      if (keyCode == DOWN || key == 's') {
        if (player.y + player.speed < height) {
          player.direction.y = 1;
          PImage spriteImage = loadImage("mcS0.png");
          player.setSprite(spriteImage);
        }
      }
      if (keyCode == LEFT || key == 'a') {
        if (player.x - player.speed > 0) {
          player.direction.x = -1;
          PImage spriteImage = loadImage("mcA0.png");
          player.setSprite(spriteImage);
        }
      }
      if (keyCode == RIGHT || key == 'd') {
        if (player.x + player.speed < width) {
          player.direction.x = 1;
          PImage spriteImage = loadImage("mcD0.png");
          player.setSprite(spriteImage);
        }
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
    redraw();
  }


  /**
   * Stops the player when the arrow keys are released.
   */
  public void keyReleased() {
    if (gameOn) {
      if (keyCode == UP || key == 'w' || keyCode == DOWN || key == 's') {
        player.direction.y = 0;
      }
      if (keyCode == LEFT || key == 'a' || keyCode == RIGHT || key == 'd') {
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
          Bullet bullet = new Bullet((player.x + 50), (player.y + 40), 0, 0, 10, Waves.getGoblins(),
              Waves.getSkeletons(), Waves.getTrolls(), this);

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



  public static void main(String[] args) {
    PApplet.main("org.bcit.comp2522.project.Window");
  }

}
