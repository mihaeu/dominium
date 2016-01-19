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

    private List<Card> handCards;
    private Stack<Card> deckCards;
    private Stack<Card> discardedCards;

    public RandomPlayer(String name) {
        this.name = name;
        deckCards = new Stack<Card>();
    }

    public void selectCardsToDiscard(int numberOfCardsToDiscard, Collection<Card> handCards) {
        System.out.println("Please choose " + numberOfCardsToDiscard + " cards to discard from your Hand");
        System.out.println("You have the following cards in your hand:");
        int cardNumber = 1;
        for (Card card : handCards) {
            System.out.println(cardNumber + ". " + card.getName());
        }
        System.out.println("Please enter a number to choose a card to discard");

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        try {
            String line = buffer.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //todo read user input or impl observable
    }

    public Stack<Card> getDeckCards() {
        return deckCards;
    }

    @Override
    public Card selectCard(Map<Class, Stack<Card>> cards) {
        int cardToPick = (int) (Math.random() * cards.size());
        int index = 0;
        for (Stack<Card> stack : cards.values()) {
            if (index == cardToPick && !stack.empty()) {
                Card card = stack.pop();
                deckCards.push(card);
                System.out.println(name + " chose card " + card.getName());
                return card;
            }
            index++;
        }
        return null;
    }
}
