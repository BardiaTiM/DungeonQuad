package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PImage;

import static javax.swing.DebugGraphics.loadImage;

public class Window extends PApplet {
  //Scenic game backgrounds
  private PImage[] scenes;

  //Background position
  private float sceneX = 0;
  private float sceneY = 0;

  //Background speed
  private float sceneSpeed = 1;

  //Screen height
  //THIS NEEDS TO BE UPDATED IF SCREEN SIZE CHANGES
  private float screenHeight = 640;


  //Number of scenes
  private int numScenes = 2;

  public static void main(String[] args) {
    PApplet.main("org.bcit.comp2522.project.Window");
  }

  public void settings() {
    size(640, 360);
  }

  public void setup() {
    background(0);

    //JPG will be loaded as the scenic background
    scenes = new PImage[numScenes];
    for (int i = 0; i < scenes.length; i++){
      scenes[i] = loadImage("background.jpg");
    }
  }

  //This method loads the scenic background file
  private PImage loadImage(String filename) {
    return loadImage(filename);
  }

  public void draw() {
    background(0);

    //To get the background to move from top to bottom
    //Update the Y location of the scene with scene speed
    sceneY += sceneSpeed;

    //Once the scene starts moving down, a new scene needs to be added
    //NEED TO CHANGE
    if (sceneY <= screenHeight){
      //For loop to rotate between background scenes
      for (int i = 0; i < scenes.length; i++){
        //Check to see what the current scene is
        if (scenes[0] == loadImage("background.jpg")) {
          //Add the second scene
          scenes[0] = loadImage("background1.jpg");
        } else {
          //If the current scene is the second one, load the first one next
          scenes[0] = loadImage("background.jpg");
        }

      //Adjust sceneY so that the next background will be added when
      //the screen moves.
      sceneY = -screenHeight + (sceneY - screenHeight);
    }


    for (int i = 0; i < scenes.length; i++){
      image(scenes[i], sceneX, sceneY + i * screenHeight);
    }

  }

  public void update() {

  }

  public void render() {

  }
}
