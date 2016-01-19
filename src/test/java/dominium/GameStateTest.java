package dominium;

import dominium.Cards.Card;
import org.junit.Test;

import java.util.HashMap;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class GameStateTest {

    @Test
    public void testNoStateWhenCreated() {
        GameState state = new GameState(new HashMap<Class, Stack<Card>>());
        assertEquals(0, state.getKingdomCards().size());
        assertEquals(0, state.getHandCards().size());
        assertEquals(0, state.getDiscardCards().size());
        assertEquals(0, state.getDeckCards().size());
    }
}
