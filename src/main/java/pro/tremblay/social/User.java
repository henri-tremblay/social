package pro.tremblay.social;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class User {
    private final String username;
    private final Set<User> followers;
    private final List<Message> messages;

    public User(String username){
        this.followers = new HashSet<>();
        this.messages = new ArrayList<>();
        this.username = Objects.requireNonNull(username);
    }

    public void addFollower(User user) {
        followers.add(user);
    }

    public Set<User> getFollowers() {
        return Collections.unmodifiableSet(followers);
    }

    public String getUsername() {
        return username;
    }
    
    public void addMessage(String body) {
        Message message = new Message(body);
        messages.add(message);
    }

    public List<Message> getMessages() {
        return Collections.unmodifiableList(messages);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}
