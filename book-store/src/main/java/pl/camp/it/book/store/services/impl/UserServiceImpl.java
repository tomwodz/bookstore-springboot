package pl.camp.it.book.store.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.camp.it.book.store.database.IUserRepository;
import pl.camp.it.book.store.exception.LoginAlreadyExistException;
import pl.camp.it.book.store.model.User;
import pl.camp.it.book.store.services.IUserService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;

    @Override
    public Optional<User> getByLogin(String login) {
        return this.userRepository.getByLogin(login);
    }

    @Override
    public void persistUser(User user) throws LoginAlreadyExistException {
        this.userRepository.persistUser(user);
    }

    @Override
    public Optional<User> getById(int id) {
        return this.userRepository.getById(id);
    }
}
