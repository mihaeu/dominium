package dominium.Cards;

import dominium.CardStack;
import dominium.Convenience;
import dominium.GameMaster;
import dominium.Players.Player;
import org.junit.Test;

import java.util.Arrays;

import static org.mockito.Mockito.*;

public class MilitiaTest {
    @Test
    public void currentPlayerGetsTwoCoinsAndForcesOtherPlayersToDiscardCards() {
        Player mockOtherPlayer1 = mock(Player.class);
        CardStack handCards1 = Convenience.stack(
                new Copper(),
                new Copper(),
                new Copper(),
                new Copper(),
                new Copper()
        );
        when(mockOtherPlayer1.handCards()).thenReturn(handCards1);

        Player mockOtherPlayer2 = mock(Player.class);
        CardStack handCards2 = Convenience.stack(
            new Copper(),
            new Copper(),
            new Copper(),
            new Copper(),
            new Moat()         // <-- Reaction card stops Militia
        );
        when(mockOtherPlayer2.handCards()).thenReturn(handCards2);

        GameMaster mockMaster = mock(GameMaster.class);
        Player mockPlayer = mock(Player.class);
        when(mockMaster.currentPlayer()).thenReturn(mockPlayer);

        Militia militia = new Militia();
        militia.resolve(mockPlayer, Arrays.asList(mockOtherPlayer1, mockOtherPlayer2), null);
        verify(mockPlayer).setCoins(2);
        verify(mockOtherPlayer1, times(2)).selectCard(handCards1);
        verify(mockOtherPlayer2, never()).selectCard(handCards2);
    }
}
