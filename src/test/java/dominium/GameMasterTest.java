package dominium;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import dominium.Players.FirstMoneyThenPointsPlayer;
import dominium.Players.NormalThreePointsPlayer;
import dominium.Players.TestNoBuysPlayer;
import dominium.Players.Player;
import dominium.Players.ThreePointsFirstRoundNoActionPlayer;
import dominium.Players.TestThreePointsPlayer;

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
    public void winningByPointsTest() {
        Player playerThatWillWin = new FirstMoneyThenPointsPlayer("1");
        players.add(playerThatWillWin);
        players.add(new TestNoBuysPlayer("2"));
        state = setup.initiateGameState(players,Main.NORMAL_KINGDOM_CARDS);
        gameMaster = new GameMaster(players,state);
        winningPlayers = gameMaster.startGame();
        assertTrue(winningPlayers.size() == 1);
        assertTrue(winningPlayers.get(0) == playerThatWillWin);
    }

    @Test
    public void winningByPointsTestThreePlayers() {
        Player playerThatWillWin = new FirstMoneyThenPointsPlayer("1");
        players.add(playerThatWillWin);
        players.add(new TestNoBuysPlayer("2"));
        players.add(new TestNoBuysPlayer("3"));
        state = setup.initiateGameState(players,Main.NORMAL_KINGDOM_CARDS);
        gameMaster = new GameMaster(players,state);
        winningPlayers = gameMaster.startGame();
        assertTrue(winningPlayers.size() == 1);
        assertTrue(winningPlayers.get(0) == playerThatWillWin);
    }

    @Test
    public void winningByPointsTestFourPlayers() {
        Player playerThatWillWin = new FirstMoneyThenPointsPlayer("1");
        players.add(playerThatWillWin);
        players.add(new TestNoBuysPlayer("2"));
        players.add(new TestNoBuysPlayer("3"));
        players.add(new TestNoBuysPlayer("4"));
        state = setup.initiateGameState(players,Main.NORMAL_KINGDOM_CARDS);
        gameMaster = new GameMaster(players,state);
        winningPlayers = gameMaster.startGame();
        assertTrue(winningPlayers.size() == 1);
        assertTrue(winningPlayers.get(0) == playerThatWillWin);
    }

    @Test
    public void pointAndTurnTieTest() {
        players.add(new TestThreePointsPlayer("1"));
        players.add(new TestThreePointsPlayer("2"));
        state = setup.initiateGameState(players,Main.TEST_KINGDOM_CARDS);
        gameMaster = new GameMaster(players,state);
        winningPlayers = gameMaster.startGame();
        assertTrue(winningPlayers.size() == 2);
    }

    @Test
    public void pointAndTurnTieTestThreePlayers() {
        players.add(new TestThreePointsPlayer("1"));
        players.add(new TestThreePointsPlayer("2"));
        players.add(new TestThreePointsPlayer("3"));
        state = setup.initiateGameState(players,Main.TEST_KINGDOM_CARDS);
        gameMaster = new GameMaster(players,state);
        winningPlayers = gameMaster.startGame();
        //because of the number of cards
        assertTrue(winningPlayers.size() == 3);
    }

    @Test
    public void pointAndTurnTieTestFourPlayers() {
        players.add(new TestThreePointsPlayer("1"));
        players.add(new TestThreePointsPlayer("2"));
        players.add(new TestThreePointsPlayer("3"));
        players.add(new TestThreePointsPlayer("4"));
        state = setup.initiateGameState(players,Main.TEST_KINGDOM_CARDS);
        gameMaster = new GameMaster(players,state);
        winningPlayers = gameMaster.startGame();
        //because of the number of cards
        assertTrue(winningPlayers.size() == 4);
    }

    @Test
    public void pointTieButWinningByTurnTest() {
        players.add(new ThreePointsFirstRoundNoActionPlayer("1"));
        Player playerThatWillWin = new TestNoBuysPlayer("2");
        players.add(playerThatWillWin);
        state = setup.initiateGameState(players,Main.NORMAL_KINGDOM_CARDS);
        gameMaster = new GameMaster(players,state);
        winningPlayers = gameMaster.startGame();
        assertTrue(winningPlayers.size() == 1);
        assertTrue(winningPlayers.get(0) == playerThatWillWin);
    }

    @Test
    public void pointTieButWinningByTurnTestThreePlayers() {
        players.add(new ThreePointsFirstRoundNoActionPlayer("1"));
        Player playerThatWillWin = new TestNoBuysPlayer("2");
        players.add(playerThatWillWin);
        players.add(new TestNoBuysPlayer("3"));
        state = setup.initiateGameState(players,Main.NORMAL_KINGDOM_CARDS);
        gameMaster = new GameMaster(players,state);
        winningPlayers = gameMaster.startGame();
        assertTrue(winningPlayers.size() == 2);
        assertTrue(winningPlayers.get(0) == playerThatWillWin);
    }

    @Test
    public void pointTieButWinningByTurnTestFourPlayers() {
        players.add(new ThreePointsFirstRoundNoActionPlayer("1"));
        Player playerThatWillWin = new TestNoBuysPlayer("2");
        players.add(playerThatWillWin);
        state = setup.initiateGameState(players,Main.NORMAL_KINGDOM_CARDS);
        gameMaster = new GameMaster(players,state);
        winningPlayers = gameMaster.startGame();
        assertTrue(winningPlayers.size() == 1);
        assertTrue(winningPlayers.get(0) == playerThatWillWin);
    }
}
