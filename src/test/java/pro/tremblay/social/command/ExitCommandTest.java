package pro.tremblay.social.command;

import org.junit.jupiter.api.Test;
import pro.tremblay.social.CommandStatus;
import pro.tremblay.social.util.RecordingConsole;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ExitCommandTest {

    RecordingConsole console = new RecordingConsole();
    ExitCommand command = new ExitCommand(console);

    @Test
    void process_notExit() {
        assertThat(command.process()).isEqualTo(CommandStatus.CONTINUE);
        assertThat(command.process("something")).isEqualTo(CommandStatus.CONTINUE);
    }

    @Test
    void process_exit() {
        assertThat(command.process("exit")).isEqualTo(CommandStatus.EXIT);
    }
}