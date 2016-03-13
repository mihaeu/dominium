package dominium.Cards;

import dominium.Players.Player;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class VillageTest {
    @Test
    public void currentPlayerGetsOneBuyAndTwoCoins() {
        Player mockPlayer = mock(Player.class);
        when(mockPlayer.getBuys()).thenReturn(2);
        when(mockPlayer.getCoins()).thenReturn(3);

        Woodcutter woodcutter = new Woodcutter();
        woodcutter.resolve(mockPlayer, null, null);
        verify(mockPlayer).getCoins();
        verify(mockPlayer).setCoins(5);
        verify(mockPlayer).getBuys();
        verify(mockPlayer).setBuys(3);
    }
}
