package pl.camp.it.book.store.database.memory;

import org.springframework.stereotype.Repository;
import pl.camp.it.book.store.database.IBookDAO;
import pl.camp.it.book.store.model.Book;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDAO implements IBookDAO {

    private final List<Book> books = new ArrayList<>();

    private int lastId = 4;

    public BookDAO() {
        books.add(new Book(1,"Java. Rusz głową! Wydanie III", "Kathy Sierra, Bert Bates, Trisha Gee", 95.00, 10, "78-83-283-9985-3"));
        books.add(new Book(2,"Java. Efektywne programowanie. Wydanie III", "Joshua Bloch", 64, 20, "978-83-283-9896-2"));
        books.add(new Book(3,"Java. Rusz głową! Wydanie III", "Kathy Sierra, Bert Bates, Trisha Gee", 70.00, 30, "78-83-283-9985-3"));
        books.add(new Book(4,"Java. Rusz głową! Wydanie III", "Kathy Sierra, Bert Bates, Trisha Gee", 90.00, 40, "78-83-283-9985-3"));
    }

    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(this.books);
    }

    @Override
    public void persistBook(Book book) {
        book.setId(++this.lastId);
    this.books.add(book);
    }

    @Override
    public Book getBookById(int id) {
        for(Book book: this.books){
            if(book.getId() == id){
                return book;
            }
        }
        return null;
    }

    @Override
    public void deleteBook(int id) {
        for(Book book: this.books){
            if(book.getId() == id){
                this.books.remove(book);
                return;
            }
        }
    }
}
