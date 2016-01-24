package dominium;

import dominium.Players.FirstMoneyThenPointsPlayer;
import dominium.Players.Player;
import dominium.Players.TestTreasureOrProvincePlayer;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final int NORMAL_KINGDOM_CARDS = 1;
    public static final int TEST_KINGDOM_CARDS = 0;

    public static void main(String[] args) {
        List<Player> players = new ArrayList<Player>();
        players.add(new FirstMoneyThenPointsPlayer("1 Stef "));
        players.add(new TestTreasureOrProvincePlayer("2 Michi"));
        launchAndResolveWholeGame(players, NORMAL_KINGDOM_CARDS);
    }

    public static void launchAndResolveWholeGame(List<Player> players, int kingdomCardSetNumber) {
        GameSetup gameSetup = new GameSetup();
        GameState state = gameSetup.initiateGameState(players, kingdomCardSetNumber);
        GameMaster gameMaster = new GameMaster(players, state);
        gameMaster.startGame();
    }
}
