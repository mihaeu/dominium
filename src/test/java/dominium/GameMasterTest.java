package dominium;

import dominium.Cards.Card;
import dominium.Cards.Copper;
import dominium.Cards.Estate;
import dominium.Players.Player;
import dominium.Players.RandomPlayer;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameMasterTest {

    private List<Player> players;
    private List<Player> winningPlayers;
    private GameMaster gameMaster;

    private GameState mockGameState;

    private Player mockPlayer1;
    private Player mockPlayer2;
    private Player mockPlayer3;
    private Player mockPlayer4;


    @Before
    public void setUp() {
        players = new ArrayList<>();

        mockPlayer1 = mock(Player.class);
        mockPlayer2 = mock(Player.class);
        mockPlayer3 = mock(Player.class);
        mockPlayer4 = mock(Player.class);

        mockGameState = mock(GameState.class);
        PrintStream mockStream = mock(PrintStream.class);
        gameMaster = new GameMaster(players, mockGameState, mockStream);
    }

    @Test
    public void makePlayerDrawCards() {
        players.add(mockPlayer1);
        gameMaster.startGame();
        assertEquals(mockPlayer1, gameMaster.winner().get(0));
    }

    @Test
    public void playerWithMostPointsWins() {
        when(mockPlayer1.victoryPoints()).thenReturn(0);
        when(mockPlayer2.victoryPoints()).thenReturn(100);

        players.add(mockPlayer1);
        players.add(mockPlayer2);

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
        winningPlayers = gameMaster.winner();

        assertEquals(2, winningPlayers.size());
        assertEquals(mockPlayer2, winningPlayers.get(0));
        assertEquals(mockPlayer3, winningPlayers.get(1));
    }

    @Test
    public void ifFourPlayersHaveEqualPointsAndEqualTurnsThereAreThreeWinners() {
        players.add(mockPlayer1);
        players.add(mockPlayer2);
        players.add(mockPlayer3);
        winningPlayers = gameMaster.winner();

        assertEquals(3, winningPlayers.size());
        assertEquals(mockPlayer1, winningPlayers.get(0));
        assertEquals(mockPlayer2, winningPlayers.get(1));
        assertEquals(mockPlayer3, winningPlayers.get(2));
    }

    @Test
    public void ifFourPlayersHaveEqualPointsAndEqualTurnsThereAreFourWinners() {
        players.add(mockPlayer1);
        players.add(mockPlayer2);
        players.add(mockPlayer3);
        players.add(mockPlayer4);
        winningPlayers = gameMaster.winner();

        assertEquals(4, winningPlayers.size());
        assertEquals(mockPlayer1, winningPlayers.get(0));
        assertEquals(mockPlayer2, winningPlayers.get(1));
        assertEquals(mockPlayer3, winningPlayers.get(2));
        assertEquals(mockPlayer4, winningPlayers.get(3));
    }

    @Test
    public void ifFourPlayersHaveEqualPointsAndThreeEqualTurnsThereAreThreeWinners() {
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
        winningPlayers = gameMaster.winner();

        assertEquals(2, winningPlayers.size());
        assertEquals(mockPlayer3, winningPlayers.get(0));
        assertEquals(mockPlayer4, winningPlayers.get(1));
    }

    @Test
    public void makesPlayerChooseCardFromAvailableOptions() {
        // give the player a hand full of coins
        Player player1 = new RandomPlayer("Test");
        player1.handCards().add(new Copper());
        player1.handCards().add(new Copper());
        player1.handCards().add(new Copper());
        player1.handCards().add(new Copper());
        player1.handCards().add(new Copper());
        player1.handCards().add(new Copper());
        player1.handCards().add(new Copper());
        players.add(player1);

        // give him the chance to only draw an Estate card
        Map<Class, Stack<Card>> testCards = new HashMap<>();
        CardStack provinceStack = new CardStack();
        provinceStack.add(new Estate());
        testCards.put(Estate.class, provinceStack);

        // play two rounds, the second round he can't pick a card
        when(mockGameState.getKingdomCards()).thenReturn(testCards);
        when(mockGameState.gameIsRunning())
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false);
        gameMaster.startGame();
        assertEquals(1, player1.victoryPoints());
    }

    @Test
    public void gameWithoutPlayersHasNoWinners()
    {
        when(mockGameState.gameIsRunning())
                .thenReturn(true)
                .thenReturn(false);
        gameMaster.startGame();
        assertEquals(0, gameMaster.winner().size());
    }
}
