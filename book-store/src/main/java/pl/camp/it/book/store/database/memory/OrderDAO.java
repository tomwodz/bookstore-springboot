package pl.camp.it.book.store.database.memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.book.store.database.IOrderDAO;
import pl.camp.it.book.store.database.sequence.IOrderIdSequence;
import pl.camp.it.book.store.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderDAO implements IOrderDAO {

    @Autowired
    IOrderIdSequence orderIdSequence;
    private final List<Order> orders = new ArrayList<>();

    @Override
    public void persistOrder(Order order) {
        order.setId(this.orderIdSequence.getId());
        this.orders.add(order);
    }

    @Override
    public List<Order> getOrdersByUserId(final int userId) {
        return this.orders.stream().filter(o -> o.getUser().getId() == userId).toList();
    }

    @Override
    public Optional<Order> getOrderById(final int id) {
        return this.orders.stream().filter(o -> o.getId() == id).findFirst();
    }
}
