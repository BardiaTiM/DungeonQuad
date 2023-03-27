//package org.bcit.comp2522.project;
//
//import com.google.firebase.FirebaseApp;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class MenuHandlerTest {
//  private MenuHandler menuHandler;
//  private Window window;
//  private Menu menu;
//
//  @BeforeEach
//  void setUp() {
//    window = new Window();
//    menu = new Menu(window);
//    menuHandler = new MenuHandler(window);
//  }
//
//  @Test
//  void testScreenStartHelper() {
//    FirebaseApp.initializeApp();
//
//    // Test new game button
//    menu.newGameButton.setPosition(10, 10);
//    assertTrue(menu.newGameButton.isClicked(11, 11));
//    window.setGameOn(false);
//    menuHandler.screenStartHelper(11, 11);
//    assertTrue(window.getGameOn());
//
//    // Test leaderboard button
//    menu.leaderboardButton.setPosition(10, 20);
//    assertTrue(menu.leaderboardButton.isClicked(11, 21));
//    assertEquals(Screen.START, window.getCurrentScreen());
//    menuHandler.screenStartHelper(11, 21);
//    assertEquals(Screen.LEADERBOARD, window.getCurrentScreen());
//
//    // Test controls button
//    menu.controlsButton.setPosition(10, 30);
//    assertTrue(menu.controlsButton.isClicked(11, 31));
//    assertEquals(Screen.LEADERBOARD, window.getCurrentScreen());
//    menuHandler.screenStartHelper(11, 31);
//    assertEquals(Screen.CONTROLS, window.getCurrentScreen());
//  }
//
//  @Test
//  void testScreenScoreHelper() {
//    FirebaseApp.initializeApp();
//
//    // Test continue button
//    menu.continueButton.setPosition(10, 10);
//    assertFalse(menu.continueButton.isClicked(11, 11));
//    menu.setLeaderboardSave("test1", 100);
//    window.setInputText("");
//    window.setCurrentScreen(Screen.SCORE);
//    menuHandler.screenScoreHelper(11, 11);
//    assertEquals("", window.getInputText());
//    assertEquals(Screen.END, window.getCurrentScreen());
//
//    // Test leaderboard button
//    menu.leaderboardButton.setPosition(10, 20);
//    assertTrue(menu.leaderboardButton.isClicked(11, 21));
//    menu.leaderBoardFetch();
//    assertEquals(Screen.END, window.getCurrentScreen());
//    menuHandler.screenScoreHelper(11, 21);
//    assertEquals(Screen.LEADERBOARD, window.getCurrentScreen());
//
//    // Test input active
//    assertFalse(window.getInputActive());
//    menuHandler.screenScoreHelper(11, 22);
//    assertTrue(window.getInputActive());
//  }
//
//  @Test
//  void testHandleMouseClicks() {
//    Window window = new Window();
//    // Test START screen
//    window.setCurrentScreen(Screen.START);
//    // Test new game button
//    menu.newGameButton.setPosition(10, 10);
//    assertTrue(menu.newGameButton.isClicked(11, 11));
//    window.setGameOn(false);
//    menuHandler.handleMouseClicks(11, 11);
//    assertTrue(window.getGameOn());
//
//    // Test leaderboard button
//    menu.leaderboardButton.setPosition(10, 20);
//    assertTrue(menu.leaderboardButton.isClicked(11, 21));
//    assertEquals(Screen.START, window.getCurrentScreen());
//    menuHandler.handleMouseClicks(11, 21);
//    assertEquals(Screen.LEADERBOARD, window.getCurrentScreen());
//
//    // Test controls button
//    menu.controlsButton.setPosition(10, 30);
//    assertTrue(menu.controlsButton.isClicked(11, 31));
//    assertEquals(Screen.LEADERBOARD, window.getCurrentScreen());
//    menuHandler.handleMouseClicks(11, 31);
//    assertEquals(Screen.CONTROLS, window.getCurrentScreen());
//
//    // Test LEADERBOARD and CONTROLS screens
//    window.setCurrentScreen(Screen.LEADERBOARD);
//    menu.backButton.setPosition(10, 10);
//    assertTrue(menu.backButton.isClicked(11, 11));
//    // Test back button
//    window.setCurrentScreen(Screen.LEADERBOARD);
//    menu.backButton.setPosition(10, 10);
//    assertTrue(menu.backButton.isClicked(11, 11));
//    assertEquals(Screen.LEADERBOARD, window.getCurrentScreen());
//    menuHandler.handleMouseClicks(11, 11);
//    assertEquals(Screen.START, window.getCurrentScreen());
//
//    window.setCurrentScreen(Screen.CONTROLS);
//    menu.backButton.setPosition(10, 10);
//    assertTrue(menu.backButton.isClicked(11, 11));
//    assertEquals(Screen.CONTROLS, window.getCurrentScreen());
//    menuHandler.handleMouseClicks(11, 11);
//    assertEquals(Screen.START, window.getCurrentScreen());
//  }
//}



