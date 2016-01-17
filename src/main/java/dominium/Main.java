package dominium;

import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        GameSetup gameSetup = new GameSetup();
        Collection<Player> players = gameSetup.initiatePlayers(1);
        GameState state = gameSetup.initiateGameState(1);
        GameMaster gameMaster = new GameMaster(players, state);
        gameMaster.startGame();
    }
}
