package pl.camp.it.book.store.services.impl;

import jakarta.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.camp.it.book.store.exception.LoginAlreadyExistException;
import pl.camp.it.book.store.model.User;
import pl.camp.it.book.store.services.IAuthenticationService;
import pl.camp.it.book.store.session.SessionData;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@WebAppConfiguration
class AuthenticationServiceTest extends ServiceGenericTest{

    @Autowired
    IAuthenticationService authenticationService;

    @Resource
    SessionData sessionData;
    @Test
    public void correctAuthenticationTest(){

        //given
        String login = "janusz";
        String password = "janusz";
        User user = generateFakeUser();
        when(this.userRepository.getByLogin("janusz")).thenReturn(Optional.of(user));

        //when
        this.authenticationService.authenticate(login, password);

        //then
        assertNotNull(this.sessionData.getUser());
        assertNull(this.sessionData.getUser().getPassword());
        assertSame(user, this.sessionData.getUser());

        Mockito.verify(this.userRepository, Mockito.times(1)).getByLogin("janusz");

    }


    @Test
    public void incorrectPasswordAuthenticationTest(){

        //given
        String login = "janusz";
        String password = "złe hasło";
        User user = generateFakeUser();
        when(this.userRepository.getByLogin(Mockito.any())).thenReturn(Optional.of(user));

        //when
        this.authenticationService.authenticate(login, password);

        //then
        assertNull(this.sessionData.getUser());

        Mockito.verify(this.userRepository, Mockito.times(1)).getByLogin(Mockito.any());

    }

  @Test
  public void registerUserTest() throws LoginAlreadyExistException {

        //given
      User user = generateFakeUserToRegister();
      String expectedPassword = DigestUtils.md5Hex("janusz");

      //when
      this.authenticationService.register(user);

      final ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
      Mockito.verify(this.userRepository).persistUser(captor.capture());
      final User actual = captor.getValue();

      //then
      assertEquals(expectedPassword, actual.getPassword());
      assertEquals(user.getLogin(), actual.getLogin());

  }

    @Test
    public void loginAlreadyExist() throws LoginAlreadyExistException {

        //given

        User user = generateFakeUserToRegister();
        Mockito.doThrow(LoginAlreadyExistException.class).when(this.userRepository).persistUser(Mockito.any());

        //when
        //then
        assertThrows(LoginAlreadyExistException.class, () -> this.authenticationService.register(user));

    }




    private User generateFakeUser(){
        return new User(1, "janusz", DigestUtils.md5Hex("janusz"), "Janusz", "Kowalski","test@op.pl", User.Role.USER);
    }

    private User generateFakeUserToRegister(){
        return new User(1, "janusz", "janusz", "Janusz", "Kowalski","test@op.pl", User.Role.USER);
    }

}