package dominium.Cards;

import dominium.Convenience;
import dominium.Players.Player;
import dominium.Players.RandomPlayer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WorkshopTest {
    @Test
    public void currentPlayerGainsCardCostingFour() {
        Player player = new RandomPlayer("Test");

        Workshop workshop = new Workshop();
        workshop.resolve(player, null, Convenience.kingdomCards(new Militia(), new Gold()));
        assertTrue(player.discardedCards().hasCard(new Militia()));
        assertEquals(1, player.discardedCards().size());
    }
}
