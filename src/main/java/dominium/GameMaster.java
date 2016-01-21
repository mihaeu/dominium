package dominium;

import dominium.Cards.*;
import dominium.Players.Player;

import java.util.*;

public class GameMaster {
    GameState gameState;
    List<Player> players;

    public GameMaster(List<Player> players, GameState gameState) {
        this.players = players;
        this.gameState = gameState;
    }

    public Player startGame() {
        int numberOfRounds = 0;
        while (true) {
            ++numberOfRounds;
            for (Player player : players) {
                drawCards(player);
                buyCard(player);
                discardCards(player);
                System.out.println("_____________________________");
                if (!gameIsRunning()) {
                    return winner(numberOfRounds, player);
                }
            }
        }
    }

    private Player winner(int numberOfRounds, Player playerThatEndedTheGame) {
        int points = 0;
        int maxPoints = 0;
        Player lastPlayerWithMaxPoints = null;
        Map<Player, Integer> playerVictoryPointMap = new HashMap<Player, Integer>();
        System.out.println("Game ends after " + numberOfRounds + " rounds");

        for (Player player : players) {
            points = 0;
            Stack<Card> allCardsOfPlayer = getAllCards(player);
            for (Card card : allCardsOfPlayer) {
                if (card instanceof VictoryCard) {
                    points += ((VictoryCard) card).getVictoryPoints();
                }
            }
            System.out.println("Player " + player.getName() + " points: " + points);
            playerVictoryPointMap.put(player, points);
            if (points >= maxPoints) {
                maxPoints = points;
                lastPlayerWithMaxPoints = player;
            }
        }

        Player firstPlayerwithMaxPoints = getFirstPlayerWithMaxPoints(maxPoints,playerVictoryPointMap);
        int indexOfFirstPlayer = players.indexOf(firstPlayerwithMaxPoints);
        int indexOfLastPlayer = players.indexOf(lastPlayerWithMaxPoints);

        //currently working on that

        //One Player wins
        if (numberOfPlayersWithMaxPoints(maxPoints, playerVictoryPointMap) == 1 || differenceInTurnNumbersBetweenTyingPlayers(lastPlayerWithMaxPoints, playerThatEndedTheGame)) {
            return lastPlayerWithMaxPoints;
        }

        //Tie
        return null;

    }

    private Player getFirstPlayerWithMaxPoints(int maxPoints, Map<Player, Integer> playerVictoryPointMap) {
        for(Player player: players) {
            if (playerVictoryPointMap.get(player) == maxPoints) {
                return player;
            }
        }
        return null;
    }

    private boolean differenceInTurnNumbersBetweenTyingPlayers(Player lastPlayerWithMaxPoints, Player playerThatEndedTheGame) {

        int indexOfWinningPlayer = players.indexOf(lastPlayerWithMaxPoints);
        int indexOfEndingPlayer = players.indexOf(playerThatEndedTheGame);

        return indexOfWinningPlayer <= indexOfEndingPlayer;
    }

    private int numberOfPlayersWithMaxPoints(int maxPoints, Map<Player, Integer> playerVictoryPointMap) {
        int numberOfTieingPlayers = 0;
        for (Map.Entry<Player, Integer> entry : playerVictoryPointMap.entrySet()) {
            if (entry.getValue() == maxPoints) {
                ++numberOfTieingPlayers;
            }
        }
        return numberOfTieingPlayers;
    }

    private Stack<Card> getAllCards(Player player) {
        Stack<Card> allCardsStack = new Stack<Card>();
        Stack<Card> handCards = gameState.getHandCards().get(player);
        Stack<Card> deckCards = gameState.getDeckCards().get(player);
        Stack<Card> discardedCards = gameState.getHandCards().get(player);
        allCardsStack.addAll(handCards);
        allCardsStack.addAll(deckCards);
        allCardsStack.addAll(discardedCards);
        return allCardsStack;
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
        System.out.println("Player " + player.getName() + ": Drawing cards");
        Stack<Card> deckCards = gameState.getDeckCards().get(player);
        Stack<Card> handCards = gameState.getHandCards().get(player);

        // not enough cards left in the deck
        if (deckCards.size() < 5) {
            shuffleDiscardedCards(player);
            deckCards.addAll(gameState.getDiscardCards().get(player));
            gameState.getDiscardCards().get(player).removeAllElements();
        }

        for (int i = 0; i < 5; i++) {
            Card card = deckCards.pop();
            handCards.push(card);
        }
    }

    private void buyCard(Player player) {
        List<Card> cardBuyingOptions = getCardBuyingOptions(player);
        Card selectedCard = player.selectCard(cardBuyingOptions);
        Stack<Card> selectedKingdomCardStack = gameState.getKingdomCards().get(selectedCard.getClass());
        Stack<Card> handCards = gameState.getHandCards().get(player);

        // remove the selected card from the kingdom card set
        selectedKingdomCardStack.pop();
        handCards.push(selectedCard);
        System.out.println("Player" + player.getName()
                        + ": Buying card " + selectedCard.getName()
                        + " Cost: " + selectedCard.getCost()
                        + " Money: " + getAvailableMoney(player)
        );
    }

    private void discardCards(Player player) {
        System.out.println("Player" + player.getName() + ": Discarding cards");
        Stack<Card> discardedCards = gameState.getDiscardCards().get(player);
        Stack<Card> handCards = gameState.getHandCards().get(player);

        discardedCards.addAll(handCards);
        handCards.removeAllElements();
    }

    private List<Card> getCardBuyingOptions(Player player) {
        int moneyAvailable = getAvailableMoney(player);
        List<Card> cardOptions = new ArrayList<Card>();
        for (Stack<Card> cardStack : gameState.getKingdomCards().values()) {
            if (!cardStack.empty() && cardStack.peek().getCost() <= moneyAvailable) {
                cardOptions.add(cardStack.peek());
            }
        }
        return cardOptions;
    }

    private int getAvailableMoney(Player player) {
        Stack<Card> handCards = gameState.getHandCards().get(player);
        int moneyAvailable = 0;
        for (Card card : handCards) {
            if (card instanceof TreasureCard) {
                moneyAvailable += ((TreasureCard) card).getValue();
            }
        }
        return moneyAvailable;
    }
}
