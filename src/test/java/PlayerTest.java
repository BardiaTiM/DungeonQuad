import org.bcit.comp2522.project.Player;
import org.bcit.comp2522.project.Window;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PImage;
import processing.core.PVector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * This class tests the Player class.
 *
 * @author Heeho Ryou
 * @version 1.0
 */
public class PlayerTest {

  private Player player;
  private Window window;
  private PImage playerImage;

  @BeforeEach
  public void setUp() {
    window = new Window();
    player = new Player(5, 5, 100, window, new PVector(1, 1));
    playerImage = new PImage();
  }

  @Test
  public void testSetPlayer() {
    player.setPlayer(playerImage);
    assertEquals(playerImage, Player.getPlayerImage());
  }

  @Test
  void testIsAliveFalse() {
    Player player = new Player(50, 50, 50, window, new PVector(1, 1));
    player.setHealth(0);

    assertFalse(player.isAlive());
  }
  @Test
  void testIsAliveFalse1() {
    Player player = new Player(50, 50, 50, window, new PVector(1, 1));
    player.setHealth(0);

    assertFalse(player.isAlive());
  }
}
