package pro.tremblay.social;

class UserMessage {
    private final User user;
    private final Message message;

    public UserMessage(User user, Message message) {
        this.user = user;
        this.message = message;
    }

    public int messageId() {
        return message.id();
    }

    public User user() {
        return user;
    }

    public Message message() {
        return message;
    }
}
