package dominium;

import dominium.Cards.Card;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class RandomPlayer extends Player {

    public RandomPlayer(String name) {
        this.name = "Random" + name;
    }

    @Override
    public Card selectCard(List<Card> cards) {
        int cardToPick = (int) (Math.random() * cards.size());
        return cards.get(cardToPick);
    }
}
