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

    public List<Player> startGame() {
        int numberOfRounds = 0;
        while (true) {
            ++numberOfRounds;
            for (Player player : players) {
                buyCard(player);
                discardCards(player);
                drawCards(player);
                increaseTurnsPlayedByThisPlayer(player);
                System.out.println("_____________________________");
                if (!gameIsRunning()) {
                    return winner(numberOfRounds, player);
                }
            }
        }
    }

    private void increaseTurnsPlayedByThisPlayer(Player player) {
        Map<Player, Integer> turnsPlayedPerPlayer = gameState.getTurnsPlayedPerPlayer();
        int turnsPlayed = turnsPlayedPerPlayer.get(player);
        ++turnsPlayed;
        turnsPlayedPerPlayer.put(player, turnsPlayed);
    }

    private List<Player> winner(int numberOfRounds, Player playerThatEndedTheGame) {
        int points = 0;
        int maxPoints = 0;
        List<Player> listOfWinners = new ArrayList<Player>();

        Map<Player, Integer> playerVictoryPointMap = new HashMap<Player, Integer>();
        System.out.println("Game ends after " + numberOfRounds + " rounds");

        for (Player player : players) {
            points = 0;
            List<Card> allCardsOfPlayer = getAllCards(player);
            for (Card card : allCardsOfPlayer) {
                if (card instanceof VictoryCard) {
                    points += ((VictoryCard) card).getVictoryPoints();
                }
            }
            System.out.println("Player " + player.getName() + " points: " + points);
            playerVictoryPointMap.put(player, points);
            if (points >= maxPoints) {
                maxPoints = points;
            }
        }

        List<Player> allPlayersWithMaxPoints = getAllPlayersWithMaxPoints(maxPoints, playerVictoryPointMap);
        //One Player wins by points
        if (allPlayersWithMaxPoints.size() == 1) {
            listOfWinners.add(allPlayersWithMaxPoints.get(0));
            return listOfWinners;
        }

        listOfWinners = getPlayersWithMaxPointsAndMinTurns(allPlayersWithMaxPoints);

        //Tie
        return listOfWinners;

    }

    private List<Player> getPlayersWithMaxPointsAndMinTurns(List<Player> allPlayersWithMaxPoints) {
        List<Player> playersWithMaxPointsAndMinTurns = new ArrayList<Player>();
        Map<Player, Integer> turnsPlayedPerPlayer = gameState.getTurnsPlayedPerPlayer();
        int minNumberOfTurns = 100000;
        for (Player player : allPlayersWithMaxPoints) {
            int turnsPlayedByThisPlayer = turnsPlayedPerPlayer.get(player);
            if (turnsPlayedByThisPlayer <= minNumberOfTurns) {
                minNumberOfTurns = turnsPlayedByThisPlayer;
            }
        }

        for (Player player : allPlayersWithMaxPoints) {
            int turnsPlayedByThisPlayer = turnsPlayedPerPlayer.get(player);
            if (turnsPlayedByThisPlayer <= minNumberOfTurns) {
                playersWithMaxPointsAndMinTurns.add(player);
            }
        }
        return playersWithMaxPointsAndMinTurns;
    }

    private List<Player> getAllPlayersWithMaxPoints(int maxPoints, Map<Player, Integer> playerVictoryPointMap) {
        List<Player> listOfPlayers = new ArrayList<Player>();
        for (Player player : players) {
            if (playerVictoryPointMap.get(player) == maxPoints) {
                listOfPlayers.add(player);
            }
        }
        return listOfPlayers;
    }

    private List<Card> getAllCards(Player player) {
        List<Card> allCardsStack = new ArrayList<Card>();
        List<Card> handCards = gameState.getHandCards().get(player);
        Stack<Card> deckCards = gameState.getDeckCards().get(player);
        Stack<Card> discardedCards = gameState.getDiscardCards().get(player);
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
        List<Card> handCards = gameState.getHandCards().get(player);

        // not enough cards left in the deck
        if (deckCards.size() < 5) {
            shuffleDiscardedCards(player);
            deckCards.addAll(gameState.getDiscardCards().get(player));
            gameState.getDiscardCards().get(player).removeAllElements();
        }

        for (int i = 0; i < 5; i++) {
            Card card = deckCards.pop();
            handCards.add(card);
        }
    }

    private void buyCard(Player player) {
        List<Card> cardBuyingOptions = getCardBuyingOptions(player);
        Card selectedCard = player.selectCard(cardBuyingOptions);
        if(selectedCard != null){
        Stack<Card> selectedKingdomCardStack = gameState.getKingdomCards().get(selectedCard.getClass());
        List<Card> handCards = gameState.getHandCards().get(player);

        // remove the selected card from the kingdom card set
        selectedKingdomCardStack.pop();
        handCards.add(selectedCard);
        System.out.println("Player" + player.getName()
                        + ": Buying card " + selectedCard.getName()
                        + " Cost: " + selectedCard.getCost()
                        + " Money: " + getAvailableMoney(player));
        }else{
            System.out.println("Player" + player.getName()
                    + " Chose not to buy a card "
                    + " Money: " + getAvailableMoney(player));
        }
    }

    private void discardCards(Player player) {
        System.out.println("Player" + player.getName() + ": Discarding cards");
        Stack<Card> discardedCards = gameState.getDiscardCards().get(player);
        List<Card> handCards = gameState.getHandCards().get(player);

        discardedCards.addAll(handCards);
        handCards.clear();
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
        List<Card> handCards = gameState.getHandCards().get(player);
        int moneyAvailable = 0;
        for (Card card : handCards) {
            if (card instanceof TreasureCard) {
                moneyAvailable += ((TreasureCard) card).getValue();
            }
        }
        return moneyAvailable;
    }
}
