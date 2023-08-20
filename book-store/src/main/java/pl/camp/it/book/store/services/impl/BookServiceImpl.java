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
        return this.bookDAO.getByPattern(pattern);
    }

    @Override
    public  Optional<Book> persistBook(Book book) {
        return this.bookDAO.persistBook(book);
    }

    @Override
    public Optional<Book> getBookById(int id) {
        return this.bookDAO.getBookById(id);
    }

    @Override
    public Optional<Book> updateBook(Book book) {
        return this.bookDAO.updateBook(book);
    }

    @Override
    public void deleteBook(int id) {
        this.bookDAO.deleteBook(id);
    }
}
