package dominium;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        GameSetup gameSetup = new GameSetup();
//        List<Player> players = gameSetup.initiatePlayers(2);
        List<Player> players = new ArrayList<Player>();
        players.add(new RandomPlayer("1"));
        players.add(new ConsolePlayer("Me"));
        GameState state = gameSetup.initiateGameState(players);
        GameMaster gameMaster = new GameMaster(players, state);
        Player winner = gameMaster.startGame();
        System.out.println("Player " + winner.getName() + " won.");
    }
}
