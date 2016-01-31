package dominium;

import dominium.Cards.*;
import dominium.Players.Player;

import java.io.PrintStream;
import java.util.*;

public class GameMaster {
    private static final int CARDS_TO_DRAW = 5;

    GameState gameState;
    List<Player> players;
    PrintStream out = System.out;

    public GameMaster(List<Player> players, GameState gameState) {
        this.players = players;
        this.gameState = gameState;
    }

    public GameMaster(List<Player> players, GameState gameState, PrintStream out) {
        this(players, gameState);
        this.out = out;
    }

    public void startGame() {
        while (true) {
            for (Player player : players) {
                player.incrementTurns();

                buyCard(player);
                discardCards(player);
                drawHandCards(player);

                if (!gameState.gameIsRunning()) {
                    return;
                }
            }
        }
    }

    /**
     * The player with the most victory points wins. If the highest
     * scores are tied at the end of the game, the tied player who has
     * had the fewest turns wins the game. If the tied players have had
     * the same number of turns, they rejoice in their shared victory.
     *
     * @return one or more winning players
     */
    public List<Player> winner() {
        List<Player> winners = new ArrayList<Player>();
        for (Player player : players) {
            if (winners.isEmpty()) {
                winners.add(player);
            } else if (player.victoryPoints() > winners.get(0).victoryPoints()) {
                winners.clear();
                winners.add(player);
            } else if (player.victoryPoints() == winners.get(0).victoryPoints()
                    && player.turns() < winners.get(0).turns()) {
                winners.clear();
                winners.add(player);
            } else if (player.victoryPoints() == winners.get(0).victoryPoints()
                    && player.turns() == winners.get(0).turns()) {
                winners.add(player);
            }
        }
        return winners;
    }

    private void drawHandCards(Player player) {
        out.println("Player " + player.getName() + ": Drawing cards"
                + "\n_____________________________"
        );
        player.drawCards(CARDS_TO_DRAW);
    }

    private void buyCard(Player player) {
        CardStack cardBuyingOptions = getCardBuyingOptions(player);
        Card selectedCard = player.selectCard(cardBuyingOptions);

        if (selectedCard != null) {
            gameState.getKingdomCards().get(selectedCard.getClass()).pop();
            player.handCards().add(selectedCard);

            out.println("Player " + player.getName()
                    + ": Buying card " + selectedCard.getName()
                    + " Cost: " + selectedCard.getCost()
                    + " Money: " + getAvailableMoney(player));
        } else {
            out.println("Player " + player.getName()
                    + " Chose not to buy a card "
                    + " Money: " + getAvailableMoney(player));
        }
    }

    private void discardCards(Player player) {
        player.discardAllCards();
    }

    private CardStack getCardBuyingOptions(Player player) {
        int moneyAvailable = getAvailableMoney(player);
        CardStack cardOptions = new CardStack();
        for (Stack<Card> cardStack : gameState.getKingdomCards().values()) {
            if (!cardStack.empty()
                    && nextCardIsAffordable(moneyAvailable, cardStack)) {
                cardOptions.add(cardStack.peek());
            }
        }
        return cardOptions;
    }

    private boolean nextCardIsAffordable(int moneyAvailable, Stack<Card> cardStack) {
        return cardStack.peek().getCost() <= moneyAvailable;
    }

    private int getAvailableMoney(Player player) {
        return player.coins();
    }
}
