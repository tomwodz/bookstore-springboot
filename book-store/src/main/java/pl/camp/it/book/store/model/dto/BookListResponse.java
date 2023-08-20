package pl.camp.it.book.store.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.camp.it.book.store.model.Book;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class BookListResponse {
    private final List<Book> bookList;
}
