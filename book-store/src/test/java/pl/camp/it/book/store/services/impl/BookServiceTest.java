package pl.camp.it.book.store.services.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.camp.it.book.store.configuration.TestConfiguration;
import pl.camp.it.book.store.database.IBookDAO;
import pl.camp.it.book.store.database.IOrderDAO;
import pl.camp.it.book.store.database.IOrderPositionDAO;
import pl.camp.it.book.store.database.IUserRepository;
import pl.camp.it.book.store.model.Book;
import pl.camp.it.book.store.services.IBookService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class BookServiceTest {

    @Autowired
    IBookService bookService;

    @MockBean
    IBookDAO bookDAO;

    @MockBean
    IUserRepository userRepository;

    @MockBean
    IOrderDAO orderDAO;

    @MockBean
    IOrderPositionDAO orderPositionDAO;

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