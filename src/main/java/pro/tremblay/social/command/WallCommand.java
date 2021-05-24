package pro.tremblay.social.command;

import pro.tremblay.social.CommandStatus;
import pro.tremblay.social.Message;
import pro.tremblay.social.User;
import pro.tremblay.social.UserList;
import pro.tremblay.social.util.Console;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Stream;

public class WallCommand implements Command {

    private static class UserMessage {
        private final User user;
        private final Message message;

        public UserMessage(User user, Message message) {
            this.user = user;
            this.message = message;
        }

        public int messageId() {
            return message.id();
        }

        public User user() {
            return user;
        }

        public Message message() {
            return message;
        }
    }

    private final Console console;
    private final UserList userList;

    public WallCommand(Console console, UserList userList) {
        this.console = console;
        this.userList = userList;
    }

    @Override
    public CommandStatus process(String... tokens) {
        if (tokens.length == 2 && tokens[1].equals("wall")) {
            wall(tokens[0]);
            return CommandStatus.HANDLED;
        }
        return CommandStatus.CONTINUE;
    }

    private void wall(String userName) {
        User user = userList.getUser(userName);

        // Get all messages from this user
        Stream<UserMessage> myMessages = user.getMessages()
                .stream()
                .map(message -> new UserMessage(user, message));

        Set<User> followed = user.getFollowed();
        Stream<UserMessage> otherMessages = followed.stream()
                .flatMap(u -> u.getMessages().stream()
                        .map(m -> new UserMessage(u, m)));

        Stream.concat(myMessages, otherMessages)
                .sorted(Comparator.comparingInt(UserMessage::messageId).reversed())
                .map(tuple -> tuple.user().getUsername() + " - " + tuple.message().body())
                .forEachOrdered(console::write);
    }
}
