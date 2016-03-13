package dominium.Players;

import dominium.CardStack;
import dominium.Cards.Card;

public abstract class AIPlayer extends Player {

    public AIPlayer(String name) {
        super(name);
    }

    public Card findCard(CardStack cards, Class search) {
        for (Card card : cards) {
            if (card.getClass() == search) {
                return card;
            }
        }
        return null;
    }
}
