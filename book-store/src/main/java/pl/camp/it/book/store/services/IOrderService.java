package pl.camp.it.book.store.services;

import pl.camp.it.book.store.model.Order;
import pl.camp.it.book.store.model.dto.SaveOrderRequest;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    List<Order> getAllOrdersForCurrentUser();

    void persistOrder(Order order);
   Order persistOrder(SaveOrderRequest saveOrderRequest);
    List<Order> getOrdersByUserId(int userId);
    Optional<Order> getOrderById(int id);
}
