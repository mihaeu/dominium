package dominium;

import dominium.Players.FirstMoneyThenPointsPlayer;
import dominium.Players.Player;
import dominium.Players.TestTreasureOrProvincePlayer;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Player> players = new ArrayList<Player>();
        players.add(new FirstMoneyThenPointsPlayer("1 Stef "));
        players.add(new TestTreasureOrProvincePlayer("2 Michi"));
        launchAndResolveWholeGame(players);
    }

    public static void launchAndResolveWholeGame(List<Player> players) {
        GameSetup gameSetup = new GameSetup();
        GameState state = gameSetup.initiateGameState(players);
        GameMaster gameMaster = new GameMaster(players, state);
        gameMaster.startGame();
    }
}
