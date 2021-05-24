package pro.tremblay.social.command;

import pro.tremblay.social.CommandStatus;
import pro.tremblay.social.UserList;

public class FollowsCommand implements Command{

    private final UserList userList;

    public FollowsCommand(UserList userList) {
        this.userList = userList;
    }

    @Override
    public CommandStatus process(String... tokens) {
        if (tokens.length == 3 && tokens[1].equals("follows")) {
            follows(tokens);
            return CommandStatus.HANDLED;
        }
        return CommandStatus.CONTINUE;
    }

    private void follows(String[] tokens) {
        String follower = tokens[0];
        String followed = tokens[2];
        userList.getUser(follower).addFollowed(userList.getUser(followed));
    }
}
