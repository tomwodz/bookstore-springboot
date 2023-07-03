package pl.camp.it.book.store.database.memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.book.store.database.IBookDAO;
import pl.camp.it.book.store.database.sequence.IBookIdSequence;
import pl.camp.it.book.store.model.Book;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDAO implements IBookDAO {

    @Autowired
    IBookIdSequence bookIdSequence;
    private final List<Book> books = new ArrayList<>();


    public BookDAO(@Autowired IBookIdSequence bookIdSequence) {
        books.add(new Book(bookIdSequence.getId(), "Java. Rusz głową! Wydanie III", "Kathy Sierra, Bert Bates, Trisha Gee", 95.00, 10, "978-83-283-9984-6"));
        books.add(new Book(bookIdSequence.getId(),"Java. Efektywne programowanie. Wydanie III", "Joshua Bloch", 64.80, 20, "978-83-283-9896-2"));
        books.add(new Book(bookIdSequence.getId(),"Java. Kompendium programisty. Wydanie XII", "Herbert Schildt",119.40, 30, "978-83-832-2156-4"));
        books.add(new Book(bookIdSequence.getId(),"Czysty kod. Podręcznik dobrego programisty", "Robert C. Martin", 47.90, 40, "978-83-832-2344-5"));
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
    public Optional<Book> getBookById(int id) {
        for(Book book: this.books){
            if(book.getId() == id){
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }

    @Override
    public void deleteBook(int id) {
        Iterator<Book> iterator = this.books.iterator();
        while(iterator.hasNext()){
            if(iterator.next().getId() == id){
                iterator.remove();
                return;
            }
        }
    }

    @Override
    public void updateBook(Book book) {
        Iterator<Book> iterator = this.books.iterator();
        while (iterator.hasNext()){
            if(iterator.next().getId() == book.getId()){
                iterator.remove();
                break;
            }
        }
        this.books.add(book);
    }
}
