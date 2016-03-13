package dominium.Cards;

import dominium.Convenience;
import dominium.Players.Player;
import dominium.Players.RandomPlayer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RemodelTest {
    @Test
    public void currentPlayerTrashesSilverAndGainsMilitia()
    {
        Player randomPlayer = new RandomPlayer("Test");

        Silver silver = new Silver();
        randomPlayer.handCards().add(silver);

        Remodel remodel = new Remodel();
        remodel.resolve(randomPlayer, null, Convenience.kingdomCards(new Militia(), new Gold()));

        assertEquals(1, randomPlayer.discardedCards().size());
        assertEquals(0, randomPlayer.handCards().size());
        assertTrue(randomPlayer.discardedCards().hasCard(new Militia()));
    }
}
