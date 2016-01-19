package dominium;

import dominium.Cards.Card;

import java.util.Map;
import java.util.Stack;

public interface Player {
    Card selectCard(Map<Class, Stack<Card>> cards);
    Stack<Card> getDeckCards();
    String getName();
}
