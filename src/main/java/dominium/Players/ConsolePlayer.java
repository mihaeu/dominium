package dominium.Players;

import dominium.Cards.Card;

import java.util.List;
import java.util.Scanner;

public class ConsolePlayer extends Player {

    public ConsolePlayer(String name) {
        super(name);
    }

    @Override
    public Card selectCard(List<Card> cards) {
        if (cards.isEmpty()) {
            return null;
        }

        Card card = null;
        while (card == null) {
            try {
                card = cards.get(getChoice(cards));
            } catch (Exception e) {
                System.out.println("Invalid option, please try again: ");
            }
        }
        return card;
    }

    private int getChoice(List<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            System.out.printf(
                    "[%d] %-15s (Cost: %d): %s\n",
                    i,
                    cards.get(i).getName(),
                    cards.get(i).getCost(),
                    cards.get(i).getText()
            );
        }
        System.out.println(getName() + " choose a card: ");
        Scanner s = new Scanner(System.in);
        String choice = s.nextLine();
        return Integer.parseInt(choice);
    }
}
