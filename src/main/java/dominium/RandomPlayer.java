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
        this.name = name;
    }

    @Override
    public Card selectCard(Map<Class, Stack<Card>> cards) {
        int cardToPick = (int) (Math.random() * cards.size());
        int index = 0;
        for (Stack<Card> stack : cards.values()) {
            if (index == cardToPick && !stack.empty()) {
                Card card = stack.pop();
                System.out.println(name + " chose card " + card.getName());
                return card;
            }
            index++;
        }
        return null;
    }
}
