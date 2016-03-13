package dominium.Cards;

import dominium.Players.Player;
import org.junit.Test;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CouncilRoomTest {
    @Test
    public void currentPlayerDrawsFourCardsGetsOneBuyAndOtherPlayersDrawOneCard()
    {
        Player mockPlayer1 = mock(Player.class);

        Player mockPlayer2 = mock(Player.class);
        Player mockPlayer3 = mock(Player.class);

        CouncilRoom councilRoom = new CouncilRoom();
        councilRoom.resolve(mockPlayer1, Arrays.asList(mockPlayer2, mockPlayer3), null);

        verify(mockPlayer1).drawCards(4);
        verify(mockPlayer1).getBuys();
        verify(mockPlayer1).setBuys(1);

        verify(mockPlayer2).drawCards(1);
        verify(mockPlayer3).drawCards(1);
    }
}
