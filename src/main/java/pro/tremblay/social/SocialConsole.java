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

public final class SocialConsole {

    public static final String POSTING = "->";
    public static final String FOLLOWS = "follows";
    public static final String EXIT = "exit";
    private Console console;

    public SocialConsole(Console console) {
        this.console = console;
    }

    public static void main(String[] args) {
        SocialConsole console = new SocialConsole(new Console());
        console.start();
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

    public boolean takeCommand(String command) {
        String[] commands = command.split(" ");
        if (command.equals(EXIT)) {
            console.write("bye!");
            return false;
        }
        if (commands.length > 2) {
            if(POSTING.equals(commands[1])) {
               posting();
            } else if (FOLLOWS.equals(commands[1])) {
                console.write("Follows mode");
            }
        }
        return true;
    }

    public void follow() {

    }

    public void posting() {
        console.write("Posting mode");
    }

    public void wall() {

    }

    public void reading() {

    }
}
