package pl.camp.it.book.store.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.camp.it.book.store.model.Book;
import pl.camp.it.book.store.model.OrderPosition;
import pl.camp.it.book.store.services.ICartService;
import pl.camp.it.book.store.session.SessionData;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@WebAppConfiguration
public class CartServiceTest extends ServiceGenericTest{

    @Autowired
    ICartService cartService;

    @Autowired
    SessionData sessionData;

    @Test
    public void addProductToCart(){

        //given
        int bookId = 5;
        int expectedBookCount = 1;
        Book expectedBook = generateFakeBook();
        OrderPosition expectedOrderPosition = new OrderPosition();
        expectedOrderPosition.setBook(expectedBook);
        expectedOrderPosition.setQuantity(1);
        Mockito.when(this.bookDAO.getBookById(5)).thenReturn(Optional.of(generateFakeBook()));

        //when
        this.cartService.addProductToCart(bookId);

        //then
        Assertions.assertEquals(expectedBookCount, this.sessionData.getCart().getPositions().size());
        assertThat(this.sessionData.getCart().getPositions().contains(expectedOrderPosition));

    }

    private Book generateFakeBook(){
        return new Book(5, "Tytul", "Janusz Kowalski", 60, 10, "978-83-289-0174-2");
    }


}
