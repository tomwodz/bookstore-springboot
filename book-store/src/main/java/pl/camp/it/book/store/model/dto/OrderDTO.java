package pl.camp.it.book.store.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.camp.it.book.store.controllers.rest.RestConstants;
import pl.camp.it.book.store.model.Order;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {

    private int id;
    private String user;
    private final Set<String> orderPositions = new HashSet<>();
    private Order.Status status;
    private double total;
    private LocalDateTime dateTime;

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.user = RestConstants.API_LOCATION+"user/"+order.getUser().getId();
        order.getOrderPositions().forEach(op -> this.orderPositions.add(RestConstants.API_LOCATION + "order-position/" + op.getId()));
        this.status = order.getStatus();
        this.total = order.getTotal();
        this.dateTime = order.getDateTime();
    }
}
