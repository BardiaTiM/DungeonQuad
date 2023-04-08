import org.bcit.comp2522.project.PlayerDB;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the PlayerDB class.
 *
 * @author Gathrean Dela Cruz
 * @version 1.0
 */
public class PlayerDBTest {
  @Test
  public void testGetName1() {
    String expectedName = "Ean";
    PlayerDB player = new PlayerDB(expectedName, 0);
    String actualName = player.getName();
    assertEquals(expectedName, actualName, "Player name should match the provided name");
  }

  @Test
  public void testGetName2() {
    String expectedName = "Will";
    PlayerDB player = new PlayerDB(expectedName, 0);
    String actualName = player.getName();
    assertEquals(expectedName, actualName, "Player name should match the provided name");
  }

  @Test
  public void testGetName3() {
    String expectedName = "Bardia";
    PlayerDB player = new PlayerDB(expectedName, 0);
    String actualName = player.getName();
    assertEquals(expectedName, actualName, "Player name should match the provided name");
  }

  @Test
  public void testGetName4() {
    String expectedName = "Max";
    PlayerDB player = new PlayerDB(expectedName, 0);
    String actualName = player.getName();
    assertEquals(expectedName, actualName, "Player name should match the provided name");
  }

  @Test
  public void testGetName5() {
    String expectedName = "Gathrean";
    PlayerDB player = new PlayerDB(expectedName, 0);
    String actualName = player.getName();
    assertEquals(expectedName, actualName, "Player name should match the provided name");
  }

  @Test
  public void testGetScore() {
    int expectedScore = 100;
    PlayerDB player = new PlayerDB("Bob", expectedScore);
    int actualScore = player.getScore();
    assertEquals(expectedScore, actualScore, "Player score should match the provided score");
  }

  @Test
  public void testSetScore() {
    PlayerDB player = new PlayerDB("Charlie", 50);
    int expectedScore = 200;
    player.setScore(expectedScore);
    int actualScore = player.getScore();
    assertEquals(expectedScore, actualScore, "Player score should be updated to the provided score");
  }

  @Test
  public void testSetName() {
    PlayerDB player = new PlayerDB("John Doe", 1000);
    player.setName("Jane Smith");
    assertEquals("Jane Smith", player.getName());
  }

  @Test
  public void testSetNameEmptyString() {
    PlayerDB player = new PlayerDB("John Doe", 1000);
    player.setName("");
    assertEquals("", player.getName());
  }

  @Test
  public void testSetNameNull() {
    PlayerDB player = new PlayerDB("John Doe", 1000);
    player.setName(null);
    assertNull(player.getName());
  }

  @Test
  public void testSetNameSpecialCharacters() {
    PlayerDB player = new PlayerDB("John Doe", 1000);
    player.setName("!@#$%^&*()");
    assertEquals("!@#$%^&*()", player.getName());
  }
}
