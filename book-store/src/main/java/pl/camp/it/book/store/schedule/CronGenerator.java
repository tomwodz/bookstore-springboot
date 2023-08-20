package pl.camp.it.book.store.schedule;


import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.camp.it.book.store.model.Book;
import pl.camp.it.book.store.services.IBookService;

import java.util.List;

@Component
@AllArgsConstructor
public class CronGenerator {

    private final IBookService bookService;

    @Scheduled(cron = "0    0    *    *    *    *")
    public void generateAutoAddBooks() {
        List<Book> listBooksToAutoUpdate = this.bookService.getAllBooks();
        listBooksToAutoUpdate.stream()
                .peek(b -> b.setQuantity(b.getQuantity() + 1))
                .forEach(b -> this
                        .bookService.updateBook(b));
    }
}
