package dominium.Players;

import dominium.Cards.Card;

import java.util.List;

public class RandomPlayer extends Player {

    public RandomPlayer(String name) {
        super(name);
    }

    @Override
    public Card selectCard(List<Card> cards) {
        if (cards.isEmpty()) {
            return null;
        }

        int cardToPick = (int) (Math.random() * cards.size());
        return cards.get(cardToPick);
    }
}
