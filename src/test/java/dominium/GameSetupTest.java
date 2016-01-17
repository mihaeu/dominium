package dominium;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameSetupTest {

    private GameSetup setup;

    @Before
    public void setUp() {
        setup = new GameSetup();
    }

    @Test
    public void testSetUpGameOnePlayer() throws Exception {
        assertEquals(1, setup.initiatePlayers(1).size());
    }

    @Test
    public void testSetUpGame() throws Exception {
        assertNotNull(setup.initiateGameState(1));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetUpTooFewPlayers() throws Exception {
        setup.initiatePlayers(0);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetUpTooManyPlayers() throws Exception {
        setup.initiatePlayers(5);
    }
}