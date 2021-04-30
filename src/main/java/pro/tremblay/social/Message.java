package pro.tremblay.social;

public class Message {
    private String body;
    private final int id;
    private static int counterGlobal;
    
    public Message(String body){
        this.body = body;
        this.id = counterGlobal++;
    }

    public String getBody() {
        return body;
    }


    public int getId() {
        return id;
    }
}
