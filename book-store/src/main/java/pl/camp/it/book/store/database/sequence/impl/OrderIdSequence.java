package pl.camp.it.book.store.database.sequence.impl;

import org.springframework.stereotype.Component;
import pl.camp.it.book.store.database.sequence.IOrderIdSequence;

@Component
public class OrderIdSequence implements IOrderIdSequence {
    private int id = 0;
    @Override
    public int getId(){
        return ++id;
    }

}
