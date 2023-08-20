package pl.camp.it.book.store.controllers.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.camp.it.book.store.database.IBookDAO;
import pl.camp.it.book.store.exception.BookNotExistException;
import pl.camp.it.book.store.exception.UserNotExistException;
import pl.camp.it.book.store.model.Order;
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
    public ResponseEntity<Order> getOrderById(@PathVariable int id) {
        Optional<Order> orderBox = this.orderService.getOrderById(id);
        return orderBox.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping(path = "")
    public OrderListResponse getOrdersByUserId(@RequestParam int userId) {
        return new OrderListResponse(this.orderService.getOrdersByUserId(userId));
    }

    @PostMapping(path = "/")
    public ResponseEntity<Order> saveOrder(@RequestBody SaveOrderRequest orderDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(this.orderService.persistOrder(orderDto));
        } catch (UserNotExistException | BookNotExistException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
