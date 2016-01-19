package dominium;

import dominium.Cards.Card;

import java.util.*;

public class GameState {
    private Map<Class, Stack<Card>> kingdomCards;
    private Map<Player, Stack<Card>> deckCards;
    private Map<Player, Stack<Card>> handCards;
    private Map<Player, Stack<Card>> discardCards;

    public GameState(Map<Class, Stack<Card>> kingdomCards) {
        this.kingdomCards = kingdomCards;

        deckCards = new HashMap<Player, Stack<Card>>();
        handCards = new HashMap<Player, Stack<Card>>();
        discardCards = new HashMap<Player, Stack<Card>>();
    }

    public Map<Class, Stack<Card>> getKingdomCards() {
        return kingdomCards;
    }

    public Map<Player, Stack<Card>> getDeckCards() {
        return deckCards;
    }

    public Map<Player, Stack<Card>> getHandCards() {
        return handCards;
    }

    public Map<Player, Stack<Card>> getDiscardCards() {
        return discardCards;
    }
}
