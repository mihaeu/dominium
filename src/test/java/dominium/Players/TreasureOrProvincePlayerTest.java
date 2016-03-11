package dominium.Players;

import dominium.CardStack;
import dominium.Cards.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TreasureOrProvincePlayerTest {
    private Player player;

    @Before
    public void setUp() {
        player = new TreasureOrProvincePlayer("Test");
    }

    @Test
    public void canReset() {
        player.setBuys(1);
        player.setActions(1);
        player.setCoins(1);
        player.handCards().add(new Copper());
        player.reset();

        assertEquals(0, player.getBuys());
        assertEquals(0, player.getActions());
        assertEquals(0, player.getCoins());
        assertEquals(0, player.handCards().size());
    }

    @Test
    public void returnsNullWhenCardListIsEmpty() {
        assertEquals(null, player.selectCard(new CardStack()));
    }

    @Test
    public void picksARandomCardIfNoBetterChoiceIsAvailable() {
        CardStack cards = new CardStack();
        cards.add(new Village());
        assertEquals(Village.class, player.selectCard(cards).getClass());
    }

    @Test
    public void picksProvinceOverGold() {
        CardStack cards = new CardStack();
        cards.add(new Gold());
        cards.add(new Province());
        assertEquals(Province.class, player.selectCard(cards).getClass());
    }

    @Test
    public void picksGoldOverSilver() {
        CardStack cards = new CardStack();
        cards.add(new Gold());
        cards.add(new Silver());
        assertEquals(Gold.class, player.selectCard(cards).getClass());
    }

    @Test
    public void picksSilverOverDuchy() {
        CardStack cards = new CardStack();
        cards.add(new Silver());
        cards.add(new Duchy());
        assertEquals(Silver.class, player.selectCard(cards).getClass());
    }

    @Test
    public void pricksDuchyOverCopper() {
        CardStack cards = new CardStack();
        cards.add(new Duchy());
        cards.add(new Copper());
        assertEquals(Duchy.class, player.selectCard(cards).getClass());
    }

    @Test
    public void picksCopperOverEstate() {
        CardStack cards = new CardStack();
        cards.add(new Copper());
        cards.add(new Estate());
        assertEquals(Copper.class, player.selectCard(cards).getClass());
    }

    @Test
    public void picksEstateOverAnyOtherCard() {
        CardStack cards = new CardStack();
        cards.add(new Estate());
        cards.add(new Militia());
        assertEquals(Estate.class, player.selectCard(cards).getClass());
    }
}
