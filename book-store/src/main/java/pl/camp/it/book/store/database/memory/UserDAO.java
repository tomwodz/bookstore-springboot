package pl.camp.it.book.store.database.memory;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.book.store.database.IUserRepository;
import pl.camp.it.book.store.database.sequence.IUserIdSequence;
import pl.camp.it.book.store.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserDAO implements IUserRepository {

    @Autowired
    IUserIdSequence userIdSequence;
    private  final List<User> useres = new ArrayList<>();


    public UserDAO(@Autowired IUserIdSequence userIdSequence) {
        this.useres.add(new User(userIdSequence.getId(), "admin", "21232f297a57a5a743894a0e4a801fc3", "Tomasz", "Wodz","twodzinski@op.pl", User.Role.ADMIN));
        this.useres.add(new User(userIdSequence.getId(), "tomasz", "21232f297a57a5a743894a0e4a801fc3", "Tomasz", "Wodz","twodzisnki@op.pl", User.Role.USER));
        this.userIdSequence = userIdSequence;
    }

    @Override
    public Optional<User> getByLogin(final String login) {
        return this.useres.stream()
                .filter(u -> u.getLogin()
                .equals(login))
                .findFirst()
                .map(User::copOf);
    }

    @Override
    public void persistUser(User user) {
        user.setId(userIdSequence.getId());
        this.useres.add(user);
    }

    @Override
    public Optional<User> getById(int id) {
        return this.useres.stream()
                .filter(u -> u.getId() == id)
                .findFirst();
    }
}
