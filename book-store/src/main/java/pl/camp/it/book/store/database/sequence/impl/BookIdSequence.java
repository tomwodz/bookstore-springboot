package pl.camp.it.book.store.database.sequence.impl;

import org.springframework.stereotype.Component;
import pl.camp.it.book.store.database.sequence.IBookIdSequence;

@Component
public class BookIdSequence implements IBookIdSequence {

    private int id = 0;
    @Override
    public int getId(){
        return ++id;
    }
}
