package pl.camp.it.book.store.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderPositionTest {

    @Test
    public void incrementOrderPositionQuantityTest() {

        //given
        OrderPosition orderPosition = new OrderPosition(1, new Book(), 10);
        int expectedQuantity = 11;

        //when
        orderPosition.incrementQuantity();

        //then
        assertEquals(expectedQuantity, orderPosition.getQuantity());

    }

}