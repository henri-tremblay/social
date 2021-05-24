package pro.tremblay.social;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MessageTest {

    private final Message message = new Message("Test 1");

    @Test
    void getBody() {
        assertThat(message.body()).isEqualTo("Test 1");
    }

    @Test
    void getId() {
        Message otherMessage = new Message("Other");
        assertThat(otherMessage.id()).isGreaterThan(message.id());
    }
}