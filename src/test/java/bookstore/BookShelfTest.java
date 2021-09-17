package bookstore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("BookShelf")
public class BookShelfTest {

    private BookShelf shelf;
    private Book book1;
    private Book book2;
    private Book book3;


    @BeforeEach
    void init() {
        shelf = new BookShelf();
        book1 = new Book(
                "Zizi Dane",
                "Marie-Eve Bergeron",
                LocalDate.of(2021, Month.APRIL, 15));

        book2 = new Book(
                "Big Bang",
                "Patrick Cote",
                LocalDate.of(2021, Month.JANUARY, 5));

        book3 = new Book(
                "Amour",
                "Voltaire",
                LocalDate.of(2021, Month.MARCH, 9));
    }

    @Test
    @DisplayName("should be empty when no book is added")
    public void shelf_is_empty_when_no_book_added() throws Exception {
        List<Book> books = shelf.books();
        assertTrue(
                books.isEmpty(),
                () -> "BookShelf is empty.");
    }

    @Test
    @DisplayName("should contain number books when number books added")
    public void book_shelf_contains_number_of_books_added() {
        shelf.add(book1);
        shelf.add(book2);
        List<Book> books = shelf.books();
        assertEquals(
                2,
                books.size(),
                () -> "BookShelf should have 2 books."
        );
    }

    @Test
    @DisplayName("should be empty when called without books")
    public void book_shelf_empty_when_called_without_books() {
        shelf.add();
        List<Book> books = shelf.books();
        assertTrue(books.isEmpty(), () -> "BookShelf is empty.");
    }

    @Test
    @DisplayName("should return immutable books")
    public void book_shelf_immutable_books() {
        shelf.add(book1, book2);
        List<Book> books = shelf.books();
        try {
            books.add(book3);
            fail(() -> "Should not add books.");
        } catch (Exception e) {
            assertTrue(
                    e instanceof UnsupportedOperationException,
                    () -> "Should throw UnsupportedOperationException"
            );
        }
    }


    @Test
    @DisplayName("should sort books by title ASC order.")
    public void book_shelf_arrange_by_title_ASC(){
        shelf.add(book1, book3, book2);
        List<Book> books = shelf.arrange();
        assertEquals(
                List.of(book3, book2, book1),
                books,
                () -> "Books should be sorted by title ASC order."
        );

    }


    @Test
    @DisplayName("should sort books by title DESC order.")
    public void book_shelf_arrange_by_title_DESC(){
        shelf.add(book1, book3, book2);
        Comparator<Book> reversedOrder = Comparator.<Book>naturalOrder().reversed();
        List<Book> books = shelf.arrange(reversedOrder);
        assertThat(books).isSortedAccordingTo(reversedOrder);

    }
    

}
