package org.bcit.comp2522.project;

public interface Moveable {

   public Player player;

  public ArrayList<Waves> enemies;

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

  public static void trollMove(int i) {
    i = Enemies.waveNumber;
    for(int x = 0; x < )
  }
}
