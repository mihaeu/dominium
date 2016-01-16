package dominium;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class PlayerTest {
    @Test
    public void playerBuysACard() {

        Player player = new Player();
        Collection<Card> cardsToChooseFrom = new ArrayList<Card>();
        Card cardExpected = new Card("Copper", Card.Type.Money, 0, 1);
        cardsToChooseFrom.add(cardExpected);

        Card cardReal = player.selectCardsToBuy(cardsToChooseFrom);
        Assert.assertEquals(cardExpected, cardReal);


    }
}