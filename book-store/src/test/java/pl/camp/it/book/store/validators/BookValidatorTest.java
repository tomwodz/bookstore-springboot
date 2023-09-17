package pl.camp.it.book.store.validators;

import org.junit.jupiter.api.Test;
import pl.camp.it.book.store.exception.BookValidationException;
import pl.camp.it.book.store.model.Book;

import static org.junit.jupiter.api.Assertions.*;

class BookValidatorTest {

    @Test
    public void correctTitleBookTest(){

        //given
        String title = "title";

        //when
        //then
        BookValidator.validateTitle(title);

    }

    @Test
    public void incorrectTitleBookTest(){

        //given
        String title = "";

        //when
        //then
        assertThrows(BookValidationException.class, () -> BookValidator.validateTitle(title));

    }

    @Test
    public void correctAuthorWithOneNameAndOneSurnameTest(){

        //given
        String author = "Janusz Kowalski";

        //when
        //then
        BookValidator.validateAuthor(author);

    }

    @Test
    public void incorrectAuthorWithWrongNameAndOneSurnameTest(){

        //given
        String author = "";

        //when
        //then
        assertThrows(BookValidationException.class, () -> BookValidator.validateAuthor(author));

    }

    @Test
    public void correctISBMTest(){

        //given
        String isbn = "978-83-289-0174-2";

        //when
        //then
        BookValidator.validateISBN(isbn);
    }

    @Test
    public void incorrectISBMTest(){

        //given
        String isbn = "777-83-289-0174-2";

        //when
        //then
        assertThrows(BookValidationException.class, () -> BookValidator.validateISBN(isbn));
    }

    @Test
    public void correctBookTest() {

        //given
        Book book = new Book(1, "Tytul", "Janusz Kowalski", 60, 10, "978-83-289-0174-2");

        //when
        //then
        BookValidator.validateBook(book);

    }

    @Test
    public void incorrectBookTest() {

        //given
        Book book = new Book(1, "", "Janusz Kowalski", 60, 10, "777-83-289-0174-2");

        //when
        //then
        assertThrows(BookValidationException.class, () -> BookValidator.validateBook(book));

    }

}