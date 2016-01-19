package dominium;

import dominium.Cards.*;

import java.util.*;

public class GameSetup {
    public static final int MIN_PLAYER_NUMBER = 1;
    public static final int MAX_PLAYER_NUMBER = 4;

    public GameState initiateGameState(List<Player> players) {
        int numberOfCoppers = 60 - players.size() * 7;
        int numberOfEstates = 12 - players.size() * 3;
        int numberOfProvinces = 12 - ((4 - players.size()) * 2);

        Map<Class, Stack<Card>> kingdomCards = new HashMap<Class, Stack<Card>>();
        kingdomCards.put(Copper.class, createCardStack(new Copper(), numberOfCoppers));
        kingdomCards.put(Silver.class, createCardStack(new Silver(), 40));
        kingdomCards.put(Gold.class, createCardStack(new Gold(), 30));
        kingdomCards.put(Estate.class, createCardStack(new Estate(), numberOfEstates));
        kingdomCards.put(Duchy.class, createCardStack(new Duchy(), 12));
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
            gameState.getDeckCards().put(player, startCards);
            gameState.getHandCards().put(player, new Stack<Card>());
            gameState.getDiscardCards().put(player, new Stack<Card>());
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
