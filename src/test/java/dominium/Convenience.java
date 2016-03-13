package dominium;

import dominium.Cards.Card;
import dominium.Cards.KingdomCardMap;

public class Convenience {
    public static CardStack stack(Card... cards) {
        CardStack stack = new CardStack();
        for (Card card : cards) {
            stack.add(card);
        }
        return stack;
    }

    public static CardStack stack(Card card, int number) {
        CardStack stack = new CardStack();
        try {
            for (int i = 0; i < number; i++) {
                stack.add(card);
            }
        } catch (Exception e) {
            // this is a convenience method for testing, so no worries if it crashes when misused
            System.out.println("CardStack instantiation failed with " + card);
            System.exit(1);
        }
        return stack;
    }

    public static KingdomCardMap kingdomCards(Card... cards) {
        KingdomCardMap kingdomCards = new KingdomCardMap();
        for (Card card : cards) {
            kingdomCards.put(card, stack(card));
        }
        return kingdomCards;
    }
}
