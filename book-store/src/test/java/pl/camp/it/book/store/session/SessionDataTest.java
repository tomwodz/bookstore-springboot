package pl.camp.it.book.store.session;

import org.junit.jupiter.api.Test;
import pl.camp.it.book.store.model.User;

import static org.junit.jupiter.api.Assertions.*;

class SessionDataTest {

    @Test
    public void isUserNotLoggedTest(){

        //given
        SessionData sessionData = new SessionData();
        sessionData.setUser(null);

        //when
        boolean actual = sessionData.isLogged();

        //then
        assertFalse(actual);
    }

    @Test
    public void isUserNullByDefaultTest(){

        //given
        SessionData sessionData = new SessionData();

        //when
        User actual = sessionData.getUser();

        //then
        assertNull(actual);
    }

    @Test
    public void isUserLoggedTest(){

        //given
        SessionData sessionData = new SessionData();
        sessionData.setUser(new User());

        //when
        boolean actual = sessionData.isLogged();

        //then
        assertTrue(actual);
    }

    @Test
    public void isNotLoggedUserAdminIfNullTest(){

        //given
        SessionData sessionData = new SessionData();
        sessionData.setUser(null);

        //when
        //then
        assertFalse(sessionData.isAdmin());
    }

    @Test
    public void isLoggedUserAdminTest(){

        //given
        SessionData sessionData = new SessionData();
        User user = new User();
        user.setRole(User.Role.ADMIN);
        sessionData.setUser(user);

        //when
        //then
        assertTrue(sessionData.isAdmin());
    }

    @Test
    public void isLoggedUserNotAdminTest(){

        //given
        SessionData sessionData = new SessionData();
        User user = new User();
        user.setRole(User.Role.USER);
        sessionData.setUser(user);

        //when
        //then
        assertFalse(sessionData.isAdmin());


    }

    @Test
    public void getInfoIfNotNullTest(){

        //given
        SessionData sessionData = new SessionData();
        sessionData.setInfo("Test");
        String expected = "Test";
        String expected2 = "";

        //when
        String actual = sessionData.getInfo();
        String actual2 = sessionData.getInfo();

        //then
        assertEquals(expected, actual);
        assertEquals(expected2, actual2);

    }

    @Test
    public void getInfoIfNullTest(){

        //given
        SessionData sessionData = new SessionData();
        sessionData.setInfo(null);
        String expected = "";
        String expected2 = "";

        //when
        String actual = sessionData.getInfo();
        String actual2 = sessionData.getInfo();

        //then
        assertEquals(expected, actual);
        assertEquals(expected2, actual2);

    }

}