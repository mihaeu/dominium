package dominium;

import dominium.Players.Player;
import dominium.Players.RandomPlayer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameSetupTest {

    private GameState state;
    private List<Player> players;

    @Before
    public void setUp() {
        GameSetup setup = new GameSetup();
        players = new ArrayList<Player>();
        players.add(new RandomPlayer("Test"));
        state = setup.initiateGameState(players);
    }

    @Test
    public void setUpGame() throws Exception {
        assertNotNull(state);
    }

    @Test
    public void rightNumberOfStacksForPlayers() throws Exception {
        assertTrue(state.getHandCards().size()== players.size());
        assertTrue(state.getDeckCards().size()== players.size());
        assertTrue(state.getDiscardCards().size()== players.size());
    }

    @Test
     public void rightNumberOfKingdomCardStacks() throws Exception {
        assertTrue(state.getKingdomCards().size()== 6);
    }

    @Test
    public void rightNumberOfCardsOnTheStacksPerPlayer() throws Exception {
        for(Player player:players) {
            assertTrue(state.getHandCards().get(player).size() == 5);
            assertTrue(state.getDeckCards().get(player).size()==5);
            assertTrue(state.getDiscardCards().get(player).size()== 0);
        }

    }




}