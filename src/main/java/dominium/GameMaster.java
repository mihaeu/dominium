package dominium;

import java.util.*;

public class GameMaster {
    TurnBasedGameState turnBasedGameState;
    PermanentGameState permanentGameState;

    public GameMaster(PermanentGameState permanentGameState, TurnBasedGameState turnBasedGameState) {
        this.permanentGameState = permanentGameState;
        this.turnBasedGameState = turnBasedGameState;

    }

    public void discardCard(int numberOfCardsToDiscard) {
        Player currentPlayer = turnBasedGameState.getCurrentPlayer();
        Collection<Card> handCards = currentPlayer.getHandCards();
        currentPlayer.selectCardsToDiscard(numberOfCardsToDiscard, handCards);
    }

    public void drawCard(int numberOfCardsToDraw) {
        Player currentPlayer = turnBasedGameState.getCurrentPlayer();
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
        Player currentPlayer = turnBasedGameState.getCurrentPlayer();
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
