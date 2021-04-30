package pro.tremblay.social;

import java.time.Instant;

public class Message {
    private String body;
    private Instant timestamp;
    
    public Message(String body){
        this.body = body;
        this.timestamp = Instant.now();
    }
}
