package pl.camp.it.book.store.database.memory;

import org.springframework.stereotype.Repository;
import pl.camp.it.book.store.database.IUserRepository;
import pl.camp.it.book.store.model.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAO implements IUserRepository {

    private  final List<User> useres = new ArrayList<>();

    public UserDAO() {
        this.useres.add(new User(1, "admin", "21232f297a57a5a743894a0e4a801fc3", "Janusz", "Krawczyk","adres@gmail.com"));
        this.useres.add(new User(2, "janusz", "21232f297a57a5a743894a0e4a801fc3", "Janusz", "Krawczyk","adres@gmail.com"));
    }

    @Override
    public User getByLogin(String login){
       for(User user: this.useres){
           if(user.getLogin().equals(login)){
               return user;
           }
       }
       return null;
    }
}
