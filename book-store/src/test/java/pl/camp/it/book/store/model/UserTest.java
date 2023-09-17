package pl.camp.it.book.store.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    public void copyUserTest() {

        //given
        User user = new User(1, "sadadssda", "sasadsdaasd", "asdasdsda", "dassadasd","fdssdfds", User.Role.USER);

        //when
        User copiedUser = User.copOf(user);

        //then
        assertNotSame(copiedUser, user);
        assertEquals(user.getId(), copiedUser.getId());
        assertEquals(user.getName(), copiedUser.getName());
        assertEquals(user.getSurname(), copiedUser.getSurname());
        assertEquals(user.getLogin(), copiedUser.getLogin());
        assertEquals(user.getEmail(), copiedUser.getEmail());
        assertEquals(user.getPassword(), copiedUser.getPassword());
    }

}