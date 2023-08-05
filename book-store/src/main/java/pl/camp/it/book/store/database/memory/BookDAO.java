package pl.camp.it.book.store.database.memory;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.book.store.database.IBookDAO;
import pl.camp.it.book.store.database.sequence.IBookIdSequence;
import pl.camp.it.book.store.model.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


public class BookDAO implements IBookDAO {

    @Autowired
    IBookIdSequence bookIdSequence;
    private final List<Book> books = new ArrayList<>();


    public BookDAO(@Autowired IBookIdSequence bookIdSequence) {
        books.add(new Book(bookIdSequence.getId(), "Java. Rusz głową! Wydanie III", "Kathy Sierra, Bert Bates, Trisha Gee", 95.00, 10, "978-83-283-9984-6"));
        books.add(new Book(bookIdSequence.getId(), "Java. Efektywne programowanie. Wydanie III", "Joshua Bloch", 64.80, 20, "978-83-283-9896-2"));
        books.add(new Book(bookIdSequence.getId(), "Java. Kompendium programisty. Wydanie XII", "Herbert Schildt", 119.40, 30, "978-83-832-2156-4"));
        books.add(new Book(bookIdSequence.getId(), "Czysty kod. Podręcznik dobrego programisty", "Robert C. Martin", 47.90, 40, "978-83-832-2344-5"));
        this.bookIdSequence = bookIdSequence;
    }

    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(this.books);
    }

    @Override
    public void persistBook(Book book) {
        book.setId(bookIdSequence.getId());
        this.books.add(book);
    }

    @Override
    public Optional<Book> getBookById(final int id) {
        return this.books.stream().filter(b -> b.getId() == id).findFirst();
    }

    @Override
    public boolean deleteBook(final int id) {
        Optional<Book> bookBox = this.books.stream().filter(b -> b.getId() == id).findFirst();
        if (bookBox.isPresent()) {
            this.books.remove(bookBox.get());
            return true;
        }
        return false;
    }

    @Override
    public void updateBook(Book book) {
        Optional<Book> bookBox = this.books.stream()
                .filter(b -> b.getId() == book.getId())
                .findFirst();
        if (bookBox.isPresent()) {
            this.books.remove(bookBox.get());
            this.books.add((book));
        }
    }

    @Override
    public List<Book> getByPattern(String pattern) {
        throw new RuntimeException();
    }
}
