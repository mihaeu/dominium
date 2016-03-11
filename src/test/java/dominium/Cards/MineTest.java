package dominium.Cards;

import dominium.CardStack;
import dominium.Convenience;
import dominium.GameMaster;
import dominium.Players.Player;
import dominium.Players.RandomPlayer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class MineTest {
    @Test
    public void currentPlayerTrashedCardForTwoGetsCardForFive() {
        GameMaster mockMaster = mock(GameMaster.class);
        Player randomPlayer = new RandomPlayer("Test");
        when(mockMaster.currentPlayer()).thenReturn(randomPlayer);

        Moat moat = new Moat();
        Silver silver = new Silver();
        randomPlayer.handCards().add(moat);
        randomPlayer.handCards().add(silver);

        when(mockMaster.kingdomCards()).thenReturn(
                Convenience.kingdomCards(Market.class, Gold.class)
        );

        Mine mine = new Mine();
        mine.resolve(mockMaster);

        assertEquals(2, randomPlayer.handCards().size());
        assertTrue(randomPlayer.handCards().contains(moat));
        assertTrue(randomPlayer.handCards().hasCard(Gold.class));
    }

    @Test
    public void abortsIfNoCardIsTrashed() {
        GameMaster mockMaster = mock(GameMaster.class);
        Player player = mock(Player.class);
        when(mockMaster.currentPlayer()).thenReturn(player);
        when(player.handCards()).thenReturn(new CardStack());

        Mine mine = new Mine();
        mine.resolve(mockMaster);

        verify(player, never()).trashCardFromHand(any());
    }
}
