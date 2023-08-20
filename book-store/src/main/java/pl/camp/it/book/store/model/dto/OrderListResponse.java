package pl.camp.it.book.store.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.camp.it.book.store.model.Order;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class OrderListResponse {
    private final List<Order> orderList;
}
