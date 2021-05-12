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
import pro.tremblay.social.util.SystemConsole;

import java.util.*;

public final class SocialConsole {

    private final Console console;

    public SocialConsole(Console console) {
        this.console = console;
    }

    public static void main(String[] args) {
        Console console = new SystemConsole();
        SocialConsole socialConsole = new SocialConsole(console);
        socialConsole.start();
    }

    public void start() {
        Map<String, List<String>> messages = new HashMap<>();
        console.write("Start socializing");
        while (true) {
            String line = console.readline();
            if (line.equals("exit")) {
                console.write("bye!");
                break;
            }
            if (line.contains("->")) {
                String[] splitToken = line.split(" -> ");
                String username = splitToken[0];
                String message = splitToken[1];
                if (!messages.containsKey(username)) {
                    List<String> messageList = new ArrayList<>();
                    messages.put(username, messageList);
                }


                messages.get(username).add(message);
                continue;

            }


            if (line.contains("follows")) {
                console.write("Posting!");


            }
            messages.get(line).stream().sorted(Comparator.naturalOrder().reversed()) .forEach(message -> console.write(line + " - " + message));

        }
    }

}
