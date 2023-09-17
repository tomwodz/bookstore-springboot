package pl.camp.it.book.store.database;

import pl.camp.it.book.store.exception.LoginAlreadyExistException;
import pl.camp.it.book.store.model.User;

import java.util.Optional;

public class UserDAO implements IUserRepository{

    @Override
    public Optional<User> getByLogin(String login) {
        return Optional.empty();
    }

    @Override
    public void persistUser(User user) throws LoginAlreadyExistException {

    }

    @Override
    public Optional<User> getById(int id) {
        return Optional.empty();
    }
}
