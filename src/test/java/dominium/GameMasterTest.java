package dominium;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import dominium.Players.FirstMoneyThenPointsPlayer;
import dominium.Players.Player;
import dominium.Players.ThreePointsFirstRoundNoActionPlayer;
import dominium.Players.ThreePointsPlayer;

import static org.junit.Assert.*;

public class GameMasterTest {
    private GameState state;
    private List<Player> players;
    private List<Player> winningPlayers;
    private GameSetup setup;
    private GameMaster gameMaster;
    @Before
    public void setUp() {
        setup = new GameSetup();
        players = new ArrayList<Player>();

    }

    @Test
    public void pointAndTurnTieTest() {
        players.add(new ThreePointsPlayer("1"));
        players.add(new ThreePointsPlayer("2"));
        state = setup.initiateGameState(players);
        gameMaster = new GameMaster(players,state);
        winningPlayers = gameMaster.startGame();
        assertTrue(winningPlayers.size() == 2);
    }

    @Test
    public void pointTieButWinningByTurnTest() {
        players.add(new ThreePointsFirstRoundNoActionPlayer("1"));
        Player playerThatWillWin = new ThreePointsPlayer("2");
        players.add(playerThatWillWin);
        state = setup.initiateGameState(players);
        gameMaster = new GameMaster(players,state);
        winningPlayers = gameMaster.startGame();
        assertTrue(winningPlayers.size() == 1);
        assertTrue(winningPlayers.get(0) == playerThatWillWin);
    }

    @Test
    public void winningByPointsTest() {
        Player playerThatWillWin = new FirstMoneyThenPointsPlayer("1");
        players.add(playerThatWillWin);
        players.add(new ThreePointsPlayer("2"));
        state = setup.initiateGameState(players);
        gameMaster = new GameMaster(players,state);
        winningPlayers = gameMaster.startGame();
        assertTrue(winningPlayers.size() == 1);
        assertTrue(winningPlayers.get(0) == playerThatWillWin);
    }

    

}
