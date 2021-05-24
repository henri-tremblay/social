package pro.tremblay.social.command;

import pro.tremblay.social.CommandStatus;
import pro.tremblay.social.util.Console;

public class ExitCommand implements Command {

    private final Console console;

    public ExitCommand(Console console) {
        this.console = console;
    }

    @Override
    public CommandStatus process(String... tokens) {
        if (tokens.length == 1 && tokens[0].equals("exit")) {
            exit();
            return CommandStatus.EXIT;
        }
        return CommandStatus.CONTINUE;
    }

    private void exit() {
        console.write("bye!");
    }
}
