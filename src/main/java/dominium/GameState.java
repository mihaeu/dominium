package dominium;

import dominium.Cards.Card;
import dominium.Players.Player;

import java.util.*;

public class GameState {
    private Map<Class, Stack<Card>> kingdomCards;
    private Map<Player, Stack<Card>> deckCards;
    private Map<Player, List<Card>> handCards;
    private Map<Player, Stack<Card>> discardCards;
    private Map<Player, Integer> turnsPlayedPerPlayer;

    public GameState(Map<Class, Stack<Card>> kingdomCards) {
        this.kingdomCards = kingdomCards;

        deckCards = new HashMap<Player, Stack<Card>>();
        handCards = new HashMap<Player, List<Card>>();
        discardCards = new HashMap<Player, Stack<Card>>();
        turnsPlayedPerPlayer = new HashMap<Player, Integer>();
    }

    public Map<Player, Integer> getTurnsPlayedPerPlayer() {
        return turnsPlayedPerPlayer;
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
