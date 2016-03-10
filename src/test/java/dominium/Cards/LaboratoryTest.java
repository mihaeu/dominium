package dominium.Cards;

import dominium.GameMaster;
import dominium.Players.Player;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LaboratoryTest {
    @Test
    public void currentPlayerDrawsTwoCardsAndGetsOneAction() {
        GameMaster mockMaster = mock(GameMaster.class);
        Player mockPlayer = mock(Player.class);
        when(mockMaster.currentPlayer()).thenReturn(mockPlayer);

        Laboratory laboratory = new Laboratory();
        laboratory.resolve(mockMaster);
        verify(mockPlayer).drawCards(2);
        verify(mockPlayer).setActions(1);
    }
}
