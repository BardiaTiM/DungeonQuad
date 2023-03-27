import org.bcit.comp2522.project.Controls;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;
import processing.core.PImage;

import static org.junit.jupiter.api.Assertions.*;

//class ControlsTest {
//
//  private PApplet parent;
//  private Controls controls;
//
//  @BeforeEach
//  void setUp() {
//    parent = new PApplet();
//    controls = new Controls(parent, 800, 600);
//  }
//
//  @Test
//  void testDraw1() {
//    controls.draw();
//    PImage controlsImage = parent.loadImage("controls.png");
//    assertTrue(controlsImage.width == parent.width);
//    assertTrue(controlsImage.height == parent.height);
//  }
//
//  @Test
//  void testLoadImageNotFound() {
//    // Test loading an image that does not exist
//    PApplet applet = new PApplet();
//    assertThrows(NullPointerException.class, () -> new Controls(applet, 800, 600));
//  }
//}
