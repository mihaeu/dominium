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
        for (Card card : handCards) {
            System.out.printf(" _____________________________\n" +
                    "|%-29s|\n" +
                    "|-----------------------------|\n" +
                    "|                             |\n" +
                    "|                             |\n" +
                    "|                             |\n" +
                    "|-----------------------------|\n" +
                    "| Cost: %d     Type: Action    |\n" +
                    "|_____________________________|\n",
                    card.getName(),
                    card.getCost()
            );
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
        System.out.println("Choose a card:");
        for (int i = 0; i < cards.size(); i++) {
            System.out.println("[" + i + "] " + cards.get(i).getName());
        }
        Scanner s = new Scanner(System.in);
        String choice = s.nextLine();
        return Integer.parseInt(choice);
    }
}
