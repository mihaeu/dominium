package dominium.Players;

import dominium.Cards.*;

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
                "[%2d] %-15s (Cost: %d): %s" + (char)27 + "[39m\n",
                i,
                getColoredCardName(cards.get(i)),
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

    private String getColoredCardName(Card card) {
        if (card instanceof TreasureCard) {
            // yellow
            return (char)27 + "[33m" + card.getName();
        } else if (card instanceof VictoryCard) {
            // green
            return (char)27 + "[30m" + card.getName();
        } else if (card instanceof ReactionCard) {
            // blue
            return (char)27 + "[34m" + card.getName();
        } else {
            return (char)27 + "[39m" + card.getName();
        }
    }
}
