package pro.tremblay.social.command;

import pro.tremblay.social.CommandStatus;
import pro.tremblay.social.Message;
import pro.tremblay.social.User;
import pro.tremblay.social.UserList;
import pro.tremblay.social.util.Console;

import java.util.Comparator;
import java.util.List;

public class ReadingCommand implements Command {

    private final Console console;
    private final UserList userList;

    public ReadingCommand(Console console, UserList userList) {
        this.console = console;
        this.userList = userList;
    }

    @Override
    public CommandStatus process(String... tokens) {
        if(tokens.length == 1) {
            reading(tokens[0]);
            return CommandStatus.HANDLED;
        }
        return CommandStatus.CONTINUE;
    }

    private void reading(String userName) {
        User user = userList.getUser(userName);
        List<Message> messages = user.getMessages();
        messages.stream()
                .sorted(Comparator.comparing(Message::id).reversed())
                .forEachOrdered(message -> console.write(userName + " - " + message.body()));
    }
}
