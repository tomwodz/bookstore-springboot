package pl.camp.it.book.store.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.camp.it.book.store.model.Order;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaveOrderRequest {
    private int userId;
    private final Set<OrderPositionDto> orderPositions = new HashSet<>();
    private Order.Status status;
    private double total;
    private LocalDateTime dateTime;
}
