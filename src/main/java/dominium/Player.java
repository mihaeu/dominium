package dominium;

import dominium.Cards.Card;

import java.util.Map;
import java.util.Stack;

public abstract class Player {
    protected String name;

    public String getName() {
        return name;
    }

    public abstract Card selectCard(Map<Class, Stack<Card>> cards);
}
