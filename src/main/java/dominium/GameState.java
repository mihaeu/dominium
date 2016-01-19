package dominium;

import dominium.Cards.Card;

import java.util.*;

public class GameState {
    public int actionsLeft = 0;
    public int buysLeft = 0;
    public int coinsLeft = 0;

    private Map<Class, Stack<Card>> kingdomCards;
    private Map<Player, Stack<Card>> discardedCards;
    private Map<Player, Stack<Card>> handCards;

    private Map<Player, Stack<Card>> deckCards;

    private List<Card> trashedCards;

    public GameState(Map<Class, Stack<Card>> kingdomCards) {
        this.kingdomCards = kingdomCards;

        discardedCards = new HashMap<Player, Stack<Card>>();
        handCards = new HashMap<Player, Stack<Card>>();
        deckCards = new HashMap<Player, Stack<Card>>();
    }

    public Map<Class, Stack<Card>> getKingdomCards() {
        return kingdomCards;
    }

    public Map<Player, Stack<Card>> getDeckCards() {
        return deckCards;
    }

    public Map<Player, Stack<Card>> getDiscardedCards() {
        return discardedCards;
    }

    public Map<Player, Stack<Card>> getHandCards() {
        return handCards;
    }
}
