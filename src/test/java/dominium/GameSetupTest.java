package dominium;

import dominium.Players.Player;
import dominium.Players.RandomPlayer;
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
    public void testSetUpGame() throws Exception {
        assertNotNull(setup.initiateGameState(players));
    }
}