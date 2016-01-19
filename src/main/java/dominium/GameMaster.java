package dominium;

import dominium.Cards.*;

import java.util.*;

public class GameMaster {
    GameState gameState;
    List<Player> players;

    public GameMaster(List<Player> players, GameState gameState) {
        this.players = players;
        this.gameState = gameState;
    }

    public Player startGame() {
        while (gameIsRunning()) {
            for (Player player : players) {
                drawCards(player);
                buyCard(player);
                discardCards(player);
            }
        }
        return winner();
    }

    private Player winner() {
        int points = 0;
        int maxPoints = 0;
        Player winningPlayer = null;
        for (Player player : players) {
            points = 0;
            for (Card card : gameState.getDeckCards().get(player)) {
                if (card instanceof VictoryCard) {
                    points += ((VictoryCard) card).getVictoryPoints();
                }
            }
            System.out.println("Player " + player.getName() + " points: " + points);
            if (points > maxPoints) {
                maxPoints = points;
                winningPlayer = player;
            }
        }
        return winningPlayer;
    }

    private boolean gameIsRunning() {
        return !provinceCardsEmpty() && !threeStacksEmpty();
    }

    private boolean threeStacksEmpty() {
        int count = 0;
        for (Stack<Card> stack : gameState.getKingdomCards().values()) {
            if (stack.size() == 0) {
                count++;
            }
        }
        return count >= 3;
    }

    private boolean provinceCardsEmpty() {
        return gameState.getKingdomCards().get(Province.class).size() == 0;
    }

    /**
     * This implementation should work, but actually it would be better for the
     * Collection to know how to shuffle itself.
     */
    private void shuffleDiscardedCards(Player player) {
        System.out.println("Player" + player.getName() + ": Shuffling");
        List list = new ArrayList<Card>(gameState.getDiscardCards().get(player));
        Collections.shuffle(list);

        Stack<Card> shuffledStack = new Stack<Card>();
        shuffledStack.addAll(list);
        gameState.getDiscardCards().put(player, shuffledStack);
    }

    private void drawCards(Player player) {
        System.out.println("Player" + player.getName() + ": Drawing cards");
        // not enough cards left in the deck
        if (gameState.getDeckCards().get(player).size() < 5) {
            shuffleDiscardedCards(player);
            gameState.getDeckCards().get(player).addAll(gameState.getDiscardCards().get(player));
            gameState.getDiscardCards().get(player).removeAllElements();
        }

        for (int i = 0; i < 5; i++) {
            Card card = gameState.getDeckCards().get(player).pop();
            gameState.getHandCards().get(player).push(card);
        }
    }

    private void buyCard(Player player) {
        System.out.println("Player" + player.getName() + ": Buying card");
        Card selectedCard = player.selectCard(gameState.getKingdomCards());
        gameState.getHandCards().get(player).push(selectedCard);
    }

    private void discardCards(Player player) {
        System.out.println("Player" + player.getName() + ": Discarding cards");
        gameState.getDiscardCards().get(player).addAll(
                gameState.getHandCards().get(player)
        );
        gameState.getHandCards().get(player).removeAllElements();
    }
}
