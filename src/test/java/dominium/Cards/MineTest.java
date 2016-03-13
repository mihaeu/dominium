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

        Mine mine = new Mine();
        mine.resolve(randomPlayer, null, Convenience.kingdomCards(new Market(), new Gold()));

        assertEquals(2, randomPlayer.handCards().size());
        assertTrue(randomPlayer.handCards().contains(moat));
        assertTrue(randomPlayer.handCards().hasCard(new Gold()));
    }

    @Test
    public void abortsIfNoCardIsTrashed() {
        Player player = mock(Player.class);
        when(player.handCards()).thenReturn(new CardStack());

        Mine mine = new Mine();
        mine.resolve(player, null, null);

        verify(player, never()).trashCardFromHand(any());
    }
}
