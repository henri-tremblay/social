package pro.tremblay.social;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    private final User user = new User("Henri");

    @Test
    public void empty() {
        assertThat(user.getUsername()).isEqualTo("Henri");
        assertThat(user.getFollowed()).isEmpty();
        assertThat(user.getMessages()).isEmpty();
    }

    @Test
    public void addFollower() {
        user.addFollowed(new User("Mikaël"));
        user.addFollowed(new User("Ivraj"));
        assertThat(user.getFollowed()).map(User::getUsername).containsOnly("Ivraj", "Mikaël");
    }

    @Test
    public void addMessage() {
        user.addMessage("Message 1");
        user.addMessage("Message 2");
        assertThat(user.getMessages()).map(Message::body).containsExactly("Message 1", "Message 2");
    }

}
