package dominium;

import dominium.Cards.Card;
import dominium.Cards.Estate;
import dominium.Cards.Province;

import java.util.*;

public class GameMaster {
    GameState gameState;
    List<Player> players;

    public GameMaster(List<Player> players, GameState gameState) {
        this.players = players;
        this.gameState = gameState;
    }

    public void startGame() {
        while (gameIsRunning()) {
            for (Player player : players) {
                player.selectCardsToBuy(gameState.getKingdomCards());
            }
        }
    }

    private boolean gameIsRunning() {
        return !provinceCardsEmpty() && !threeStacksEmpty();
    }

    private boolean threeStacksEmpty() {
        int count = 0;
        for (Stack<Card> stack : gameState.getKingdomCards().values()) {
            if (stack.size() == 0) {
                count++;
            }
        }
        return count >= 3;
    }

    private boolean provinceCardsEmpty() {
        return gameState.getKingdomCards().get(Province.class).size() == 0;
    }


    public void discardCard(int numberOfCardsToDiscard) {
        Player currentPlayer = players.get(0);
//        Collection<Card> handCards = currentPlayer.getHandCards();
//        currentPlayer.selectCardsToDiscard(numberOfCardsToDiscard, handCards);
    }


    public void drawCard(int numberOfCardsToDraw) {
//        Player currentPlayer = players.get(0);
//        Collection<Card> handCards = currentPlayer.getHandCards();
//        Collection<Card> deckCards = currentPlayer.getDeckCards();
//        Collection<Card> discardedCards = currentPlayer.getDiscardedCards();
//
//        while (numberOfCardsToDraw > 0) {
//            --numberOfCardsToDraw;
//
//            int possibleCardsToDraw = deckCards.size();
//            if (possibleCardsToDraw == 0) {
//                shuffleDiscardedCardsIntoDeck();
//            }
//
//            possibleCardsToDraw = deckCards.size();
//
//            if (possibleCardsToDraw != 0) {
//                Iterator<Card> iterator = deckCards.iterator();
//                Card cardToDraw = iterator.next();
//                handCards.add(cardToDraw);
//                deckCards.remove(cardToDraw);
//            }
//        }
    }

    public void revealCard(int numberOfCardsToDraw) {

    }

//    public boolean playerHasCard(Player player, Card card) {
//        Collection<Card> handCards = player.getHandCards();
//        return handCards.contains(card);
//    }
//
//    private void shuffleDiscardedCardsIntoDeck() {
//        Player currentPlayer = players.get(0);
//        Collection<Card> deckCards = currentPlayer.getDeckCards();
//        Collection<Card> discardedCards = currentPlayer.getDiscardedCards();
//
//        if (deckCards.size() == 0) {
//            deckCards = discardedCards;
//            discardedCards = new ArrayList<Card>();
//            shuffleCards(deckCards);
//        }
//    }

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
