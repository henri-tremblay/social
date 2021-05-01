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

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SocialConsole {

    private static class UserMessage {
        final User user;
        final Message message;

        public UserMessage(User user, Message message) {
            this.user = user;
            this.message = message;
        }

        public int getMessageId() {
            return message.getId();
        }
    }

    private static final String POSTING = "->";
    private static final String FOLLOWS = "follows";
    private static final String WALL = "wall";
    private static final String EXIT = "exit";

    private final Console console;
    private final UserList userList = new UserList();

    public static void main(String[] args) {
        SocialConsole console = new SocialConsole(new SystemConsole());
        console.start();
    }

    public SocialConsole(Console console) {
        this.console = console;
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
        String[] commands = command.split(" ", 3);
        if (command.equals(EXIT)) {
            console.write("bye!");
            return false;
        }
        if (commands.length > 2) {
            if (POSTING.equals(commands[1])) {
                posting(commands[0], commands[2]);
            } else if (FOLLOWS.equals(commands[1])) {
                follow(commands[0], commands[2]);
            }
        } else if (commands.length == 2) {
            if (WALL.equals(commands[1])) {
                wall(commands[0]);
            }
        } else if (commands.length == 1) {
            reading(commands[0]);
        }
        return true;
    }

    void follow(String follower, String followed) {
        userList.getUser(follower).addFollower(userList.getUser(followed));
    }

    void posting(String userName, String body) {
        User user = userList.getUser(userName);
        user.addMessage(body);
    }

    void wall(String userName) {
        User user = userList.getUser(userName);
        Set<User> followers = user.getFollowers();
        Stream<UserMessage> myMessages = user.getMessages()
                .stream()
                .map(message -> new UserMessage(user, message));

        Stream<UserMessage> otherMessages = followers.stream()
                .flatMap(u -> u.getMessages().stream()
                        .map(m -> new UserMessage(u, m)));

        Stream.concat(myMessages, otherMessages)
                .sorted(Comparator.comparingInt(UserMessage::getMessageId).reversed())
                .map(tuple -> tuple.user.getUsername() + " - " + tuple.message.getBody())
                .forEachOrdered(console::write);
    }

    void reading(String userName) {
        User user = userList.getUser(userName);
        List<Message> messages = user.getMessages();
        for (Message message : messages.stream().sorted(Comparator.comparing(Message::getId).reversed()).collect(Collectors.toList())) {
            console.write(userName + " - " + message.getBody());
        }
    }
}


