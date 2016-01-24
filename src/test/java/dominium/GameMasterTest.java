package dominium;

import dominium.Players.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GameMasterTest {

    private GameState state;
    private List<Player> players;
    private List<Player> winningPlayers;
    private GameSetup setup;
    private GameMaster gameMaster;
    private PrintStream nullOutput;

    @Before
    public void setUp() {
        setup = new GameSetup();
        players = new ArrayList<Player>();
        nullOutput = new PrintStream(new NullStream());
    }


    @Test
    public void winningByPointsTest() {
        //mock creation
        List mockedList = mock(List.class);

        //using mock object
        mockedList.add("one");
        mockedList.clear();

        //verification
        verify(mockedList).add("one");
        verify(mockedList).clear();

        Player playerThatWillWin = new FirstMoneyThenPointsPlayer("1");
        players.add(playerThatWillWin);
        players.add(new NormalThreePointsPlayer("2"));

        state = setup.initiateGameState(players, Main.NORMAL_KINGDOM_CARDS);
        gameMaster = new GameMaster(players, state, nullOutput);
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
        state = setup.initiateGameState(players, Main.NORMAL_KINGDOM_CARDS);
        gameMaster = new GameMaster(players, state, nullOutput);
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
        state = setup.initiateGameState(players, Main.NORMAL_KINGDOM_CARDS);
        gameMaster = new GameMaster(players, state, nullOutput);
        winningPlayers = gameMaster.startGame();
        assertTrue(winningPlayers.size() == 1);
        assertTrue(winningPlayers.get(0) == playerThatWillWin);
    }

    @Test
    public void pointAndTurnTieTest() {
        players.add(new TestThreePointsPlayer("1"));
        players.add(new TestThreePointsPlayer("2"));
        state = setup.initiateGameState(players, Main.TEST_KINGDOM_CARDS);
        gameMaster = new GameMaster(players, state, nullOutput);
        winningPlayers = gameMaster.startGame();
        assertTrue(winningPlayers.size() == 2);
    }

    @Test
    public void pointAndTurnTieTestThreePlayers() {
        players.add(new TestThreePointsPlayer("1"));
        players.add(new TestThreePointsPlayer("2"));
        players.add(new TestThreePointsPlayer("3"));
        state = setup.initiateGameState(players, Main.TEST_KINGDOM_CARDS);
        gameMaster = new GameMaster(players, state, nullOutput);
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
        state = setup.initiateGameState(players, Main.TEST_KINGDOM_CARDS);
        gameMaster = new GameMaster(players, state, nullOutput);
        winningPlayers = gameMaster.startGame();
        //because of the number of cards
        assertTrue(winningPlayers.size() == 4);
    }

    @Test
    public void pointTieButWinningByTurnTestTwoPlayersOneWinner() {
        players.add(new ThreePointsFirstRoundNoActionPlayer("1"));
        Player playerThatWillWin = new TestNoBuysPlayer("2");
        players.add(playerThatWillWin);
        state = setup.initiateGameState(players, Main.NORMAL_KINGDOM_CARDS);
        gameMaster = new GameMaster(players, state, nullOutput);
        winningPlayers = gameMaster.startGame();
        assertTrue(winningPlayers.size() == 1);
        assertTrue(winningPlayers.get(0) == playerThatWillWin);
    }

    @Test
    public void pointTieButWinningByTurnTestTwoPlayersTwoWinners() {
        players.add(new TestNoBuysPlayer("1"));
        players.add(new ThreePointsFirstRoundNoActionPlayer("2"));
        state = setup.initiateGameState(players, Main.NORMAL_KINGDOM_CARDS);
        gameMaster = new GameMaster(players, state, nullOutput);
        winningPlayers = gameMaster.startGame();
        assertTrue(winningPlayers.size() == 2);
    }

    @Test
    public void pointTieButWinningByTurnTestThreePlayersOneWinner() {
        players.add(new TestNoBuysPlayer("2"));
        players.add(new ThreePointsFirstRoundNoActionPlayer("2"));
        Player playerThatWillWin = new TestNoBuysPlayer("3");
        players.add(playerThatWillWin);
        state = setup.initiateGameState(players, Main.NORMAL_KINGDOM_CARDS);
        gameMaster = new GameMaster(players, state, nullOutput);
        winningPlayers = gameMaster.startGame();
        assertTrue(winningPlayers.size() == 1);
        assertTrue(winningPlayers.get(0) == playerThatWillWin);
    }

    @Test
    public void pointTieButWinningByTurnTestThreePlayersTwoWinners() {
        players.add(new ThreePointsFirstRoundNoActionPlayer("1"));
        Player playerThatWillWin = new TestNoBuysPlayer("2");
        players.add(playerThatWillWin);
        players.add(new TestNoBuysPlayer("3"));
        state = setup.initiateGameState(players, Main.NORMAL_KINGDOM_CARDS);
        gameMaster = new GameMaster(players, state, nullOutput);
        winningPlayers = gameMaster.startGame();
        assertTrue(winningPlayers.size() == 2);
    }

    @Test
    public void pointTieButWinningByTurnTestThreePlayersThreeWinners() {
        players.add(new TestNoBuysPlayer("1"));
        players.add(new TestNoBuysPlayer("2"));
        Player playerThatWillWin = new ThreePointsFirstRoundNoActionPlayer("3");
        players.add(playerThatWillWin);
        state = setup.initiateGameState(players, Main.NORMAL_KINGDOM_CARDS);
        gameMaster = new GameMaster(players, state, nullOutput);
        winningPlayers = gameMaster.startGame();
        assertTrue(winningPlayers.size() == 3);
    }

    @Test
    public void pointTieButWinningByTurnTestFourPlayersFourWinners() {
        players.add(new TestNoBuysPlayer("1"));
        players.add(new TestNoBuysPlayer("2"));
        players.add(new TestNoBuysPlayer("3"));
        Player playerThatWillWin = new ThreePointsFirstRoundNoActionPlayer("4");
        players.add(playerThatWillWin);
        state = setup.initiateGameState(players, Main.NORMAL_KINGDOM_CARDS);
        gameMaster = new GameMaster(players, state, nullOutput);
        winningPlayers = gameMaster.startGame();
        assertTrue(winningPlayers.size() == 4);
    }

    @Test
    public void pointTieButWinningByTurnTestFourPlayersThreeWinners() {
        Player playerThatWillWin = new ThreePointsFirstRoundNoActionPlayer("1");
        players.add(playerThatWillWin);
        players.add(new TestNoBuysPlayer("2"));
        players.add(new TestNoBuysPlayer("3"));
        players.add(new TestNoBuysPlayer("4"));
        state = setup.initiateGameState(players, Main.NORMAL_KINGDOM_CARDS);
        gameMaster = new GameMaster(players, state, nullOutput);
        winningPlayers = gameMaster.startGame();
        assertTrue(winningPlayers.size() == 3);
    }

    @Test
    public void pointTieButWinningByTurnTestFourPlayersTwoWinners() {
        players.add(new TestNoBuysPlayer("1"));
        Player playerThatWillWin = new ThreePointsFirstRoundNoActionPlayer("2");
        players.add(playerThatWillWin);
        players.add(new TestNoBuysPlayer("3"));
        players.add(new TestNoBuysPlayer("4"));
        state = setup.initiateGameState(players, Main.NORMAL_KINGDOM_CARDS);
        gameMaster = new GameMaster(players, state, nullOutput);
        winningPlayers = gameMaster.startGame();
        assertTrue(winningPlayers.size() == 2);
    }

    @Test
    public void pointTieButWinningByTurnTestFourPlayersOneWinners() {
        players.add(new TestNoBuysPlayer("1"));
        players.add(new TestNoBuysPlayer("2"));
        Player playerThatWillWin = new ThreePointsFirstRoundNoActionPlayer("3");
        players.add(playerThatWillWin);
        players.add(new TestNoBuysPlayer("4"));
        state = setup.initiateGameState(players, Main.NORMAL_KINGDOM_CARDS);
        gameMaster = new GameMaster(players, state, nullOutput);
        winningPlayers = gameMaster.startGame();
        assertTrue(winningPlayers.size() == 1);
    }

    private class NullStream extends OutputStream {
        @Override
        public void write(int i) throws IOException {

        }
    }
}
