package pl.camp.it.book.store.database;

import pl.camp.it.book.store.model.OrderPosition;

import java.util.List;
import java.util.Optional;

public interface IOrderPositionDAO {
    void persist(OrderPosition orderPosition, int id);
    List<OrderPosition> getOrderId (int orderId);
    Optional<OrderPosition> getById(int id);

}
