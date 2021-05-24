/*
 * Copyright 2021-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pro.tremblay.social;

import pro.tremblay.social.command.Command;
import pro.tremblay.social.command.ExitCommand;
import pro.tremblay.social.command.FollowsCommand;
import pro.tremblay.social.command.PostingCommand;
import pro.tremblay.social.command.ReadingCommand;
import pro.tremblay.social.command.WallCommand;
import pro.tremblay.social.util.Console;
import pro.tremblay.social.util.SystemConsole;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SocialConsole {

    private final Console console;
    private final Command[] commands;

    public static void main(String[] args) {
        UserList userList = new UserList();
        SystemConsole systemConsole = new SystemConsole();
        SocialConsole console = new SocialConsole(systemConsole,
                // this list is ordered since "reading" has not keywords
                new ExitCommand(systemConsole),
                new PostingCommand(userList),
                new FollowsCommand(userList),
                new WallCommand(systemConsole, userList),
                new ReadingCommand(systemConsole, userList));
        console.start();
    }

    public SocialConsole(Console console, Command... commands) {
        this.console = console;
        this.commands = commands;
    }

    public void start() {
        console.write("Start socializing");
        while (true) {
            String command = console.readline();
            if (!takeCommand(command)) {
                return;
            }
        }
    }

    boolean takeCommand(String line) {
        String[] tokens = parseLine(line);
        for (Command command : commands) {
            CommandStatus status = command.process(tokens);
            switch (status) {
                case EXIT:
                    return false;
                case HANDLED:
                    return true;
            }
        }
        return true;
    }

    String[] parseLine(String line) {
        return line.split(" ", 3);
    }

}


