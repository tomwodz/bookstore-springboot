package pl.camp.it.book.store.database.sequence.impl;

import org.springframework.stereotype.Component;
import pl.camp.it.book.store.database.sequence.IUserIdSequence;


public class UserIdSequence implements IUserIdSequence {

    private int id;
    @Override
    public int getId() {
        return ++id;
    }
}
