package dominium.Cards;

import dominium.GameMaster;
import dominium.Players.Player;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class VillageTest {
    @Test
    public void currentPlayerGetsOneBuyAndTwoCoins() {
        GameMaster mockMaster = mock(GameMaster.class);
        Player mockPlayer = mock(Player.class);
        when(mockMaster.currentPlayer()).thenReturn(mockPlayer);
        when(mockPlayer.getBuys()).thenReturn(2);
        when(mockPlayer.getCoins()).thenReturn(3);

        Woodcutter woodcutter = new Woodcutter();
        woodcutter.resolve(mockMaster);
        verify(mockPlayer).getCoins();
        verify(mockPlayer).setCoins(5);
        verify(mockPlayer).getBuys();
        verify(mockPlayer).setBuys(3);
    }
}
