package pro.tremblay.social;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import pro.tremblay.social.util.RecordingConsole;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@Timeout(10)
class SocialConsoleTest {

    private final RecordingConsole console = new RecordingConsole();
    private final SocialConsole socialConsole = spy(new SocialConsole(console));

    @Test
    void exit() {
        console.addLine("exit");
        socialConsole.start();
        console.assertOutput("bye!");
    }

    @Test
    void posting() {
        console.addLine("Henri -> Bonjour tout le monde")
               .addLine("exit");
        socialConsole.start();
        verify(socialConsole).posting("Henri", "Bonjour tout le monde");
    }

    @Test
    void reading() {
        console.addLine("Henri -> Bonjour")
               .addLine("Henri -> Salut")
               .addLine("Djamel -> Hello")
               .addLine("Henri")
               .addLine("exit");
        socialConsole.start();

        console.assertOutput("Start socializing", "Henri - Salut", "Henri - Bonjour", "bye!");
    }

    @Test
    void follow() {
        console.addLine("Henri follows Djamel")
               .addLine("exit");
        socialConsole.start();
        verify(socialConsole).follow("Henri", "Djamel");
    }

    @Test
    void wall() {
        console.addLine("Henri -> Bonjour")
               .addLine("Henri -> Salut")
               .addLine("Djamel -> Hello")
               .addLine("Henri follows Djamel")
               .addLine("Henri wall")
               .addLine("exit");
        socialConsole.start();

        console.assertOutput("Start socializing", "Djamel - Hello", "Henri - Salut", "Henri - Bonjour", "bye!");
    }
}