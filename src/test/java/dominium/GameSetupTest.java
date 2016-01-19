package dominium;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameSetupTest {

    private GameSetup setup;
    private List<Player> players;

    @Before
    public void setUp() {
        setup = new GameSetup();
        players = new ArrayList<Player>();
        players.add(new RandomPlayer("Test"));
    }

    @Test
    public void testSetUpGameOnePlayer() throws Exception {
        assertEquals(1, setup.initiatePlayers(1).size());
    }

    @Test
    public void testSetUpGame() throws Exception {
        assertNotNull(setup.initiateGameState(players));
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