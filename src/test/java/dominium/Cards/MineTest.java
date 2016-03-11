package dominium.Cards;

import dominium.CardStack;
import dominium.GameMaster;
import dominium.Players.Player;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static org.mockito.Mockito.*;

public class MineTest {
    @Test
    public void currentPlayerTrashedCardForTwoGetsCardForFive() {
        GameMaster mockMaster = mock(GameMaster.class);
        Player mockPlayer = mock(Player.class);
        when(mockMaster.currentPlayer()).thenReturn(mockPlayer);

        CardStack handCards = mock(CardStack.class);
        when(mockPlayer.handCards()).thenReturn(handCards);

        Moat moat = new Moat();
        Market market = new Market();
        when(mockPlayer.selectCard(any()))
                .thenReturn(moat)
                .thenReturn(market);

        Map<Class, Stack<Card>> kingdomCards = new HashMap<>();
        CardStack marketStack = new CardStack();
        marketStack.add(market);

        kingdomCards.put(Market.class, marketStack);
        when(mockMaster.kingdomCards()).thenReturn(kingdomCards);

        Mine mine = new Mine();
        mine.resolve(mockMaster);

        verify(mockPlayer).trashCardFromHand(moat);
        verify(handCards).add(market);
    }
}
