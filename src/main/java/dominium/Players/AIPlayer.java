package dominium.Players;

import dominium.Cards.Card;

import java.util.List;

public abstract class AIPlayer extends Player {

    public AIPlayer(String name) {
        super(name);
    }

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
