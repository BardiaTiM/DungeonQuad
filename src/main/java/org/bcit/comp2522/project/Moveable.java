package org.bcit.comp2522.project;

public interface Moveable {

   public Player player;


  public ArrayList<Waves> enemies;

  public Troll troll;

  public Goblin goblin;

  public Skeleton skeleton;

  public static void playerMove(char key) {
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
  }

  public static void trollMove() {

    int i = Enemies.waveNumber;
    for(int x = 0; x < i; x++) {
      int m = (int)(Math.random() * (i +  2)) + 1;
      troll.xPos += m;
    }
    for (int y = 0; y < i; y++) {
      int n = (int)(Math.random() * (i +  2)) + 1;
      troll.yPos += n;
    }
  }
  public static void goblinMove() {

    int i = Enemies.waveNumber;
    for(int x = 0; x < i; x++) {
      int m = (int)(Math.random() * (i +  2)) + 1;
      goblin.xPos += m;
    }
    for (int y = 0; y < i; y++) {
      int n = (int)(Math.random() * (i +  2)) + 1;
      goblin.yPos += n;
    }
  }
  public static void skeletonMove() {

    int i = Enemies.waveNumber;
    for(int x = 0; x < i; x++) {
      int m = (int)(Math.random() * (i +  2)) + 1;
      skeleton.xPos += m;
    }
    for (int y = 0; y < i; y++) {
      int n = (int)(Math.random() * (i +  2)) + 1;
      skeleton.yPos += n;
    }
  }
}
