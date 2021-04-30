package pro.tremblay.social;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import pro.tremblay.social.util.Console;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Timeout(10)
class SocialConsoleTest {

    private Console console = mock(Console.class);
    private SocialConsole socialConsole = spy(new SocialConsole(console));

    @Test
    void exit() {
        when(console.readline()).thenReturn("exit");
        socialConsole.start();
        verify(console).write("bye!");
    }

    @Test
    void posting() {
        when(console.readline())
            .thenReturn("Henri -> Bonjour tout le monde")
            .thenReturn("exit");
        socialConsole.start();
        verify(socialConsole).posting("Henri", "Bonjour tout le monde");
    }

    @Test
    void reading() {
        when(console.readline())
                .thenReturn("Henri -> Bonjour")
                .thenReturn("Henri -> Salut")
                .thenReturn("Djamel -> Hello")
                .thenReturn("Henri")
                .thenReturn("exit");
        socialConsole.start();
        verify(console).write("Start socializing");
        verify(console).write("Henri - Bonjour");
        verify(console).write("Henri - Salut");
        verify(console).write("bye!");
    }
}