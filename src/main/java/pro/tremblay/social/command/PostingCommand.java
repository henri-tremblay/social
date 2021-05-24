package pro.tremblay.social.command;

import pro.tremblay.social.CommandStatus;
import pro.tremblay.social.User;
import pro.tremblay.social.UserList;
import pro.tremblay.social.command.Command;

public class PostingCommand implements Command {

    private final UserList userList;

    public PostingCommand(UserList userList) {
        this.userList = userList;
    }

    @Override
    public CommandStatus process(String... tokens) {
        if (tokens.length == 3 && tokens[1].equals("->")) {
            posting(tokens);
            return CommandStatus.HANDLED;
        }
        return CommandStatus.CONTINUE;
    }

    private void posting(String[] tokens) {
        String name = tokens[0];
        String message = tokens[2];
        User user = userList.getUser(name);
        user.addMessage(message);
    }
}
