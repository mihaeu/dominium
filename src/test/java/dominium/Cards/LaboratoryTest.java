package dominium.Cards;

import dominium.Players.Player;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LaboratoryTest {
    @Test
    public void currentPlayerDrawsTwoCardsAndGetsOneAction() {
        Player mockPlayer = mock(Player.class);

        Laboratory laboratory = new Laboratory();
        laboratory.resolve(mockPlayer, null, null);
        verify(mockPlayer).drawCards(2);
        verify(mockPlayer).setActions(1);
    }
}
