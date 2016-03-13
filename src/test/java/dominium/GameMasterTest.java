package dominium;

import dominium.Cards.*;
import dominium.Players.Player;
import dominium.Players.RandomPlayer;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InOrder;
import org.slf4j.LoggerFactory;

import java.util.*;

import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

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
        gameMaster = new GameMaster(players, mockGameState, LoggerFactory.getLogger("quiet"));
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
        KingdomCardMap kingdomCards = Convenience.kingdomCards(new Estate());

        // play two rounds, the second round he can't pick a card
        when(mockGameState.getKingdomCards()).thenReturn(kingdomCards);
        when(mockGameState.gameIsRunning())
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false);
        gameMaster.startGame();
        assertEquals(1, player1.victoryPoints());
    }

    @Test
    @Ignore
    public void playerCannotBuyMoreAfterRefusingToBuyOnce()
    {
        when(mockPlayer1.selectCard(new ArrayList<>()))
                .thenReturn(new Estate())
                .thenReturn(null)
        ;
        when(mockPlayer1.getBuys()).thenReturn(2);
        players.add(mockPlayer1);

        when(mockGameState.gameIsRunning())
                .thenReturn(true)
                .thenReturn(false);
        gameMaster.startGame();
        assertEquals(1, mockPlayer1.victoryPoints());
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

    @Test
    public void findsCurrentPlayer()
    {
        players.add(mockPlayer1);
        players.add(mockPlayer2);
        when(mockGameState.gameIsRunning())
                .thenReturn(true)
                .thenReturn(false);
        gameMaster.startGame();
        assertEquals(mockPlayer1, gameMaster.currentPlayer());
    }

    @Test
    public void findsOtherPlayers()
    {
        players.add(mockPlayer1);
        players.add(mockPlayer2);
        players.add(mockPlayer3);
        when(mockGameState.gameIsRunning())
                .thenReturn(true)
                .thenReturn(false);
        gameMaster.startGame();
        assertEquals(2, gameMaster.otherPlayers().size());
        assertEquals(mockPlayer2, gameMaster.otherPlayers().get(0));
        assertEquals(mockPlayer3, gameMaster.otherPlayers().get(1));
    }

    @Test
    public void beforeStartingTheGameThereIsNoCurrentPlayer()
    {
        players.add(mockPlayer1);
        players.add(mockPlayer2);
        players.add(mockPlayer3);
        players.add(mockPlayer4);
        assertEquals(4, gameMaster.otherPlayers().size());
        assertEquals(null, gameMaster.currentPlayer());
    }

    @Test
    public void forwardsKingdomCards() {
        KingdomCardMap kingdomCards = Convenience.kingdomCards(new Copper());
        when(mockGameState.getKingdomCards()).thenReturn(kingdomCards);
        assertSame(kingdomCards, gameMaster.kingdomCards());
    }

    @Test
    public void ifPlayerDoesNotSelectCardForBuyingHeCannotChooseAgain()
    {
        when(mockPlayer1.selectCard(any())).thenReturn(null);
        when(mockPlayer1.getBuys()).thenReturn(1);
        players.add(mockPlayer1);

        KingdomCardMap kingdomCards = Convenience.kingdomCards(new Copper());
        when(mockGameState.getKingdomCards()).thenReturn(kingdomCards);

        when(mockGameState.gameIsRunning())
                .thenReturn(true)
                .thenReturn(false);
        gameMaster.startGame();

        InOrder inOrder = inOrder(mockPlayer1);
        inOrder.verify(mockPlayer1).setBuys(1);     // beginning action phase
        inOrder.verify(mockPlayer1).setBuys(0);     // reduced from buying
        inOrder.verify(mockPlayer1).setBuys(0);     // set again because no card selection
    }

    @Test
    public void resolvesSelectedActionCard()
    {
        Village village = new Village();
        when(mockPlayer1.selectCard(any())).thenReturn(village);
        when(mockPlayer1.getActions())
            .thenReturn(1)
            .thenReturn(0);
        players.add(mockPlayer1);

        CardStack villageStack = Convenience.stack(new Village());
        KingdomCardMap kingdomCards = Convenience.kingdomCards(new Village());
        when(mockGameState.getKingdomCards()).thenReturn(kingdomCards);
        when(mockPlayer1.handCards()).thenReturn(villageStack);

        when(mockGameState.gameIsRunning())
                .thenReturn(true)
                .thenReturn(false);
        gameMaster.startGame();
        assertTrue(village.isPlayed());
    }

    @Test
    public void countsCurseCards() {
        Player player1 = new RandomPlayer("Test 1");
        player1.handCards().add(new Estate());
        player1.handCards().add(new Duchy());
        player1.handCards().add(new Province());
        player1.handCards().add(new Moat());
        player1.handCards().add(new Gold());

        Player player2 = new RandomPlayer("Test 2");
        player2.handCards().add(new Estate());
        player2.handCards().add(new Duchy());
        player2.handCards().add(new Province());
        player2.handCards().add(new Moat());
        player2.handCards().add(new Gold());
        player2.handCards().add(new Curse());

        players.add(player1);
        players.add(player2);
        winningPlayers = gameMaster.winner();
        assertEquals(1, winningPlayers.size());
        assertEquals(player1, winningPlayers.get(0));
    }
}
