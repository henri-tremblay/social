package pro.tremblay.social.command;

import org.junit.jupiter.api.Test;
import pro.tremblay.social.CommandStatus;
import pro.tremblay.social.User;
import pro.tremblay.social.UserList;
import pro.tremblay.social.util.RecordingConsole;

import static org.assertj.core.api.Assertions.assertThat;

class WallCommandTest {

    RecordingConsole console = new RecordingConsole();
    UserList userList = new UserList();
    WallCommand command = new WallCommand(console, userList);

    @Test
    void processNotWall() {
        assertThat(command.process()).isEqualTo(CommandStatus.CONTINUE);
        assertThat(command.process("Alice follows Bob")).isEqualTo(CommandStatus.CONTINUE);
        assertThat(command.process("Alice xxx")).isEqualTo(CommandStatus.CONTINUE);
    }

    @Test
    void processWall() {
        User alice = userList.getUser("Alice");
        User bob = userList.getUser("Bob");
        User charles = userList.getUser("Charles");
        User dan = userList.getUser("Dan");

        dan.addMessage("5");
        alice.addMessage("1");
        bob.addMessage("2");
        alice.addMessage("3");
        charles.addMessage("4");

        alice.addFollowed(charles);
        alice.addFollowed(dan);
        bob.addFollowed(charles);
        dan.addFollowed(alice);

        assertThat(command.process("Alice", "wall")).isEqualTo(CommandStatus.HANDLED);
        console.assertOutput("Charles - 4", "Alice - 3", "Alice - 1", "Dan - 5");
    }
}