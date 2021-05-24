package pro.tremblay.social;

public class Message {

    private static int counterGlobal = 0;

    private final String body;
    private final int id;

    public Message(String body){
        this.body = body;
        this.id = counterGlobal++;
    }

    public String body() {
        return body;
    }

    public int id() {
        return id;
    }
}
