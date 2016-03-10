package dominium.Cards;

import dominium.GameMaster;
import dominium.Players.Player;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MoatTest {
    @Test
    public void currentPlayerDrawsTwoCards() {
        GameMaster mockMaster = mock(GameMaster.class);
        Player mockPlayer = mock(Player.class);
        when(mockMaster.currentPlayer()).thenReturn(mockPlayer);

        Moat moat = new Moat();
        moat.resolve(mockMaster);
        verify(mockPlayer).drawCards(2);
    }
}
