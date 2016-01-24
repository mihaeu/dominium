package dominium.Players;

import dominium.Cards.Card;
import dominium.Cards.TreasureCard;

import java.util.List;
import java.util.Stack;

public abstract class Player {
    protected String name;
    protected Stack<Card> handCards;
    protected Stack<Card> discardedCards;
    protected Stack<Card> deckCards;

    public String getName() {
        return name;
    }

    public abstract Card selectCard(List<Card> cards);

    protected int victoryPoints() {
        return countVictoryPoints(handCards)
                + countVictoryPoints(discardedCards)
                + countVictoryPoints(deckCards);
    }

    protected int coins() {
        int sum = 0;
        for (Card card : handCards) {
            if (card instanceof TreasureCard) {
                sum += ((TreasureCard) card).getValue();
            }
        }
        return sum;
    }

    private int countVictoryPoints(Stack<Card> cardStack) {
        int sum = 0;
        for (Card card : cardStack) {
            if (card instanceof TreasureCard) {
                sum += ((TreasureCard) card).getValue();
            }
        }
        return sum;
    }
}
