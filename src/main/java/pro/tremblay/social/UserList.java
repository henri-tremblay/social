package pro.tremblay.social;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserList {
    private Map<String, User> registredUsers;

    public UserList() {
        registredUsers = new HashMap<>();
    }

    public User getUser(String userName) {
        return registredUsers.getOrDefault(userName, new User(userName));
    }
}
