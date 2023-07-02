package pl.camp.it.book.store.database;

import pl.camp.it.book.store.model.Order;

import java.util.List;

public interface IOrderDAO {
    void persistOrder(Order order);
    List<Order> getOrdersByUserId(int userId);
    Order getOrderById(int id);
}
