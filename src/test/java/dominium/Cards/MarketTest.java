package dominium.Cards;

import dominium.Players.Player;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MarketTest {
    @Test
    public void currentPlayerGetsOneActionOneBuyOneCoinAndDrawsOneCard() {
        Player mockPlayer = mock(Player.class);

        Market market = new Market();
        market.resolve(mockPlayer, null, null);
        verify(mockPlayer).drawCards(1);
        verify(mockPlayer).setActions(1);
        verify(mockPlayer).setBuys(1);
        verify(mockPlayer).setCoins(1);
    }
}
