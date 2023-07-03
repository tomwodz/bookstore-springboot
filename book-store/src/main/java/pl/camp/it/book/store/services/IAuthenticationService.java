package pl.camp.it.book.store.services;

import jakarta.servlet.http.HttpServletRequest;
import pl.camp.it.book.store.exception.LoginAlreadyExistException;
import pl.camp.it.book.store.model.User;

public interface IAuthenticationService {
    void authenticate(String login, String password);

    void logout(HttpServletRequest request);

    void register(User user) throws LoginAlreadyExistException;

}
