package pl.camp.it.book.store.services;

import org.springframework.stereotype.Component;
import pl.camp.it.book.store.model.Book;

import java.util.List;

@Component
public interface IBookService {
    List<Book> getAllBooks();
    List<Book> getFilteredBooks(String pattern);
}
