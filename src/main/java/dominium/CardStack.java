package dominium;

import dominium.Cards.Card;

import java.util.Collections;
import java.util.Stack;

public class CardStack extends Stack<Card> {
    public CardStack(Class... cards) {
        for (Class cardClass : cards) {
            try {
                add((Card) cardClass.newInstance());
            } catch (Exception e) {
                // this is a convenience method for testing, so no worries if it crashes when misused
                System.out.println("CardStack instantiation failed with " + cardClass);
                System.exit(1);
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(this);
    }
}
