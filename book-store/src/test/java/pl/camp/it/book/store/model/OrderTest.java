package pl.camp.it.book.store.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    public void getPrettyDateTest(){

        //given
        LocalDateTime date = LocalDateTime.of(2023,9,17,10,00,00);
        Order order = new Order(1, new User(), Order.Status.NEW, 200, date);
        String expected = "2023.09.17 10:00:00";

        //when
        String actual = order.getPrettyTime();

        //then
        assertEquals(expected,actual);
    }

}