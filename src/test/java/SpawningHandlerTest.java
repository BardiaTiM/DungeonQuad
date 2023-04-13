import org.bcit.comp2522.project.Goblin;
import org.bcit.comp2522.project.Skeleton;
import org.bcit.comp2522.project.SpawningHandler;
import org.bcit.comp2522.project.Troll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * This class tests the SpawningHandler class.
 *
 * @author Heeho Ryou
 * @version 1.0
 */
public class SpawningHandlerTest {
  @Test
  void allEnemiesDead_noEnemies_returnsFalse() {
    ConcurrentLinkedQueue<Skeleton> skeletons = new ConcurrentLinkedQueue<>();
    ConcurrentLinkedQueue<Goblin> goblins = new ConcurrentLinkedQueue<>();
    ConcurrentLinkedQueue<Troll> trolls = new ConcurrentLinkedQueue<>();
    SpawningHandler spawningHandler = new SpawningHandler(null, skeletons, goblins, trolls, 1);

    spawningHandler.allEnemiesDead();

    Assertions.assertFalse(SpawningHandler.newWave);
  }

  @Test
  void allEnemiesDead_withEnemies_returnsTrue() {
    ConcurrentLinkedQueue<Skeleton> skeletons = new ConcurrentLinkedQueue<>();
    skeletons.add(new Skeleton(0, 0, 0, true, null, null));
    ConcurrentLinkedQueue<Goblin> goblins = new ConcurrentLinkedQueue<>();
    goblins.add(new Goblin(0, 0, 0, true, null, null));
    ConcurrentLinkedQueue<Troll> trolls = new ConcurrentLinkedQueue<>();
    trolls.add(new Troll(0, 0, 0, true, null, null));
    SpawningHandler spawningHandler = new SpawningHandler(null, skeletons, goblins, trolls, 1);

    spawningHandler.allEnemiesDead();

    Assertions.assertTrue(SpawningHandler.newWave);
  }

}