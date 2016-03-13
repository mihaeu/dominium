package dominium.Players;

import dominium.CardStack;
import dominium.Cards.Copper;
import dominium.Cards.Village;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.*;

public class ConsolePlayerTest {
    private PrintStream out;
    private ConsolePlayer consolePlayer;

    @Before
    public void setUp() {
        InputStream in = new ByteArrayInputStream("1".getBytes());
        out = mock(PrintStream.class);
        consolePlayer = new ConsolePlayer("Test", out, in);
    }

    @Test
    public void askUserForSelection() {
        CardStack stack = new CardStack();
        stack.add(new Copper());
        stack.add(new Village());
        consolePlayer.selectCard(stack);

        verify(out).print("Test choose a card (or -1 to pass): ");
    }
}
