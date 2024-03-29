package dominium;

import dominium.Cards.ActionCard;
import dominium.Cards.Card;
import dominium.Cards.CardType;
import dominium.Players.Player;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class GameMaster {
    private static final int CARDS_TO_DRAW = 5;

    private GameState gameState;
    private List<Player> players;
    private Logger logger = null;
    private Player currentPlayer;

    public GameMaster(List<Player> players, GameState gameState) {
        this.players = players;
        this.gameState = gameState;
    }

    public GameMaster(List<Player> players, GameState gameState, Logger logger) {
        this(players, gameState);
        this.logger = logger;
    }

    public void startGame() {
        while (gameState.gameIsRunning()) {
            for (Player player : players) {
                currentPlayer = player;

                actionPhase(currentPlayer);
                buyPhase(currentPlayer);
                cleanUpPhase(currentPlayer);

                if (!gameState.gameIsRunning()) {
                    return;
                }
            }
        }
    }

    private void actionPhase(Player player) {
        logger.info(player + " begins action phase");
        player.incrementTurns();
        player.setCoinsFromTreasureCardsOnHand();
        player.setBuys(1);
        player.setActions(1);

        playAction(player);
    }

    private void playAction(Player player) {
        while (player.getActions() > 0) {
            player.setActions(player.getActions() - 1);
            CardStack cardsToChooseFrom = player.handCards()
                .filterCards(CardType.Action)
                .filterCards(card -> !card.isPlayed());
            Card selectedActionCard = player.selectCard(cardsToChooseFrom);
            if (selectedActionCard == null) {
                player.setActions(0);
                return;
            }

            logger.info(player + " plays action card: " + selectedActionCard);
            ((ActionCard) selectedActionCard).resolve(currentPlayer, otherPlayers(), gameState.getKingdomCards());
            selectedActionCard.setPlayed(true);
        }
    }

    private void buyPhase(Player player) {
        logger.info(player + " begins buy phase");
        buyCards(player);
    }

    private void cleanUpPhase(Player player) {
        logger.info(player + " begins clean up phase");
        player.discardAllCards();
        player.drawCards(CARDS_TO_DRAW);
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

    private void buyCards(Player player) {
        while (player.getBuys() > 0) {
            player.setBuys(player.getBuys() - 1);

            CardStack cardBuyingOptions = kingdomCards().keysOfNonEmptyStacks()
                    .filterCards(player.getCoins());
            if (cardBuyingOptions.empty()) {
                return;
            }

            Card selectedCard = player.selectCard(cardBuyingOptions);
            if (selectedCard != null) {
                selectedCard = gameState.getKingdomCards().get(selectedCard).pop();
                player.handCards().add(selectedCard);
                player.spendCoins(selectedCard.getCost());

                logger.info(player + " buys card " + selectedCard);
            } else {
                logger.info(player + " chose not to buy a card ");
                player.setBuys(0);
                return;
            }
        }
    }

    public Player currentPlayer() {
        return currentPlayer;
    }

    public List<Player> otherPlayers() {
        List<Player> otherPlayers = new ArrayList<>(players);
        otherPlayers.remove(currentPlayer);
        return otherPlayers;
    }

    public KingdomCardMap kingdomCards() {
        return gameState.getKingdomCards();
    }
}
