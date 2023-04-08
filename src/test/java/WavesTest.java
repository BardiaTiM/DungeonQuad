import org.bcit.comp2522.project.Waves;
import org.bcit.comp2522.project.Skeleton;
import org.bcit.comp2522.project.Goblin;
import org.bcit.comp2522.project.Troll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentLinkedQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the Waves class.
 *
 * @author Will Ondrik
 * @author Bardia Timouri
 */
public class WavesTest {
  private Waves waves;

  /**
   * This method runs before each test.
   * <p>
   * A new waves object is initialized with empty enemy queues.
   */
  @BeforeEach
  void setUp() {
    ConcurrentLinkedQueue<Skeleton> skeletons = new ConcurrentLinkedQueue<>();
    ConcurrentLinkedQueue<Goblin> goblins = new ConcurrentLinkedQueue<>();
    ConcurrentLinkedQueue<Troll> trolls = new ConcurrentLinkedQueue<>();
    waves = new Waves(1, null, skeletons, goblins, trolls);
  }

  /**
   * Tests if the getSkeletonCount() method returns the correct number of skeletons.
   */
  @Test
  void testGetSkeletonCount() {
    assertEquals(0, waves.getSkeletonCount());
  }

  /**
   * This test checks if the getGoblinCount() method enters the correct number of goblins.
   */
  @Test
  void testGetGoblinCount() {
    assertEquals(0, waves.getGoblinCount());
  }

  /**
   * This test checks if the getTrollCount() method returns the correct number of trolls.
   */
  @Test
  void testGetTrollCount() {
    assertEquals(0, waves.getTrollCount());
  }

  /**
   * This test checks if the spawnSkeletonAmount() method returns the correct amount
   * of skeletons to spawn, based on the wave number.
   */
  @Test
  void testSpawnSkeletonAmount() {
    assertEquals(1.0f, waves.spawnSkeletonAmount());
  }

  /**
   * This test checks if the spawnGoblinAmount() method returns the correct amount
   * of goblins to spawn, based on the wave number.
   */
  @Test
  void testSpawnGoblinAmount() {
    assertEquals(0.5f, waves.spawnGoblinAmount());
  }

  /**
   * This test checks if the spawnTrollAmount() method returns the correct amount
   * of trolls to spawn, based on the wave number.
   */
  @Test
  void testSpawnTrollAmount() {
    assertEquals(0.2f, waves.spawnTrollAmount());
  }

}
