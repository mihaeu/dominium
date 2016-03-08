package dominium;

import dominium.Cards.Card;
import dominium.Cards.Province;

import java.util.Map;
import java.util.Stack;

public class GameState {
    private Map<Class, Stack<Card>> kingdomCards;

    public GameState(Map<Class, Stack<Card>> kingdomCards) {
        this.kingdomCards = kingdomCards;
    }

    public boolean gameIsRunning() {
        return !provinceCardsEmpty() && !threeStacksEmpty();
    }

    private boolean threeStacksEmpty() {
        int count = 0;
        for (Stack<Card> stack : kingdomCards.values()) {
            if (stack.size() == 0) {
                count++;
            }
        }
        return count >= 3;
    }

    private boolean provinceCardsEmpty() {
        return kingdomCards.get(Province.class).size() == 0;
    }

    public Map<Class, Stack<Card>> getKingdomCards() {
        return kingdomCards;
    }
}
