package pl.camp.it.book.store.database.memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.book.store.database.IOrderDAO;
import pl.camp.it.book.store.database.sequence.IOrderIdSequence;
import pl.camp.it.book.store.model.Order;

import java.util.ArrayList;
import java.util.List;

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
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> result = new ArrayList<>();
        for (Order order : this.orders) {
            if (order.getUser().getId() == userId) {
                result.add(order);
            }
        }
        return result;
    }

    @Override
    public Order getOrderById(int id) {
        for (Order order : this.orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }
}
