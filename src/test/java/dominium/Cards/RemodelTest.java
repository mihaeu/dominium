package dominium.Cards;

import dominium.CardStack;
import dominium.GameMaster;
import dominium.Players.Player;
import dominium.Players.RandomPlayer;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RemodelTest {
    @Test
    public void currentPlayerTrashesSilverAndGainsMilitia()
    {
        GameMaster mockMaster = mock(GameMaster.class);
        Player randomPlayer = new RandomPlayer("Test");
        when(mockMaster.currentPlayer()).thenReturn(randomPlayer);

        Silver silver = new Silver();
        randomPlayer.handCards().add(silver);

        Map<Class, Stack<Card>> kingdomCards = new HashMap<>();
        CardStack militiaStack = new CardStack();
        Militia militia = new Militia();
        militiaStack.add(militia);
        CardStack goldStack = new CardStack();
        Gold gold = new Gold();
        goldStack.add(gold);

        kingdomCards.put(Militia.class, militiaStack);
        kingdomCards.put(Gold.class, goldStack);
        when(mockMaster.kingdomCards()).thenReturn(kingdomCards);

        Remodel remodel = new Remodel();
        remodel.resolve(mockMaster);

        assertEquals(1, randomPlayer.discardedCards().size());
        assertEquals(0, randomPlayer.handCards().size());
        assertTrue(randomPlayer.discardedCards().contains(militia));
    }
}
