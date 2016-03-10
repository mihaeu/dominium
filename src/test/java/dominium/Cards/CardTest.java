package dominium.Cards;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardTest {
    @Test
    public void cardsHaveAPrice() {
        Copper copper = new Copper();
        assertEquals(0, copper.getCost());
        assertEquals(1, copper.getValue());

        Silver silver = new Silver();
        assertEquals(3, silver.getCost());
        assertEquals(2, silver.getValue());

        Gold gold = new Gold();
        assertEquals(6, gold.getCost());
        assertEquals(3, gold.getValue());

        Estate estate = new Estate();
        assertEquals(2, estate.getCost());
        assertEquals(1, estate.getVictoryPoints());

        Duchy duchy = new Duchy();
        assertEquals(5, duchy.getCost());
        assertEquals(3, duchy.getVictoryPoints());

        Province province = new Province();
        assertEquals(8, province.getCost());
        assertEquals(6, province.getVictoryPoints());
    }

    @Test
    public void cardsHaveAText()
    {
        assertEquals("1 Coin", new Copper().getText());
    }

    @Test
    public void cardsHaveAName()
    {
        assertEquals("Copper", new Copper().getName());
    }
}
