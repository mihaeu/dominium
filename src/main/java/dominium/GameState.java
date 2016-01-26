package dominium;

import dominium.Cards.Card;
import dominium.Cards.Province;
import dominium.Players.Player;

import java.util.*;

public class GameState {
    private Map<Class, Stack<Card>> kingdomCards;
    private Map<Player, Stack<Card>> deckCards;
    private Map<Player, List<Card>> handCards;
    private Map<Player, Stack<Card>> discardCards;

    public GameState(Map<Class, Stack<Card>> kingdomCards) {
        this.kingdomCards = kingdomCards;

        deckCards = new HashMap<Player, Stack<Card>>();
        handCards = new HashMap<Player, List<Card>>();
        discardCards = new HashMap<Player, Stack<Card>>();
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

    public Map<Player, Stack<Card>> getDeckCards() {
        return deckCards;
    }

    public Map<Player, List<Card>> getHandCards() {
        return handCards;
    }

    public Map<Player, Stack<Card>> getDiscardCards() {
        return discardCards;
    }
}
