package dominium;

import dominium.Cards.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CardStackTest {
    @Test
    public void shufflesTwoCards() {
        CardStack stack = new CardStack();
        stack.add(new Copper());
        stack.add(new Silver());

        // this is less than ideal, but necessary to check for the randomness
        // the chance for this test to be a false negative is
        // 7.88860905221011805411728565282786229673206435109023004... × 10^-31
        for (int i = 0; i < 100; i++) {
            stack.shuffle();
            if (stack.get(0).equals(new Silver())) {
                break;
            }
        }
        assertEquals(stack.get(0), new Silver());
    }

    @Test
    public void filtersByCard() {
        CardStack stack = Convenience.stack(new Copper(), new Moat(), new Duchy());
        assertEquals(1, stack.filterCards(new Moat()).size());
    }

    @Test
    public void filtersByType() {
        CardStack stack = Convenience.stack(new Copper(), new Moat(), new Duchy());
        assertEquals(1, stack.filterCards(CardType.Action).size());
    }

    @Test
    public void filtersByCost() {
        CardStack stack = Convenience.stack(new Copper(), new Moat(), new Militia(), new Market());
        assertEquals(3, stack.filterCards(4).size());
        assertFalse(stack.filterCards(4).hasCard(new Market()));
    }
}
