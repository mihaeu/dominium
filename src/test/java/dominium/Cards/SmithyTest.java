package dominium.Cards;

import dominium.Players.Player;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SmithyTest {
    @Test
    public void currentPlayerDrawsTwoCards() {
        Player mockPlayer = mock(Player.class);

        Smithy smithy = new Smithy();
        smithy.resolve(mockPlayer, null, null);
        verify(mockPlayer).drawCards(3);
    }
}
