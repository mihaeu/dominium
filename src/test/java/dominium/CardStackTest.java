package dominium;

import dominium.Cards.Copper;
import dominium.Cards.Silver;
import org.junit.Test;

import static org.junit.Assert.*;

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
            if (stack.get(0).getClass() == Silver.class) {
                break;
            }
        }
        assertEquals(stack.get(0).getClass(), Silver.class);
    }
}
