package dominium;

import dominium.Cards.Province;

import java.util.Stack;
import java.util.function.Predicate;

public class GameState {
    private KingdomCardMap kingdomCards;

    public GameState(KingdomCardMap kingdomCards) {
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
        return kingdomCards.get(new Province()).size() == 0;
    }

    public KingdomCardMap getKingdomCards() {
        return kingdomCards;
    }
}
