package pro.tremblay.social.command;

import org.junit.jupiter.api.Test;
import pro.tremblay.social.CommandStatus;
import pro.tremblay.social.User;
import pro.tremblay.social.UserList;

import static org.assertj.core.api.Assertions.assertThat;

class FollowsCommandTest {

    UserList userList = new UserList();
    FollowsCommand command = new FollowsCommand(userList);

    @Test
    void process_notFollow() {
        assertThat(command.process()).isEqualTo(CommandStatus.CONTINUE);
        assertThat(command.process("Alice", "xxx", "Bob")).isEqualTo(CommandStatus.CONTINUE);
    }

    @Test
    void process_follows() {
        assertThat(command.process("Alice", "follows", "Bob")).isEqualTo(CommandStatus.HANDLED);
        assertThat(command.process("Alice", "follows", "Charles")).isEqualTo(CommandStatus.HANDLED);
        assertThat(command.process("Bob", "follows", "Dan")).isEqualTo(CommandStatus.HANDLED);
        assertThat(command.process("Elon", "follows", "Dan")).isEqualTo(CommandStatus.HANDLED);

        assertThat(userList.getUser("Alice").getFollowed()).extracting(User::getUsername).containsExactlyInAnyOrder("Bob", "Charles");
        assertThat(userList.getUser("Bob").getFollowed()).extracting(User::getUsername).containsExactlyInAnyOrder("Dan");
        assertThat(userList.getUser("Charles").getFollowed()).isEmpty();
        assertThat(userList.getUser("Dan").getFollowed()).isEmpty();
        assertThat(userList.getUser("Elon").getFollowed()).extracting(User::getUsername).containsExactlyInAnyOrder("Dan");

    }
}