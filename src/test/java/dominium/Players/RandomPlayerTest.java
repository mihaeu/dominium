package dominium.Players;

import dominium.CardStack;
import dominium.Cards.Card;
import dominium.Cards.Copper;
import dominium.Cards.Curse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RandomPlayerTest {

    @Test
    public void hasAName() {
        Player player = new RandomPlayer("Test");
        assertEquals("Test", player.getName());
    }

    @Test
    public void playerPicksOneCardFromOne() {
        Player player = new RandomPlayer("Test");
        CardStack cardList = new CardStack();
        Card cardInList = new Copper();
        cardList.add(cardInList);
        Card selectedCard = player.selectCard(cardList);
        assertEquals(cardInList, selectedCard);
    }

    @Test
    public void countsCurseCards() {
        Player player = new RandomPlayer("Test");
        player.handCards().add(new Curse());
        player.discardedCards().add(new Curse());
        player.deckCards().add(new Curse());
        assertEquals(-3, player.victoryPoints());
    }
}