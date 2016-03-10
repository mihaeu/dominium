package dominium.Cards;

import dominium.GameMaster;
import dominium.Players.Player;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MarketTest {
    @Test
    public void currentPlayerGetsOneActionOneBuyOneCoinAndDrawsOneCard() {
        GameMaster mockMaster = mock(GameMaster.class);
        Player mockPlayer = mock(Player.class);
        when(mockMaster.currentPlayer()).thenReturn(mockPlayer);

        Market market = new Market();
        market.resolve(mockMaster);
        verify(mockPlayer).drawCards(1);
        verify(mockPlayer).setActions(1);
        verify(mockPlayer).setBuys(1);
        verify(mockPlayer).setCoins(1);
    }
}
