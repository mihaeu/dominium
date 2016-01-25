package dominium;

import dominium.Cards.*;
import dominium.Players.Player;

import java.io.PrintStream;
import java.util.*;

public class GameMaster {
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

    public List<Player> startGame() {
        while (true) {
            for (Player player : players) {
                player.incrementTurns();
                buyCard(player);
                discardCards(player);
                drawCards(player);
                out.println("_____________________________");
                if (!gameState.gameIsRunning()) {
                    return winner();
                }
            }
        }

//        int numberOfRounds = 0;
//        while (gameState.gameIsRunning()) {
//            int playerNumber = numberOfRounds % players.size();
//            Player player = players.get(playerNumber);
//            buyCard(player);
//            discardCards(player);
//            drawCards(player);
//            increaseTurnsPlayedByThisPlayer(player);
//            ++numberOfRounds;
//            out.println("_____________________________");
//        }
//        return winner(numberOfRounds);
    }

    /**
     * The player with the most victory points wins. If the highest
     * scores are tied at the end of the game, the tied player who has
     * had the fewest turns wins the game. If the tied players have had
     * the same number of turns, they rejoice in their shared victory.
     *
     * @return one or more winning players
     */
    private List<Player> winner() {
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

    private void drawCards(Player player) {
        out.println("Player " + player.getName() + ": Drawing cards");
        player.drawCards();
    }

    private void buyCard(Player player) {
        CardStack cardBuyingOptions = getCardBuyingOptions(player);
        Card selectedCard = player.selectCard(cardBuyingOptions);

        if (selectedCard != null) {
            Stack<Card> selectedKingdomCardStack = gameState.getKingdomCards().get(selectedCard.getClass());

            // remove the selected card from the kingdom card set
            selectedKingdomCardStack.pop();
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
            if (!cardStack.empty() && cardStack.peek().getCost() <= moneyAvailable) {
                cardOptions.add(cardStack.peek());
            }
        }
        return cardOptions;
    }

    private int getAvailableMoney(Player player) {
        return player.coins();
    }
}
