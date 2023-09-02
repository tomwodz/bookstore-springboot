package pl.camp.it.book.store.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class OrderListResponse {
    private final List<OrderDTO> orderList;
}
