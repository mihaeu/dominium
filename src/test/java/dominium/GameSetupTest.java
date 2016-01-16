package dominium;

import static org.junit.Assert.*;

public class GameSetupTest {

    @org.junit.Test
    public void testSetUpGameOnePlayer() throws Exception {
        GameSetup gameSetup = new GameSetup();
        assertNotNull(gameSetup.setUpGame(1, 1));
    }


    @org.junit.Test
    public void testSetUpGame() throws Exception {
        GameSetup gameSetup = new GameSetup();
        assertNotNull(gameSetup.setUpGame(2, 1));
    }

    @org.junit.Test
    public void testSetUpTooFewPlayers() throws Exception {
        GameSetup gameSetup = new GameSetup();
        assertNull(gameSetup.setUpGame(0, 1));
    }

    @org.junit.Test
    public void testSetUpTooManyPlayers() throws Exception {
        GameSetup gameSetup = new GameSetup();
        assertNull(gameSetup.setUpGame(5, 1));
    }

    @org.junit.Test
    public void testSetUpGameTooLowKingdomCardSetNumber() throws Exception {
        GameSetup gameSetup = new GameSetup();
        assertNull(gameSetup.setUpGame(1, 0));
    }

    @org.junit.Test
    public void testSetUpGameTooHighKingdomCardSetNumber() throws Exception {
        GameSetup gameSetup = new GameSetup();
        assertNull(gameSetup.setUpGame(1, 1000000));
    }
}