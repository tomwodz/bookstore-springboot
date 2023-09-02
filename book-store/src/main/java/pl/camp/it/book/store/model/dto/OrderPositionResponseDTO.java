package pl.camp.it.book.store.model.dto;

import lombok.*;
import pl.camp.it.book.store.controllers.rest.RestConstants;
import pl.camp.it.book.store.model.OrderPosition;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderPositionResponseDTO {

    private int id;

    private String book;
    private int quantity;
    private String order;

    public OrderPositionResponseDTO(OrderPosition orderPosition) {
        this.id = orderPosition.getId();
        this.book = RestConstants.API_LOCATION+ "book/"+ orderPosition.getBook().getId();
        this.quantity = orderPosition.getQuantity();
        this.order = RestConstants.API_LOCATION + "order/" + orderPosition.getOrder().getId();
    }
}
