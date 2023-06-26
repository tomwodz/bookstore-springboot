package pl.camp.it.book.store.database;

import org.springframework.beans.factory.annotation.Autowired;
import pl.camp.it.book.store.model.User;

public interface IUserRepository {

    User getByLogin(String login);

}
