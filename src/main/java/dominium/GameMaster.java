package dominium;

import dominium.Cards.Card;
import dominium.Cards.Estate;

import java.util.*;

public class GameMaster {
    GameState gameState;
    Collection<Player> players;

    public GameMaster(Collection<Player> players, GameState gameState) {
        this.players = players;
        this.gameState = gameState;
    }

    public void discardCard(int numberOfCardsToDiscard) {
        Player currentPlayer = gameState.getCurrentPlayer();
        Collection<Card> handCards = currentPlayer.getHandCards();
        currentPlayer.selectCardsToDiscard(numberOfCardsToDiscard, handCards);
    }

    public void startGame() {
        Stack<Card> cardCollection = new Stack<Card>();
        cardCollection.add(new Estate());
        while (!cardCollection.empty()) {
            for (Player player : players) {
                player.selectCardsToBuy(cardCollection);
            }
        }
    }


    public void drawCard(int numberOfCardsToDraw) {
        Player currentPlayer = gameState.getCurrentPlayer();
        Collection<Card> handCards = currentPlayer.getHandCards();
        Collection<Card> deckCards = currentPlayer.getDeckCards();
        Collection<Card> discardedCards = currentPlayer.getDiscardedCards();

        while (numberOfCardsToDraw > 0) {
            --numberOfCardsToDraw;

            int possibleCardsToDraw = deckCards.size();
            if (possibleCardsToDraw == 0) {
                shuffleDiscardedCardsIntoDeck();
            }

            possibleCardsToDraw = deckCards.size();

            if (possibleCardsToDraw != 0) {
                Iterator<Card> iterator = deckCards.iterator();
                Card cardToDraw = iterator.next();
                handCards.add(cardToDraw);
                deckCards.remove(cardToDraw);
            }
        }
    }

    public void revealCard(int numberOfCardsToDraw) {

    }

    public boolean playerHasCard(Player player, Card card) {
        Collection<Card> handCards = player.getHandCards();
        return handCards.contains(card);
    }

    private void shuffleDiscardedCardsIntoDeck() {
        Player currentPlayer = gameState.getCurrentPlayer();
        Collection<Card> deckCards = currentPlayer.getDeckCards();
        Collection<Card> discardedCards = currentPlayer.getDiscardedCards();

        if (deckCards.size() == 0) {
            deckCards = discardedCards;
            discardedCards = new ArrayList<Card>();
            shuffleCards(deckCards);
        }
    }

    /**
     * This implementation should work, but actually it would be better for the
     * Collection to know how to shuffle itself.
     *
     * @param deckCards
     */
    private void shuffleCards(Collection<Card> deckCards) {
        List list = new ArrayList<Card>(deckCards);
        Collections.shuffle(list);
    }
}
