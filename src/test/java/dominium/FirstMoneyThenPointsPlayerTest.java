package dominium;

import dominium.Cards.*;
import dominium.Players.FirstMoneyThenPointsPlayer;
import dominium.Players.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FirstMoneyThenPointsPlayerTest {

    private Player player;

    @Before
    public void setUp()
    {
        player = new FirstMoneyThenPointsPlayer("Test");
    }

    @Test
    public void discardsAllHandCards() {
        Card copper = new Copper();
        Card province = new Province();
        player.handCards().push(copper);
        player.handCards().push(province);

        CardStack expected = new CardStack();
        expected.push(copper);
        expected.push(province);

        player.discardAllCards();
        assertEquals(expected, player.discardedCards());
    }

    @Test
    public void discardsOnlyOneCard()
    {
        Card copper = new Copper();
        Card province = new Province();
        player.handCards().push(copper);
        player.handCards().push(province);

        CardStack expected = new CardStack();
        expected.push(copper);

        player.discardCard(province);
        assertEquals(expected, player.handCards());
    }

    @Test
    public void drawsCardsFromDeckIfItHasMoreThanFiveCards()
    {
        Card card1 = new Copper();
        Card card2 = new Silver();
        Card card3 = new Gold();
        Card card4 = new Province();
        Card card5 = new Duchy();
        Card card6 = new Estate();
        player.deckCards().add(card1);
        player.deckCards().add(card2);
        player.deckCards().add(card3);
        player.deckCards().add(card4);
        player.deckCards().add(card5);
        player.deckCards().add(card6);

        player.drawCards(5);
        assertEquals(5, player.handCards().size());
        assertEquals(1, player.deckCards().size());
    }

    @Test
    public void rebuildsDeckIfItDoesNotHaveEnoughCardsForDrawing()
    {
        Card card1 = new Copper();
        Card card2 = new Silver();
        Card card3 = new Gold();
        Card card4 = new Province();
        Card card5 = new Duchy();
        Card card6 = new Estate();
        player.deckCards().add(card1);
        player.deckCards().add(card2);
        player.deckCards().add(card3);
        player.discardedCards().add(card4);
        player.discardedCards().add(card5);
        player.discardedCards().add(card6);

        player.drawCards(5);
        assertEquals(5, player.handCards().size());
        assertEquals(1, player.deckCards().size());
        assertEquals(0, player.discardedCards().size());
    }

    @Test
    public void computesVictoryPoints()
    {
        player.deckCards().add(new Province());
        player.handCards().add(new Duchy());
        player.discardedCards().add(new Estate());
        assertEquals(10, player.victoryPoints());
    }

    @Test
    public void noVictoryPointsWithoutVictoryCards()
    {
        assertEquals(0, player.victoryPoints());
    }

    @Test
    public void computesCoins()
    {
        player.handCards().add(new Gold());
        player.handCards().add(new Silver());
        player.handCards().add(new Copper());
        player.discardedCards().add(new Gold());
        player.deckCards().add(new Silver());
        assertEquals(6, player.coins());
    }

    @Test
    public void noTreasureCardsNoCoins()
    {
        assertEquals(0, player.coins());
    }
}
