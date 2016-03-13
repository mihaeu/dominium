package dominium.Cards;

import dominium.Convenience;
import dominium.GameMaster;
import dominium.KingdomCardMap;
import dominium.Players.Player;
import dominium.Players.RandomPlayer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WorkshopTest {
    @Test
    public void currentPlayerGainsCardCostingFour() {
        GameMaster mockMaster = mock(GameMaster.class);
        Player player = new RandomPlayer("Test");
        when(mockMaster.currentPlayer()).thenReturn(player);

        KingdomCardMap kingdomCards = Convenience.kingdomCards(new Militia(), new Gold());
        when(mockMaster.kingdomCards()).thenReturn(kingdomCards);

        Workshop workshop = new Workshop();
        workshop.resolve(mockMaster);
        assertTrue(player.discardedCards().hasCard(new Militia()));
        assertEquals(1, player.discardedCards().size());
    }
}
