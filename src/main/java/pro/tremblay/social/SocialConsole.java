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

import pro.tremblay.social.util.Console;

import java.util.function.Function;

public final class SocialConsole {

    public static final String POSTING = "->";
    public static final String FOLLOWS = "follows";
    public static final String EXIT = "exit";
    private static SocialConsole console;

    public static void main(String[] args) {
        console = new SocialConsole();
        console.start();
    }

    public void start() {
        Console console = new Console();
        console.write("Start socializing");
        while (true) {
            enterCommand();
        }
    }

    public void enterCommand(){
        String command = console.readline();
        takeCommand(command, console);
    }

    public Function<Void, Void> takeCommand(String command, Console console) {
        String[] commands = command.split(" ");
        if (commands.length > 2) {
            if(POSTING.equals(commands[1])) {
                return posting(console);
            } else if (FOLLOWS.equals(commands[1])) {
                console.write("Follows mode");
            }
        }
        if (command.equals(EXIT)) {
            console.write("bye!");
            break;
        }
    }

    public void follow(Console console) {

    }

    public void posting(Console console) {
        console.write("Posting mode");
    }

    public void wall() {

    }

    public void reading() {

    }
}
