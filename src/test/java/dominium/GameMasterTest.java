package dominium;

import dominium.Players.Player;
import dominium.Players.TestNoBuysPlayer;
import dominium.Players.ThreePointsFirstRoundNoActionPlayer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameMasterTest {

    private GameState state;
    private List<Player> players;
    private List<Player> winningPlayers;
    private GameSetup setup;
    private GameMaster gameMaster;
    private PrintStream nullOutput;

    private Player mockPlayer1;
    private Player mockPlayer2;
    private Player mockPlayer3;
    private Player mockPlayer4;


    @Before
    public void setUp() {
        setup = new GameSetup();
        players = new ArrayList<Player>();
        nullOutput = new PrintStream(new NullStream());

        mockPlayer1 = mock(Player.class);
        mockPlayer2 = mock(Player.class);
        mockPlayer3 = mock(Player.class);
        mockPlayer4 = mock(Player.class);

        state = mock(GameState.class);
    }


    @Test
    public void playerWithMostPointsWins() {
        when(mockPlayer1.victoryPoints()).thenReturn(0);
        when(mockPlayer2.victoryPoints()).thenReturn(100);

        players.add(mockPlayer1);
        players.add(mockPlayer2);
        state = mock(GameState.class);

        gameMaster = new GameMaster(players, state, nullOutput);
        winningPlayers = gameMaster.winner();
        assertEquals(1, winningPlayers.size());
        assertEquals(mockPlayer2, winningPlayers.get(0));
    }

    @Test
    public void ifTwoPlayersHaveSamePointsAndTurnsThereAreTwoWinners() {
        when(mockPlayer1.victoryPoints()).thenReturn(10);
        when(mockPlayer2.victoryPoints()).thenReturn(10);
        when(mockPlayer1.turns()).thenReturn(10);
        when(mockPlayer2.turns()).thenReturn(10);

        players.add(mockPlayer1);
        players.add(mockPlayer2);
        state = mock(GameState.class);

        gameMaster = new GameMaster(players, state, nullOutput);
        winningPlayers = gameMaster.winner();
        assertEquals(2, winningPlayers.size());
    }

    @Test
    public void ifThreePlayersHaveSamePointsAndTurnsThereAreThreeWinners() {
        when(mockPlayer1.victoryPoints()).thenReturn(10);
        when(mockPlayer2.victoryPoints()).thenReturn(10);
        when(mockPlayer3.victoryPoints()).thenReturn(10);

        players.add(mockPlayer1);
        players.add(mockPlayer2);
        players.add(mockPlayer3);
        state = mock(GameState.class);

        gameMaster = new GameMaster(players, state, nullOutput);
        winningPlayers = gameMaster.winner();
        assertEquals(3, winningPlayers.size());
    }

    @Test
    public void ifFourPlayersHaveSamePointsAndTurnsThereAreFourWinners() {
        when(mockPlayer1.victoryPoints()).thenReturn(10);
        when(mockPlayer2.victoryPoints()).thenReturn(10);
        when(mockPlayer3.victoryPoints()).thenReturn(10);
        when(mockPlayer4.victoryPoints()).thenReturn(10);

        players.add(mockPlayer1);
        players.add(mockPlayer2);
        players.add(mockPlayer3);
        players.add(mockPlayer4);
        state = mock(GameState.class);
        gameMaster = new GameMaster(players, state, nullOutput);
        winningPlayers = gameMaster.winner();
        assertEquals(4, winningPlayers.size());
    }

    @Test
    public void ifTwoPlayersHaveEqualPointsThePlayerWithLessTurnsWins() {
        when(mockPlayer1.victoryPoints()).thenReturn(10);
        when(mockPlayer2.victoryPoints()).thenReturn(10);
        when(mockPlayer1.turns()).thenReturn(100);
        when(mockPlayer2.turns()).thenReturn(10);

        players.add(mockPlayer1);
        players.add(mockPlayer2);
        state = mock(GameState.class);
        gameMaster = new GameMaster(players, state, nullOutput);
        winningPlayers = gameMaster.winner();

        assertEquals(1, winningPlayers.size());
        assertEquals(mockPlayer2, winningPlayers.get(0));
    }

    @Test
    public void ifThreePlayersHaveEqualPointsAndTwoPlayersSameTurnsThenTwoWinners() {
        when(mockPlayer1.victoryPoints()).thenReturn(10);
        when(mockPlayer2.victoryPoints()).thenReturn(10);
        when(mockPlayer3.victoryPoints()).thenReturn(10);
        when(mockPlayer1.turns()).thenReturn(100);
        when(mockPlayer2.turns()).thenReturn(10);
        when(mockPlayer3.turns()).thenReturn(10);

        players.add(mockPlayer1);
        players.add(mockPlayer2);
        players.add(mockPlayer3);
        state = mock(GameState.class);
        gameMaster = new GameMaster(players, state, nullOutput);
        winningPlayers = gameMaster.winner();

        assertEquals(2, winningPlayers.size());
        assertEquals(mockPlayer2, winningPlayers.get(0));
        assertEquals(mockPlayer3, winningPlayers.get(1));
    }

    @Test
    public void pointTieButWinningByTurnTestThreePlayersThreeWinners() {
        players.add(new TestNoBuysPlayer("1"));
        players.add(new TestNoBuysPlayer("2"));
        Player playerThatWillWin = new ThreePointsFirstRoundNoActionPlayer("3");
        players.add(playerThatWillWin);
        state = setup.initiateGameState(players);
        gameMaster = new GameMaster(players, state, nullOutput);
        winningPlayers = gameMaster.winner();
        assertTrue(winningPlayers.size() == 3);
    }

    @Test
    public void pointTieButWinningByTurnTestFourPlayersFourWinners() {
        players.add(new TestNoBuysPlayer("1"));
        players.add(new TestNoBuysPlayer("2"));
        players.add(new TestNoBuysPlayer("3"));
        Player playerThatWillWin = new ThreePointsFirstRoundNoActionPlayer("4");
        players.add(playerThatWillWin);
        state = setup.initiateGameState(players);
        gameMaster = new GameMaster(players, state, nullOutput);
        winningPlayers = gameMaster.winner();
        assertTrue(winningPlayers.size() == 4);
    }

    @Test
    public void pointTieButWinningByTurnTestFourPlayersThreeWinners() {
        when(mockPlayer1.victoryPoints()).thenReturn(10);
        when(mockPlayer2.victoryPoints()).thenReturn(10);
        when(mockPlayer3.victoryPoints()).thenReturn(10);
        when(mockPlayer4.victoryPoints()).thenReturn(10);
        when(mockPlayer1.turns()).thenReturn(100);
        when(mockPlayer2.turns()).thenReturn(10);
        when(mockPlayer3.turns()).thenReturn(10);
        when(mockPlayer4.turns()).thenReturn(10);

        players.add(mockPlayer1);
        players.add(mockPlayer2);
        players.add(mockPlayer3);
        players.add(mockPlayer4);
        state = mock(GameState.class);
        gameMaster = new GameMaster(players, state, nullOutput);
        winningPlayers = gameMaster.winner();

        assertEquals(3, winningPlayers.size());
        assertEquals(mockPlayer2, winningPlayers.get(0));
        assertEquals(mockPlayer3, winningPlayers.get(1));
        assertEquals(mockPlayer4, winningPlayers.get(2));
    }

    @Test
    public void ifFourPlayersHaveEqualPointsAndTwoEqualTurnsThereAreTwoWinners() {
        when(mockPlayer1.victoryPoints()).thenReturn(10);
        when(mockPlayer2.victoryPoints()).thenReturn(10);
        when(mockPlayer3.victoryPoints()).thenReturn(10);
        when(mockPlayer4.victoryPoints()).thenReturn(10);
        when(mockPlayer1.turns()).thenReturn(100);
        when(mockPlayer2.turns()).thenReturn(100);
        when(mockPlayer3.turns()).thenReturn(10);
        when(mockPlayer4.turns()).thenReturn(10);

        players.add(mockPlayer1);
        players.add(mockPlayer2);
        players.add(mockPlayer3);
        players.add(mockPlayer4);
        state = mock(GameState.class);
        gameMaster = new GameMaster(players, state, nullOutput);
        winningPlayers = gameMaster.winner();

        assertEquals(2, winningPlayers.size());
        assertEquals(mockPlayer3, winningPlayers.get(0));
        assertEquals(mockPlayer4, winningPlayers.get(1));
    }

    private class NullStream extends OutputStream {
        @Override
        public void write(int i) throws IOException {

        }
    }
}
