package dominium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

public class Player {
    private Collection<Card> handCards;
    private Collection<Card> deckCards;
    private Collection<Card> discardedCards;

    public Collection<Card> getHandCards() {
        return handCards;
    }

    public void setHandCards(Collection<Card> handCards) {
        this.handCards = handCards;
    }

    public Collection<Card> getDeckCards() {
        return deckCards;
    }

    public void setDeckCards(Collection<Card> deckCards) {
        this.deckCards = deckCards;
    }

    public Collection<Card> getDiscardedCards() {
        return discardedCards;
    }

    public void setDiscardedCards(Collection<Card> discardedCards) {
        this.discardedCards = discardedCards;
    }


    public void selectCardsToDiscard(int numberOfCardsToDiscard, Collection<Card> handCards) {
        System.out.println("Please choose " + numberOfCardsToDiscard + " cards to discard from your Hand");
        System.out.println("You have the following cards in your hand:");
        int cardNumber =1;
        for(Card card:handCards) {
            System.out.println(cardNumber + ". " + card.getName());
        }
        System.out.println("Please enter a number to choose a card to discard");

        BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));
        
            try {
                String line = buffer.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }


        //todo read user input or impl observable

    }
}
