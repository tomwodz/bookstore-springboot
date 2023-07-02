package pl.camp.it.book.store.services;

import pl.camp.it.book.store.model.Order;

import java.util.List;

public interface IOrderService {
    List<Order> getAllOrdersForCurrentUser();
}
