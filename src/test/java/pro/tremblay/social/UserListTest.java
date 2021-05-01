package pro.tremblay.social;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserListTest {

    private final UserList list = new UserList();

    @Test
    void getUser_addNewUser() {
        User user = list.getUser("Henri");
        assertThat(user).extracting(User::getUsername).isEqualTo("Henri");
    }

    @Test
    void getUser_addExistingUser() {
        User expected = list.getUser("Henri");
        User actual = list.getUser("Henri");
        assertThat(actual).isSameAs(expected);
    }
}