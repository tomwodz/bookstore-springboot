package pl.camp.it.book.store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.book.store.database.IBookDAO;
import pl.camp.it.book.store.model.Book;
import pl.camp.it.book.store.services.IBookService;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    IBookDAO bookDAO;

    @Override
    public List<Book> getAllBooks() {
        return this.bookDAO.getAllBooks();
    }

    @Override
    public List<Book> getFilteredBooks(String pattern) {
        List<Book> books = this.bookDAO.getAllBooks();
        return books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(pattern.toLowerCase())
                        || b.getAuthor().toLowerCase().contains(pattern.toLowerCase()))
                .toList();
    }

    @Override
    public void persistBook(Book book) {
        this.bookDAO.persistBook(book);
    }

    @Override
    public Optional<Book> getBookById(int id) {
        return this.bookDAO.getBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        this.bookDAO.updateBook(book);
    }

    @Override
    public void deleteBook(int id) {
        this.bookDAO.deleteBook(id);
    }
}
