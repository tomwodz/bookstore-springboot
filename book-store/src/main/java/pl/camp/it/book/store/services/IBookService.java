package pl.camp.it.book.store.services;
import pl.camp.it.book.store.model.Book;

import java.util.List;

public interface IBookService {
    List<Book> getAllBooks();
    List<Book> getFilteredBooks(String pattern);

    void persistBook(Book book);

    Book getBookById(int id);

    void updateBook(Book book);

    void deleteBook(int id);
}
