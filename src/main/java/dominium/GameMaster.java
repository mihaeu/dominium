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

    private List<Player> winner() {
        int points;
        int maxPoints = 0;
        List<Player> listOfWinners = new ArrayList<Player>();

        Map<Player, Integer> playerVictoryPointMap = new HashMap<Player, Integer>();

        for (Player player : players) {
            points = player.victoryPoints();
            int numberOfRoundsPlayedByThisPlayer = player.turns();
            out.println("Player " + player.getName()
                    + " points: " + points
                    + " turns: " + numberOfRoundsPlayedByThisPlayer
            );
            playerVictoryPointMap.put(player, points);
            if (points >= maxPoints) {
                maxPoints = points;
            }
        }

        List<Player> allPlayersWithMaxPoints = getAllPlayersWithMaxPoints(maxPoints, playerVictoryPointMap);
        //One Player wins by points
        if (allPlayersWithMaxPoints.size() == 1) {
            listOfWinners.add(allPlayersWithMaxPoints.get(0));
            out.println("Player " + listOfWinners.get(0).getName() + " won.");
            return listOfWinners;
        }

        listOfWinners = getPlayersWithMaxPointsAndMinTurns(allPlayersWithMaxPoints);
        if(listOfWinners.size()== 1){
            out.println("The Player " + listOfWinners.get(0).getName() + " won by turns.");
        }else {
            out.println("The Game ends in a tie between the following players:");
            for (Player player : listOfWinners) {
                out.println("Player " + player.getName());
            }
        }

        return listOfWinners;
    }

    private List<Player> getPlayersWithMaxPointsAndMinTurns(List<Player> allPlayersWithMaxPoints) {
        List<Player> playersWithMaxPointsAndMinTurns = new ArrayList<Player>();
        int minNumberOfTurns = 100000;
        for (Player player : allPlayersWithMaxPoints) {
            if (player.turns() <= minNumberOfTurns) {
                minNumberOfTurns = player.turns();
            }
        }

        for (Player player : allPlayersWithMaxPoints) {
            int turnsPlayedByThisPlayer = player.turns();
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
