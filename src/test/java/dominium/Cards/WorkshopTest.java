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

public class WorkshopTest {
    @Test
    public void currentPlayerGainsCardCostingFour() {
        GameMaster mockMaster = mock(GameMaster.class);
        Player player = new RandomPlayer("Test");
        when(mockMaster.currentPlayer()).thenReturn(player);

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

        Workshop workshop = new Workshop();
        workshop.resolve(mockMaster);
        assertTrue(player.discardedCards().contains(militia));
        assertEquals(1, player.discardedCards().size());
    }
}
