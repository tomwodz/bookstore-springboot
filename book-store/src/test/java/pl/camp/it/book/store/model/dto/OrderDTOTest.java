package pl.camp.it.book.store.model.dto;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pl.camp.it.book.store.controllers.rest.RestConstants;
import pl.camp.it.book.store.model.Book;
import pl.camp.it.book.store.model.Order;
import pl.camp.it.book.store.model.OrderPosition;
import pl.camp.it.book.store.model.User;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OrderDTOTest {

    @Test
    public void orderToOrderDtoMappingTest() {

        //given
        LocalDateTime date = LocalDateTime.of(2023,9,17,10,00,00);
        Order order = new Order(1, new User(20), Order.Status.NEW, 50.0, date);
        order.getOrderPositions().add(new OrderPosition(1, new Book(), 200));
        order.getOrderPositions().add(new OrderPosition(2, new Book(), 200));
        order.getOrderPositions().add(new OrderPosition(3, new Book(), 200));
        String rest = RestConstants.API_LOCATION;
        String expectedUser = rest + "user/20";
        Set<String> expectedOrderPosition = Set.of(
                rest + "order-position/1",
                rest + "order-position/2",
                rest +  "order-position/3");

        //when
        OrderDTO actual = new OrderDTO(order);

        //then
        assertEquals(order.getId(), actual.getId());
        assertEquals(expectedUser, actual.getUser());
        assertEquals(expectedOrderPosition, actual.getOrderPositions());
        assertEquals(Order.Status.NEW, actual.getStatus());


    }

}