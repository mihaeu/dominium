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
                Card selectedCard = player.selectCard(gameState.getKingdomCards());
                gameState.getDeckCards().get(player).push(selectedCard);
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
    private Stack<Card> shuffleCards(Stack<Card> cards) {
        List list = new ArrayList<Card>(cards);
        Collections.shuffle(list);

        Stack<Card> shuffledStack = new Stack<Card>();
        shuffledStack.addAll(list);
        return shuffledStack;
    }
}
