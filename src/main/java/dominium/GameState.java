package dominium;

import dominium.Cards.Card;

import java.util.*;

public class GameState {
    private Map<Class, Stack<Card>> kingdomCards;
    private Map<Player, Stack<Card>> deckCards;

    public GameState(Map<Class, Stack<Card>> kingdomCards) {
        this.kingdomCards = kingdomCards;

        deckCards = new HashMap<Player, Stack<Card>>();
    }

    public Map<Class, Stack<Card>> getKingdomCards() {
        return kingdomCards;
    }

    public Map<Player, Stack<Card>> getDeckCards() {
        return deckCards;
    }
}
