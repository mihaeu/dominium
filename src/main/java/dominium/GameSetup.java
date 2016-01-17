package dominium;

import dominium.Cards.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

public class GameSetup {
    public static final int MIN_PLAYER_NUMBER = 1;
    public static final int MAX_PLAYER_NUMBER = 4;

    private Collection<Collection<Card>> kingdomCards;

    public GameState initiateGameState(int playerNumber) {
        kingdomCards = new ArrayList<Collection<Card>>();

        //fill with basic Cards, always the same
        int numberOfCoppers = 60 - playerNumber * 7;

        int numberOfEstates = 12 - playerNumber * 3;
        fillKingdomCards(new Copper(), numberOfCoppers);
        fillKingdomCards(new Silver(), 40);
        fillKingdomCards(new Gold(), 30);
        fillKingdomCards(new Estate(), numberOfEstates);
        fillKingdomCards(new Duchy(), 12);
        fillKingdomCards(new Province(), 12);

        return new GameState(kingdomCards);
    }

    private void fillKingdomCards(Card card, int numberOfCards) {
        if (numberOfCards < 1) {
            return;
        }

        Stack<Card> cards = new Stack<Card>();
        for (int i = 0; i < numberOfCards; i++) {
            cards.add(card);
        }
        kingdomCards.add(cards);
    }

//    private void initiateKingdomCardSets() {
//        cardCollection = new ArrayList<Card>();
//        cardCollection.add(new Card("Copper", Card.Type.Money, 0, 1));
//        cardCollection.add(new Card("Silver", Card.Type.Money, 3, 2));
//        cardCollection.add(new Card("Gold", Card.Type.Money, 6, 3));
//        cardCollection.add(new Card("Estate", Card.Type.Point, 2));
//        cardCollection.add(new Card("Duchy", Card.Type.Point, 5));
//        cardCollection.add(new Card("Province", Card.Type.Point, 8));
//        cardCollection.add(new Card("Curse", Card.Type.Point, 0));
//    }

    public ArrayList<Player> initiatePlayers(int playerNumber) {
        ensureNotTooFewPlayers(playerNumber);
        ensureNotTooManyPlayers(playerNumber);

        ArrayList<Player> playerCollection = new ArrayList<Player>();
        while (playerNumber > 0) {
            playerCollection.add(new Player(Integer.toString(playerNumber)));
            --playerNumber;
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
