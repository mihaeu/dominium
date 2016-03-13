package dominium;

import dominium.Cards.Card;
import dominium.Cards.CardType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CardStack extends Stack<Card> {
    public void shuffle() {
        Collections.shuffle(this);
    }

    public boolean hasCard(Card card) {
        return stream().anyMatch(c -> c.equals(card));
    }

    public CardStack filterCards(Card card) {
        return stream()
                .filter(c -> c.equals(card))
                .collect(Collectors.toCollection(CardStack::new));
    }

    public CardStack filterCards(CardType type) {
        return stream()
                .filter(c -> c.getTypes().contains(type))
                .collect(Collectors.toCollection(CardStack::new));
    }

    public CardStack filterCards(Predicate<Card> predicate) {
        return stream()
                .filter(predicate)
                .collect(Collectors.toCollection(CardStack::new));
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
