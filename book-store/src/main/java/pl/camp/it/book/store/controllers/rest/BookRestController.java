package pl.camp.it.book.store.controllers.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.camp.it.book.store.model.Book;
import pl.camp.it.book.store.model.dto.BookListResponse;
import pl.camp.it.book.store.services.IBookService;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(path="/api/v1/book")
@AllArgsConstructor
public class BookRestController {

    private final IBookService bookService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Optional<Book> bookBox = this.bookService.getBookById(id);
        if (bookBox.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(bookBox.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(path = "/")
    public BookListResponse getBooks(@RequestParam(required = false) String pattern){
        if(pattern == null){
            return new BookListResponse(this.bookService.getAllBooks());
        }
        return new BookListResponse(this.bookService.getFilteredBooks(pattern));
    }

    @PostMapping(path = "/")
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        book.setId(0);
        Optional<Book> bookFromDb = this.bookService.persistBook(book);
        return bookFromDb.map(vaule -> ResponseEntity.status(CREATED).body(vaule))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PutMapping(path = "/")
    public ResponseEntity<Book> updateBook(@PathVariable int id,
                                           @RequestBody Book book){
        book.setId(id);
        if (this.bookService.getBookById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Optional<Book> bookFromDbBox = this.bookService.updateBook(book);
        return bookFromDbBox.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @DeleteMapping(path = "/")
    public void deleteBook(@PathVariable int id){
        this.bookService.deleteBook(id);
    }

}
