package dominium;

import dominium.Players.FirstMoneyThenPointsPlayer;
import dominium.Players.Player;
import dominium.Players.RandomPlayer;
import dominium.Util.ConsoleLogger;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        players.add(new RandomPlayer("1 Real Michi"));
        players.add(new FirstMoneyThenPointsPlayer("2 Stef-Bot"));
        launchAndResolveWholeGame(players);
    }

    public static void launchAndResolveWholeGame(List<Player> players) {
        GameSetup gameSetup = new GameSetup();
        GameState state = gameSetup.initiateGameState(players);
        GameMaster gameMaster = new GameMaster(players, state, new ConsoleLogger());
        gameMaster.startGame();

        gameMaster.winner().forEach(System.out::println);
    }
}
