package src.test;

import src.main.GameSetup;

import static org.junit.Assert.*;

/**
 * Created by SWINE on 10.01.2016.
 */
public class GameSetupTest {



    @org.junit.Test
    public void testSetUpGame() throws Exception {
        GameSetup gameSetup = new GameSetup();
        assertTrue(gameSetup.setUpGame(2,1));
    }



    @org.junit.Test
    public void testSetUpTooFewPlayers() throws Exception {
        GameSetup gameSetup = new GameSetup();
        assertFalse(gameSetup.setUpGame(0, 1));
    }

    @org.junit.Test
    public void testSetUpTooManyPlayers() throws Exception {
        GameSetup gameSetup = new GameSetup();
        assertFalse(gameSetup.setUpGame(5, 1));
    }

    @org.junit.Test
    public void testSetUpGameTooLowKingdomCardSetNumber() throws Exception {
        GameSetup gameSetup = new GameSetup();
        assertFalse(gameSetup.setUpGame(1, 0));
    }

    @org.junit.Test
    public void testSetUpGameTooHighKingdomCardSetNumber() throws Exception {
        GameSetup gameSetup = new GameSetup();
        assertFalse(gameSetup.setUpGame(1, 1000000));
    }
}