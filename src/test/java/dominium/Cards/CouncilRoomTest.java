package dominium.Cards;

import dominium.GameMaster;
import dominium.Players.Player;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class CouncilRoomTest {
    @Test
    public void currentPlayerDrawsFourCardsGetsOneBuyAndOtherPlayersDrawOneCard()
    {
        GameMaster mockMaster = mock(GameMaster.class);

        Player mockPlayer1 = mock(Player.class);
        when(mockMaster.currentPlayer()).thenReturn(mockPlayer1);

        Player mockPlayer2 = mock(Player.class);
        Player mockPlayer3 = mock(Player.class);
        List<Player> others = new ArrayList<>();
        others.add(mockPlayer2);
        others.add(mockPlayer3);
        when(mockMaster.otherPlayers()).thenReturn(others);

        CouncilRoom councilRoom = new CouncilRoom();
        councilRoom.resolve(mockMaster);
        verify(mockPlayer1).drawCards(4);
        verify(mockPlayer1).getBuys();
        verify(mockPlayer1).setBuys(1);

        verify(mockPlayer2).drawCards(1);
        verify(mockPlayer3).drawCards(1);
    }
}
