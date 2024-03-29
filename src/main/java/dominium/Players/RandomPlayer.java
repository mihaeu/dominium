package dominium.Players;

import dominium.CardStack;
import dominium.Cards.Card;

import java.util.Random;

public class RandomPlayer extends Player {
    private Random generator;

    public RandomPlayer(String name) {
        super(name);

        generator = new Random(System.currentTimeMillis() + (int) (Math.random() * 42));
    }

    @Override
    public Card selectCard(CardStack cards) {
        if (cards.isEmpty()) {
            return null;
        }

        int cardToPick = generator.nextInt(cards.size());
        return cards.get(cardToPick);
    }
}
