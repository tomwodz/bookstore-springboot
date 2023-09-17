package pl.camp.it.book.store.database;

import pl.camp.it.book.store.model.Book;

import java.util.List;
import java.util.Optional;

public class BookDAO implements IBookDAO {

    @Override
    public List<Book> getAllBooks() {
        List<Book> result = List.of(
        new Book(1, "Java. Rusz głową! Wydanie III", "Kathy Sierra, Bert Bates, Trisha Gee", 95.00, 10, "978-83-283-9984-6"),
        new Book(2, "Java. Efektywne programowanie. Wydanie III", "Joshua Bloch", 64.80, 20, "978-83-283-9896-2"),
        new Book(3, "Java. Kompendium programisty. Wydanie XII", "Herbert Schildt", 119.40, 30, "978-83-832-2156-4"));
        return result;
    }

    @Override
    public Optional<Book> persistBook(Book book) {
        return Optional.empty();
    }

    @Override
    public Optional<Book> getBookById(int id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteBook(int id) {
        return false;
    }

    @Override
    public Optional<Book> updateBook(Book book) {
        return Optional.empty();
    }

    @Override
    public List<Book> getByPattern(String pattern) {
        return null;
    }
}
