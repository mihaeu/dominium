package dominium;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        GameSetup gameSetup = new GameSetup();
        List<Player> players = gameSetup.initiatePlayers(2);
        GameState state = gameSetup.initiateGameState(players.size());
        GameMaster gameMaster = new GameMaster(players, state);
        Player winner = gameMaster.startGame();
        System.out.println("Player " + winner.getName() + " won.");
    }
}
