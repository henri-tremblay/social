package pro.tremblay.social;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private List<User> followers;
    private List<Message> messages;
    
    public User(String username){
        this.followers = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.username = username;
    }

    public List<User> getFollowers() {
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
}
