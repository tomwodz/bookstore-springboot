package pl.camp.it.book.store.validators;

import org.junit.jupiter.api.Test;
import pl.camp.it.book.store.exception.UserValidationException;
import pl.camp.it.book.store.model.User;

import static org.junit.jupiter.api.Assertions.*;

class UserValidatorTest {

    @Test
    public void notUpperCaseFirstLetterNameTest() {

        //given
        String name = "janusz";

        //when
        //then
        assertThrows(UserValidationException.class, () -> UserValidator.validateName(name));

    }

    @Test
    public void correctNameTest (){

        //given
        String name = "Janusz";

        //when
        //then
        UserValidator.validateName(name);

    }

    @Test
    public void correctSurnameTest() {

        //given
        String surname = "Kowlaski";

        //when
        //then
        UserValidator.validateName(surname);

    }

    @Test
    public void surnameWithNumberTest() {

        //given
        String surname = "Kowlaskie123";

        //when
        //then
        assertThrows(UserValidationException.class, () -> UserValidator.validateSurname(surname));

    }

    @Test
    public void correctLoginTest() {

        //given
        String login = "login";

        //when
        //then
        UserValidator.validateLogin(login);

    }

    @Test
    public void tooShrotLoginTest() {

        //given
        String login = "lo";

        //when
        //then
        assertThrows(UserValidationException.class, () -> UserValidator.validateLogin(login));

    }

    @Test
    public void correctPasswordTest() {

        //given
        String password = "password";

        //when
        //then
        UserValidator.validateLogin(password);

    }

    @Test
    public void tooShortPasswordTest() {

        //given
        String password = "pa";

        //when
        //then
        assertThrows(UserValidationException.class, () -> UserValidator.validatePassword(password));

    }

    @Test
    public void correctEmailTest() {

        //given
        String login = "test@op.pl";

        //when
        //then
        UserValidator.validateLogin(login);

    }

    @Test
    public void incorrectEmailTest() {

        //given
        String email = "test.op.pl";

        //when
        //then
        assertThrows(UserValidationException.class, () -> UserValidator.validateEmail(email));
    }

    @Test
    public void passwordEqualTest() {

        //given
        String pass1 = "janusz123";
        String pass2 = "janusz123";

        //when
        UserValidator.validatePasswordsEquality(pass1, pass2);

    }

    @Test
    public void correctUserTest() {

        //given
        User user = new User(1, "janusz", "janusz", "Janusz", "Kowalski","janusz@op.pl", User.Role.USER);

        //when
        //then
        UserValidator.validateUser(user);

    }

    @Test
    public void incorrectUserTest() {

        //given
        User user = new User(1, "janusz", "janusz", "Janusz", "Kowalski","janusz.op.pl", User.Role.USER);

        //when
        //then
        assertThrows(UserValidationException.class, () -> UserValidator.validateUser(user));
    }



}