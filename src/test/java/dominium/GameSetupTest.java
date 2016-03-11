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
    public void setUpGame() {
        GameState state = setup.initiateGameState(players);
        assertNotNull(state);
    }

    @Test
    public void handsOutStartingCardsToPlayer() {
        setup.initiateGameState(players);
        assertEquals(5, players.get(0).handCards().size());
        assertEquals(5, players.get(0).deckCards().size());
        assertEquals(0, players.get(0).discardedCards().size());
    }

    @Test
     public void rightNumberOfKingdomCardStacks() {
        GameState state = setup.initiateGameState(players);

        assertTrue(state.getKingdomCards().size() == 16);
    }
}