package pl.camp.it.book.store.model.dto;

import org.junit.jupiter.api.Test;
import pl.camp.it.book.store.controllers.rest.RestConstants;
import pl.camp.it.book.store.model.Book;
import pl.camp.it.book.store.model.Order;
import pl.camp.it.book.store.model.OrderPosition;

import static org.junit.jupiter.api.Assertions.*;

class OrderPositionResponseDTOTest {

    @Test
    public void orderPositionToOrderPositionDTOMappingTest() {

        //given
        OrderPosition orderPosition = new OrderPosition(10, new Book(1), 5, new Order(17));
        String rest = RestConstants.API_LOCATION;
        String expectedBook = rest + "book/1";
        String expectedOrder = rest + "order/17";

        //when
        OrderPositionResponseDTO actual = new OrderPositionResponseDTO(orderPosition);

        //then
        assertEquals(orderPosition.getId(), actual.getId());
        assertEquals(expectedOrder, actual.getOrder());
        assertEquals(expectedBook, actual.getBook());

    }

}