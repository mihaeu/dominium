package dominium.Cards;

import dominium.CardStack;
import dominium.GameMaster;
import dominium.Players.Player;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class MilitiaTest {
    @Test
    public void currentPlayerGetsTwoCoinsAndForcesOtherPlayersToDiscardCards() {
        Player mockOtherPlayer1 = mock(Player.class);
        CardStack handCards1 = mock(CardStack.class);
        when(handCards1.size()).thenReturn(5);
        when(mockOtherPlayer1.handCards()).thenReturn(handCards1);

        Player mockOtherPlayer2 = mock(Player.class);
        CardStack handCards2 = mock(CardStack.class);
        when(mockOtherPlayer2.handCards()).thenReturn(handCards2);
        when(handCards2.size()).thenReturn(3);

        GameMaster mockMaster = mock(GameMaster.class);
        Player mockPlayer = mock(Player.class);
        when(mockMaster.currentPlayer()).thenReturn(mockPlayer);

        List<Player> players = new ArrayList<>();
        players.add(mockOtherPlayer1);
        players.add(mockOtherPlayer2);
        when(mockMaster.otherPlayers()).thenReturn(players);

        Militia militia = new Militia();
        militia.resolve(mockMaster);
        verify(mockPlayer).setCoins(2);
        verify(mockOtherPlayer1, times(2)).selectCard(handCards1);
        verify(mockOtherPlayer2, never()).selectCard(handCards2);
    }
}
