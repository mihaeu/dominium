package dominium;

import dominium.Cards.Card;
import dominium.Players.Player;
import dominium.Util.Logger;
import dominium.Util.NullLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GameMaster {
    private static final int CARDS_TO_DRAW = 5;

    private GameState gameState;
    private List<Player> players;
    private Logger logger = null;

    public GameMaster(List<Player> players, GameState gameState) {
        this.players = players;
        this.gameState = gameState;

        this.logger = new NullLogger();
    }

    public GameMaster(List<Player> players, GameState gameState, Logger logger) {
        this(players, gameState);
        this.logger = logger;
    }

    public void startGame() {
        while (gameState.gameIsRunning()) {
            for (Player player : players) {
                actionPhase(player);
                buyPhase(player);
                cleanUpPhase(player);

                if (!gameState.gameIsRunning()) {
                    return;
                }
            }
        }
    }

    private void actionPhase(Player player) {
        player.incrementTurns();
        player.setCoins();
        player.setBuys(1);
    }

    private void buyPhase(Player player) {
        buyCards(player);
    }

    private void cleanUpPhase(Player player) {
        discardCards(player);
        drawHandCards(player);
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
        List<Player> winners = new ArrayList<>();
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
        logger.info("Player " + player.getName() + ": Drawing cards");
        player.drawCards(CARDS_TO_DRAW);
    }

    private void buyCards(Player player) {
        while (player.getBuys() > 0) {
            CardStack cardBuyingOptions = getCardBuyingOptions(player);
            if (cardBuyingOptions.empty()) {
                return;
            }

            Card selectedCard = player.selectCard(cardBuyingOptions);

            if (selectedCard != null) {
                gameState.getKingdomCards().get(selectedCard.getClass()).pop();
                player.handCards().add(selectedCard);
                player.setBuys(player.getBuys() - 1);
                player.spendCoins(selectedCard.getCost());

                logger.info("Player " + player.getName()
                        + ": Buying card " + selectedCard.getName()
                        + " Cost: " + selectedCard.getCost()
                        + " Money: " + player.coins());
            } else {
                logger.info("Player " + player.getName()
                        + " Chose not to buy a card "
                        + " Money: " + player.coins());
                return;
            }
        }
    }

    private void discardCards(Player player) {
        player.discardAllCards();
    }

    private CardStack getCardBuyingOptions(Player player) {
        CardStack cardOptions = new CardStack();
        for (Stack<Card> cardStack : gameState.getKingdomCards().values()) {
            if (!cardStack.empty()
                    && nextCardIsAffordable(player.coins(), cardStack)) {
                cardOptions.add(cardStack.peek());
            }
        }
        return cardOptions;
    }

    private boolean nextCardIsAffordable(int moneyAvailable, Stack<Card> cardStack) {
        return cardStack.peek().getCost() <= moneyAvailable;
    }
}
