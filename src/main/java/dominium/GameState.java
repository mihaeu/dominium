package dominium;

import dominium.Cards.Card;
import dominium.Cards.Province;

import java.util.Map;
import java.util.Stack;
import java.util.function.Predicate;

public class GameState {
    private Map<Class, Stack<Card>> kingdomCards;

    public GameState(Map<Class, Stack<Card>> kingdomCards) {
        this.kingdomCards = kingdomCards;
    }

    public boolean gameIsRunning() {
        return !provinceCardsEmpty() && !threeStacksEmpty();
    }

    private boolean threeStacksEmpty() {
        Predicate<Stack> isEmpty = stack -> stack.size() == 0;
        return kingdomCards.values()
                .stream()
                .filter(isEmpty)
                .count() >= 3;
    }

    private boolean provinceCardsEmpty() {
        return kingdomCards.get(Province.class).size() == 0;
    }

    public Map<Class, Stack<Card>> getKingdomCards() {
        return kingdomCards;
    }
}
