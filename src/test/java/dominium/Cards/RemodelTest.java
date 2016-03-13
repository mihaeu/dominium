package dominium.Cards;

import dominium.Convenience;
import dominium.GameMaster;
import dominium.Players.Player;
import dominium.Players.RandomPlayer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RemodelTest {
    @Test
    public void currentPlayerTrashesSilverAndGainsMilitia()
    {
        GameMaster mockMaster = mock(GameMaster.class);
        Player randomPlayer = new RandomPlayer("Test");
        when(mockMaster.currentPlayer()).thenReturn(randomPlayer);

        Silver silver = new Silver();
        randomPlayer.handCards().add(silver);

        KingdomCardMap kingdomCards = Convenience.kingdomCards(new Militia(), new Gold());
        when(mockMaster.kingdomCards()).thenReturn(kingdomCards);

        Remodel remodel = new Remodel();
        remodel.resolve(mockMaster);

        assertEquals(1, randomPlayer.discardedCards().size());
        assertEquals(0, randomPlayer.handCards().size());
        assertTrue(randomPlayer.discardedCards().hasCard(new Militia()));
    }
}
