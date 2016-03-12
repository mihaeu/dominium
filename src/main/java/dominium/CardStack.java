package dominium;

import dominium.Cards.Card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class CardStack extends Stack<Card> {
    public void shuffle() {
        Collections.shuffle(this);
    }

    public boolean hasCard(Class cardClass) {
        return stream().anyMatch(card -> card.getClass() == cardClass);
    }

    public CardStack filterCards(Class cardClass) {
        CardStack filteredStack = new CardStack();
        for (Card card : this) {
            if (card.getClass() == cardClass
                    || Arrays.asList(card.getClass().getInterfaces()).contains(cardClass)) {
                filteredStack.add(card);
            }
        }
        return filteredStack;
    }

    public static CardStack filterCards(List<Card> cards, Class cardClass) {
        CardStack filteredStack = new CardStack();
        for (Card card : cards) {
            if (card.getClass() == cardClass
                    || Arrays.asList(card.getClass().getInterfaces()).contains(cardClass)) {
                filteredStack.add(card);
            }
        }
        return filteredStack;
    }
}
