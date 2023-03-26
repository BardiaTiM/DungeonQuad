import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentLinkedQueue;

import static org.junit.jupiter.api.Assertions.*;

class BulletTest {
  private Bullet bullet;
  private ConcurrentLinkedQueue<Goblin> goblinList;
  private ConcurrentLinkedQueue<Skeleton> skeletonList;
  private ConcurrentLinkedQueue<Troll> trollList;
  private Window window;

  @BeforeEach
  void setUp() {
    goblinList = new ConcurrentLinkedQueue<>();
    skeletonList = new ConcurrentLinkedQueue<>();
    trollList = new ConcurrentLinkedQueue<>();
    Sprite player = new Sprite(0, 0, 0, 0, 0);
    window = new Window();
    bullet = new Bullet(0, 0, 0, 0, 10, goblinList, skeletonList, trollList, player, window);
  }

  @Test
  void setVelocity() {
    bullet.setVelocity(1, 1);
    assertEquals(10, bullet.vx);
    assertEquals(10, bullet.vy);
  }

  @Test
  void update() {
    bullet.setVelocity(1, 1);
    bullet.update();
    assertEquals(1, bullet.x);
    assertEquals(1, bullet.y);
  }

  @Test
  void collideWithSkeleton() {
    Skeleton skeleton = new Skeleton(0, 0, 20, skeletonList, window);
    skeletonList.add(skeleton);
    bullet.setVelocity(1, 1);
    bullet.update();
    bullet.collide();
    assertEquals(0, bullet.skeletonsList.size());
    assertEquals(2, skeleton.health);
  }

  @Test
  void collideWithGoblin() {
    Goblin goblin = new Goblin(0, 0, 20, goblinList, window);
    goblinList.add(goblin);
    bullet.setVelocity(1, 1);
    bullet.update();
    bullet.collide();
    assertEquals(0, bullet.goblinsList.size());
    assertEquals(2, goblin.health);
  }

  @Test
  void collideWithTroll() {
    Troll troll = new Troll(0, 0, 20, trollList, window);
    trollList.add(troll);
    bullet.setVelocity(1, 1);
    bullet.update();
    bullet.collide();
    assertEquals(0, bullet.trollsList.size());
    assertEquals(2, troll.health);
  }

  @Test
  void collideWithSprite() {
    Sprite.health = 2;
    bullet.setVelocity(1, 1);
    bullet.update();
    bullet.collide();
    assertEquals(1, Sprite.health);
  }
}
