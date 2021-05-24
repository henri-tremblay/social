package pro.tremblay.social.command;

import org.junit.jupiter.api.Test;
import pro.tremblay.social.CommandStatus;
import pro.tremblay.social.UserList;
import pro.tremblay.social.util.Console;
import pro.tremblay.social.util.RecordingConsole;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ReadingCommandTest {

    RecordingConsole console = new RecordingConsole();
    UserList userList = new UserList();
    ReadingCommand command = new ReadingCommand(console, userList);

    @Test
    void process_notReading() {
        assertThat(command.process()).isEqualTo(CommandStatus.CONTINUE);
        assertThat(command.process("Alice", "says")).isEqualTo(CommandStatus.CONTINUE);
    }

    @Test
    void process_reading() {
        userList.getUser("Alice").addMessage("the message");
        userList.getUser("Alice").addMessage("the other message");
        userList.getUser("Bob").addMessage("the wrong message");
        assertThat(command.process("Alice")).isEqualTo(CommandStatus.HANDLED);
        console.assertOutput("Alice - the other message", "Alice - the message");
    }
}