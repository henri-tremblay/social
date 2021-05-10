package pro.tremblay.social;

import org.junit.jupiter.api.Test;
import pro.tremblay.social.util.RecordingConsole;

class SocialConsoleTest {

    RecordingConsole console = new RecordingConsole();
    SocialConsole socialConsole = new SocialConsole(console);

    @Test
    void exit() {
        // TBD
        socialConsole.start();
    }

}