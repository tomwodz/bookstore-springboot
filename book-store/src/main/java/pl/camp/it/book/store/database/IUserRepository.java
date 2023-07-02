package pl.camp.it.book.store.database;

import pl.camp.it.book.store.model.User;

public interface IUserRepository {

    User getByLogin(String login);

    void persistUser(User user);

}
