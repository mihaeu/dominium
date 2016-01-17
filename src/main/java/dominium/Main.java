package dominium;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        GameSetup gameSetup = new GameSetup();
        List<Player> players = gameSetup.initiatePlayers(1);
        GameState state = gameSetup.initiateGameState(1);
        GameMaster gameMaster = new GameMaster(players, state);
        gameMaster.startGame();
    }
}
