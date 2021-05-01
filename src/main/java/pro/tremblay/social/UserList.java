package pro.tremblay.social;

import java.util.HashMap;
import java.util.Map;

public class UserList {
    private final Map<String, User> registeredUsers = new HashMap<>();

    public User getUser(String userName) {
        return registeredUsers.computeIfAbsent(userName, User::new);
    }
}
