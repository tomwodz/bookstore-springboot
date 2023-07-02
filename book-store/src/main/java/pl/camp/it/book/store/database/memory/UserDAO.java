package pl.camp.it.book.store.database.memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.book.store.database.IUserRepository;
import pl.camp.it.book.store.database.sequence.IUserIdSequence;
import pl.camp.it.book.store.model.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAO implements IUserRepository {

    @Autowired
    IUserIdSequence userIdSequence;
    private  final List<User> useres = new ArrayList<>();


    public UserDAO(@Autowired IUserIdSequence userIdSequence) {
        this.useres.add(new User(userIdSequence.getId(), "admin", "21232f297a57a5a743894a0e4a801fc3", "Tomasz", "Wodz","twodzinski@op.pl", User.Role.ADMIN));
        this.useres.add(new User(userIdSequence.getId(), "tomasz", "2df8ce7d317c7d89dfa95be7695d2de0", "Tomasz", "Wodz","twodzisnki@op.pl", User.Role.USER));
        this.userIdSequence = userIdSequence;
    }

    @Override
    public User getByLogin(String login){
       for(User user: this.useres){
           if(user.getLogin().equals(login)){
               return User.copOf(user);
           }
       }
       return null;
    }

    @Override
    public void persistUser(User user) {
        user.setId(userIdSequence.getId());
        this.useres.add(user);
    }
}
