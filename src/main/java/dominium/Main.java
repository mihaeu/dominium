package dominium;

import dominium.Players.ConsolePlayer;
import dominium.Players.FirstMoneyThenPointsPlayer;
import dominium.Players.Player;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Player> players = new ArrayList<Player>();
        players.add(new ConsolePlayer("1 Real Michi"));
        players.add(new FirstMoneyThenPointsPlayer("2 Stef-Bot"));
        launchAndResolveWholeGame(players);
    }

    public static void launchAndResolveWholeGame(List<Player> players) {
        GameSetup gameSetup = new GameSetup();
        GameState state = gameSetup.initiateGameState(players);
        GameMaster gameMaster = new GameMaster(players, state);
        gameMaster.startGame();

        for (Player player : gameMaster.winner()) {
            System.out.println(player);
        }
    }
}
