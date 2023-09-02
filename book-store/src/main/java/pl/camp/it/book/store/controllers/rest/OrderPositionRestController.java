package pl.camp.it.book.store.controllers.rest;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.camp.it.book.store.model.OrderPosition;
import pl.camp.it.book.store.model.dto.OrderPositionListResponse;
import pl.camp.it.book.store.model.dto.OrderPositionResponseDTO;
import pl.camp.it.book.store.services.IOrderPositionService;

import java.util.Optional;

@RestController
@RequestMapping(path="/api/v1/order-position")
@AllArgsConstructor
public class OrderPositionRestController {

    @Autowired
    IOrderPositionService orderPositionService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderPositionResponseDTO> getOrderPositionById(@PathVariable int id){
        Optional<OrderPosition> orderPositionBox = this.orderPositionService.getById(id);
        return orderPositionBox.map(op -> ResponseEntity.ok(new OrderPositionResponseDTO(op)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public OrderPositionListResponse getByOrderId(@RequestParam int orderId){
        return new OrderPositionListResponse(this.orderPositionService.getOrderById(orderId).stream()
                .map(OrderPositionResponseDTO::new)
                .toList());
    }

}
