package dominium;

import dominium.Cards.Card;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Convenience {
    public static CardStack stack(Class... cards) {
        CardStack stack = new CardStack();
        for (Class cardClass : cards) {
            try {
                stack.add((Card) cardClass.newInstance());
            } catch (Exception e) {
                // this is a convenience method for testing, so no worries if it crashes when misused
                System.out.println("CardStack instantiation failed with " + cardClass);
                System.exit(1);
            }
        }
        return stack;
    }

    public static CardStack stack(Class card, int number) {
        CardStack stack = new CardStack();
        try {
            for (int i = 0; i < number; i++) {
                stack.add((Card) card.newInstance());
            }
        } catch (Exception e) {
            // this is a convenience method for testing, so no worries if it crashes when misused
            System.out.println("CardStack instantiation failed with " + card);
            System.exit(1);
        }
        return stack;
    }

    public static Map<Class, Stack<Card>> kingdomCards(Class... cards) {
        Map<Class, Stack<Card>> kingdomCards = new HashMap<>();
        for (Class cardClass : cards) {
            try {
                kingdomCards.put(cardClass, stack(cardClass, 10));
            } catch (Exception e) {
                // this is a convenience method for testing, so no worries if it crashes when misused
                System.out.println("CardStack instantiation failed with " + cardClass);
                System.exit(1);
            }
        }
        return kingdomCards;
    }
}
