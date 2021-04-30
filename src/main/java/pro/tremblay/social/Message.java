package pro.tremblay.social;

import java.time.Instant;

public class Message {
    private String body;
    private final Instant timestamp;
    
    public Message(String body){
        this.body = body;
        this.timestamp = Instant.now();
    }

    public String getBody() {
        return body;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
