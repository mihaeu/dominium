package dominium;

import dominium.Cards.Card;
import dominium.Cards.Copper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        List<Card> cardList = new ArrayList<Card>();
        Card cardInList = new Copper();
        cardList.add(cardInList);
        Card selectedCard = player.selectCard(cardList);
        assertEquals(cardInList, selectedCard);
    }
}