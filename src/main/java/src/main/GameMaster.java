package src.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by SWINE on 10.01.2016.
 */
public class GameMaster {
    TurnbasedGameState turnbasedGameState;
    PermanentGameState permanentGameState;

    public GameMaster(PermanentGameState permanentGameState, TurnbasedGameState turnbasedGameState) {
        this.permanentGameState = permanentGameState;
        this.turnbasedGameState = turnbasedGameState;

    }

    ;

    public void discardCard(int numberOfCardsToDiscard) {

    }

    ;

    public void drawCard(int numberOfCardsToDraw) {
        Player currentPlayer = turnbasedGameState.getCurrentPlayer();
        Collection<Card> handCards = currentPlayer.getHandCards();
        Collection<Card> deckCards = currentPlayer.getDeckCards();
        Collection<Card> discardedCards = currentPlayer.getDiscardedCards();

        while (numberOfCardsToDraw > 0){
            --numberOfCardsToDraw;

            int possibleCardsToDraw = deckCards.size();
            if (possibleCardsToDraw == 0 ){
                shuffleDiscardedCardsIntoDeck();
            }

            possibleCardsToDraw = deckCards.size();

            if (possibleCardsToDraw != 0 ) {
                Iterator<Card> iterator = deckCards.iterator();
                Card cardToDraw = iterator.next();
                handCards.add(cardToDraw);
                deckCards.remove(cardToDraw);
            }
        }
    }



    ;

    public void revealCard(int numberOfCardsToDraw) {

    }

    ;

    public boolean playerHasCard(src.main.Player player, src.main.Card card) {

        Collection<Card> handCards = player.getHandCards();
        boolean hasCard = handCards.contains(card);
        return hasCard;
    }

    private void shuffleDiscardedCardsIntoDeck() {
        Player currentPlayer = turnbasedGameState.getCurrentPlayer();
        Collection<Card> deckCards = currentPlayer.getDeckCards();
        Collection<Card> discardedCards = currentPlayer.getDiscardedCards();

        if(deckCards.size() == 0) {
            deckCards = discardedCards;
            discardedCards = new ArrayList<Card>();
            shuffleCards(deckCards);
        }
    }

    private void shuffleCards(Collection<Card> deckCards) {
        //todo shuffle Cards
    }
}
