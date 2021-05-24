package pro.tremblay.social.util;

import pro.tremblay.social.CommandStatus;
import pro.tremblay.social.command.Command;

import java.util.BitSet;

public class SimpleCommand implements Command {

    private int count = 0;
    private final BitSet called;
    private final CommandStatus[] statuses;

    public SimpleCommand(CommandStatus... statuses) {
        this.statuses = statuses;
        this.called = new BitSet(statuses.length);
    }

    public boolean called(int index) {
        return called.get(index);
    }

    @Override
    public CommandStatus process(String... tokens) {
        called.set(count);
        return statuses[count++];
    }
}
