package pl.camp.it.book.store.database.sequence;

import org.springframework.stereotype.Component;

@Component
public class UserIdSequence implements IUserIdSequence{

    private int id;
    @Override
    public int getId() {
        return ++id;
    }
}
