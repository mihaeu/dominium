package dominium.Cards;

import dominium.GameMaster;
import dominium.Players.Player;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WoodcutterTest {
    public void afterResolvingCurrentPlayerHasTwoMoreCards() {
        GameMaster mockMaster = mock(GameMaster.class);
        Player mockPlayer = mock(Player.class);
        when(mockMaster.currentPlayer()).thenReturn(mockPlayer);
        when(mockPlayer.getActions()).thenReturn(2);

        Village village = new Village();
        village.resolve(mockMaster);
        verify(mockPlayer).drawCards(1);
        verify(mockPlayer).getActions();
        verify(mockPlayer).setActions(4);
    }
}
