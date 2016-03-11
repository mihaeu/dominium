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
import static org.mockito.Mockito.*;

public class MineTest {
    @Test
    public void currentPlayerTrashedCardForTwoGetsCardForFive() {
        GameMaster mockMaster = mock(GameMaster.class);
        Player randomPlayer = new RandomPlayer("Test");
        when(mockMaster.currentPlayer()).thenReturn(randomPlayer);

        Moat moat = new Moat();
        Silver silver = new Silver();
        randomPlayer.handCards().add(moat);
        randomPlayer.handCards().add(silver);

        Map<Class, Stack<Card>> kingdomCards = new HashMap<>();
        CardStack marketStack = new CardStack();
        Market market = new Market();
        marketStack.add(market);
        CardStack goldStack = new CardStack();
        Gold gold = new Gold();
        goldStack.add(gold);

        kingdomCards.put(Market.class, marketStack);
        kingdomCards.put(Gold.class, goldStack);
        when(mockMaster.kingdomCards()).thenReturn(kingdomCards);

        Mine mine = new Mine();
        mine.resolve(mockMaster);

        assertEquals(2, randomPlayer.handCards().size());
        assertTrue(randomPlayer.handCards().contains(moat));
        assertTrue(randomPlayer.handCards().contains(gold));
    }
}
