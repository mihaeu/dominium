package dominium.Cards;

import dominium.GameMaster;
import dominium.Players.Player;
import dominium.Players.RandomPlayer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChapelTest {
    @Test
    public void currentPlayerCanTrashOnlyTwoCards() {
        GameMaster mockMaster = mock(GameMaster.class);
        Player player = new RandomPlayer("Test");
        player.handCards().add(new Copper());
        player.handCards().add(new Silver());
        when(mockMaster.currentPlayer()).thenReturn(player);

        Chapel chapel = new Chapel();
        chapel.resolve(player, null, null);
        assertEquals(0, player.handCards().size());
    }

    @Test
    public void currentPlayerCanTrashUpToFourCards() {
        Player player = new RandomPlayer("Test");
        player.handCards().add(new Copper());
        player.handCards().add(new Silver());
        player.handCards().add(new Silver());
        player.handCards().add(new Silver());
        player.handCards().add(new Silver());

        Chapel chapel = new Chapel();
        chapel.resolve(player, null, null);
        assertEquals(1, player.handCards().size());
    }
}
