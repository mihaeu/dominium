package dominium.Players;

import dominium.Cards.Card;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class ConsolePlayer extends Player {
    private PrintStream out;
    private Scanner scanner;

    public ConsolePlayer(String name) {
        super(name);

        scanner = new Scanner(System.in);
        out = System.out;
    }

    public ConsolePlayer(String name, PrintStream out, InputStream in) {
        super(name);

        scanner = new Scanner(in);
        this.out = out;
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
                out.println("Invalid option, please try again: ");
            }
        }
        return card;
    }

    private int getChoice(List<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            out.printf(
                    "[%2d] %-15s (Cost: %d): %s\n",
                    i,
                    cards.get(i).getName(),
                    cards.get(i).getCost(),
                    cards.get(i).getText()
            );
        }
        out.printf(
                "Turn %d: You have %d coins, %d actions and %d buys left\n",
                turns,
                getCoins(),
                getActions(),
                getBuys()
        );
        out.print(getName() + " choose a card: ");
        String choice = scanner.nextLine();
        return Integer.parseInt(choice);
    }
}
