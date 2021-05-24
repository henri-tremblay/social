package pro.tremblay.social;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import pro.tremblay.social.util.RecordingConsole;
import pro.tremblay.social.util.SimpleCommand;

import static org.assertj.core.api.Assertions.assertThat;

@Timeout(10)
class SocialConsoleTest {

    RecordingConsole console = new RecordingConsole();
    SocialConsole socialConsole;

    private void uselessLine() {
        console.addLine("unused");
    }

    @Test
    void continueAndExit() {
        uselessLine();

        SimpleCommand command = new SimpleCommand(CommandStatus.CONTINUE, CommandStatus.EXIT);

        socialConsole = new SocialConsole(console, command, command);
        socialConsole.start();

        assertThat(command.called(0)).isTrue();
        assertThat(command.called(1)).isTrue();
    }

    @Test
    void handledExit() {
        uselessLine();
        uselessLine();

        SimpleCommand command = new SimpleCommand(CommandStatus.HANDLED, CommandStatus.EXIT);

        socialConsole = new SocialConsole(console, command, command);
        socialConsole.start();

        assertThat(command.called(0)).isTrue();
        assertThat(command.called(1)).isTrue();
    }

    @Test
    public void parseLine() {
        socialConsole = new SocialConsole(console);
        assertThat(socialConsole.parseLine("")).containsExactly("");
        assertThat(socialConsole.parseLine("Alice")).containsExactly("Alice");
        assertThat(socialConsole.parseLine("Alice wall")).containsExactly("Alice", "wall");
        assertThat(socialConsole.parseLine("Alice -> the message")).containsExactly("Alice", "->", "the message");
    }
}