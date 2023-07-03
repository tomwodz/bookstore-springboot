package pl.camp.it.book.store.database;

import org.springframework.stereotype.Component;
import pl.camp.it.book.store.model.Book;

import java.util.List;
import java.util.Optional;

@Component
public interface IBookDAO {
    List<Book> getAllBooks();

    void persistBook(Book book);

    Optional<Book> getBookById(int id);

    void deleteBook(int id);

    void  updateBook(Book book);
}
