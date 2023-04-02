import org.bcit.comp2522.project.MusicPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MusicPlayerTest {

  private MusicPlayer musicPlayer;

  @BeforeEach
  void setUp() {
    // Use a test music file for testing
    musicPlayer = new MusicPlayer("music/dungeon.wav");
  }

  @Test
  void testPlay() {
    musicPlayer.play();
    assertTrue(musicPlayer.getMicrosecondPosition() > 0);
  }

  @Test
  void testStop() {
    musicPlayer.play();
    musicPlayer.stop();
    assertEquals(0, musicPlayer.getMicrosecondPosition());
  }

  @Test
  void testStart() {
    musicPlayer.start();
    assertFalse(musicPlayer.getMicrosecondPosition() > 0);
  }

  @Test
  void testSetMicrosecondPosition() {
    long position = 5000000L; // 5 seconds
    musicPlayer.setMicrosecondPosition(position);
    assertEquals(position, musicPlayer.getMicrosecondPosition());
  }

  @Test
  void testInvalidMusicFile() {
    MusicPlayer invalidMusicPlayer = new MusicPlayer("music/dungeon.wav");
    assertThrows(Exception.class, invalidMusicPlayer::play);
  }
}
