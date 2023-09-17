package pl.camp.it.book.store.database;

import pl.camp.it.book.store.model.Order;

import java.util.List;
import java.util.Optional;

public class OrderDAO implements  IOrderDAO{

    @Override
    public void persistOrder(Order order) {

    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        return null;
    }

    @Override
    public Optional<Order> getOrderById(int id) {
        return Optional.empty();
    }
}
