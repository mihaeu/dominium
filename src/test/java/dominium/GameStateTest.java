package dominium;

import dominium.Cards.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.Stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GameStateTest {

    @Test
    public void testNoStateWhenCreated() {
        GameState state = new GameState(new HashMap<Class, Stack<Card>>());
        assertEquals(0, state.getKingdomCards().size());
    }

    @Test
    public void gameIsNotRunningAnymoreWhenProviceCardsSoldOut() {
        GameState state = new GameState(new HashMap<Class, Stack<Card>>());
        state.getKingdomCards().put(Province.class, new CardStack());
        assertFalse(state.gameIsRunning());
    }

    @Test
    public void gameIsNotRunningAnymoreWhenThreeCardStacksSoldOut() {
        GameState state = new GameState(new HashMap<Class, Stack<Card>>());
        state.getKingdomCards().put(Province.class, new CardStack());
        state.getKingdomCards().get(Province.class).push(new Province());

        state.getKingdomCards().put(Copper.class, new CardStack());
        state.getKingdomCards().put(Silver.class, new CardStack());
        state.getKingdomCards().put(Gold.class, new CardStack());
        assertFalse(state.gameIsRunning());
    }
}
