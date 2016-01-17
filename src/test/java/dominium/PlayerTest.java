package dominium;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

public class PlayerTest {
    @Test
    public void playerBuysACard() {

        Player player = new Player("test");
        Stack<Card> cardsToChooseFrom = new Stack<Card>();
        Card cardExpected = new Card("Copper", Card.Type.Money, 0, 1);
        cardsToChooseFrom.add(cardExpected);

        Card cardReal = player.selectCardsToBuy(cardsToChooseFrom);
        Assert.assertEquals(cardExpected, cardReal);


    }
}