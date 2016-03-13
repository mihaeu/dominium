package dominium;

import dominium.Cards.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GameStateTest {

    @Test
    public void testNoStateWhenCreated() {
        GameState state = new GameState(new KingdomCardMap());
        assertEquals(0, state.getKingdomCards().size());
    }

    @Test
    public void gameIsNotRunningAnymoreWhenProviceCardsSoldOut() {
        GameState state = new GameState(new KingdomCardMap());
        state.getKingdomCards().put(new Province(), new CardStack());
        assertFalse(state.gameIsRunning());
    }

    @Test
    public void gameIsNotRunningAnymoreWhenThreeCardStacksSoldOut() {
        GameState state = new GameState(new KingdomCardMap());
        state.getKingdomCards().put(new Province(), new CardStack());
        state.getKingdomCards().get(new Province()).push(new Province());

        state.getKingdomCards().put(new Copper(), new CardStack());
        state.getKingdomCards().put(new Silver(), new CardStack());
        state.getKingdomCards().put(new Gold(), new CardStack());
        assertFalse(state.gameIsRunning());
    }
}
