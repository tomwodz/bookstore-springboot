package pl.camp.it.book.store.database.sequence;

import org.springframework.stereotype.Component;

@Component
public class OrderIdSequence implements IOrderIdSequence{
    private int id = 0;
    @Override
    public int getId(){
        return ++id;
    }

}
