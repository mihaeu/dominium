package dominium;

import dominium.Cards.Card;

import java.util.Map;
import java.util.Stack;

public abstract class Player {
    protected String name;

    public abstract Card selectCard(Map<Class, Stack<Card>> cards);
    public abstract Stack<Card> getDeckCards();

    public String getName() {
        return name;
    }
}
