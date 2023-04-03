import org.bcit.comp2522.project.Goblin;
import org.bcit.comp2522.project.Skeleton;
import org.bcit.comp2522.project.SpawningHandler;
import org.bcit.comp2522.project.Troll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentLinkedQueue;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SpawningHandlerTest {

//  @Test
//  void onlyOneSpace_emptiesQueues() {
//    // Given
//    ConcurrentLinkedQueue<Skeleton> skeletons = new ConcurrentLinkedQueue<>();
//    skeletons.add(new Skeleton(0, 0, 0, true, null, null));
//    ConcurrentLinkedQueue<Goblin> goblins = new ConcurrentLinkedQueue<>();
//    goblins.add(new Goblin(0, 0, 0, true, null, null));
//    ConcurrentLinkedQueue<Troll> trolls = new ConcurrentLinkedQueue<>();
//    trolls.add(new Troll(0, 0, 0, true, null, null));
//    SpawningHandler spawningHandler = new SpawningHandler(null, skeletons, goblins, trolls, 1);
//
//    // When
//    spawningHandler.onlyOneSpace();
//
//    // Then
//    Assertions.assertTrue(skeletons.isEmpty());
//    Assertions.assertTrue(goblins.isEmpty());
//    Assertions.assertTrue(trolls.isEmpty());
//  }

  @Test
  void allEnemiesDead_noEnemies_returnsFalse() {
    // Given
    ConcurrentLinkedQueue<Skeleton> skeletons = new ConcurrentLinkedQueue<>();
    ConcurrentLinkedQueue<Goblin> goblins = new ConcurrentLinkedQueue<>();
    ConcurrentLinkedQueue<Troll> trolls = new ConcurrentLinkedQueue<>();
    SpawningHandler spawningHandler = new SpawningHandler(null, skeletons, goblins, trolls, 1);

    // When
    spawningHandler.allEnemiesDead();

    // Then
    Assertions.assertFalse(SpawningHandler.newWave);
  }

  @Test
  void allEnemiesDead_withEnemies_returnsTrue() {
    // Given
    ConcurrentLinkedQueue<Skeleton> skeletons = new ConcurrentLinkedQueue<>();
    skeletons.add(new Skeleton(0, 0, 0, true, null, null));
    ConcurrentLinkedQueue<Goblin> goblins = new ConcurrentLinkedQueue<>();
    goblins.add(new Goblin(0, 0, 0, true, null, null));
    ConcurrentLinkedQueue<Troll> trolls = new ConcurrentLinkedQueue<>();
    trolls.add(new Troll(0, 0, 0, true, null, null));
    SpawningHandler spawningHandler = new SpawningHandler(null, skeletons, goblins, trolls, 1);

    // When
    spawningHandler.allEnemiesDead();

    // Then
    Assertions.assertTrue(SpawningHandler.newWave);
  }

}