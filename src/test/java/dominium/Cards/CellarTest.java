package dominium.Cards;

import dominium.GameMaster;
import dominium.Players.Player;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class CellarTest {
    @Test
    public void currentPlayerGetsOneMoreActionDiscardsTwoCardsAndDrawsTwo() {
        GameMaster mockMaster = mock(GameMaster.class);
        Player mockPlayer = mock(Player.class);
        when(mockMaster.currentPlayer()).thenReturn(mockPlayer);
        Copper copper1 = new Copper();
        Copper copper2 = new Copper();
        when(mockPlayer.selectCard(null))
                .thenReturn(copper1)
                .thenReturn(copper2)
                .thenReturn(null);

        Cellar cellar = new Cellar();
        cellar.resolve(mockMaster);
        verify(mockPlayer).setActions(1);
        verify(mockPlayer, times(1)).discardCard(copper1);
        verify(mockPlayer, times(1)).discardCard(copper2);
        verify(mockPlayer).drawCards(2);
    }

    @Test
    public void currentPlayerGetsOneMoreActionButDiscardsNoCard() {
        GameMaster mockMaster = mock(GameMaster.class);
        Player mockPlayer = mock(Player.class);
        when(mockMaster.currentPlayer()).thenReturn(mockPlayer);
        when(mockPlayer.selectCard(null))
                .thenReturn(null);

        Cellar cellar = new Cellar();
        cellar.resolve(mockMaster);
        verify(mockPlayer).setActions(1);
        verify(mockPlayer).drawCards(0);
    }
}
