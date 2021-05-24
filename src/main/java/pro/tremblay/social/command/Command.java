package pro.tremblay.social.command;

import pro.tremblay.social.CommandStatus;

public interface Command {
    CommandStatus process(String... tokens);
}
