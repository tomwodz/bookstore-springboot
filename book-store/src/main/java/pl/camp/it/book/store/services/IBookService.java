package pl.camp.it.book.store.services;
import pl.camp.it.book.store.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    List<Book> getAllBooks();
    List<Book> getByPattern(String pattern);

    void persistBook(Book book);

    Optional<Book> getBookById(int id);

    void updateBook(Book book);

    void deleteBook(int id);
}
