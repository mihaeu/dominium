package dominium;

import dominium.Cards.Card;

import java.util.HashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class KingdomCardMap extends HashMap<Card, CardStack> {
    /**
     * @param predicate Filter which is applied to all keys
     * @return CardStack filled with the filtered cards
     */
    public CardStack mapToCards(Predicate<Card> predicate) {
        return keySet().stream()
                .filter(predicate)
                .collect(Collectors.toCollection(CardStack::new));
    }
}
