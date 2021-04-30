package pro.tremblay.social;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.tremblay.social.util.Console;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SocialConsoleTest {

    private Console console = mock(Console.class);
    private SocialConsole socialConsole = new SocialConsole(console);

    @BeforeEach
    void init(){
        socialConsole.start();
    }

    @Test
    void exit() {
        when(console.readline()).thenReturn("exit");
        verify(console).write("bye!");
    }
}