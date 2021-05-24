package pro.tremblay.social.command;

import org.junit.jupiter.api.Test;
import pro.tremblay.social.CommandStatus;
import pro.tremblay.social.Message;
import pro.tremblay.social.UserList;

import static org.assertj.core.api.Assertions.assertThat;

class PostingCommandTest {

    UserList userList = new UserList();
    PostingCommand command = new PostingCommand(userList);

    @Test
    void process_notPosting() {
        assertThat(command.process("")).isEqualTo(CommandStatus.CONTINUE);
        assertThat(command.process("Alice xxx message")).isEqualTo(CommandStatus.CONTINUE);
    }

    @Test
    void process_posting() {
        assertThat(command.process("Alice", "->", "this is a message")).isEqualTo(CommandStatus.HANDLED);
        assertThat(command.process("Alice", "->", "this is another message")).isEqualTo(CommandStatus.HANDLED);
        assertThat(userList.getUser("Alice").getMessages())
                .extracting(Message::body)
                .containsExactly("this is a message", "this is another message");
    }
}