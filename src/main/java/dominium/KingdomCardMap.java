package dominium;

import dominium.Cards.Card;

import java.util.HashMap;
import java.util.stream.Collectors;

public class KingdomCardMap extends HashMap<Card, CardStack> {
    public CardStack keysOfNonEmptyStacks() {
        return keySet().stream()
                .filter(card -> get(card).size() > 0)
                .collect(Collectors.toCollection(CardStack::new));
    }
}
