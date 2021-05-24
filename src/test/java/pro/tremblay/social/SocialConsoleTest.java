package pro.tremblay.social;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import pro.tremblay.social.util.RecordingConsole;

import static org.assertj.core.api.Assertions.assertThat;

@Timeout(10)
class SocialConsoleTest {

    RecordingConsole console = new RecordingConsole();
    SocialConsole socialConsole;

    @Test
    void stopOnExit() {
        console.addLine("useless");
        socialConsole = new SocialConsole(console, tokens -> CommandStatus.EXIT);
        socialConsole.start();
    }
    @Test
    void takeCommand_exit() {
        socialConsole = new SocialConsole(console, tokens -> CommandStatus.EXIT);
        assertThat(socialConsole.takeCommand("unused")).isFalse();
    }

    @Test
    void takeCommand_handled() {
        socialConsole = new SocialConsole(console, tokens -> CommandStatus.HANDLED);
        assertThat(socialConsole.takeCommand("unused")).isTrue();
    }

    @Test
    void takeCommand_unknown() {
        socialConsole = new SocialConsole(console, tokens -> CommandStatus.CONTINUE);
        assertThat(socialConsole.takeCommand("unused")).isTrue();
    }

    @Test
    void takeCommand_multipleCommands() {
        boolean[] called = { false, false, false };
        socialConsole = new SocialConsole(console,
                tokens -> {
                    called[0] = true;
                    return CommandStatus.CONTINUE;
                },
                tokens ->  {
                    called[1] = true;
                    return CommandStatus.HANDLED;
                },
                tokens -> {
                    called[2] = true;
                    return CommandStatus.CONTINUE;
                });
        assertThat(socialConsole.takeCommand("unused")).isTrue();
        assertThat(called).containsExactly(true, true, false);
    }

    @Test
    void parseLine() {
        socialConsole = new SocialConsole(console);
        assertThat(socialConsole.parseLine("")).containsExactly("");
        assertThat(socialConsole.parseLine("Alice")).containsExactly("Alice");
        assertThat(socialConsole.parseLine("Alice wall")).containsExactly("Alice", "wall");
        assertThat(socialConsole.parseLine("Alice -> the message")).containsExactly("Alice", "->", "the message");
    }
}