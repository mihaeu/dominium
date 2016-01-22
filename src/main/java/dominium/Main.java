package dominium;

import dominium.Players.FirstMoneyThenPointsPlayer;
import dominium.Players.Player;
import dominium.Players.RandomPlayer;
import dominium.Players.TreasureOrProvincePlayer;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Player> players = new ArrayList<Player>();
        players.add(new FirstMoneyThenPointsPlayer("1 Stef "));
        players.add(new TreasureOrProvincePlayer("2 Michi"));
        //        players.add(new ConsolePlayer("Me"));
        launchAndResolveWholeGame(players);
    }

    //Lets Test And Mock the shit out of this!
    public static void launchAndResolveWholeGame(List<Player> players) {
        GameSetup gameSetup = new GameSetup();
        GameState state = gameSetup.initiateGameState(players);
        GameMaster gameMaster = new GameMaster(players, state);
        List<Player> winners = gameMaster.startGame();

        if (winners.size() == 1) {
            System.out.println("Player " + winners.get(0).getName() + " won.");
        } else {
            System.out.println("The Game ends in a tie between the following players:");
            for (Player player : winners) {
                System.out.println("Player " + player.getName());
            }
        }
    }
}
