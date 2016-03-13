package dominium.Cards;

import dominium.Players.Player;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MoatTest {
    @Test
    public void currentPlayerDrawsTwoCards() {
        Player mockPlayer = mock(Player.class);

        Moat moat = new Moat();
        moat.resolve(mockPlayer, null, null);
        verify(mockPlayer).drawCards(2);
    }
}
