package pro.tremblay.social;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
    private String username;
    private Set<User> followers;
    private List<Message> messages;

    public User(String username){
        this.followers = new HashSet<>();
        this.messages = new ArrayList<>();
        this.username = username;
    }

    public void addFollower(User user) {
        followers.add(user);
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public String getUsername() {
        return username;
    }
    
    public Message addMessage(String body) {
        Message message = new Message(body);
        messages.add(message);
        return message;
    }

    public List<Message> getMessages() {
        return messages;
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
