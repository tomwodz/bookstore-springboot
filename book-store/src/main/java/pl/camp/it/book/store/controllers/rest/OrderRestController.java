package pl.camp.it.book.store.controllers.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.camp.it.book.store.database.IBookDAO;
import pl.camp.it.book.store.exception.BookNotExistException;
import pl.camp.it.book.store.exception.UserNotExistException;
import pl.camp.it.book.store.model.Order;
import pl.camp.it.book.store.model.dto.OrderDTO;
import pl.camp.it.book.store.model.dto.OrderListResponse;
import pl.camp.it.book.store.model.dto.SaveOrderRequest;
import pl.camp.it.book.store.services.IOrderService;
import pl.camp.it.book.store.services.IUserService;

import java.util.Optional;

@RestController
@RequestMapping(path="/api/v1/order")
@AllArgsConstructor
public class OrderRestController {

    private final IOrderService orderService;
    private final IUserService userService;
    private final IBookDAO bookDAO;

    @GetMapping(path = "/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable int id) {
        Optional<Order> orderBox = this.orderService.getOrderById(id);
        return orderBox.map(order -> ResponseEntity.ok(new OrderDTO(order)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(path = "")
    public OrderListResponse getOrdersByUserId(@RequestParam int userId) {
        return new OrderListResponse(
                this.orderService.getOrdersByUserId(userId).stream()
                .map(o -> new OrderDTO(o))
                .toList());
    }

    @PostMapping(path = "/")
    public ResponseEntity<OrderDTO> saveOrder(@RequestBody SaveOrderRequest orderDto) {
        try {
            return ResponseEntity.ok(new OrderDTO(this.orderService.persistOrder(orderDto)));
        } catch (UserNotExistException | BookNotExistException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
