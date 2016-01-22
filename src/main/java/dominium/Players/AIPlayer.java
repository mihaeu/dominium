package dominium.Players;

import java.util.List;

import dominium.Cards.Card;

public abstract class AIPlayer extends Player {

    public boolean hasCard(List<Card> cards, Class search) {
        for (Card card : cards) {
            if (card.getClass() == search) {
                return true;
            }
        }
        return false;
    }

    public Card findCard(List<Card> cards, Class search) {
        for (Card card : cards) {
            if (card.getClass() == search) {
                return card;
            }
        }
        return null;
    }
}
