package pl.camp.it.book.store.database;

import pl.camp.it.book.store.model.OrderPosition;

import java.util.List;
import java.util.Optional;

public class OrderPositionDAO implements IOrderPositionDAO {

    @Override
    public void persist(OrderPosition orderPosition, int id) {

    }

    @Override
    public List<OrderPosition> getOrderId(int orderId) {
        return null;
    }

    @Override
    public Optional<OrderPosition> getById(int id) {
        return Optional.empty();
    }
}
