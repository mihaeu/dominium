package dominium.Players;

import dominium.CardStack;
import dominium.Cards.Card;
import dominium.Cards.TreasureCard;
import dominium.Cards.VictoryCard;

import java.util.List;

public abstract class Player {
    private static final int CARDS_TO_DRAW = 5;

    protected String name;
    protected CardStack handCards;
    protected CardStack discardedCards;
    protected CardStack deckCards;
    protected int turns;

    public String getName() {
        return name;
    }

    public Player(String name) {
        this.name = name;

        this.handCards = new CardStack();
        this.discardedCards = new CardStack();
        this.deckCards = new CardStack();
    }

    public abstract Card selectCard(List<Card> cards);

    public void discardCard(Card card) {
        ensureCardIsInStack(card, handCards);

        handCards.remove(card);
        discardedCards.push(card);
    }

    public void discardAllCards() {
        discardedCards.addAll(handCards);
        handCards.removeAllElements();
    }

    public void drawCards() {
        if (deckCards.size() < CARDS_TO_DRAW) {
            discardedCards.shuffle();
            deckCards.addAll(discardedCards);
            discardedCards.removeAllElements();
        }

        for (int i = 0; i < CARDS_TO_DRAW; i++) {
            handCards.push(deckCards.pop());
        }
    }

    public CardStack handCards() {
        return handCards;
    }

    public CardStack discardedCards() {
        return discardedCards;
    }

    public CardStack deckCards() {
        return deckCards;
    }

    public int victoryPoints() {
        return victoryPointsInCardStack(handCards)
                + victoryPointsInCardStack(discardedCards)
                + victoryPointsInCardStack(deckCards);
    }

    public int coins() {
        int sum = 0;
        for (Card card : handCards) {
            if (card instanceof TreasureCard) {
                sum += ((TreasureCard) card).getValue();
            }
        }
        return sum;
    }

    public int turns() {
        return turns;
    }

    public void incrementTurns() {
        turns++;
    }

    private void ensureCardIsInStack(Card card, CardStack stack) {
        if (!stack.contains(card)) {
            throw new IllegalArgumentException("Card cannot be used, because it is not in the stack.");
        }
    }

    private int victoryPointsInCardStack(CardStack cardStack) {
        int sum = 0;
        for (Card card : cardStack) {
            if (card instanceof VictoryCard) {
                sum += ((VictoryCard) card).getVictoryPoints();
            }
        }
        return sum;
    }
}
