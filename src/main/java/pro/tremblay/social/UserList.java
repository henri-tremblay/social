package pro.tremblay.social;

import java.util.HashMap;
import java.util.Map;

public class UserList {
    private Map<String, User> registredUsers;

    public UserList() {
        registredUsers = new HashMap<>();
    }

    public User getUser(String userName) {
        User user;
        
        if (registredUsers.containsKey(userName)) {
            user = registredUsers.get(userName);
        } else {
            user = new User(userName);
            registredUsers.put(userName, user);
        }
        return user;
    }
}
