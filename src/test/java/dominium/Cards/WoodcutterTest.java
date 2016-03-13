package dominium.Cards;

import dominium.Players.Player;

import static org.mockito.Mockito.*;

public class WoodcutterTest {
    public void afterResolvingCurrentPlayerHasTwoMoreCards() {
        Player mockPlayer = mock(Player.class);
        when(mockPlayer.getActions()).thenReturn(2);

        Village village = new Village();
        village.resolve(mockPlayer, null, null);
        verify(mockPlayer).drawCards(1);
        verify(mockPlayer).getActions();
        verify(mockPlayer).setActions(4);
    }
}
