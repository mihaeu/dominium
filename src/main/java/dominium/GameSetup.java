package dominium;

import dominium.Cards.*;
import dominium.Players.Player;
import dominium.Players.RandomPlayer;

import java.util.*;

public class GameSetup {
    public static final int MIN_PLAYER_NUMBER = 1;
    public static final int MAX_PLAYER_NUMBER = 4;
    public static final int NUMBER_OF_VICTORY_CARDS = 12;
    public static final int NUMBER_COPPER_START_HAND = 7;
    public static final int NUMBER_ESTATES_START_HAND = 3;
    public static final int TOTAL_NUMBER_OF_COPPER = 60;
    public static final int NUMBER_OF_GOLD_CARDS = 30;
    public static final int NUMBER_OF_SILVER_CARDS = 40;

    public GameState initiateGameState(List<Player> players) {
        int numberOfCoppers = TOTAL_NUMBER_OF_COPPER - players.size() * NUMBER_COPPER_START_HAND;
        int numberOfEstates = NUMBER_OF_VICTORY_CARDS - players.size() * NUMBER_ESTATES_START_HAND;
        //Change here if more than 4 players allowed
        int numberOfProvinces = NUMBER_OF_VICTORY_CARDS - ((4 - players.size()) * 2);

        Map<Class, Stack<Card>> kingdomCards = new HashMap<Class, Stack<Card>>();
        kingdomCards.put(Copper.class, createCardStack(new Copper(), numberOfCoppers));
        kingdomCards.put(Silver.class, createCardStack(new Silver(), NUMBER_OF_SILVER_CARDS));
        kingdomCards.put(Gold.class, createCardStack(new Gold(), NUMBER_OF_GOLD_CARDS));
        kingdomCards.put(Estate.class, createCardStack(new Estate(), numberOfEstates));
        kingdomCards.put(Duchy.class, createCardStack(new Duchy(), NUMBER_OF_VICTORY_CARDS));
        kingdomCards.put(Province.class, createCardStack(new Province(), numberOfProvinces));

        GameState gameState = new GameState(kingdomCards);
        for (Player player : players) {
            Stack<Card> startCards = new Stack<Card>();
            startCards.push(new Copper());
            startCards.push(new Copper());
            startCards.push(new Copper());
            startCards.push(new Copper());
            startCards.push(new Copper());
            startCards.push(new Copper());
            startCards.push(new Copper());
            startCards.push(new Estate());
            startCards.push(new Estate());
            startCards.push(new Estate());
            Collections.shuffle(startCards);

            List<Card> handCards = new ArrayList<Card>();
            for (int i = 0; i < 5; i++) {
                Card card = startCards.pop();
                handCards.add(card);
            }

            gameState.getDeckCards().put(player, startCards);
            gameState.getHandCards().put(player, new Stack<Card>());
            gameState.getDiscardCards().put(player, new Stack<Card>());
            gameState.getTurnsPlayedPerPlayer().put(player,0);
        }

        return gameState;
    }

    private Stack<Card> createCardStack(Card card, int numberOfCards) {
        Stack<Card> cards = new Stack<Card>();
        for (int i = 0; i < numberOfCards; i++) {
            cards.add(card);
        }
        return cards;
    }

    public List<Player> initiatePlayers(int playerNumber) {
        ensureNotTooFewPlayers(playerNumber);
        ensureNotTooManyPlayers(playerNumber);

        List<Player> playerCollection = new ArrayList<Player>();
        for (int i = 0; i < playerNumber; i++) {
            playerCollection.add(new RandomPlayer(Integer.toString(i + 1)));
        }
        return playerCollection;
    }

    private void ensureNotTooManyPlayers(int playerNumber) {
        if (playerNumber > MAX_PLAYER_NUMBER) {
            throw new IllegalArgumentException();
        }
    }

    private void ensureNotTooFewPlayers(int playerNumber) {
        if (playerNumber < MIN_PLAYER_NUMBER) {
            throw new IllegalArgumentException();
        }
    }
}
