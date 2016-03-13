package dominium.Cards;

import dominium.Players.Player;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class CellarTest {
    @Test
    public void currentPlayerGetsOneMoreActionDiscardsTwoCardsAndDrawsTwo() {
        Player mockPlayer = mock(Player.class);
        when(mockPlayer.selectCard(null))
                .thenReturn(new Copper())
                .thenReturn(new Copper())
                .thenReturn(null);

        Cellar cellar = new Cellar();
        cellar.resolve(mockPlayer, null, null);
        verify(mockPlayer).setActions(1);
        verify(mockPlayer, times(2)).discardCard(new Copper());
        verify(mockPlayer).drawCards(2);
    }

    @Test
    public void currentPlayerGetsOneMoreActionButDiscardsNoCard() {
        Player mockPlayer = mock(Player.class);
        when(mockPlayer.selectCard(null))
                .thenReturn(null);

        Cellar cellar = new Cellar();
        cellar.resolve(mockPlayer, null, null);
        verify(mockPlayer).setActions(1);
        verify(mockPlayer).drawCards(0);
    }
}
