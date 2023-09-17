package pl.camp.it.book.store.services.impl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import pl.camp.it.book.store.model.Book;
import pl.camp.it.book.store.services.IBookService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


class BookServiceTest extends ServiceGenericTest {

    @Autowired
    IBookService bookService;


    @Test
    public void getAllBooksTest() {

        //given
        int expectedListSize = 3;
        Mockito.when(this.bookDAO.getAllBooks()).thenReturn(generateFakeBooks());
        List<Integer> expectedBookIds = List.of(1,2,3);

        //when
        List<Book> actual = bookService.getAllBooks();


        //then
        assertThat(expectedListSize).isEqualTo(actual.size());
        List<Integer> ids = actual.stream().map(b -> b.getId()).toList();
        assertTrue(ids.containsAll(expectedBookIds));
        Mockito.verify(this.bookDAO, Mockito.timeout(1)).getAllBooks();

    }

    private List<Book> generateFakeBooks(){
        return List.of(
                new Book(1, "Java. Rusz głową! Wydanie III", "Kathy Sierra, Bert Bates, Trisha Gee", 95.00, 10, "978-83-283-9984-6"),
                new Book(2, "Java. Efektywne programowanie. Wydanie III", "Joshua Bloch", 64.80, 20, "978-83-283-9896-2"),
                new Book(3, "Java. Kompendium programisty. Wydanie XII", "Herbert Schildt", 119.40, 30, "978-83-832-2156-4"));
    }

}