package pl.camp.it.book.store.services;

import pl.camp.it.book.store.exception.LoginAlreadyExistException;
import pl.camp.it.book.store.model.User;

import java.util.Optional;

public interface IUserService {

    Optional<User> getByLogin(String login);

    void persistUser(User user) throws LoginAlreadyExistException;

    Optional<User> getById(int id);

}
