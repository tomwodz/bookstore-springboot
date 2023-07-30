package pl.camp.it.book.store.database;

import pl.camp.it.book.store.model.User;

import java.util.Optional;

public interface IUserRepository {

    Optional<User> getByLogin(String login);

    void persistUser(User user);

    Optional<User> getById(int id);

}
