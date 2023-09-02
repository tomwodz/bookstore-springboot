package pl.camp.it.book.store.services;

import pl.camp.it.book.store.model.OrderPosition;

import java.util.List;
import java.util.Optional;

public interface IOrderPositionService {
    List<OrderPosition> getOrderById(int orderId);
    Optional<OrderPosition> getById(int id);

}
