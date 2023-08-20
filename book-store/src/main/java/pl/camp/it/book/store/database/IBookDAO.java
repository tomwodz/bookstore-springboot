package pl.camp.it.book.store.database;

import org.springframework.stereotype.Component;
import pl.camp.it.book.store.model.Book;

import java.util.List;
import java.util.Optional;

@Component
public interface IBookDAO {
    List<Book> getAllBooks();

    Optional<Book> persistBook(Book book);

    Optional<Book> getBookById(int id);

    boolean deleteBook(int id);

    Optional<Book> updateBook(Book book);

    List<Book> getByPattern(String pattern);
}
