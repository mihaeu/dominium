package dominium;

import dominium.Cards.Card;
import dominium.Cards.CardType;

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

    public CardStack filterCards(Predicate<Card> predicate) {
        if (empty()) {
            return new CardStack();
        }
        return stream()
                .filter(predicate)
                .collect(Collectors.toCollection(CardStack::new));
    }

    public CardStack filterCards(Card card) {
        return filterCards(c -> c.equals(card));
    }

    public CardStack filterCards(CardType type) {
        return filterCards(c -> c.getTypes().contains(type));
    }

    public CardStack filterCards(int maxCost) {
        return filterCards(card -> card.getCost() <= maxCost);
    }

    public static List<Card> filterCards(List<Card> cards, Card card) {
        return filterCards(cards, c -> c.equals(card));
    }

    public static List<Card> filterCards(List<Card> cards, CardType type) {
        return filterCards(cards, c -> c.getTypes().contains(type));
    }

    public static List<Card> filterCards(List<Card> cards, Predicate<Card> predicate) {
        if (cards.isEmpty()) {
            return new CardStack();
        }
        return cards.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
